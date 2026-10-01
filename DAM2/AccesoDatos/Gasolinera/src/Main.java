import java.io.IOException;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        Terminal terminal = new Terminal();
        try{
            GestionFicherosCSV gestorFich = new GestionFicherosCSV(terminal);
            LinkedList<Clientes> listaClientes = gestorFich.getClientes();
            LinkedList<Pagos> listaPagos = gestorFich.getPagos();

            Clientes.siguienteIdCliente = GestionClientes.ultimoIdClientes(listaClientes);
            Pagos.siguienteIdPago = GestionPagos.ultimoIdPagos(listaPagos);

            int op;

            do{
                op = terminal.menu();
                terminal.limpiarScanner();
                switch (op) {
                    case 1 -> {
                        GestionClientes.crearCliente(terminal, listaClientes);
                    }
                    case 2 -> {
                        GestionClientes.listarClientes(terminal, listaClientes);
                    }
                    case 3 -> {
                        String palabra= terminal.pedirString("Texto que buscar: ");
                        GestionClientes.listarClientes(terminal, GestionClientes.buscarClientes(palabra,listaClientes));
                    }
                    case 4 -> {
                        GestionPagos.registrarPago( terminal,  listaClientes, listaPagos);
                    }
                    case 5 -> {
                        GestionPagos.consultarPagos(terminal, listaPagos, listaClientes);
                    }
                    case 0 -> {
                        terminal.mostrar("Hasta pronto!!");
                    }
                    default -> {
                        terminal.mostrar("No has seleccionado un número correcto.");
                    }
                }
            } while (op != 0);

            gestorFich.setClientes(listaClientes);
            gestorFich.setPagos(listaPagos);
            terminal.cerrarScanner();
        } catch (IOException e){
            terminal.mostrar("Error al crear o acceder a ficheros, comprueba permisos. "+e.getMessage());
        }
    }
}