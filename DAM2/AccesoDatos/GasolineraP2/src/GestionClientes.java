import java.util.*;

public class GestionClientes {


    public static void crearCliente(Terminal terminal, LinkedList<Clientes> listaClientes, GestionFicheros fich){


        String nombre    = terminal.pedirStringObligatorio("Nombre: ");
        String telefono  = terminal.pedirStringObligatorio("Teléfono: ");
        String matricula = terminal.pedirStringObligatorio("Matrícula: ").toUpperCase();

        if (!buscarMatricula(matricula, listaClientes)){
            Clientes c = new Clientes(Clientes.siguienteIdCliente, nombre, telefono, matricula);
            fich.setUnCliente(c);
            terminal.mostrar("Cliente creado con id: " + c.getId());
            Clientes.siguienteIdCliente += 1;
        } else {
            terminal.mostrar("Ya existe un cliente con esa matricula.");
        }
    }

    public static void listarClientes(Terminal terminal, LinkedList<Clientes> listaClientes){

        if(listaClientes.isEmpty())
            terminal.mostrar("No hay clientes en la lista.");
        else {
            Collections.sort(listaClientes);
            terminal.mostrar("ID\tNOMBRE\tTELEFONO\tMATRICULA");
            for (Clientes c : listaClientes){
                terminal.mostrar(c.toString());
            }
        }
    }

    public static void buscarClientes(Terminal terminal, LinkedList<Clientes> listaClientes){
        String palabra = terminal.pedirStringObligatorio("Indica palabra a buscar: ").toLowerCase();

        LinkedList<Clientes> coincidencias = new LinkedList<>();

        for (Clientes c : listaClientes){
            if(c.getNombre().toLowerCase().contains(palabra) || c.getMatricula().toLowerCase().contains(palabra) || c.getTelefono().toLowerCase().contains(palabra))
                coincidencias.add(c);
        }

        listarClientes(terminal, coincidencias);
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
            id += 1;
        }

        return id;
    }

    public static boolean buscarMatricula(String matricula, LinkedList<Clientes> listaClientes){
        boolean localizado = false;

        for (Clientes c : listaClientes){
            localizado = matricula.equalsIgnoreCase(c.getMatricula());
        }

        return localizado;
    }
}
