PRAGMA foreign_keys = ON;

BEGIN TRANSACTION;

INSERT INTO clientes (id, nombre, telefono, matricula) VALUES
    (1, 'Carlos Martínez López', '600123456', '1234BCD'),
    (2, 'Laura Sánchez Gil', '611234567', '5678FGH'),
    (3, 'Javier Ruiz Moreno', '622345678', '9012JKL'),
    (4, 'Marta Fernández Díaz', '633456789', '3456MNP'),
    (5, 'Pablo Navarro Vega', '644567890', '7890RST'),
    (6, 'Elena Torres Ramos', '655678901', '2468VWX'),
    (7, 'Sergio Romero Cano', '666789012', '1357HZB'),
    (8, 'Ana Molina Prieto', '677890123', '8642LGT'),
    (9, 'Diego Ortega Blanco', '688901234', '4680DKP');

INSERT INTO pagos (id, idCliente, fechaRepostaje, importe, litros, combustible) VALUES
    (1, 1, '02/10/2026', 60.80, 40.0, 'Gasolina 95'),
    (2, 1, '16/10/2026', 53.96, 35.5, 'Gasolina 95'),
    (3, 1, '01/11/2026', 64.30, 42.3, 'Gasolina 95'),
    (4, 2, '05/10/2026', 55.39, 38.2, 'Diésel'),
    (5, 2, '21/10/2026', 59.45, 41.0, 'Diésel'),
    (6, 3, '08/10/2026', 50.40, 30.0, 'Gasolina 98'),
    (7, 3, '03/11/2026', 48.22, 28.7, 'Gasolina 98'),
    (8, 4, '10/10/2026', 65.25, 45.0, 'Diésel'),
    (9, 4, '25/10/2026', 57.42, 39.6, 'Diésel'),
    (10, 4, '08/11/2026', 63.95, 44.1, 'Diésel'),
    (11, 5, '12/10/2026', 50.77, 33.4, 'Gasolina 95'),
    (12, 5, '05/11/2026', 54.72, 36.0, 'Gasolina 95'),
    (13, 6, '14/10/2026', 39.22, 25.8, 'Gasolina 95'),
    (14, 6, '30/10/2026', 47.42, 31.2, 'Gasolina 95'),
    (15, 7, '18/10/2026', 79.00, 50.0, 'Diésel+'),
    (16, 7, '10/11/2026', 75.05, 47.5, 'Diésel+'),
    (17, 8, '20/10/2026', 37.63, 22.4, 'Gasolina 98'),
    (18, 8, '12/11/2026', 46.87, 27.9, 'Gasolina 98');

COMMIT;