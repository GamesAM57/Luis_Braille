package sqlite;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class HealthCheckSQLite {
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
            }
            System.out.println("Conexión abierta con: "+sqlite.toAbsolutePath());
            System.out.println("Estado conexión: "+con.isValid(3));
        } catch (SQLException e) {
            System.out.println("Error al establecer conexión con "+urlSQLite+" "+e.getMessage());
        }


    }
}
