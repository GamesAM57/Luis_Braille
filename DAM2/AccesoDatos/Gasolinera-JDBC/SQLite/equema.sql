-- Ejecutar en una base de datos nueva. Activar las FK en CADA conexión.
PRAGMA foreign_keys = ON;

CREATE TABLE IF NOT EXISTS clientes (
    id INTEGER PRIMARY KEY NOT NULL CHECK(id > 0),
    nombre TEXT NOT NULL CHECK (length(trim(nombre))>0),
    telefono TEXT NOT NULL CHECK (length(trim(telefono))>0),
    matricula TEXT UNIQUE NOT NULL CHECK (length(trim(matricula))>0)
);

CREATE TABLE IF NOT EXISTS pagos(
    id INTEGER PRIMARY KEY NOT NULL CHECK(id > 0),
    idCliente INTEGER CHECK(idCliente > 0),
    fechaRepostaje TEXT NOT NULL CHECK (length(trim(fechaRepostaje))>0),
    importe REAL NOT NULL CHECK(importe > 0),
    litros REAL NOT NULL CHECK(litros > 0),
    combustible TEXT NOT NULL CHECK (length(trim(combustible))>0),
    FOREIGN KEY (idCliente) REFERENCES clientes(id)
);