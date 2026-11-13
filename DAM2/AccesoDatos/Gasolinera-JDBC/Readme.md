# GasolineraP2 - Sistema de Gestión de Gasolinera con JSON

## Estructura del Proyecto

```
GasolineraP2/
├── src/
│   ├── Main.java              # Punto de entrada de la aplicación
│   ├── Terminal.java          # Gestión de entrada/salida por consola
│   ├── Clientes.java          # Modelo de datos para clientes
│   ├── Pagos.java             # Modelo de datos para pagos/repostajes
│   ├── GestionClientes.java   # Lógica de negocio para gestión de clientes
│   ├── GestionPagos.java      # Lógica de negocio para gestión de pagos
│   ├── GestionFicherosJSON.java # Manejo de archivos JSON
│   ├── GestionFicherosCSV.java  # Manejo de archivos CSV (mantenido para migración)
│   ├── GestionFicheros.java     # Interfaz para gestión de ficheros
│   └── MigraCSVToJSON.java      # Utilidad para migrar de CSV a JSON
├── datos/                     # Directorio para archivos CSV originales
├── datosJSON/                 # Directorio para archivos JSON
├── out/                       # Directorio de salida
├── .idea/                     # Configuración de IDE
└── .gitignore                 # Archivos y directorios ignorados por git
```

## Descripción

Evolución del sistema de gestión de gasolinera que ahora utiliza formato JSON para el almacenamiento de datos en lugar de CSV. Incluye funcionalidad de migración desde el formato CSV anterior.

### Nuevas Características respecto a P1:
- Almacenamiento de datos en formato JSON
- Utilidad de migración CSV → JSON
- Soporte para ambos formatos durante la transición

## Clases Principales

### Main.java
Clase principal que inicia la aplicación:
- Ejecuta primero la utilidad de migración si es necesario
- Luego inicializa el sistema usando el gestor de ficheros JSON
- Flujo similar a P1 pero adaptado para el nuevo sistema de ficheros

### Terminal.java
Mantiene la misma funcionalidad que en P1 para interacción con consola.

### Clientes.java y Pagos.java
Modelos de datos idénticos a los de P1.

### GestionClientes.java
Modificada para aceptar un objeto GestionFicheros y persistir cambios inmediatamente:
- crearCliente(): Ahora persiste el nuevo cliente inmediatamente
- Otros métodos mantienen la misma lógica que en P1

### GestionPagos.java
Idéntica a la versión de P1 (no requiere cambios para funcionar con JSON).

### GestionFicherosJSON.java
Nueva implementación de la interfaz GestionFicheros para trabajar con JSON:
- Lectura/escritura de clientes y pagos en formato JSON
- Formato JSON personalizado con estructura específica
- Métodos para guardar individualmente o en lote

### GestionFicherosCSV.java
Mantiene la implementación original de P1 para soportar la migración.

### MigraCSVToJSON.java
Nueva clase utilitaria para migrar datos de CSV a JSON:
- Verifica existencia de archivos necesarios
- Solicita al usuario confirmación para migrar
- Transfiere datos desde gestores CSV a gestores JSON
- Informa sobre el número de registros migrados

## Flujo de la Aplicación

1. Al iniciar, ejecuta MigraCSVToJSON.menuMigracion() para verificar/migrar datos si es necesario
2. Inicializa el gestor de ficheros JSON
3. Carga los datos desde los archivos JSON (o crea archivos vacíos si no existen)
4. Inicializa los contadores de IDs basado en los datos existentes
5. Muestra un menú interactivo hasta que el usuario selecciona salir
6. Los cambios se persisten inmediatamente en los archivos JSON

## Requisitos

- Java JDK 8 o superior