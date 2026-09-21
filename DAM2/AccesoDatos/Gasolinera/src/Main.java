import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        ArrayList<Clientes> listaClientes = GestionFicheros.getClientes();
        ArrayList<Pagos> listaPagos = GestionFicheros.getPagos();

        int op = 99;

        do{
            op = menu();
            switch (op) {
                case 1 -> {
                    listaClientes.add(GestionClientes.crearCliente());
                }
                case 2 -> {
                    GestionClientes.listarClientes(listaClientes);
                }
                case 3 -> {
                    GestionClientes.buscarClientes();
                }
                case 4 -> {
                    listaPagos.add(GestionPagos.registrarPago());
                }
                case 5 -> {
                    GestionPagos.consultarPagos(listaPagos);
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
        GestionFicheros.setClientes(listaClientes);
        GestionFicheros.setPagos(listaPagos);
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