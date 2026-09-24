import java.util.*;

public class GestionClientes {
    static GestionFicherosCSV gFich = new GestionFicherosCSV();

    public static void crearCliente(Scanner sc){
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
            Main.listaClientes.add(c);
        } else {
            System.out.println("El cliente no se ha podido crear.");
        }
    }

    public static void listarClientes(){

        if(Main.listaClientes.isEmpty())
            System.out.println("Lista vacía");
        else {
            Collections.sort(Main.listaClientes);
            System.out.println("ID\tNOMBRE\tTELEFONO\tMATRICULA");
            for (Clientes c : Main.listaClientes){
                System.out.println(c.toString());
            }
        }


    }

    public static void listarClientes(LinkedList<Clientes> listaClientes){

        if(listaClientes.isEmpty())
            System.out.println("Lista vacía");
        else {
            Collections.sort(listaClientes);
            System.out.println("ID\tNOMBRE\tTELEFONO\tMATRICULA");
            for (Clientes c : listaClientes){
                System.out.println(c.toString());
            }
        }


    }

    public static LinkedList<Clientes> buscarClientes(String palabra){ //Hacer que devuelva lista de correlaciones, otro metodo mostrará el cliente
        palabra = palabra.toLowerCase();

        LinkedList<Clientes> coincidencias = new LinkedList<>();

        for (Clientes c : Main.listaClientes){
            if(c.getNombre().toLowerCase().contains(palabra) || c.getMatricula().toLowerCase().contains(palabra) || c.getTelefono().toLowerCase().contains(palabra))
                coincidencias.add(c);
        }

        return coincidencias;
    }
}
