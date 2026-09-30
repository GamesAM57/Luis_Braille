import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        try{
            GestionFicherosCSV gestorFich = new GestionFicherosCSV();
            LinkedList<Clientes> listaClientes = gestorFich.getClientes();
            LinkedList<Pagos> listaPagos = gestorFich.getPagos();

            Clientes.siguienteIdCliente = GestionClientes.ultimoIdClientes(listaClientes);
            Pagos.siguienteIdPago = GestionPagos.ultimoIdPagos(listaPagos);

            int op;

            do{
                op = Terminal.menu();;
                switch (op) {
                    case 1 -> {
                        GestionClientes.crearCliente(Terminal.sc, listaClientes);
                    }
                    case 2 -> {
                        GestionClientes.listarClientes(listaClientes);
                    }
                    case 3 -> {
                        System.out.print("Texto que buscar: ");
                        Terminal.limpiarScanner();
                        GestionClientes.listarClientes(GestionClientes.buscarClientes(sc.nextLine(),listaClientes));
                    }
                    case 4 -> {
                        GestionPagos.registrarPago( Terminal.sc,  listaClientes, listaPagos);
                    }
                    case 5 -> {
                        GestionPagos.consultarPagos(listaPagos, listaClientes);
                    }
                    case 0 -> {
                        System.out.println("Has decidido salir.");
                    }
                    default -> {
                        System.out.println("No has seleccionado un número correcto.");
                        op = Terminal.menu();
                    }
                }
            } while (op != 0);

            System.out.println("Saliendo del programa...");
            gestorFich.setClientes(listaClientes);
            gestorFich.setPagos(listaPagos);
            Terminal.cerrarScanner();
        } catch (IOException e){
            System.out.println("Error al crear o acceder a ficheros, comprueba permisos. "+e.getMessage());
        }
    }
}