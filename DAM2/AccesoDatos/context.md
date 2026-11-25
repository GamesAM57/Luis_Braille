# Acceso a Datos — Context Portal (AI Sessions)

> **Punto de entrada** para cualquier sesión de IA en esta carpeta. Léelo primero.
> Cada proyecto tiene su propio `context.md` con detalle técnico; este archivo navega y estandariza.

## 1. Mapa de proyectos

| Proyecto | Persistencia | Build | Estado | Context propio |
|----------|--------------|-------|--------|----------------|
| `GasolineraP3-MySQL-JDBC` | MySQL + CSV/JSON | IntelliJ + NetBeans | **Fase 1 ✅ / Fase 2 🚧** | `GasolineraP3-MySQL-JDBC/docs/context.md` |
| `Gasolinera-JDBC` | SQLite | IntelliJ | Done (referencia) | — |
| `GasolineraP2` | JSON + CSV | IntelliJ | Done (legacy) | — |
| `GasolineraP1` | CSV | IntelliJ | Done (legacy) | — |
| `RA2-JDBC` | MySQL/SQLite | Gradle | Ejercicios | — |
| `Ficheros binarios con Java` | Binario | Gradle | Ejercicios | — |

**Regla**: Para trabajar en un proyecto, lee SU `context.md` + este portal.

---

## 2. Estándares comunes (no repetir en cada proyecto)

- **Java**: 21, UTF-8, nombres dominio en español (`Clientes`), técnicos en inglés (`DAO`)
- **Arquitectura**: Dominio (POJOs) / Lógica / Persistencia (Strategy `GestionFicheros`)
- **Git**: Conventional Commits (`feat:`, `fix:`, `docs:`, `refactor:`, `test:`, `chore:`)
- **BD**: MySQL 8.0.16+, `utf8mb4_0900_ai_ci`, PK=`INT AI`, claves negocio=`VARCHAR UNIQUE`, dinero=`DECIMAL`
- **Tests**: JUnit 5, H2/Testcontainers
- **Credenciales**: `.env` / `db.properties` **nunca en código ni en repo**

---

## 3. Knowledge Base (Obsidian — leer al inicio)

| Nota | Ruta | Cuándo leer |
|------|------|-------------|
| `gasolinera-p3-mysql-jdbc.md` | `~/Documents/Brain/LuisBraille/AccesoDatos/` | **Siempre** (proyecto activo) |
| `Gasolinera_MySQL_Fase_1_RESUMEN.md` | `~/Documents/Brain/LuisBraille/AccesoDatos/` | **Siempre** (spec fuente) |
| `Caso_practico_Gasolinera_MySQL_Fase_1.md` | `~/Documents/Brain/LuisBraille/AccesoDatos/` | Si hay duda en enunciado |
| `Persistencia Gasolinera JDBC - Liam Bezanilla Alonso.md` | `~/Documents/Brain/LuisBraille/AccesoDatos/` | Referencia SQLite |

---

## 4. Protocolo de sesión IA

### Al entrar
1. Lee este archivo + `gasolinera-p3-mysql-jdbc.md` + `Gasolinera_MySQL_Fase_1_RESUMEN.md`
2. `git status` y `git log --oneline -5` en `Luis_Braille/`
3. Verifica qué proyecto toca (lee su `context.md`)

### Durante
- Actualiza la nota Brain del proyecto con decisiones/historial
- Marca `[x]` checklists en el `context.md` del proyecto
- Commits Conventional Commits

### Al salir
- `graphify update .` en el proyecto activo
- `git push origin main`
- Actualiza `updated` y `Historial` en la nota Brain

---

## 5. Límites globales

**Siempre**
- Mantener convivencia CSV/JSON/MySQL en GasolineraP3
- Cambios mínimos, localizados, con archivo:línea
- Compilar tras cada cambio Java

**Preguntar antes**
- Cambiar esquema, scripts, decisiones de `context.md` de proyecto
- Añadir dependencias o renombrar clases/archivos existentes
- Tocar `Pagos.java` o clases de clientes en GasolineraP3

**Nunca**
- Desactivar FKs para ocultar fallos
- Subir credenciales (`.env`, `db.properties` reales)
- Borrar `datos/` o `datosJSON/`
- Inventar columnas/campos: si falta info, preguntar
- Reinterpretar en silencio datos antiguos de pagos

---

## 6. Comandos rápidos (desde `Luis_Braille/`)

```bash
# Proyecto activo
cd DAM2/AccesoDatos/GasolineraP3-MySQL-JDBC

# BD
mysql -u root -p < sql/01_esquema.sql && mysql -u root -p < sql/02_triggers.sql && mysql -u root -p < sql/03_datos.sql

# Graphify
graphify update .                    # AST only, gratis
graphify query "pregunta"            # Explorar
open graphify-out/graph.html         # Visual

# Git
git status && git log --oneline -5
```

---

## 7. Estado global (2025-11-25)

- **GasolineraP3**: Fase 1 completa (SQL + dominio base). Fase 2 = JDBC DAOs + modelo completo.
- **Otros**: Congelados como referencia/legacy.
- **Próximo commit**: MySQL Connector/J + DataSource (ver `GasolineraP3-MySQL-JDBC/docs/context.md`)

---

*Este archivo es el índice. El detalle técnico vive en cada `context.md` de proyecto.*