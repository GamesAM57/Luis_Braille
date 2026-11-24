-- =====================================================================
-- 04_comprobaciones.sql  |  Gasolinera MySQL, Fase 1
-- Ejecutar DESPUÉS de 01, 02 y 03, sobre una base recién cargada.
--
-- Cómo ejecutarlo: sentencia a sentencia (cursor sobre ella y Run), o por
-- secciones. Las secciones C, D y E contienen sentencias que FALLAN a
-- propósito: no lances el fichero entero de golpe salvo que IntelliJ esté
-- configurado para continuar tras un error.
--
-- Todo lo que modifica datos va dentro de START TRANSACTION ... ROLLBACK,
-- así que la base queda intacta y el fichero se puede repetir.
-- Códigos de error: 1062 duplicado, 1452 FK hijo sin padre, 1451 FK padre
-- con hijos, 3819 CHECK incumplido, 1644 error lanzado por un trigger,
-- 3105 columna calculada.
-- =====================================================================

USE gasolinera;

-- =====================================================================
-- A) CONSULTAS: los datos cumplen las reglas del caso
-- =====================================================================

-- A1. Cada estación tiene su ficha (1 a 1).
-- ESPERADO: 5 filas: EST-01 A 104 | EST-02 B 57 | EST-03 C 1 | EST-04 D 230 | EST-05 E 11
SELECT e.codigo, f.serie_ticket, f.siguiente_numero
FROM estacion e JOIN ficha_facturacion f ON f.estacion_id = e.id
ORDER BY e.id;

-- A2. Surtidores por estación (una con varios equipos, uno averiado).
-- ESPERADO: estación 1 -> 4, 2 -> 2, 3 -> 2, 4 -> 1, 5 -> 2
SELECT estacion_id, COUNT(*) AS surtidores,
       SUM(estado_servicio = 'AVERIADO') AS averiados
FROM surtidor GROUP BY estacion_id ORDER BY estacion_id;

-- A3. Tarjetas sin asignar y clientes sin tarjeta.
-- ESPERADO: 4 tarjetas libres; clientes sin tarjeta: CLI-005 y CLI-006
SELECT COUNT(*) AS tarjetas_libres FROM tarjeta_fidelizacion WHERE cliente_id IS NULL;
SELECT c.codigo FROM cliente c
LEFT JOIN tarjeta_fidelizacion t ON t.cliente_id = c.id
WHERE t.id IS NULL;

-- A4. Supervisión: quién supervisa a cuántos y quién no tiene supervisor.
-- ESPERADO: Ana Ruiz 3 | Luis Pérez 1 | Carlos Soto 1
SELECT s.nombre AS supervisor, COUNT(*) AS supervisados
FROM empleado e JOIN empleado s ON s.id = e.supervisor_id
GROUP BY s.id, s.nombre ORDER BY s.id;
-- ESPERADO: Ana Ruiz, Carlos Soto, Sara Nieto
SELECT nombre FROM empleado WHERE supervisor_id IS NULL ORDER BY id;

-- A5. Funciones activas por empleado.
-- ESPERADO: Ana JEFE | Luis CAJERO,EXPENDEDOR | Marta CAJERO | Carlos JEFE |
--           Elena RESPONSABLE | Pablo EXPENDEDOR | Sara NULL (sin funciones) | Jorge CAJERO
SELECT e.nombre, GROUP_CONCAT(p.funcion ORDER BY p.funcion) AS funciones_activas
FROM empleado e
LEFT JOIN periodo_funcion p ON p.empleado_id = e.id AND p.fecha_fin IS NULL
GROUP BY e.id, e.nombre ORDER BY e.id;

-- A6. Luis recupera la función de cajero tras un periodo cerrado.
-- ESPERADO: 3 filas: CAJERO 2023-01-09..2024-06-30 | EXPENDEDOR 2024-01-15..NULL | CAJERO 2025-03-03..NULL
SELECT funcion, fecha_inicio, fecha_fin FROM periodo_funcion
WHERE empleado_id = 2 ORDER BY fecha_inicio;
-- ESPERADO: 2 filas, con límites distintos: 100.00 y 200.00
SELECT p.id, p.fecha_inicio, c.limite_devoluciones
FROM periodo_funcion p JOIN periodo_cajero c ON c.periodo_id = p.id
WHERE p.empleado_id = 2 ORDER BY p.fecha_inicio;

-- A7. Elena: responsable de tienda y jefatura temporal a la vez.
-- ESPERADO: RESPONSABLE 2024-02-01..NULL | JEFE 2025-08-01..2025-08-31
SELECT funcion, fecha_inicio, fecha_fin FROM periodo_funcion
WHERE empleado_id = 5 ORDER BY fecha_inicio;

-- A8. Productos por modalidad.
-- ESPERADO: ENVASADO 5 | GRANEL 3
SELECT modalidad, COUNT(*) AS productos FROM producto GROUP BY modalidad;

-- A9. Proveedores y productos (varios en ambos sentidos, sin aislados).
-- ESPERADO proveedores con varios productos: 1 -> 2, 2 -> 2, 3 -> 3, 4 -> 2
SELECT proveedor_id, COUNT(*) AS productos FROM acuerdo_suministro
GROUP BY proveedor_id HAVING COUNT(*) > 1 ORDER BY proveedor_id;
-- ESPERADO productos con varios proveedores: 3 -> 2, 4 -> 2
SELECT producto_id, COUNT(*) AS proveedores FROM acuerdo_suministro
GROUP BY producto_id HAVING COUNT(*) > 1 ORDER BY producto_id;
-- ESPERADO: 0 filas en cada una (nadie aislado)
SELECT codigo FROM proveedor WHERE id NOT IN (SELECT proveedor_id FROM acuerdo_suministro);
SELECT referencia FROM producto WHERE id NOT IN (SELECT producto_id FROM acuerdo_suministro);

-- A10. Campañas e inscripciones.
-- ESPERADO campañas sin inscritos: CAM-003, CAM-005
SELECT c.codigo FROM campana c
LEFT JOIN inscripcion_campana i ON i.campana_id = c.id WHERE i.id IS NULL;
-- ESPERADO clientes sin campañas: CLI-005, CLI-006
SELECT c.codigo FROM cliente c
LEFT JOIN inscripcion_campana i ON i.cliente_id = c.id WHERE i.id IS NULL;
-- ESPERADO participaciones por cliente: 1 -> 2, 2 -> 1, 3 -> 2, 4 -> 1
SELECT cliente_id, COUNT(*) AS campanas FROM inscripcion_campana
GROUP BY cliente_id ORDER BY cliente_id;

-- A11. Ventas: total calculado por ticket y número de líneas (14 en total).
-- ESPERADO: A-000101 PAGADA 16.15 (3 líneas) | A-000102 PAGADA 8.70 (2) |
--           B-000055 PAGADA 12.40 (3) | B-000056 PENDIENTE 12.20 (2) |
--           E-000010 PAGADA 21.45 (2) | A-000103 PENDIENTE 10.80 (2)
SELECT v.referencia_ticket, v.estado_cobro, COUNT(*) AS lineas, SUM(l.importe) AS total
FROM venta v JOIN linea_venta l ON l.venta_id = v.id
GROUP BY v.id, v.referencia_ticket, v.estado_cobro ORDER BY v.id;
-- ESPERADO ventas anónimas: A-000102, B-000056
SELECT referencia_ticket FROM venta WHERE cliente_id IS NULL ORDER BY id;

-- A12. Devoluciones parciales: nunca superan lo comprado ni su importe.
-- ESPERADO: línea 4 -> comprado 3.000, devuelto 2.000, importe 7.50, reintegrado 5.00
--           línea 8 -> 2.000 / 0.500 / 3.60 / 0.90
--           línea 12 -> 0.150 / 0.050 / 1.65 / 0.55
SELECT l.id AS linea, l.cantidad AS comprado, SUM(d.cantidad) AS devuelto,
       l.importe, SUM(d.importe_reintegrado) AS reintegrado
FROM linea_venta l JOIN devolucion d ON d.linea_venta_id = l.id
GROUP BY l.id, l.cantidad, l.importe ORDER BY l.id;

-- A13. Incidencias.
-- ESPERADO sin asignar: INC-002
SELECT referencia FROM incidencia WHERE responsable_id IS NULL;
-- ESPERADO responsables de varias: empleado 2 con 2
SELECT responsable_id, COUNT(*) AS incidencias FROM incidencia
WHERE responsable_id IS NOT NULL GROUP BY responsable_id HAVING COUNT(*) > 1;
-- ESPERADO empleados sin incidencias: Ana Ruiz, Marta Gil, Elena Vidal, Sara Nieto
SELECT nombre FROM empleado
WHERE id NOT IN (SELECT responsable_id FROM incidencia WHERE responsable_id IS NOT NULL)
ORDER BY id;

-- =====================================================================
-- B) TRIGGER DE PRECIOS
-- =====================================================================

-- B1. Cambio real, mismo precio, otro campo, ticket antiguo y ROLLBACK,
--     todo en una transacción.
START TRANSACTION;
UPDATE producto SET precio_venta = 1.30 WHERE id = 1;
-- ESPERADO: 1 fila: producto 1, anterior 1.20, nuevo 1.30
SELECT producto_id, precio_anterior, precio_nuevo, fecha_hora FROM historial_precio;

UPDATE producto SET precio_venta = 1.30 WHERE id = 1;                 -- mismo precio
UPDATE producto SET descripcion = 'Agua mineral 1,5 L (oferta)' WHERE id = 1;  -- otro campo
-- ESPERADO: sigue 1 fila (ni el mismo precio ni la descripción registran nada)
SELECT COUNT(*) AS entradas FROM historial_precio;

-- ESPERADO: la venta antigua conserva su precio: 1.20
SELECT precio_unitario FROM linea_venta WHERE id = 1;
ROLLBACK;

-- ESPERADO tras el ROLLBACK: precio 1.20 y 0 entradas (se revierten las dos cosas)
SELECT precio_venta FROM producto WHERE id = 1;
SELECT COUNT(*) AS entradas FROM historial_precio;

-- B2. La creación inicial de un producto no genera historial.
START TRANSACTION;
INSERT INTO producto (referencia, descripcion, precio_venta, modalidad, formato_envase)
VALUES ('PRD-099', 'Producto de prueba', 3.00, 'ENVASADO', 'Unidad');
-- ESPERADO: 0
SELECT COUNT(*) AS entradas FROM historial_precio;
ROLLBACK;

-- B3. (Opcional, para la memoria) Dejar una entrada persistente: ejecuta el
-- UPDATE y haz COMMIT en vez de ROLLBACK. Quedará un cambio permanente.
-- START TRANSACTION;
-- UPDATE producto SET precio_venta = 1.30 WHERE id = 1;
-- COMMIT;

-- =====================================================================
-- C) PRUEBAS QUE DEBEN FUNCIONAR (con ROLLBACK)
-- =====================================================================

-- C1. Supervisión válida (un empleado sin supervisor pasa a tener uno).
-- ESPERADO: 1 fila afectada y supervisor_id = 4
START TRANSACTION;
UPDATE empleado SET supervisor_id = 4 WHERE id = 7;
SELECT id, supervisor_id FROM empleado WHERE id = 7;
ROLLBACK;

-- C2. Funciones DISTINTAS a la vez sí se permiten (Marta ya es cajera).
-- ESPERADO: se inserta sin error
START TRANSACTION;
INSERT INTO periodo_funcion (empleado_id, funcion, fecha_inicio, fecha_fin)
VALUES (3, 'EXPENDEDOR', '2025-01-01', NULL);
ROLLBACK;

-- C3. Mismo número de surtidor en OTRA estación sí se permite.
-- ESPERADO: se inserta sin error
START TRANSACTION;
INSERT INTO surtidor (estacion_id, codigo_equipo, numero_visible, tipo_combustible)
VALUES (4, 'SUR-099', 1, 'Diesel');
ROLLBACK;

-- C4. Borrar un acuerdo que NO es el último del proveedor ni del producto.
-- ESPERADO: 1 fila borrada (proveedor 3 tiene 3 acuerdos y producto 4 tiene 2)
START TRANSACTION;
DELETE FROM acuerdo_suministro WHERE id = 10;
ROLLBACK;

-- =====================================================================
-- D) ERRORES PROVOCADOS A PROPÓSITO (cada sentencia debe fallar)
-- =====================================================================

-- --- Estaciones, fichas y surtidores ---
-- ERROR ESPERADO 1062: código de estación duplicado
INSERT INTO estacion (codigo, nombre_comercial, direccion, telefono, fecha_apertura)
VALUES ('EST-01', 'Duplicada', 'Calle X', '910000099', '2026-01-01');
-- ERROR ESPERADO 1062: segunda ficha para la misma estación (1 a 1)
INSERT INTO ficha_facturacion (estacion_id, codigo_configuracion, serie_ticket, siguiente_numero, texto_pie)
VALUES (1, 'CFG-99', 'Z', 1, 'x');
-- ERROR ESPERADO 1452: ficha de una estación que no existe
INSERT INTO ficha_facturacion (estacion_id, codigo_configuracion, serie_ticket, siguiente_numero, texto_pie)
VALUES (999, 'CFG-98', 'Y', 1, 'x');
-- ERROR ESPERADO 1062: número visible repetido dentro de la misma estación
INSERT INTO surtidor (estacion_id, codigo_equipo, numero_visible, tipo_combustible)
VALUES (1, 'SUR-098', 1, 'Gasolina 95');
-- ERROR ESPERADO 1452: surtidor en una estación inexistente
INSERT INTO surtidor (estacion_id, codigo_equipo, numero_visible, tipo_combustible)
VALUES (999, 'SUR-097', 1, 'Gasolina 95');
-- ERROR ESPERADO 3819: estado de servicio no válido
UPDATE surtidor SET estado_servicio = 'ROTO' WHERE id = 1;

-- --- Personal ---
-- ERROR ESPERADO 1062: documento de identidad duplicado
INSERT INTO empleado (estacion_id, numero_interno, documento, nombre, contacto, fecha_incorporacion)
VALUES (1, 'E-099', '11111111A', 'Repetido', 'rep@test.test', '2026-01-01');
-- ERROR ESPERADO 1452: empleado adscrito a una estación inexistente
INSERT INTO empleado (estacion_id, numero_interno, documento, nombre, contacto, fecha_incorporacion)
VALUES (999, 'E-098', '99999999Z', 'Sin estacion', 'x@test.test', '2026-01-01');
-- ERROR ESPERADO 1644: un empleado no puede supervisarse a sí mismo
UPDATE empleado SET supervisor_id = 3 WHERE id = 3;
-- ERROR ESPERADO 1644: ciclo (6 -> 2 -> 1; poner a 6 como supervisor de 1 lo cierra)
UPDATE empleado SET supervisor_id = 6 WHERE id = 1;

-- --- Periodos de función ---
-- ERROR ESPERADO 1644: solape con otro periodo de CAJERO de Luis (abierto desde 2025-03-03)
INSERT INTO periodo_funcion (empleado_id, funcion, fecha_inicio, fecha_fin)
VALUES (2, 'CAJERO', '2025-06-01', NULL);
-- ERROR ESPERADO 3819: fecha de fin anterior a la de inicio
INSERT INTO periodo_funcion (empleado_id, funcion, fecha_inicio, fecha_fin)
VALUES (7, 'CAJERO', '2025-05-01', '2025-04-01');
-- ERROR ESPERADO 3819: función que no existe
INSERT INTO periodo_funcion (empleado_id, funcion, fecha_inicio, fecha_fin)
VALUES (7, 'BARRENDERO', '2025-05-01', NULL);
-- ERROR ESPERADO 1452: detalle de cajero colgando de un periodo de JEFE (periodo 4)
INSERT INTO periodo_cajero (periodo_id, funcion, limite_devoluciones) VALUES (4, 'CAJERO', 100);
-- ERROR ESPERADO 1452: detalle de jefe colgando de un periodo de CAJERO (periodo 1)
INSERT INTO periodo_jefe (periodo_id, funcion, nivel_autorizacion) VALUES (1, 'JEFE', 1);
-- ERROR ESPERADO 1062: segundo detalle para el mismo periodo
INSERT INTO periodo_cajero (periodo_id, funcion, limite_devoluciones) VALUES (1, 'CAJERO', 50);

-- --- Clientes y tarjetas ---
-- ERROR ESPERADO 1062: el cliente 1 ya tiene tarjeta (no puede tener dos)
UPDATE tarjeta_fidelizacion SET cliente_id = 1, fecha_asignacion = '2026-11-01' WHERE id = 5;
-- ERROR ESPERADO 3819: tarjeta con cliente pero sin fecha de asignación
UPDATE tarjeta_fidelizacion SET cliente_id = 5 WHERE id = 5;
-- ERROR ESPERADO 1062: número de tarjeta duplicado
INSERT INTO tarjeta_fidelizacion (numero, fecha_emision) VALUES ('TF-1001', '2026-01-10');
-- ERROR ESPERADO 1451: no se puede borrar un cliente con tarjeta
DELETE FROM cliente WHERE id = 1;

-- --- Productos ---
-- ERROR ESPERADO 1062: referencia de producto duplicada
INSERT INTO producto (referencia, descripcion, precio_venta, modalidad, formato_envase)
VALUES ('PRD-001', 'Duplicado', 1.00, 'ENVASADO', 'Unidad');
-- ERROR ESPERADO 3819: precio de venta cero
INSERT INTO producto (referencia, descripcion, precio_venta, modalidad, formato_envase)
VALUES ('PRD-090', 'Precio cero', 0, 'ENVASADO', 'Unidad');
-- ERROR ESPERADO 3819: envasado sin formato de envase
INSERT INTO producto (referencia, descripcion, precio_venta, modalidad)
VALUES ('PRD-091', 'Envasado sin formato', 1.00, 'ENVASADO');
-- ERROR ESPERADO 3819: a granel con formato de envase (y sin precisión de pesada)
INSERT INTO producto (referencia, descripcion, precio_venta, modalidad, formato_envase)
VALUES ('PRD-092', 'Granel con envase', 1.00, 'GRANEL', 'Bolsa');
-- ERROR ESPERADO 1644: la modalidad no cambia durante la vida del producto
UPDATE producto SET modalidad = 'GRANEL', formato_envase = NULL, precision_pesada = 0.010 WHERE id = 1;
-- ERROR ESPERADO 1451: no se puede borrar un producto con acuerdos
DELETE FROM producto WHERE id = 1;

-- --- Proveedores y acuerdos ---
-- ERROR ESPERADO 1062: identificación fiscal duplicada
INSERT INTO proveedor (codigo, identificacion_fiscal, razon_social, contacto)
VALUES ('PRV-099', 'B12345678', 'Duplicado S.L.', 'x@test.test');
-- ERROR ESPERADO 1062: mismo acuerdo proveedor + producto repetido
INSERT INTO acuerdo_suministro (proveedor_id, producto_id, referencia_proveedor, precio_compra, plazo_entrega_dias)
VALUES (1, 1, 'DUP', 1.00, 1);
-- ERROR ESPERADO 3819: precio de compra cero
INSERT INTO acuerdo_suministro (proveedor_id, producto_id, referencia_proveedor, precio_compra, plazo_entrega_dias)
VALUES (5, 1, 'CERO', 0, 1);
-- ERROR ESPERADO 1452: acuerdo con un producto inexistente
INSERT INTO acuerdo_suministro (proveedor_id, producto_id, referencia_proveedor, precio_compra, plazo_entrega_dias)
VALUES (5, 999, 'NOPROD', 1.00, 1);
-- ERROR ESPERADO 1644: es el último acuerdo del proveedor 5
DELETE FROM acuerdo_suministro WHERE id = 7;
-- ERROR ESPERADO 1644: es el último acuerdo del producto 8
DELETE FROM acuerdo_suministro WHERE id = 9;

-- --- Campañas ---
-- ERROR ESPERADO 3819: fin anterior al inicio
INSERT INTO campana (codigo, nombre, descripcion, fecha_inicio, fecha_fin)
VALUES ('CAM-099', 'Mal fechada', 'x', '2026-12-31', '2026-12-01');
-- ERROR ESPERADO 1062: cliente ya inscrito en esa campaña
INSERT INTO inscripcion_campana (campana_id, cliente_id, fecha_inscripcion) VALUES (1, 1, '2026-06-30');
-- ERROR ESPERADO 1452: inscribir a un cliente que no existe (sin cliente registrado no hay inscripción)
INSERT INTO inscripcion_campana (campana_id, cliente_id, fecha_inscripcion) VALUES (1, 999, '2026-06-30');

-- --- Ventas, líneas y devoluciones ---
-- ERROR ESPERADO 1062: referencia de ticket duplicada
INSERT INTO venta (referencia_ticket, estacion_id, empleado_id, fecha_hora)
VALUES ('A-000101', 1, 2, '2026-11-07 09:00:00');
-- ERROR ESPERADO 3819: estado de cobro no válido
UPDATE venta SET estado_cobro = 'REGALADA' WHERE id = 4;
-- ERROR ESPERADO 1062: número de orden repetido dentro del ticket
INSERT INTO linea_venta (venta_id, producto_id, numero_orden, cantidad, precio_unitario)
VALUES (1, 1, 1, 1.000, 1.20);
-- ERROR ESPERADO 3819: cantidad cero
INSERT INTO linea_venta (venta_id, producto_id, numero_orden, cantidad, precio_unitario)
VALUES (1, 1, 9, 0, 1.20);
-- ERROR ESPERADO 1452: línea con un producto que no existe
INSERT INTO linea_venta (venta_id, producto_id, numero_orden, cantidad, precio_unitario)
VALUES (1, 999, 9, 1.000, 1.20);
-- ERROR ESPERADO 3105: el importe es calculado, no se puede escribir
UPDATE linea_venta SET importe = 99.99 WHERE id = 1;
-- ERROR ESPERADO 3819: devolución de cantidad cero
INSERT INTO devolucion (linea_venta_id, cantidad, motivo, fecha_hora, empleado_id, importe_reintegrado, apta_reventa)
VALUES (4, 0, 'x', '2026-11-07 10:00:00', 3, 0, FALSE);
-- ERROR ESPERADO 1451: no se puede borrar una línea con devoluciones (la línea original no se toca)
DELETE FROM linea_venta WHERE id = 4;
-- ERROR ESPERADO 1451: no se puede borrar una estación con ventas, empleados o incidencias
DELETE FROM estacion WHERE id = 1;

-- --- Incidencias ---
-- ERROR ESPERADO 3819: estado no válido
UPDATE incidencia SET estado = 'PERDIDA' WHERE id = 1;
-- ERROR ESPERADO 1062: referencia duplicada
INSERT INTO incidencia (referencia, descripcion, fecha_hora_apertura, estacion_id)
VALUES ('INC-001', 'Duplicada', '2026-11-07 10:00:00', 1);

-- =====================================================================
-- E) LIMITACIONES: reglas que la BD NO rechaza (pendientes para Java, fase 2)
-- Cada sentencia se ejecuta SIN error aunque rompe una regla de negocio.
-- Se documentan como pendientes, no como protecciones automáticas.
-- =====================================================================

-- E1. Estación sin ficha (el alta debe ir en una transacción en Java).
-- RESULTADO: se inserta. Regla pendiente.
START TRANSACTION;
INSERT INTO estacion (codigo, nombre_comercial, direccion, telefono, fecha_apertura)
VALUES ('EST-99', 'Sin ficha', 'Calle Y', '910000098', '2026-11-07');
ROLLBACK;

-- E2. Proveedor sin ningún acuerdo (alta con transacción en Java).
-- RESULTADO: se inserta. Regla pendiente.
START TRANSACTION;
INSERT INTO proveedor (codigo, identificacion_fiscal, razon_social, contacto)
VALUES ('PRV-099', 'B99999999', 'Sin acuerdo S.L.', 'x@test.test');
ROLLBACK;

-- E3. Venta sin líneas (alta con transacción en Java).
-- RESULTADO: se inserta. Regla pendiente.
START TRANSACTION;
INSERT INTO venta (referencia_ticket, estacion_id, empleado_id, fecha_hora)
VALUES ('A-000999', 1, 2, '2026-11-07 09:00:00');
ROLLBACK;

-- E4. Devolver más de lo comprado (la línea 4 compró 3 y ya hay 2 devueltas).
-- RESULTADO: se inserta. Regla pendiente (acumulado y concurrencia en Java).
START TRANSACTION;
INSERT INTO devolucion (linea_venta_id, cantidad, motivo, fecha_hora, empleado_id, importe_reintegrado, apta_reventa)
VALUES (4, 5.000, 'Prueba de limitación', '2026-11-07 10:00:00', 3, 12.50, TRUE);
ROLLBACK;

-- E5. Devolver una línea de una venta NO pagada (la venta 4 está PENDIENTE).
-- RESULTADO: se inserta. Regla pendiente.
START TRANSACTION;
INSERT INTO devolucion (linea_venta_id, cantidad, motivo, fecha_hora, empleado_id, importe_reintegrado, apta_reventa)
VALUES (9, 1.000, 'Prueba de limitación', '2026-11-07 10:00:00', 3, 1.20, TRUE);
ROLLBACK;

-- E6. Marcar como PAGADA sin pagos que sumen el total (trigger pendiente de `pago`).
-- RESULTADO: se actualiza. Regla pendiente.
START TRANSACTION;
UPDATE venta SET estado_cobro = 'PAGADA' WHERE id = 4;
ROLLBACK;

-- E7. Cantidad decimal en un producto envasado (1.5 botellas).
-- RESULTADO: se inserta. La validación por modalidad se hace en Java.
START TRANSACTION;
INSERT INTO linea_venta (venta_id, producto_id, numero_orden, cantidad, precio_unitario)
VALUES (6, 3, 9, 1.500, 2.50);
ROLLBACK;
