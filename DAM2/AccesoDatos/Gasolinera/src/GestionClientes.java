import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class GestionClientes {
    static Scanner sc = new Scanner(System.in);
    public static Clientes crearCliente(){
        System.out.println("Creación cliente.");
        Clientes c = null;
        return c;
    }

    public static void listarClientes(){
        System.out.println("Listado clientes.");


        if (listaClientes.isEmpty())
            System.out.println("La lista está vacía de clientes.");
        else {
            System.out.println("ID\tNOMBRE\tTELÉFONO\tMATRÍCULA");
            for (Clientes c : listaClientes) {
                System.out.println(c.toString());
            }
        }

    }

    public static void buscarClientes(ArrayList<Clientes> listaClientes){ //Hacer que devuelva lista de correlaciones, otro metodo mostrará el cliente
        System.out.println("Introduce palabra a buscar: ");
        String palabra = sc.nextLine();

        System.out.println("Búsqueda de palabra.");
    }
}
