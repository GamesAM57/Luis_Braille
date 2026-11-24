# GasolineraP3-MySQL-JDBC - Sistema de Gestión de Gasolinera con MySQL (Fase 1)

## Estructura del Proyecto

```
GasolineraP3-MySQL-JDBC/
├── .gitignore
├── .idea/                          # Configuración IntelliJ IDEA
│   ├── .gitignore
│   ├── misc.xml                    # JDK 21
│   ├── modules.xml
│   └── vcs.xml
├── nbproject/                      # Configuración NetBeans
│   ├── build-impl.xml
│   ├── genfiles.properties
│   ├── project.properties
│   └── project.xml
├── Cambios.md                      # Registro de cambios
├── Readme.md                       # Este archivo
├── Gasolinera.iml
├── GasolineraP2.iml
├── datos/                          # Archivos CSV (P1/P2)
│   ├── clientes.csv
│   └── pagos.csv
├── datosJSON/                      # Archivos JSON (P2)
│   ├── clientes.json
│   └── pagos.json
├── sql/                            # Scripts SQL Fase 1 (orden de ejecución)
│   ├── 01_esquema.sql              # BD, tablas, PK/FK, constraints
│   ├── 02_triggers.sql             # Trigger historial de precios
│   ├── 03_datos.sql                # Datos de ejemplo
│   └── 04_comprobaciones.sql       # Pruebas con resultados esperados
└── src/                            # Modelo Java de dominio
    ├── Main.java                   # Punto de entrada (usa JSON actualmente)
    ├── Terminal.java               # Interfaz consola
    ├── Clientes.java               # Modelo cliente
    ├── Pagos.java                  # Modelo pago (compatibilidad CSV/JSON)
    ├── GestionClientes.java        # Lógica negocio clientes
    ├── GestionPagos.java           # Lógica negocio pagos
    ├── GestionFicheros.java        # Interface almacenamiento
    ├── GestionFicherosCSV.java     # Implementación CSV
    ├── GestionFicherosJSON.java    # Implementación JSON
    └── MigraCSVToJSON.java         # Utilidad migración CSV → JSON
```

## Descripción

**Fase 1 del caso práctico "Gasolinera + MySQL" (Acceso a Datos, 2º DAM).**

Este proyecto implementa el **diseño relacional completo** y el **modelo Java de dominio** para un sistema de gestión de gasolinera con persistencia en MySQL, manteniendo la convivencia con las implementaciones previas en CSV (P1) y JSON (P2).

### Qué incluye la Fase 1
- ✅ Esquema relacional completo (18 tablas): estaciones, fichas facturación, surtidores, personal (empleados, periodos, funciones, supervisión), clientes, tarjetas fidelización, productos (envasado/granel), proveedores, acuerdos, campañas, inscripciones, ventas, líneas, pagos, devoluciones, incidencias
- ✅ Trigger obligatorio: historial de precios de venta (`historial_precio`)
- ✅ Datos de ejemplo coherentes (≥5 registros por tabla principal, ≥10 líneas de venta)
- ✅ Scripts de comprobación con pruebas positivas y negativas
- ✅ Modelo Java de dominio (POJOs) sin dependencias de BD
- ✅ Interfaz `GestionFicheros` para abstraer almacenamiento (CSV/JSON/MySQL)
- ✅ Convivencia CSV + JSON + futuro MySQL sin perder funcionalidad

### Qué NO incluye (Fase 2 - pendiente)
- ❌ Capa JDBC DAO (`GestionFicherosJDBC`, `DataSource`, conexión MySQL)
- ❌ Driver MySQL Connector/J en classpath
- ❌ Clases de dominio completas para todas las entidades nuevas (Estación, Surtidor, Empleado, Producto con herencia, etc.)
- ❌ Validación polimórfica de cantidades/importe por modalidad de producto

## Clases Principales (Dominio actual - CSV/JSON)

### Main.java
Punto de entrada. Ejecuta migración CSV→JSON si procede, inicializa `GestionFicherosJSON`, contadores de IDs y bucle de menú.

### Terminal.java
Gestión de entrada/salida por consola (menús, lecturas, validaciones básicas).

### Clientes.java / Pagos.java
Modelos de datos para la funcionalidad CSV/JSON existente. **Nota**: `Pagos.java` mantiene la estructura legacy (id, idCliente, fechaRepostaje, importe, litros, combustible) distinta de la nueva tabla `pago` relacional (id, venta_id, importe, fecha_hora, medio_pago, estado_cobro).

### GestionClientes.java / GestionPagos.java
Lógica de negocio que opera sobre la interfaz `GestionFicheros` (polimorfismo CSV/JSON/MySQL futuro).

### GestionFicheros.java (Interface)
Contrato para almacenamiento: `getClientes()`, `getPagos()`, `guardarCliente()`, `guardarPago()`, `guardarTodosClientes()`, `guardarTodosPagos()`.

### GestionFicherosCSV.java / GestionFicherosJSON.java
Implementaciones concretas para CSV y JSON. Formato JSON personalizado con estructura tipada.

### MigraCSVToJSON.java
Utilidad interactiva: detecta CSV, pide confirmación, migra a JSON, informa registros migrados.

## Scripts SQL (Fase 1) — Orden de ejecución obligatorio

```bash
# 1. Esquema: BD, 18 tablas, PK/FK/UNIQUE/CHECK
mysql -u root -p < sql/01_esquema.sql

# 2. Trigger: historial_precio (AFTER UPDATE ON producto)
mysql -u root -p < sql/02_triggers.sql

# 3. Datos de ejemplo coherentes
mysql -u root -p < sql/03_datos.sql

# 4. Comprobaciones: pruebas positivas + negativas (errores esperados)
mysql -u root -p < sql/04_comprobaciones.sql
```

### Resumen de tablas (01_esquema.sql)
| Tabla | Propósito |
|-------|-----------|
| `estacion` | Estaciones (código único, datos fiscales/comerciales) |
| `ficha_facturacion` | Configuración facturación 1:1 con estación |
| `surtidor` | Equipos por estación (código global, nº visible, combustible, estado) |
| `empleado` | Personal adscrito a estación, supervisor opcional |
| `periodo_funcion` | Historial funciones (CAJERO, EXPENDEDOR, RESPONSABLE, JEFE) sin solape |
| `cliente` | Clientes registrados (código único, contacto opcional) |
| `tarjeta_fidelizacion` | Tarjetas (número único, 0:1 cliente, emisión/asignación) |
| `producto` | Catálogo (ref. única, precio, modalidad ENVASE/GRANEL, estado) |
| `proveedor` | Proveedores con acuerdo vigente |
| `acuerdo_suministro` | N:M proveedor-producto (precio compra, plazo) |
| `campana` | Campañas marketing (fechas, código único) |
| `inscripcion` | N:M cliente-campaña |
| `venta` | Cabecera ticket (estación, empleado, cliente opcional, ref. única) |
| `linea_venta` | Detalle (producto, nº orden, cantidad, **precio aplicado guardado**) |
| `pago` | 1 venta = 1..N pagos (importe, medio, estado cobro) |
| `devolucion` | Sobre línea pagada (parciales, importe = precio original) |
| `incidencia` | Ref única, estado, estación, responsable actual |
| `historial_precio` | **Trigger**: cambios reales de precio_venta |

## Requisitos

- **Java 21** (JDK 21)
- **MySQL 8.0.16+** (requerido para CHECK constraints funcionales)
- **MySQL Connector/J** (driver JDBC) — *pendiente de añadir a `lib/` y classpath*
- IntelliJ IDEA o NetBeans (configuraciones incluidas)

## Configuración conexión MySQL (pendiente implementación Fase 2)

Se necesitará un `DataSource` o clase de conexión con:
```properties
url=jdbc:mysql://localhost:3306/gasolinera?useSSL=false&serverTimezone=UTF-8&allowPublicKeyRetrieval=true
user=<usuario>
password=<contraseña>
```
**No hardcodear credenciales**: usar variables de entorno, `.env` o properties fuera del repo.

## Compilar y ejecutar (estado actual: solo CSV/JSON)

### IntelliJ IDEA
1. `File > Open` → seleccionar carpeta `GasolineraP3-MySQL-JDBC`
2. `File > Project Structure > Modules > Dependencies` → añadir MySQL Connector/J (cuando exista)
3. Ejecutar `Main.java` — usa `GestionFicherosJSON` por defecto

### NetBeans
1. `File > Open Project` → seleccionar carpeta (reconoce `nbproject/`)
2. `Properties > Libraries` → añadir MySQL Connector/J
3. Run `Main.java`

### Terminal (requiere compilar manualmente)
```bash
# Compilar (añadir driver MySQL al classpath cuando exista)
javac -d out src/*.java

# Ejecutar
java -cp out Main
```

## Verificar que CSV y JSON siguen funcionando

Al ejecutar `Main.java`:
1. `MigraCSVToJSON.menuMigracion(terminal)` detecta `datos/clientes.csv` y `datos/pagos.csv`
2. Si existen y no hay JSON, pide confirmación y migra a `datosJSON/`
3. Inicializa `GestionFicherosJSON` y carga desde `datosJSON/`
4. Menú interactivo funcional (crear/listar/buscar clientes, registrar/consultar pagos)

## Próximos pasos (Fase 2)

1. Añadir MySQL Connector/J a `lib/` y configurar en IDEs
2. Crear `DataSource` / clase conexión (HikariCP o `DriverManager` simple)
3. Implementar `GestionFicherosJDBC` con DAOs para todas las entidades
4. Completar modelo dominio: `Estacion`, `FichaFacturacion`, `Surtidor`, `Empleado`, `PeriodoFuncion`, `TarjetaFidelizacion`, `Producto` (jerarquía `ProductoEnvasado`/`ProductoGranel`), `Proveedor`, `AcuerdoSuministro`, `Campana`, `Inscripcion`, `Venta`, `LineaVenta`, `PagoJDBC`, `Devolucion`, `Incidencia`, `HistorialPrecio`
5. Validación polimórfica `validarCantidad()` / `calcularImporte()` en `Producto`
6. Tests JUnit para dominio y DAO
7. Documentación entrega: `AGENTS.md`, `AUDITORIA_IA.odt`, justificación Java↔MySQL, reglas negocio, 3 operaciones JDBC