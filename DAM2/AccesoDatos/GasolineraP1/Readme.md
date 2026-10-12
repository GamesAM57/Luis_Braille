# GasolineraP1 - Sistema de Gestión de Gasolinera

## Estructura del Proyecto

```
GasolineraP1/
├── src/
│   ├── Main.java          # Punto de entrada de la aplicación
│   ├── Terminal.java      # Gestión de entrada/salida por consola
│   ├── Clientes.java      # Modelo de datos para clientes
│   ├── Pagos.java         # Modelo de datos para pagos/repostajes
│   ├── GestionClientes.java # Lógica de negocio para gestión de clientes
│   ├── GestionPagos.java  # Lógica de negocio para gestión de pagos
│   ├── GestionFicherosCSV.java # Manejo de archivos CSV
│   └── GestionFicheros.java  # Interfaz para gestión de ficheros
├── datos/                 # Directorio para almacenar archivos de datos
└── .idea/                 # Configuración de IDE
```

## Descripción

Sistema de consola para la gestión de una gasolinera que permite:
- Dar de alta clientes
- Listar clientes
- Buscar clientes por nombre, teléfono o matrícula
- Registrar pagos de repostaje
- Consultar pagos realizados

Los datos se almacenan en formato CSV en el directorio `datos/`.

## Clases Principales

### Main.java
Clase principal que inicia la aplicación y gestiona el flujo principal del menú.

### Terminal.java
Clase utilitaria para manejar la interacción con el usuario por consola:
- Menú de opciones
- Solicitud y validación de diferentes tipos de datos (texto, enteros, decimales, fechas)
- Limpieza y cierre del scanner

### Clientes.java
Modelo que representa a un cliente de la gasolinera:
- Atributos: id, nombre, teléfono, matrícula
- Métodos: getters, setters, toString(), compareTo() para ordenación por nombre

### Pagos.java
Modelo que representa un pago/repostaje:
- Atributos: id, idCliente, fecha, importe, litros, combustible
- Métodos: getters, setters, toString(), compareTo() para ordenación por fecha (descendente)

### GestionClientes.java
Lógica de negocio para la gestión de clientes:
- crearCliente(): Dar de alta un nuevo cliente
- listarClientes(): Mostrar lista de clientes ordenados alfabéticamente
- buscarClientes(): Buscar clientes por texto
- obtenerClientePorId(): Obtener cliente por su ID
- ultimoIdClientes(): Obtener el siguiente ID disponible
- buscarMatricula(): Verificar si existe una matrícula

### GestionPagos.java
Lógica de negocio para la gestión de pagos:
- registrarPago(): Registrar un nuevo pago de repostaje
- consultarPagos(): Mostrar lista de pagos ordenados por fecha (más reciente primero)
- ultimoIdPagos(): Obtener el siguiente ID de pago disponible

### GestionFicherosCSV.java
Implementación para manejar archivos CSV:
- Lectura y escritura de clientes y pagos en formato CSV
- Persistencia de datos entre ejecuciones

## Flujo de la Aplicación

1. Al iniciar, carga los datos desde los archivos CSV
2. Inicializa los contadores de IDs basado en los datos existentes
3. Muestra un menú interactivo hasta que el usuario selecciona salir
4. Antes de salir, guarda los cambios en los archivos CSV

## Requisitos

- Java JDK 8 o superior