import java.io.IOException;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        Terminal terminal = new Terminal();
        try{
            MigraCSVToJSON.menuMigracion(terminal);

            GestionFicheros fich = new GestionFicherosJSON(terminal);
            Clientes.siguienteIdCliente = GestionClientes.ultimoIdClientes(fich.getClientes());
            Pagos.siguienteIdPago = GestionPagos.ultimoIdPagos(fich.getPagos());

            int op;

            do{
                op = terminal.menu();
                switch (op) {
                    case 1 -> {
                        GestionClientes.crearCliente(terminal, fich.getClientes(),  fich);
                    }
                    case 2 -> {
                        GestionClientes.listarClientes(terminal, fich.getClientes());
                    }
                    case 3 -> {
                        GestionClientes.buscarClientes(terminal,fich.getClientes());
                    }
                    case 4 -> {
                        GestionPagos.registrarPago( terminal,  fich.getClientes(), fich);
                    }
                    case 5 -> {
                        GestionPagos.consultarPagos(terminal, fich.getPagos(), fich.getClientes());
                    }
                    case 0 -> {
                        terminal.mostrar("Hasta pronto!!");
                    }
                    default -> {
                        terminal.mostrar("No has seleccionado un número correcto.");
                    }
                }
            } while (op != 0);

            terminal.cerrarScanner();
        } catch (IOException e){
            terminal.mostrar("Error al crear o acceder a ficheros, comprueba permisos. "+e.getMessage());
        }
    }
}