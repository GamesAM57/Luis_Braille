import java.io.IOException;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        Terminal terminal = new Terminal();
        try{
            MigraCSVToJSON.menuMigracion(terminal);

            GestionFicherosJSON json = new GestionFicherosJSON(terminal);


            int op;

            do{
                op = terminal.menu();
                switch (op) {
                    case 1 -> {
                        GestionClientes.crearCliente(terminal, json.getClientes());
                    }
                    case 2 -> {
                        GestionClientes.listarClientes(terminal, json.getClientes());
                    }
                    case 3 -> {
                        GestionClientes.buscarClientes(terminal,json.getClientes());
                    }
                    case 4 -> {
                        GestionPagos.registrarPago( terminal,  json.getClientes(), json.getPagos());
                    }
                    case 5 -> {
                        GestionPagos.consultarPagos(terminal, json.getPagos(), json.getClientes());
                    }
                    case 0 -> {
                        terminal.mostrar("Hasta pronto!!");
                    }
                    default -> {
                        terminal.mostrar("No has seleccionado un número correcto.");
                    }
                }
            } while (op != 0);

            json.cerrarFichClientes();
            json.cerrarFichPagos();
            terminal.cerrarScanner();
        } catch (IOException e){
            terminal.mostrar("Error al crear o acceder a ficheros, comprueba permisos. "+e.getMessage());
        }
    }
}