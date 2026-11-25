# context.md — GasolineraP3 MySQL (Fase 1/2)

> Contexto para agentes IA y profesor. Léelo antes de tocar nada.
> Solo recoge lo que NO se deduce del código. Esquema completo en `sql/`.

## Qué es y para qué
- Acceso a Datos (2º DAM): caso práctico **Gasolinera + MySQL**.
- App Java consola (`Main`, `Terminal`) gestiona clientes/pagos con CSV y JSON. Se añade MySQL JDBC como 3ª opción **sin romper las otras dos**.
- **Fase 1 (hecha)**: diseño relacional, scripts SQL, modelo Java dominio base, trigger historial precios.
- **Fase 2 (actual)**: implementar métodos JDBC (insert/select/update/delete), modelo dominio completo, DAOs, tests, docs entrega.

## Entorno y comandos
- Java 21. IntelliJ IDEA (JDK 21) + NetBeans. MySQL 8.0.16+ (Docker o local).
- BD: `mysql -u root -p < sql/01_esquema.sql` → `02_triggers.sql` → `03_datos.sql` (orden obligatorio, BD vacía).
- `sql/04_comprobaciones.sql`: ejecutar por secciones; contiene errores provocados a propósito.
- Compilar/ejecutar: "Run Main" en IntelliJ o `./gradlew run` si se migra a Gradle.
- Conexión: `jdbc:mysql://localhost:3306/gasolinera?useSSL=false&serverTimezone=UTC`. Credenciales en `db.properties` (template en repo, real en `.gitignore`), nunca en código.

## Decisiones de diseño que NO se deducen del código
- **PK técnica vs clave negocio**: Todas las tablas `id INT AI` (PK). Códigos/referencias = `VARCHAR UNIQUE NOT NULL` (no son PK).
- **1:1 Estacion–FichaFacturacion**: `ficha_facturacion.estacion_id UNIQUE FK`. Alta transaccional (ambas o ninguna) — pendiente en `EstacionDAO`.
- **Producto**: Tabla única con `modalidad ENUM(ENVASE,GRANEL)` + CHECK columnas propias. Modalidad inmutable (trigger).
- **Precio histórico**: Se copia en `linea_venta.precio_unitario`. Total venta = suma líneas (no se guarda).
- **Tarjeta fidelización**: `cliente_id NULL UNIQUE` → tarjeta sin asignar = sin cliente.
- **Cliente**: email obligatorio, teléfono opcional.
- **Supervisión**: `empleado.supervisor_id` FK self. Autosupervisión y ciclos → trigger (MySQL no permite CHECK con ON DELETE SET NULL).
- **Borrado**: RESTRICT por defecto. CASCADE: estacion→ficha/surtidores, campana→inscripciones. SET NULL: supervisor, responsable incidencia.
- **Decisiones libres**: Fecha inscripción campaña no validada contra fechas campaña; responsable incidencia puede ser de otra estación.

## Reglas que la BD NO impide (pendientes en Java, Fase 2)
- Transacciones multi-tabla: `setAutoCommit(false)` + commit/rollback en: estación+ficha, proveedor+primer acuerdo, producto+primer acuerdo, venta+≥1 línea, periodo+detalle.
- Devoluciones: solo venta ya pagada; total devuelto ≤ comprado (concurrencia); importe = precio venta original + redondeo que nunca supere importe línea.
- Validar cantidad según modalidad: envasado = entero positivo; granel = múltiplo de `precision_pesada`.
- Venta → PAGADA solo si Σ pagos = total líneas. Borrador trigger comentado en `02_triggers.sql` (espera tabla `pago`).
- **Pagos legacy**: `src/Pagos.java` tiene campos (id, idCliente, fechaRepostaje, importe, litros, combustible). Nueva tabla `pago` = (id, venta_id, importe, fecha_hora, medio_pago, estado_cobro). **No reinterpretar en silencio** — mantener compatibilidad CSV/JSON.

## Modelo dominio (Fase 1 → Fase 2)
| Capa | Fase 1 (hecho) | Fase 2 (pendiente) |
|------|----------------|-------------------|
| Entidades | `Clientes`, `Pagos` | +15: `Estacion`, `FichaFacturacion`, `Surtidor`, `Empleado`, `PeriodoFuncion`, `Funcion(enum)`, `Supervision`, `TarjetaFidelizacion`, `Producto(abstract)`, `ProductoEnvasado`, `ProductoGranel`, `Proveedor`, `AcuerdoSuministro`, `Campana`, `Inscripcion`, `Venta`, `LineaVenta`, `PagoJDBC`, `Devolucion`, `Incidencia`, `HistorialPrecio` |
| Lógica | `GestionClientes`, `GestionPagos` | Mismos + nuevos DAOs |
| Persistencia | `GestionFicheros` + CSV/JSON | + `GestionFicherosJDBC` + 9 DAOs |

## Polimorfismo Producto (requisito explícito Fase 1)
```java
// Producto (abstract)
validarCantidad(BigDecimal) → boolean
calcularImporte(BigDecimal) → BigDecimal

// ProductoEnvasado: entero positivo
// ProductoGranel: múltiplo de precision_pesada
```
Demo breve en `Main` o test sin BD.

## Límites
**Siempre**
- Respetar funcionamiento CSV/JSON. Dominio sin SQL ni conexiones.
- Cambios mínimos, localizados; indicar fichero:línea.
- Compilar tras cada cambio Java.

**Preguntar antes**
- Cambiar esquema, scripts, o decisiones de este fichero.
- Añadir dependencias o renombrar clases/ficheros existentes.
- Tocar `Pagos.java` o clases de clientes.

**Nunca**
- Desactivar FKs para ocultar fallo.
- Poner credenciales en código o subir `.env`/`db.properties` real.
- Borrar/reescribir `datos/` o `datosJSON/`.
- Inventar columnas/campos/nombres: si falta info, preguntar.
- Reinterpretar en silencio datos antiguos de pagos.

## Estado
- **Hecho**: esquema (18 tablas), trigger `historial_precio`, datos ejemplo, comprobaciones, dominio base (11 clases), Strategy pattern CSV/JSON, README actualizado, graphify (91 nodos), nota Brain.
- **Pendiente Fase 2**:
  - [ ] MySQL Connector/J + `db.properties` template + DataSource (HikariCP)
  - [ ] 9 DAOs (ver checklist abajo)
  - [ ] Modelo dominio completo (+15 clases, polimorfismo Producto)
  - [ ] `GestionFicherosJDBC` implementando Strategy
  - [ ] Tests JUnit (dominio + DAO)
  - [ ] Docs entrega: `AGENTS.md`, `Justificacion_Java_MySQL.md`, `Reglas_Negocio.md`, `Operaciones_JDBC.md`, `AUDITORIA_IA.odt` (3 intervenciones IA)
  - [ ] Tabla `pago` + CHECK `tipo_combustible` con textos reales (ver `sql/04_comprobaciones.sql` sección E)

## Checklist DAOs (orden de dependencias)
```text
[ ] EstacionDAO + FichaFacturacionDAO + SurtidorDAO (transacción 1:1)
[ ] EmpleadoDAO + PeriodoFuncionDAO (no-solape, no-ciclos)
[ ] ClienteDAO + TarjetaFidelizacionDAO
[ ] ProductoDAO + ProductoEnvasadoDAO + ProductoGranelDAO (polimorfismo)
[ ] ProveedorDAO + AcuerdoSuministroDAO
[ ] CampanaDAO + InscripcionDAO
[ ] VentaDAO + LineaVentaDAO + PagoJDBCDAO + DevolucionDAO
[ ] IncidenciaDAO + HistorialPrecioDAO
[ ] GestionFicherosJDBC (Strategy)
```

## Cómo trabajar
- Una tarea cada vez; confirmar antes de continuar.
- Si script falla: pegar error completo + sentencia → proponer corrección mínima.
- Respuestas cortas.

## Referencias (leer solo si hace falta)
- `sql/01_esquema.sql`: fuente verdad esquema + restricciones
- `sql/02_triggers.sql`: reglas en triggers
- `sql/04_comprobaciones.sql`: secciones D (errores esperados) y E (limitaciones)
- `Readme.md` y `Cambios.md`: docs + registro cambios
- `~/Documents/Brain/LuisBraille/AccesoDatos/gasolinera-p3-mysql-jdbc.md`: decisiones, debates, historial
- `~/Documents/Brain/LuisBraille/AccesoDatos/Gasolinera_MySQL_Fase_1_RESUMEN.md`: spec condensada