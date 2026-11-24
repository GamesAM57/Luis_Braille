-- =====================================================================
-- 03_datos.sql  |  Gasolinera MySQL, Fase 1
-- Ejecutar después de 01_esquema.sql y 02_triggers.sql, en entorno vacío.
-- Todos los ids van explícitos para que las relaciones se lean a simple
-- vista. Los datos respetan las reglas; los errores provocados a propósito
-- están en 04_comprobaciones.sql.
--
-- PENDIENTE (depende de la tabla `pago`, aún sin definir):
--   * Insertar los pagos de las ventas PAGADA (ids 1, 2, 3 y 5). La venta 3
--     debe tener 2 pagos que sumen 12.40 (p. ej. 10.00 efectivo + 2.40
--     tarjeta) para cubrir el caso "venta pagada con más de un pago".
--   * Ajustar tipo_combustible a los textos exactos de tu clase Pagos.
-- =====================================================================

USE gasolinera;

-- ---------------------------------------------------------------------
-- Estaciones, fichas y surtidores
-- (la serie y el siguiente_numero de cada ficha cuadran con los tickets)
-- ---------------------------------------------------------------------
INSERT INTO estacion (id, codigo, nombre_comercial, direccion, telefono, fecha_apertura) VALUES
(1, 'EST-01', 'Gasolinera Centro', 'Calle Mayor 12, Madrid',            '910000001', '2015-03-01'),
(2, 'EST-02', 'Gasolinera Norte',  'Avenida de Burgos 45, Madrid',      '910000002', '2018-06-15'),
(3, 'EST-03', 'Gasolinera Sur',    'Calle de Alcalá 200, Madrid',       '910000003', '2019-09-20'),
(4, 'EST-04', 'Gasolinera Este',   'Avenida de América 80, Madrid',     '910000004', '2021-01-11'),
(5, 'EST-05', 'Gasolinera Oeste',  'Paseo de la Castellana 300, Madrid','910000005', '2023-04-05');

INSERT INTO ficha_facturacion (id, estacion_id, codigo_configuracion, serie_ticket, siguiente_numero, texto_pie) VALUES
(1, 1, 'CFG-01', 'A', 104, 'Gracias por su visita. Conserve el ticket.'),
(2, 2, 'CFG-02', 'B',  57, 'Gracias por su visita. Conserve el ticket.'),
(3, 3, 'CFG-03', 'C',   1, 'Gracias por su visita. Conserve el ticket.'),
(4, 4, 'CFG-04', 'D', 230, 'Gracias por su visita. Conserve el ticket.'),
(5, 5, 'CFG-05', 'E',  11, 'Gracias por su visita. Conserve el ticket.');

-- EST-01 tiene 4 surtidores (uno averiado: sigue perteneciendo a la estación).
INSERT INTO surtidor (id, estacion_id, codigo_equipo, numero_visible, tipo_combustible, estado_servicio) VALUES
(1,  1, 'SUR-001', 1, 'Gasolina 95', 'EN_SERVICIO'),
(2,  1, 'SUR-002', 2, 'Gasolina 98', 'EN_SERVICIO'),
(3,  1, 'SUR-003', 3, 'Diesel',      'EN_SERVICIO'),
(4,  1, 'SUR-004', 4, 'Diesel',      'AVERIADO'),
(5,  2, 'SUR-005', 1, 'Gasolina 95', 'EN_SERVICIO'),
(6,  2, 'SUR-006', 2, 'Diesel',      'EN_SERVICIO'),
(7,  3, 'SUR-007', 1, 'Gasolina 95', 'EN_SERVICIO'),
(8,  3, 'SUR-008', 2, 'Diesel',      'EN_SERVICIO'),
(9,  4, 'SUR-009', 1, 'Gasolina 95', 'EN_SERVICIO'),
(10, 5, 'SUR-010', 1, 'Gasolina 95', 'EN_SERVICIO'),
(11, 5, 'SUR-011', 2, 'Diesel',      'EN_SERVICIO');

-- ---------------------------------------------------------------------
-- Personal
-- Sin supervisor: 1, 4, 7. Ana (1) supervisa a varios (2, 3, 8).
-- Cadena: 6 -> 2 -> 1. El empleado 7 no tiene ninguna función.
-- ---------------------------------------------------------------------
INSERT INTO empleado (id, estacion_id, supervisor_id, numero_interno, documento, nombre, contacto, fecha_incorporacion) VALUES
(1, 1, NULL, 'E-001', '11111111A', 'Ana Ruiz',     'ana.ruiz@gasolinera.test',     '2015-03-01'),
(2, 1, 1,    'E-002', '22222222B', 'Luis Pérez',   'luis.perez@gasolinera.test',   '2016-02-15'),
(3, 1, 1,    'E-003', '33333333C', 'Marta Gil',    'marta.gil@gasolinera.test',    '2022-05-02'),
(4, 2, NULL, 'E-004', '44444444D', 'Carlos Soto',  'carlos.soto@gasolinera.test',  '2018-06-15'),
(5, 2, 4,    'E-005', '55555555E', 'Elena Vidal',  'elena.vidal@gasolinera.test',  '2020-01-20'),
(6, 3, 2,    'E-006', '66666666F', 'Pablo Mora',   'pablo.mora@gasolinera.test',   '2023-09-01'),
(7, 4, NULL, 'E-007', '77777777G', 'Sara Nieto',   'sara.nieto@gasolinera.test',   '2024-10-07'),
(8, 5, 1,    'E-008', '88888888H', 'Jorge Lara',   'jorge.lara@gasolinera.test',   '2023-04-05');

-- Periodos de función.
--  Luis (2): cajero (periodo cerrado), expendedor a la vez, y vuelve a ser
--            cajero con otras condiciones (recupera una función).
--  Ana (1) y Carlos (4): jefes de su estación.
--  Elena (5): responsable de tienda y, a la vez, jefatura temporal.
INSERT INTO periodo_funcion (id, empleado_id, funcion, fecha_inicio, fecha_fin) VALUES
(1,  2, 'CAJERO',      '2023-01-09', '2024-06-30'),
(2,  2, 'EXPENDEDOR',  '2024-01-15', NULL),
(3,  2, 'CAJERO',      '2025-03-03', NULL),
(4,  1, 'JEFE',        '2015-03-01', NULL),
(5,  3, 'CAJERO',      '2022-05-02', NULL),
(6,  4, 'JEFE',        '2018-06-15', NULL),
(7,  5, 'RESPONSABLE', '2024-02-01', NULL),
(8,  5, 'JEFE',        '2025-08-01', '2025-08-31'),
(9,  6, 'EXPENDEDOR',  '2023-09-01', NULL),
(10, 8, 'CAJERO',      '2023-04-05', NULL);

INSERT INTO periodo_cajero (id, periodo_id, funcion, limite_devoluciones) VALUES
(1, 1,  'CAJERO', 100.00),
(2, 3,  'CAJERO', 200.00),
(3, 5,  'CAJERO', 150.00),
(4, 10, 'CAJERO', 120.00);

INSERT INTO periodo_expendedor (id, periodo_id, funcion, acreditacion_numero, acreditacion_caducidad) VALUES
(1, 2, 'EXPENDEDOR', 'AC-778', '2027-05-31'),
(2, 9, 'EXPENDEDOR', 'AC-912', '2026-12-31');

INSERT INTO periodo_responsable (id, periodo_id, funcion, presupuesto) VALUES
(1, 7, 'RESPONSABLE', 1500.00);

INSERT INTO periodo_jefe (id, periodo_id, funcion, nivel_autorizacion) VALUES
(1, 4, 'JEFE', 3),
(2, 6, 'JEFE', 2),
(3, 8, 'JEFE', 2);

-- ---------------------------------------------------------------------
-- Clientes y tarjetas
-- Clientes 5 y 6 sin tarjeta. Tarjetas 5 a 8 sin asignar.
-- Teléfono opcional (NULL en 2, 4 y 6).
-- ---------------------------------------------------------------------
INSERT INTO cliente (id, codigo, nombre, email, telefono) VALUES
(1, 'CLI-001', 'Laura Gómez',   'laura.gomez@mail.test',   '600000001'),
(2, 'CLI-002', 'Miguel Torres', 'miguel.torres@mail.test', NULL),
(3, 'CLI-003', 'Nuria Campos',  'nuria.campos@mail.test',  '600000003'),
(4, 'CLI-004', 'Raúl Ortega',   'raul.ortega@mail.test',   NULL),
(5, 'CLI-005', 'Isabel Ramos',  'isabel.ramos@mail.test',  '600000005'),
(6, 'CLI-006', 'Hugo Prieto',   'hugo.prieto@mail.test',   NULL);

INSERT INTO tarjeta_fidelizacion (id, numero, fecha_emision, cliente_id, fecha_asignacion) VALUES
(1, 'TF-1001', '2026-01-10', 1,    '2026-02-03'),
(2, 'TF-1002', '2026-01-10', 2,    '2026-02-10'),
(3, 'TF-1003', '2026-01-10', 3,    '2026-03-15'),
(4, 'TF-1004', '2026-01-10', 4,    '2026-04-01'),
(5, 'TF-1005', '2026-01-10', NULL, NULL),
(6, 'TF-1006', '2026-01-10', NULL, NULL),
(7, 'TF-1007', '2026-01-10', NULL, NULL),
(8, 'TF-1008', '2026-01-10', NULL, NULL);

-- ---------------------------------------------------------------------
-- Productos (ENVASADO por unidades enteras; GRANEL por kg con precisión)
-- Frutos secos y café existen envasados y a granel: referencias distintas.
-- ---------------------------------------------------------------------
INSERT INTO producto (id, referencia, descripcion, precio_venta, estado, modalidad, formato_envase, precision_pesada) VALUES
(1, 'PRD-001', 'Agua mineral 1,5 L',               1.20, 'ACTIVO',   'ENVASADO', 'Botella 1,5 L', NULL),
(2, 'PRD-002', 'Aceite de motor 5W30 1 L',         9.90, 'ACTIVO',   'ENVASADO', 'Bote 1 L',      NULL),
(3, 'PRD-003', 'Frutos secos 200 g',               2.50, 'ACTIVO',   'ENVASADO', 'Bolsa 200 g',   NULL),
(4, 'PRD-004', 'Café en grano 250 g',              4.80, 'ACTIVO',   'ENVASADO', 'Paquete 250 g', NULL),
(5, 'PRD-005', 'Frutos secos a granel (kg)',      11.00, 'ACTIVO',   'GRANEL',   NULL,            0.050),
(6, 'PRD-006', 'Hielo a granel (kg)',              1.80, 'ACTIVO',   'GRANEL',   NULL,            0.250),
(7, 'PRD-007', 'Café en grano a granel (kg)',     16.00, 'ACTIVO',   'GRANEL',   NULL,            0.010),
(8, 'PRD-008', 'Limpiaparabrisas universal',       6.50, 'RETIRADO', 'ENVASADO', 'Unidad',        NULL);

-- ---------------------------------------------------------------------
-- Proveedores y acuerdos
-- Proveedor con varios productos (3 y 1); producto con varios proveedores
-- (3 y 4). Ningún proveedor ni producto queda aislado.
-- ---------------------------------------------------------------------
INSERT INTO proveedor (id, codigo, identificacion_fiscal, razon_social, contacto) VALUES
(1, 'PRV-001', 'B12345678', 'Distribuciones Aqua S.L.', 'aqua@prov.test'),
(2, 'PRV-002', 'B23456789', 'Lubricantes Iberia S.A.',  'ventas@lubiberia.test'),
(3, 'PRV-003', 'B34567890', 'Snacks del Sur S.L.',      'pedidos@snackssur.test'),
(4, 'PRV-004', 'B45678901', 'Cafés Maravilla S.L.',     'info@cafesmaravilla.test'),
(5, 'PRV-005', 'B56789012', 'Frío y Hielo S.A.',        'frio@hielo.test');

INSERT INTO acuerdo_suministro (id, proveedor_id, producto_id, referencia_proveedor, precio_compra, plazo_entrega_dias) VALUES
(1,  1, 1, 'AQ-15',    0.70, 2),
(2,  2, 2, 'LI-5W30',  6.50, 5),
(3,  3, 3, 'SN-FS200', 1.60, 3),
(4,  3, 5, 'SN-FSG',   7.20, 3),
(5,  4, 4, 'CM-250',   3.10, 4),
(6,  4, 7, 'CM-G',    10.50, 4),
(7,  5, 6, 'FH-KG',    0.90, 1),
(8,  1, 3, 'AQ-FS200', 1.75, 2),
(9,  2, 8, 'LI-LIMP',  4.20, 6),
(10, 3, 4, 'SN-CM250', 3.40, 3);

-- ---------------------------------------------------------------------
-- Campañas e inscripciones
-- Campañas 3 y 5 sin inscritos. Clientes 5 y 6 sin campañas.
-- Cliente 1 en dos campañas; campaña 1 con tres clientes.
-- ---------------------------------------------------------------------
INSERT INTO campana (id, codigo, nombre, descripcion, fecha_inicio, fecha_fin) VALUES
(1, 'CAM-001', 'Verano 2026',    'Descuentos de verano para clientes registrados', '2026-06-01', '2026-08-31'),
(2, 'CAM-002', 'Otoño Café',     'Promoción de café y snacks',                     '2026-09-15', '2026-11-30'),
(3, 'CAM-003', 'Navidad 2026',   'Campaña navideña',                               '2026-12-01', '2027-01-06'),
(4, 'CAM-004', 'Puntos Dobles',  'Doble de puntos los fines de semana',            '2026-10-01', '2026-12-31'),
(5, 'CAM-005', 'Primavera 2027', 'Próxima campaña, aún sin inscritos',             '2027-03-01', '2027-05-31');

INSERT INTO inscripcion_campana (id, campana_id, cliente_id, fecha_inscripcion) VALUES
(1, 1, 1, '2026-06-05'),
(2, 1, 2, '2026-06-12'),
(3, 1, 3, '2026-07-01'),
(4, 2, 1, '2026-09-20'),
(5, 2, 4, '2026-09-25'),
(6, 4, 3, '2026-10-05');

-- ---------------------------------------------------------------------
-- Ventas y líneas
-- Anónimas: 2 y 4. Pendientes de cobro: 4 y 6. precio_unitario = precio
-- del catálogo en el momento de la venta. El importe lo calcula la BD.
-- ---------------------------------------------------------------------
INSERT INTO venta (id, referencia_ticket, estacion_id, empleado_id, cliente_id, fecha_hora, estado_cobro) VALUES
(1, 'A-000101', 1, 2, 1,    '2026-11-02 09:15:00', 'PAGADA'),
(2, 'A-000102', 1, 3, NULL, '2026-11-02 10:40:00', 'PAGADA'),
(3, 'B-000055', 2, 5, 3,    '2026-11-03 18:05:00', 'PAGADA'),
(4, 'B-000056', 2, 4, NULL, '2026-11-04 08:30:00', 'PENDIENTE'),
(5, 'E-000010', 5, 8, 4,    '2026-11-05 12:20:00', 'PAGADA'),
(6, 'A-000103', 1, 2, 2,    '2026-11-06 17:45:00', 'PENDIENTE');

INSERT INTO linea_venta (id, venta_id, producto_id, numero_orden, cantidad, precio_unitario) VALUES
-- Venta 1 (importe total 16.15)
(1,  1, 1, 1, 2.000,  1.20),
(2,  1, 2, 2, 1.000,  9.90),
(3,  1, 5, 3, 0.350, 11.00),
-- Venta 2 (8.70)
(4,  2, 3, 1, 3.000,  2.50),
(5,  2, 1, 2, 1.000,  1.20),
-- Venta 3 (12.40)
(6,  3, 7, 1, 0.250, 16.00),
(7,  3, 4, 2, 1.000,  4.80),
(8,  3, 6, 3, 2.000,  1.80),
-- Venta 4 (12.20)
(9,  4, 1, 1, 6.000,  1.20),
(10, 4, 3, 2, 2.000,  2.50),
-- Venta 5 (21.45)
(11, 5, 2, 1, 2.000,  9.90),
(12, 5, 5, 2, 0.150, 11.00),
-- Venta 6 (10.80)
(13, 6, 4, 1, 2.000,  4.80),
(14, 6, 1, 2, 1.000,  1.20);

-- ---------------------------------------------------------------------
-- Devoluciones (solo de ventas pagadas)
--  Línea 4 (3 uds a 2.50): dos devoluciones parciales sucesivas, 2 uds y
--  5.00 en total, sin superar lo comprado (3 uds, 7.50).
--  Líneas 8 y 12 (granel): no son aptas para reventa.
-- ---------------------------------------------------------------------
INSERT INTO devolucion (id, linea_venta_id, cantidad, motivo, fecha_hora, empleado_id, importe_reintegrado, apta_reventa) VALUES
(1, 4,  1.000, 'Producto en mal estado',         '2026-11-03 09:00:00', 3, 2.50, FALSE),
(2, 4,  1.000, 'El cliente cambia de opinión',   '2026-11-05 11:30:00', 3, 2.50, TRUE),
(3, 8,  0.500, 'Cantidad pesada incorrecta',     '2026-11-04 08:50:00', 5, 0.90, FALSE),
(4, 12, 0.050, 'Exceso de producto servido',     '2026-11-06 10:10:00', 8, 0.55, FALSE);

-- ---------------------------------------------------------------------
-- Incidencias
-- INC-002 sin asignar. Luis (2) es responsable de dos. Los empleados
-- 3, 5 y 7 no tienen ninguna.
-- ---------------------------------------------------------------------
INSERT INTO incidencia (id, referencia, descripcion, fecha_hora_apertura, estado, estacion_id, responsable_id) VALUES
(1, 'INC-001', 'El surtidor 4 no dispensa diésel',        '2026-10-28 08:10:00', 'EN_CURSO', 1, 2),
(2, 'INC-002', 'Fallo del datáfono en caja',              '2026-11-01 17:30:00', 'ABIERTA',  1, NULL),
(3, 'INC-003', 'Iluminación de la marquesina fundida',    '2026-11-02 21:00:00', 'EN_CURSO', 2, 4),
(4, 'INC-004', 'Pérdida de aceite en la zona de lavado',  '2026-11-03 07:45:00', 'ABIERTA',  1, 2),
(5, 'INC-005', 'Cartel de precios desactualizado',        '2026-11-04 10:00:00', 'CERRADA',  5, 8),
(6, 'INC-006', 'La puerta de la tienda no cierra bien',   '2026-11-05 15:20:00', 'ABIERTA',  3, 6);

-- historial_precio: se deja vacío a propósito. Lo rellena el trigger la
-- primera vez que cambie un precio (ver 04_comprobaciones.sql).
