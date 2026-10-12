# Cambios de GasolineraP1 a GasolineraP2

Este documento describe los cambios realizados al evolucionar el sistema de gestión de gasolinera de la versión P1 (CSV) a la versión P2 (JSON).

## Resumen de Cambios

### 1. Cambio de Formato de Almacenamiento
- **P1**: Almacenamiento en formato CSV
- **P2**: Almacenamiento en formato JSON

### 2. Nuevas Clases Añadidas
- `GestionFicherosJSON.java`: Implementación de la interfaz `GestionFicheros` para trabajar con archivos JSON
- `MigraCSVToJSON.java`: Utilidad para migrar datos existentes de CSV a JSON

### 3. Modificaciones en Clases Existentes
- **GestionClientes.java**: 
  - Añadido parámetro `GestionFicheros fich` al método `crearCliente()`
  - El método ahora persiste inmediatamente cada nuevo cliente mediante `fich.setUnCliente(c)`
- **Main.java**:
  - Añadida llamada inicial a `MigraCSVToJSON.menuMigracion(terminal)`
  - Cambiado de `GestionFicherosCSV` a `GestionFicherosJSON` como gestor de ficheros principal
  - Eliminado el guardado final de datos

### 4. Cambios en la Estructura del Proyecto
- Añadido directorio `datosJSON/` para almacenar archivos JSON
- Mantener el directorio `datos/` para compatibilidad con archivos CSV originales durante la migración

### 5. Flujo de Aplicación Modificado
**P1**:
1. Cargar todos los datos desde CSV al inicio
2. Trabajar con datos en memoria durante la sesión
3. Guardar todos los cambios en CSV al finalizar

**P2**:
1. Ejecutar utilidad de migración (si es necesario) al inicio
2. Cargar datos desde JSON al inicio (o crear archivos vacíos)
3. Persistir cambios inmediatamente en JSON después de cada operación de creación
4. No requiere guardado final explícito

## Detalles de Implementación

### Formato JSON Utilizado
El sistema utiliza un formato JSON personalizado:
```
{
 [
  {"clave": "valor", ...},
  {"clave": "valor", ...}
 ]
}
```

### Proceso de Migración
1. El sistema verifica si existen archivos JSON en `datosJSON/`
2. Si no existen, solicita al usuario confirmación para migrar desde CSV
3. Si el usuario confirma, solicita las rutas de los archivos CSV fuente
4. Lee los datos usando `GestionFicherosCSV` y los escribe usando `GestionFicherosJSON`
5. Informa al usuario del número de registros migrados

### Ventajas del Cambio a JSON
- Mejor legibilidad e inspección manual de los datos
- Soporte nativo para estructuras de datos complejas
- Más fácil de integrar con sistemas web y APIs
- Mejor manejo de caracteres especiales y internacionalización

## Compatibilidad hacia atrás
- Se mantiene la clase `GestionFicherosCSV` para permitir la migración desde formatos antiguos
- Los archivos CSV originales se conservan en el directorio `datos/` como respaldo
- El proceso de migración es opcional y controlado por el usuario