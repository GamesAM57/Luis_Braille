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
            gFich.setClientes(c);
        } else {
            System.out.println("El cliente no se ha podido crear.");
        }
    }

    public static void listarClientes(){
        List<Clientes> listaClientes = gFich.getClientes();
        if(listaClientes.isEmpty())
            System.out.println("Lista vacía");
        else {
            listaClientes.sort(
                    Comparator.comparing((Clientes c) -> c.getNombre().toLowerCase()).thenComparing(c -> c.getId())
            );
            System.out.println("ID\tNOMBRE\tTELEFONO\tMATRICULA");
            for (Clientes c : listaClientes){
                System.out.println(c.toString());
            }
        }


    }

    public static Collection<Clientes> buscarClientes(String palabra){ //Hacer que devuelva lista de correlaciones, otro metodo mostrará el cliente

        Collection<Clientes> listaClientes = gFich.getClientes();
        Collection<Clientes> coincidencias = new ArrayList<>();

        for (Clientes c : listaClientes){
            if(c.getNombre().contains(palabra) || c.getMatricula().contains(palabra) || c.getTelefono().contains(palabra))
                coincidencias.add(c);
        }

        return coincidencias;
    }
}
