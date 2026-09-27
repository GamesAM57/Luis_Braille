import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Scanner;


public class Main {
    //Ver como separar entradas y salidas por consola.
    //Cambiar para que solo lea al principio y al final.
    static GestionFicherosCSV gestorFich = new GestionFicherosCSV();

    static Scanner sc = new Scanner(System.in);



    public static void main(String[] args) {

        LinkedList<Clientes> listaClientes = gestorFich.getClientes();
        LinkedList<Pagos> listaPagos = gestorFich.getPagos();

        int op = 99;

        do{
            op = menu();
            switch (op) {
                case 1 -> {
                    GestionClientes.crearCliente(sc, listaClientes);
                }
                case 2 -> {
                    GestionClientes.listarClientes(listaClientes);
                }
                case 3 -> {
                    System.out.print("Texto que buscar: ");
                    GestionClientes.listarClientes(GestionClientes.buscarClientes(sc.nextLine(),listaClientes));
                }
                case 4 -> {
                    GestionPagos.registrarPago( sc,  listaClientes, listaPagos);
                }
                case 5 -> {
                    GestionPagos.consultarPagos();
                }
                case 0 -> {
                    System.out.println("Has decidido salir.");
                }
                default -> {
                    System.out.println("No has seleccionado un número correcto.");
                    op = menu();
                }
            }
        } while (op != 0);

        System.out.println("Saliendo del programa...");
        gestorFich.setClientes(listaClientes);
        gestorFich.setPagos(listaPagos);
    }

    public static int menu(){
        System.out.println("=== GESTION DE GASOLINERA ===");
        System.out.println("1. Dar de alta cliente");
        System.out.println("2. Listar clientes");
        System.out.println("3. Buscar clientes");
        System.out.println("4. Procesar un pago de repostaje");
        System.out.println("5. Consultar pagos");
        System.out.println("0. Salir");
        System.out.println("=============================");
        System.out.printf("Indique una opción: ");
        return sc.nextInt();
    }


}