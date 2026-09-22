import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class GestionClientes {
    static Scanner sc = new Scanner(System.in);
    static GestionFicherosCSV gFich = new GestionFicherosCSV();

    public static void crearCliente(){
        System.out.println("Creación cliente.");

        System.out.print("Introduce el Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Introduce el teléfono: ");
        String telefono = sc.nextLine();
        System.out.print("Introduce la matrícula: ");
        String matricula = sc.nextLine();

        if (buscarClientes(matricula).isEmpty()){
            Clientes c = new Clientes(Clientes.siguienteIdCliente, nombre, telefono, matricula);
            System.out.println("Cliente creado con id: " + c.getId());
            Clientes.siguienteIdCliente += 1;
            gFich.setClientes(c);
        } else {
            System.out.println("El cliente no se ha podido crear.");
        }
    }

    public static void listarClientes(){
        Collection<Clientes> listaClientes = gFich.getClientes();
        if(listaClientes.isEmpty())
            System.out.println("Lista vacía");
        else {
            System.out.println("ID\tNOMBRE\tTELEFONO\tMATRICULA");
            for (Clientes c : listaClientes){
                System.out.println(c.toString());
            }
        }


    }

    public static Collection<Clientes> buscarClientes(String palabra){ //Hacer que devuelva lista de correlaciones, otro metodo mostrará el cliente

        System.out.println("Búsqueda de palabra.");

        return new ArrayList<>();
    }
}
