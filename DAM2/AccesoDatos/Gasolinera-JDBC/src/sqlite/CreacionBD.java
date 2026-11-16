package sqlite;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;

public class CreacionBD {
    public static void main(String[] args) {
        Path sqlite;

        try{
            sqlite = Path.of(args[0]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("No se ha pasado la ruta al fichero a través de argumento.");
            System.out.println("Se va a asignar una ruta por defecto.");
            sqlite = Path.of("gasolinera.sqlite");
        }

        if(Files.notExists(sqlite)){
            System.out.println("El fichero no existe o no se llama "+sqlite.toAbsolutePath());
            return;
        }

        String urlSQLite = "jdbc:sqlite:"+sqlite.toAbsolutePath();

        try(Connection con = DriverManager.getConnection(urlSQLite)){
            try (Statement comando = con.createStatement()){
                comando.execute("PRAGMA FOREIGN_KEYS = ON");
                String crearTablaClientes = """
                            CREATE TABLE IF NOT EXISTS clientes (
                                id INTEGER PRIMARY KEY NOT NULL CHECK(id > 0),
                                nombre TEXT NOT NULL CHECK (length(trim(nombre))>0),
                                telefono TEXT NOT NULL CHECK (length(trim(telefono))>0),
                                matricula TEXT UNIQUE NOT NULL CHECK (length(trim(matricula))>0)
                            )
                        """;
                comando.execute(crearTablaClientes);
                String crearTablaPagos = """
                        CREATE TABLE IF NOT EXISTS pagos(
                            id INTEGER PRIMARY KEY NOT NULL CHECK(id > 0),
                            idCliente INTEGER CHECK(idCliente > 0),
                            fechaRepostaje TEXT NOT NULL CHECK (length(trim(fechaRepostaje))>0),
                            importe REAL NOT NULL CHECK(importe > 0),
                            litros REAL NOT NULL CHECK(litros > 0),
                            combustible TEXT NOT NULL CHECK (length(trim(combustible))>0),
                            FOREIGN KEY (idCliente) REFERENCES clientes(id)
                        )
                        """;
                comando.execute(crearTablaPagos);

                DatabaseMetaData metaDatos = con.getMetaData();

                try(ResultSet rs = metaDatos.getTables(null, null, "clientes", null)){
                    System.out.println("Existe tabla clientes: " +rs.next() );
                }

                try(ResultSet rs = metaDatos.getTables(null, null, "pagos", null)){
                    System.out.println("Existe tabla pagos: " +rs.next() );
                }

            }
            System.out.println("Conexión abierta con: "+sqlite.toAbsolutePath());
            System.out.println("Estado conexión: "+con.isValid(3));
        } catch (SQLException e) {
            System.out.println("Error al establecer conexión con "+urlSQLite+" "+e.getMessage());
        }
    }
}
