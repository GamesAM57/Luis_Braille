import java.util.*;

public class GestionClientes {

    public static void crearCliente(Scanner sc, LinkedList<Clientes> listaClientes){
        System.out.println("Creación cliente.");

        System.out.print("Introduce el Nombre: ");
        sc.nextLine();
        String nombre = sc.nextLine();
        System.out.print("Introduce el teléfono: ");
        String telefono = sc.nextLine();
        System.out.print("Introduce la matrícula: ");
        String matricula = sc.nextLine();

        if (buscarClientes(matricula, listaClientes).isEmpty()){
            Clientes c = new Clientes(Clientes.siguienteIdCliente, nombre, telefono, matricula);
            System.out.println("Cliente creado con id: " + c.getId());
            Clientes.siguienteIdCliente += 1;
            listaClientes.add(c);
        } else {
            System.out.println("El cliente no se ha podido crear.");
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

    public static LinkedList<Clientes> buscarClientes(String palabra, LinkedList<Clientes> listaClientes){ //Hacer que devuelva lista de correlaciones, otro metodo mostrará el cliente
        palabra = palabra.toLowerCase();

        LinkedList<Clientes> coincidencias = new LinkedList<>();

        for (Clientes c : listaClientes){
            if(c.getNombre().toLowerCase().contains(palabra) || c.getMatricula().toLowerCase().contains(palabra) || c.getTelefono().toLowerCase().contains(palabra))
                coincidencias.add(c);
        }

        return coincidencias;
    }

    public static Clientes obtenerClientePorId(int id, LinkedList<Clientes> listaClientes){
        Clientes encontrado = new Clientes();

        Iterator<Clientes> it = listaClientes.iterator();

        while(it.hasNext() && id != encontrado.getId()){
            Clientes c = it.next();
            if (c.getId() == id){
                encontrado = c;
            }

        }

        return encontrado;
    }

    public static int ultimoIdClientes(LinkedList<Clientes> listaClientes){
        int id = 1;

        if(!listaClientes.isEmpty()){
            for (Clientes c : listaClientes){
                id = Math.max(id, c.getId());
            }
        }

        return id+1;
    }
}
