-- =====================================================================
-- 02_triggers.sql  |  Gasolinera MySQL, Fase 1
-- Ejecutar después de 01_esquema.sql.
--
-- Usa DELIMITER (cliente mysql / DataGrip). Si IntelliJ se queja, ejecuta
-- cada trigger por separado: selecciónalo y pulsa Run, o usa la terminal:
--   docker exec -i <contenedor> mysql -u root -p gasolinera < 02_triggers.sql
--
-- Límite general: estos triggers leen la tabla para decidir. Con dos
-- operaciones simultáneas sobre los mismos datos podrían colarse ambas;
-- el control definitivo con concurrencia se resuelve en Java (fase 2).
-- =====================================================================

USE gasolinera;

DELIMITER $$

-- ---------------------------------------------------------------------
-- 1) OBLIGATORIO: historial de precios
-- Registra solo cambios reales del precio de venta. No salta al crear el
-- producto (es un trigger de UPDATE), ni al guardar el mismo precio, ni
-- al cambiar otros campos. Va dentro de la misma transacción que el
-- cambio: si hay ROLLBACK, desaparece también la fila del historial.
-- ---------------------------------------------------------------------
CREATE TRIGGER trg_producto_precio
AFTER UPDATE ON producto
FOR EACH ROW
BEGIN
  IF NEW.precio_venta <> OLD.precio_venta THEN
    INSERT INTO historial_precio (producto_id, precio_anterior, precio_nuevo)
    VALUES (NEW.id, OLD.precio_venta, NEW.precio_venta);
  END IF;
END$$

-- ---------------------------------------------------------------------
-- 2) La modalidad del producto no cambia durante toda su vida
-- ---------------------------------------------------------------------
CREATE TRIGGER trg_producto_modalidad_fija
BEFORE UPDATE ON producto
FOR EACH ROW
BEGIN
  IF NEW.modalidad <> OLD.modalidad THEN
    SIGNAL SQLSTATE '45000'
      SET MESSAGE_TEXT = 'La modalidad de un producto no se puede cambiar';
  END IF;
END$$

-- ---------------------------------------------------------------------
-- 3) Supervisión: nadie se supervisa a sí mismo ni forma ciclos
-- Sube por la cadena de supervisores del nuevo supervisor; si encuentra
-- al propio empleado, hay ciclo. Solo hace falta en UPDATE: un empleado
-- recién insertado aún no tiene subordinados, así que no puede cerrar un
-- ciclo (y su id todavía no existe para autosupervisarse).
-- ---------------------------------------------------------------------
CREATE TRIGGER trg_empleado_supervisor
BEFORE UPDATE ON empleado
FOR EACH ROW
BEGIN
  DECLARE actual INT;
  DECLARE siguiente INT;
  DECLARE pasos INT DEFAULT 0;

  IF NEW.supervisor_id IS NOT NULL
     AND NOT (NEW.supervisor_id <=> OLD.supervisor_id) THEN

    IF NEW.supervisor_id = NEW.id THEN
      SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Un empleado no puede supervisarse a si mismo';
    END IF;

    SET actual = NEW.supervisor_id;
    WHILE actual IS NOT NULL AND pasos < 1000 DO
      IF actual = NEW.id THEN
        SIGNAL SQLSTATE '45000'
          SET MESSAGE_TEXT = 'La supervision formaria un ciclo';
      END IF;
      SET siguiente = NULL;
      SELECT supervisor_id INTO siguiente FROM empleado WHERE id = actual;
      SET actual = siguiente;
      SET pasos = pasos + 1;
    END WHILE;
  END IF;
END$$

-- ---------------------------------------------------------------------
-- 4) Periodos de función: no solapar los de la MISMA función del mismo
-- empleado (funciones distintas a la vez sí). fecha_fin NULL = sin fin.
-- Fechas inclusivas. Un procedimiento compartido por INSERT y UPDATE.
-- ---------------------------------------------------------------------
CREATE PROCEDURE sp_comprobar_periodo(
  IN p_id INT, IN p_empleado INT, IN p_funcion VARCHAR(20),
  IN p_inicio DATE, IN p_fin DATE)
BEGIN
  DECLARE solapes INT;
  SELECT COUNT(*) INTO solapes
  FROM periodo_funcion
  WHERE empleado_id = p_empleado
    AND funcion = p_funcion
    AND id <> p_id
    AND fecha_inicio <= COALESCE(p_fin, '9999-12-31')
    AND COALESCE(fecha_fin, '9999-12-31') >= p_inicio;
  IF solapes > 0 THEN
    SIGNAL SQLSTATE '45000'
      SET MESSAGE_TEXT = 'Periodo solapado con otro de la misma funcion';
  END IF;
END$$

CREATE TRIGGER trg_periodo_solape_ins
BEFORE INSERT ON periodo_funcion
FOR EACH ROW
BEGIN
  CALL sp_comprobar_periodo(0, NEW.empleado_id, NEW.funcion,
                            NEW.fecha_inicio, NEW.fecha_fin);
END$$

CREATE TRIGGER trg_periodo_solape_upd
BEFORE UPDATE ON periodo_funcion
FOR EACH ROW
BEGIN
  CALL sp_comprobar_periodo(OLD.id, NEW.empleado_id, NEW.funcion,
                            NEW.fecha_inicio, NEW.fecha_fin);
END$$

-- ---------------------------------------------------------------------
-- 5) Acuerdos de suministro: ningún proveedor ni producto se queda sin
-- acuerdo. No se puede borrar el último, ni mover un acuerdo dejando al
-- proveedor o al producto anterior sin ninguno.
-- (Que el alta cree el primer acuerdo se garantiza con la transacción de Java.)
-- ---------------------------------------------------------------------
CREATE TRIGGER trg_acuerdo_ultimo_del
BEFORE DELETE ON acuerdo_suministro
FOR EACH ROW
BEGIN
  IF (SELECT COUNT(*) FROM acuerdo_suministro
      WHERE proveedor_id = OLD.proveedor_id) = 1 THEN
    SIGNAL SQLSTATE '45000'
      SET MESSAGE_TEXT = 'No se puede borrar el ultimo acuerdo del proveedor';
  END IF;
  IF (SELECT COUNT(*) FROM acuerdo_suministro
      WHERE producto_id = OLD.producto_id) = 1 THEN
    SIGNAL SQLSTATE '45000'
      SET MESSAGE_TEXT = 'No se puede borrar el ultimo acuerdo del producto';
  END IF;
END$$

CREATE TRIGGER trg_acuerdo_ultimo_upd
BEFORE UPDATE ON acuerdo_suministro
FOR EACH ROW
BEGIN
  IF NEW.proveedor_id <> OLD.proveedor_id
     AND (SELECT COUNT(*) FROM acuerdo_suministro
          WHERE proveedor_id = OLD.proveedor_id) = 1 THEN
    SIGNAL SQLSTATE '45000'
      SET MESSAGE_TEXT = 'El proveedor anterior se quedaria sin acuerdos';
  END IF;
  IF NEW.producto_id <> OLD.producto_id
     AND (SELECT COUNT(*) FROM acuerdo_suministro
          WHERE producto_id = OLD.producto_id) = 1 THEN
    SIGNAL SQLSTATE '45000'
      SET MESSAGE_TEXT = 'El producto anterior se quedaria sin acuerdos';
  END IF;
END$$

DELIMITER ;

-- ---------------------------------------------------------------------
-- PENDIENTE: paso de la venta a PAGADA solo si los pagos suman el total.
-- Depende de la tabla `pago` (aún sin definir). Borrador, a ajustar a los
-- nombres reales de columnas cuando exista:
--
-- CREATE TRIGGER trg_venta_pagada
-- BEFORE UPDATE ON venta
-- FOR EACH ROW
-- BEGIN
--   IF NEW.estado_cobro = 'PAGADA' AND OLD.estado_cobro <> 'PAGADA' THEN
--     IF (SELECT COALESCE(SUM(importe),0) FROM pago WHERE venta_id = NEW.id)
--        <> (SELECT COALESCE(SUM(importe),0) FROM linea_venta WHERE venta_id = NEW.id)
--     THEN SIGNAL SQLSTATE '45000'
--          SET MESSAGE_TEXT = 'Los pagos no suman el importe de la venta';
--     END IF;
--   END IF;
-- END
-- ---------------------------------------------------------------------
