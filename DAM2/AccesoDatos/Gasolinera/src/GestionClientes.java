import java.util.*;

public class GestionClientes {

    public static void crearCliente(Terminal terminal, LinkedList<Clientes> listaClientes){

        String nombre    = terminal.pedirString("Nombre: ");
        String telefono  = terminal.pedirString("Teléfono: ");
        String matricula = terminal.pedirString("Matrícula: ").toUpperCase();

        if (buscarClientes(matricula, listaClientes).isEmpty()){
            Clientes c = new Clientes(Clientes.siguienteIdCliente, nombre, telefono, matricula,terminal);
            terminal.mostrar("Cliente creado con id: " + c.getId());
            Clientes.siguienteIdCliente += 1;
            listaClientes.add(c);
        } else {
            terminal.mostrar("Ya existe un cliente con esa matricula.");
        }
    }

    public static void listarClientes(Terminal terminal, LinkedList<Clientes> listaClientes){

        if(listaClientes.isEmpty())
            terminal.mostrar("No hay clientes creados.");
        else {
            Collections.sort(listaClientes);
            terminal.mostrar("ID\tNOMBRE\tTELEFONO\tMATRICULA");
            for (Clientes c : listaClientes){
                terminal.mostrar(c.toString());
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
