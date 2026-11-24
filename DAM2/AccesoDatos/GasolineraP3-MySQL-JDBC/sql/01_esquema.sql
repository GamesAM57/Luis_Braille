-- =====================================================================
-- 01_esquema.sql  |  Gasolinera MySQL, Fase 1
-- Ejecutar primero, sobre un entorno vacío. Requiere MySQL 8.0.16+
-- (antes de esa versión los CHECK se aceptan pero no se aplican).
--
-- NO está aquí:
--   * tabla `pago`: pendiente de revisar la clase Pagos existente.
--   * triggers: van en 02_triggers.sql.
--   * CHECK de tipo_combustible: pendiente de usar los textos de Pagos.
-- =====================================================================

CREATE DATABASE IF NOT EXISTS gasolinera
  CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE gasolinera;

-- ---------------------------------------------------------------------
-- Estaciones, fichas de facturación y surtidores
-- ---------------------------------------------------------------------
CREATE TABLE estacion (
  id               INT AUTO_INCREMENT PRIMARY KEY,
  codigo           VARCHAR(20)  NOT NULL,
  nombre_comercial VARCHAR(100) NOT NULL,
  direccion        VARCHAR(200) NOT NULL,
  telefono         VARCHAR(20)  NOT NULL,   -- texto: prefijos y ceros
  fecha_apertura   DATE         NOT NULL,
  CONSTRAINT uq_estacion_codigo UNIQUE (codigo)
) ENGINE=InnoDB;

-- Relación 1 a 1 con estacion: estacion_id NOT NULL + UNIQUE.
-- Que toda estación tenga ficha se garantiza con la transacción del alta.
CREATE TABLE ficha_facturacion (
  id                   INT AUTO_INCREMENT PRIMARY KEY,
  estacion_id          INT          NOT NULL,
  codigo_configuracion VARCHAR(30)  NOT NULL,
  serie_ticket         VARCHAR(10)  NOT NULL,
  siguiente_numero     INT          NOT NULL DEFAULT 1,
  texto_pie            VARCHAR(255) NOT NULL,
  CONSTRAINT uq_ficha_estacion UNIQUE (estacion_id),
  CONSTRAINT uq_ficha_codigo   UNIQUE (codigo_configuracion),
  CONSTRAINT uq_ficha_serie    UNIQUE (serie_ticket),
  CONSTRAINT chk_ficha_numero  CHECK (siguiente_numero >= 1),
  CONSTRAINT fk_ficha_estacion FOREIGN KEY (estacion_id)
    REFERENCES estacion(id) ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE surtidor (
  id               INT AUTO_INCREMENT PRIMARY KEY,
  estacion_id      INT         NOT NULL,
  codigo_equipo    VARCHAR(30) NOT NULL,
  numero_visible   INT         NOT NULL,
  tipo_combustible VARCHAR(30) NOT NULL,   -- PENDIENTE: CHECK con los textos de Pagos
  estado_servicio  VARCHAR(15) NOT NULL DEFAULT 'EN_SERVICIO',
  CONSTRAINT uq_surtidor_codigo UNIQUE (codigo_equipo),
  CONSTRAINT uq_surtidor_numero UNIQUE (estacion_id, numero_visible),
  CONSTRAINT chk_surtidor_estado CHECK (estado_servicio IN ('EN_SERVICIO','AVERIADO')),
  CONSTRAINT fk_surtidor_estacion FOREIGN KEY (estacion_id)
    REFERENCES estacion(id) ON DELETE CASCADE
) ENGINE=InnoDB;

-- ---------------------------------------------------------------------
-- Personal
-- ---------------------------------------------------------------------
-- supervisor_id: ON DELETE SET NULL. MySQL no permite un CHECK sobre una
-- columna con esa acción, así que "nadie se supervisa a sí mismo" y la
-- ausencia de ciclos se controlan en el trigger de 02_triggers.sql.
CREATE TABLE empleado (
  id                  INT AUTO_INCREMENT PRIMARY KEY,
  estacion_id         INT          NOT NULL,
  supervisor_id       INT          NULL,
  numero_interno      VARCHAR(20)  NOT NULL,
  documento           VARCHAR(20)  NOT NULL,
  nombre              VARCHAR(100) NOT NULL,
  contacto            VARCHAR(100) NOT NULL,
  fecha_incorporacion DATE         NOT NULL,
  CONSTRAINT uq_empleado_numero    UNIQUE (numero_interno),
  CONSTRAINT uq_empleado_documento UNIQUE (documento),
  CONSTRAINT fk_empleado_estacion FOREIGN KEY (estacion_id)
    REFERENCES estacion(id) ON DELETE RESTRICT,
  CONSTRAINT fk_empleado_supervisor FOREIGN KEY (supervisor_id)
    REFERENCES empleado(id) ON DELETE SET NULL
) ENGINE=InnoDB;

-- Un periodo = una etapa de un empleado en una función.
-- No solapar periodos de la misma función: trigger en 02_triggers.sql.
CREATE TABLE periodo_funcion (
  id           INT AUTO_INCREMENT PRIMARY KEY,
  empleado_id  INT         NOT NULL,
  funcion      VARCHAR(20) NOT NULL,
  fecha_inicio DATE        NOT NULL,
  fecha_fin    DATE        NULL,           -- NULL = sigue activa
  CONSTRAINT uq_periodo_id_funcion UNIQUE (id, funcion),  -- para la FK compuesta
  CONSTRAINT chk_periodo_funcion CHECK
    (funcion IN ('CAJERO','EXPENDEDOR','RESPONSABLE','JEFE')),
  CONSTRAINT chk_periodo_fechas CHECK (fecha_fin IS NULL OR fecha_fin >= fecha_inicio),
  CONSTRAINT fk_periodo_empleado FOREIGN KEY (empleado_id)
    REFERENCES empleado(id) ON DELETE RESTRICT
) ENGINE=InnoDB;

-- Tablas de detalle: la FK compuesta (periodo_id, funcion) y el CHECK
-- impiden que un detalle cuelgue de un periodo de otra función.
-- Que cada periodo tenga su detalle se garantiza con la transacción del alta.
CREATE TABLE periodo_cajero (
  id                  INT AUTO_INCREMENT PRIMARY KEY,
  periodo_id          INT         NOT NULL,
  funcion             VARCHAR(20) NOT NULL DEFAULT 'CAJERO',
  limite_devoluciones DECIMAL(10,2) NOT NULL,
  CONSTRAINT uq_pcajero_periodo UNIQUE (periodo_id),
  CONSTRAINT chk_pcajero_funcion CHECK (funcion = 'CAJERO'),
  CONSTRAINT chk_pcajero_limite  CHECK (limite_devoluciones >= 0),
  CONSTRAINT fk_pcajero_periodo FOREIGN KEY (periodo_id, funcion)
    REFERENCES periodo_funcion(id, funcion) ON DELETE RESTRICT
) ENGINE=InnoDB;

CREATE TABLE periodo_expendedor (
  id                     INT AUTO_INCREMENT PRIMARY KEY,
  periodo_id             INT         NOT NULL,
  funcion                VARCHAR(20) NOT NULL DEFAULT 'EXPENDEDOR',
  acreditacion_numero    VARCHAR(30) NOT NULL,
  acreditacion_caducidad DATE        NOT NULL,
  CONSTRAINT uq_pexpendedor_periodo UNIQUE (periodo_id),
  CONSTRAINT chk_pexpendedor_funcion CHECK (funcion = 'EXPENDEDOR'),
  CONSTRAINT fk_pexpendedor_periodo FOREIGN KEY (periodo_id, funcion)
    REFERENCES periodo_funcion(id, funcion) ON DELETE RESTRICT
) ENGINE=InnoDB;

CREATE TABLE periodo_responsable (
  id          INT AUTO_INCREMENT PRIMARY KEY,
  periodo_id  INT         NOT NULL,
  funcion     VARCHAR(20) NOT NULL DEFAULT 'RESPONSABLE',
  presupuesto DECIMAL(12,2) NOT NULL,
  CONSTRAINT uq_presponsable_periodo UNIQUE (periodo_id),
  CONSTRAINT chk_presponsable_funcion CHECK (funcion = 'RESPONSABLE'),
  CONSTRAINT chk_presponsable_presupuesto CHECK (presupuesto >= 0),
  CONSTRAINT fk_presponsable_periodo FOREIGN KEY (periodo_id, funcion)
    REFERENCES periodo_funcion(id, funcion) ON DELETE RESTRICT
) ENGINE=InnoDB;

CREATE TABLE periodo_jefe (
  id                 INT AUTO_INCREMENT PRIMARY KEY,
  periodo_id         INT         NOT NULL,
  funcion            VARCHAR(20) NOT NULL DEFAULT 'JEFE',
  nivel_autorizacion INT         NOT NULL,
  CONSTRAINT uq_pjefe_periodo UNIQUE (periodo_id),
  CONSTRAINT chk_pjefe_funcion CHECK (funcion = 'JEFE'),
  CONSTRAINT chk_pjefe_nivel CHECK (nivel_autorizacion >= 1),
  CONSTRAINT fk_pjefe_periodo FOREIGN KEY (periodo_id, funcion)
    REFERENCES periodo_funcion(id, funcion) ON DELETE RESTRICT
) ENGINE=InnoDB;

-- ---------------------------------------------------------------------
-- Clientes y tarjetas de fidelización
-- ---------------------------------------------------------------------
CREATE TABLE cliente (
  id       INT AUTO_INCREMENT PRIMARY KEY,
  codigo   VARCHAR(20)  NOT NULL,
  nombre   VARCHAR(100) NOT NULL,
  email    VARCHAR(100) NOT NULL,     -- obligatorio
  telefono VARCHAR(20)  NULL,         -- opcional
  CONSTRAINT uq_cliente_codigo UNIQUE (codigo)
) ENGINE=InnoDB;

-- Tarjeta sin asignar: cliente_id y fecha_asignacion en NULL.
-- UNIQUE(cliente_id) admite varios NULL en MySQL.
CREATE TABLE tarjeta_fidelizacion (
  id               INT AUTO_INCREMENT PRIMARY KEY,
  numero           VARCHAR(20) NOT NULL,
  fecha_emision    DATE        NOT NULL,
  cliente_id       INT         NULL,
  fecha_asignacion DATE        NULL,
  CONSTRAINT uq_tarjeta_numero  UNIQUE (numero),
  CONSTRAINT uq_tarjeta_cliente UNIQUE (cliente_id),
  CONSTRAINT chk_tarjeta_asignacion CHECK
    ((cliente_id IS NULL AND fecha_asignacion IS NULL)
     OR (cliente_id IS NOT NULL AND fecha_asignacion IS NOT NULL)),
  CONSTRAINT fk_tarjeta_cliente FOREIGN KEY (cliente_id)
    REFERENCES cliente(id) ON DELETE RESTRICT
) ENGINE=InnoDB;

-- ---------------------------------------------------------------------
-- Productos y proveedores
-- ---------------------------------------------------------------------
-- La modalidad no puede cambiar de por vida: trigger en 02_triggers.sql.
CREATE TABLE producto (
  id               INT AUTO_INCREMENT PRIMARY KEY,
  referencia       VARCHAR(30)   NOT NULL,
  descripcion      VARCHAR(200)  NOT NULL,
  precio_venta     DECIMAL(10,2) NOT NULL,
  estado           VARCHAR(10)   NOT NULL DEFAULT 'ACTIVO',
  modalidad        VARCHAR(10)   NOT NULL,
  formato_envase   VARCHAR(50)   NULL,      -- solo ENVASADO
  precision_pesada DECIMAL(6,3)  NULL,      -- solo GRANEL (en kg)
  CONSTRAINT uq_producto_referencia UNIQUE (referencia),
  CONSTRAINT chk_producto_precio CHECK (precio_venta > 0),
  CONSTRAINT chk_producto_estado CHECK (estado IN ('ACTIVO','RETIRADO')),
  CONSTRAINT chk_producto_modalidad CHECK (modalidad IN ('ENVASADO','GRANEL')),
  CONSTRAINT chk_producto_datos_modalidad CHECK (
    (modalidad = 'ENVASADO' AND formato_envase IS NOT NULL AND precision_pesada IS NULL)
    OR
    (modalidad = 'GRANEL' AND precision_pesada IS NOT NULL AND precision_pesada > 0
       AND formato_envase IS NULL)
  )
) ENGINE=InnoDB;

CREATE TABLE proveedor (
  id                    INT AUTO_INCREMENT PRIMARY KEY,
  codigo                VARCHAR(20)  NOT NULL,
  identificacion_fiscal VARCHAR(20)  NOT NULL,
  razon_social          VARCHAR(150) NOT NULL,
  contacto              VARCHAR(100) NOT NULL,
  CONSTRAINT uq_proveedor_codigo UNIQUE (codigo),
  CONSTRAINT uq_proveedor_fiscal UNIQUE (identificacion_fiscal)
) ENGINE=InnoDB;

-- Todo proveedor y todo producto con al menos un acuerdo:
-- altas con transacción en Java + trigger BEFORE DELETE (02_triggers.sql).
CREATE TABLE acuerdo_suministro (
  id                   INT AUTO_INCREMENT PRIMARY KEY,
  proveedor_id         INT           NOT NULL,
  producto_id          INT           NOT NULL,
  referencia_proveedor VARCHAR(30)   NOT NULL,
  precio_compra        DECIMAL(10,2) NOT NULL,
  plazo_entrega_dias   INT           NOT NULL,
  CONSTRAINT uq_acuerdo_prov_prod UNIQUE (proveedor_id, producto_id),
  CONSTRAINT chk_acuerdo_precio CHECK (precio_compra > 0),
  CONSTRAINT chk_acuerdo_plazo  CHECK (plazo_entrega_dias >= 0),
  CONSTRAINT fk_acuerdo_proveedor FOREIGN KEY (proveedor_id)
    REFERENCES proveedor(id) ON DELETE RESTRICT,
  CONSTRAINT fk_acuerdo_producto FOREIGN KEY (producto_id)
    REFERENCES producto(id) ON DELETE RESTRICT
) ENGINE=InnoDB;

-- ---------------------------------------------------------------------
-- Campañas
-- ---------------------------------------------------------------------
CREATE TABLE campana (
  id           INT AUTO_INCREMENT PRIMARY KEY,
  codigo       VARCHAR(20)  NOT NULL,
  nombre       VARCHAR(100) NOT NULL,
  descripcion  VARCHAR(255) NOT NULL,
  fecha_inicio DATE         NOT NULL,
  fecha_fin    DATE         NOT NULL,
  CONSTRAINT uq_campana_codigo UNIQUE (codigo),
  CONSTRAINT chk_campana_fechas CHECK (fecha_fin >= fecha_inicio)
) ENGINE=InnoDB;

CREATE TABLE inscripcion_campana (
  id                INT AUTO_INCREMENT PRIMARY KEY,
  campana_id        INT  NOT NULL,
  cliente_id        INT  NOT NULL,
  fecha_inscripcion DATE NOT NULL,
  CONSTRAINT uq_inscripcion UNIQUE (campana_id, cliente_id),
  CONSTRAINT fk_inscripcion_campana FOREIGN KEY (campana_id)
    REFERENCES campana(id) ON DELETE CASCADE,
  CONSTRAINT fk_inscripcion_cliente FOREIGN KEY (cliente_id)
    REFERENCES cliente(id) ON DELETE RESTRICT
) ENGINE=InnoDB;

-- ---------------------------------------------------------------------
-- Ventas, líneas y devoluciones
-- ---------------------------------------------------------------------
-- Venta con al menos una línea: transacción en Java.
-- PENDIENTE -> PAGADA solo si lo pagado suma el total: trigger (cuando exista `pago`).
CREATE TABLE venta (
  id                INT AUTO_INCREMENT PRIMARY KEY,
  referencia_ticket VARCHAR(30) NOT NULL,
  estacion_id       INT         NOT NULL,
  empleado_id       INT         NOT NULL,
  cliente_id        INT         NULL,     -- NULL = venta anónima
  fecha_hora        DATETIME    NOT NULL,
  estado_cobro      VARCHAR(10) NOT NULL DEFAULT 'PENDIENTE',
  CONSTRAINT uq_venta_ticket UNIQUE (referencia_ticket),
  CONSTRAINT chk_venta_estado CHECK (estado_cobro IN ('PENDIENTE','PAGADA')),
  CONSTRAINT fk_venta_estacion FOREIGN KEY (estacion_id)
    REFERENCES estacion(id) ON DELETE RESTRICT,
  CONSTRAINT fk_venta_empleado FOREIGN KEY (empleado_id)
    REFERENCES empleado(id) ON DELETE RESTRICT,
  CONSTRAINT fk_venta_cliente FOREIGN KEY (cliente_id)
    REFERENCES cliente(id) ON DELETE RESTRICT
) ENGINE=InnoDB;

-- precio_unitario: precio realmente aplicado, copiado del catálogo al vender.
-- importe: calculado, fija un único criterio de redondeo (2 decimales).
CREATE TABLE linea_venta (
  id              INT AUTO_INCREMENT PRIMARY KEY,
  venta_id        INT           NOT NULL,
  producto_id     INT           NOT NULL,
  numero_orden    INT           NOT NULL,
  cantidad        DECIMAL(10,3) NOT NULL,
  precio_unitario DECIMAL(10,2) NOT NULL,
  importe         DECIMAL(12,2) GENERATED ALWAYS AS (ROUND(cantidad * precio_unitario, 2)) STORED,
  CONSTRAINT uq_linea_orden UNIQUE (venta_id, numero_orden),
  CONSTRAINT chk_linea_orden    CHECK (numero_orden >= 1),
  CONSTRAINT chk_linea_cantidad CHECK (cantidad > 0),
  CONSTRAINT chk_linea_precio   CHECK (precio_unitario > 0),
  CONSTRAINT fk_linea_venta FOREIGN KEY (venta_id)
    REFERENCES venta(id) ON DELETE RESTRICT,
  CONSTRAINT fk_linea_producto FOREIGN KEY (producto_id)
    REFERENCES producto(id) ON DELETE RESTRICT
) ENGINE=InnoDB;

-- Reglas que se resuelven en Java (fase 2): venta ya pagada, total devuelto
-- <= comprado, unidades enteras / precisión de pesada, importe con el precio
-- de la venta original y redondeo acumulado sin pasarse del total de la línea.
CREATE TABLE devolucion (
  id                  INT AUTO_INCREMENT PRIMARY KEY,
  linea_venta_id      INT           NOT NULL,
  cantidad            DECIMAL(10,3) NOT NULL,
  motivo              VARCHAR(255)  NOT NULL,
  fecha_hora          DATETIME      NOT NULL,
  empleado_id         INT           NOT NULL,   -- quien acepta la devolución
  importe_reintegrado DECIMAL(12,2) NOT NULL,
  apta_reventa        BOOLEAN       NOT NULL,
  CONSTRAINT chk_devolucion_cantidad CHECK (cantidad > 0),
  CONSTRAINT chk_devolucion_importe  CHECK (importe_reintegrado >= 0),
  CONSTRAINT fk_devolucion_linea FOREIGN KEY (linea_venta_id)
    REFERENCES linea_venta(id) ON DELETE RESTRICT,
  CONSTRAINT fk_devolucion_empleado FOREIGN KEY (empleado_id)
    REFERENCES empleado(id) ON DELETE RESTRICT
) ENGINE=InnoDB;

-- ---------------------------------------------------------------------
-- Incidencias
-- ---------------------------------------------------------------------
CREATE TABLE incidencia (
  id                  INT AUTO_INCREMENT PRIMARY KEY,
  referencia          VARCHAR(30)  NOT NULL,
  descripcion         VARCHAR(500) NOT NULL,
  fecha_hora_apertura DATETIME     NOT NULL,
  estado              VARCHAR(10)  NOT NULL DEFAULT 'ABIERTA',
  estacion_id         INT          NOT NULL,
  responsable_id      INT          NULL,        -- NULL = sin asignar
  CONSTRAINT uq_incidencia_referencia UNIQUE (referencia),
  CONSTRAINT chk_incidencia_estado CHECK (estado IN ('ABIERTA','EN_CURSO','CERRADA')),
  CONSTRAINT fk_incidencia_estacion FOREIGN KEY (estacion_id)
    REFERENCES estacion(id) ON DELETE RESTRICT,
  CONSTRAINT fk_incidencia_responsable FOREIGN KEY (responsable_id)
    REFERENCES empleado(id) ON DELETE SET NULL
) ENGINE=InnoDB;

-- ---------------------------------------------------------------------
-- Historial de precios (lo rellena el trigger de 02_triggers.sql)
-- ---------------------------------------------------------------------
CREATE TABLE historial_precio (
  id              INT AUTO_INCREMENT PRIMARY KEY,
  producto_id     INT           NOT NULL,
  precio_anterior DECIMAL(10,2) NOT NULL,
  precio_nuevo    DECIMAL(10,2) NOT NULL,
  fecha_hora      DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_historial_producto FOREIGN KEY (producto_id)
    REFERENCES producto(id) ON DELETE RESTRICT
) ENGINE=InnoDB;
