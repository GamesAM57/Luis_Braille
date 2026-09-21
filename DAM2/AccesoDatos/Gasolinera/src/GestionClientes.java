import java.util.ArrayList;
import java.util.Scanner;

public class GestionClientes {
    static Scanner sc = new Scanner(System.in);
    public static Clientes crearCliente(){
        System.out.println("Creación cliente.");
        Clientes c = null;
        return c;
    }

    public static void listarClientes(ArrayList<Clientes> listaClientes){
        System.out.println("Listado clientes.");
        //validar si está vacía antes.
        System.out.println("ID\tNOMBRE\tTELÉFONO\tMATRÍCULA");
        for (Clientes c : listaClientes) {
            System.out.println(c.toString());
        }
    }

    public static void buscarClientes(){
        System.out.println("Introduce palabra a buscar: ");
        String palabra = sc.nextLine();

        System.out.println("Búsqueda de palabra.");
    }
}
