import java.util.*;

public class GestionPagos {
    public static void registrarPago(Terminal terminal, LinkedList<Clientes> listaClientes, LinkedList<Pagos> listaPagos, GestionFicherosJSON json){
        int idCliente;

        if (listaClientes.isEmpty())
            terminal.mostrar("No existen clientes, primero tienes que dar uno de alta.");
        else {
            GestionClientes.listarClientes(terminal, listaClientes);

            do{
                idCliente = terminal.pedirEntero("Indica id de cliente: ");
            } while(idCliente < 0);

            Iterator<Clientes> it = listaClientes.iterator();
            boolean existe = false;
            Clientes c = new Clientes();

            while(it.hasNext() && !existe){
                c = it.next();
                if (c.getId() == idCliente)
                    existe = true;
            }

            if(existe){
                String fecha = terminal.pedirFecha("Indica fecha en formato DD/MM/YYYY o vacío para fecha hoy: ");
                double importe = terminal.pedirDouble("Indica el importe a repostar: ");
                double litros = terminal.pedirDouble("Indica los litros a repostar: ");
                String combustible = terminal.pedirStringObligatorio("Indica el combustible utilizado: ");

                Pagos p = new Pagos(Pagos.siguienteIdPago, idCliente, fecha, importe, litros, combustible, terminal);
                json.setPagos(p);
                terminal.mostrar("Pago "+p.getId()+" registrado para "+c.getNombre()+": "+p.getImporte()+" euros.");
                Pagos.siguienteIdPago += 1;
            } else
                terminal.mostrar("No existe el cliente.");
        }
    }

    public static void consultarPagos(Terminal terminal, LinkedList<Pagos> listaPagos, LinkedList<Clientes> listaClientes){
        if(listaPagos.isEmpty()){
            terminal.mostrar("No existen pagos registrados.");
        } else {
            terminal.mostrar("ID\tCLIENTE\tFECHA\tIMPORTE\tLITROS\tCOMBUSTIBLE");
            Collections.sort(listaPagos);
            for(Pagos p : listaPagos){
                Clientes c = GestionClientes.obtenerClientePorId(p.getIdCliente(), listaClientes);
                if(c.getId() != 0) {
                    terminal.mostrar(p.getId() + "\t" + c.getNombre() + "\t" + p.getFechaRepostaje() + "\t" + p.getImporte() + " €\t" + p.getLitros() + "\t" + p.getCombusitble());
                }
            }
        }
    }

    public static int ultimoIdPagos(LinkedList<Pagos> listaPagos){
        int id = 1;

        if(!listaPagos.isEmpty()){
            for (Pagos p : listaPagos){
                id = Math.max(id, p.getId());
            }
            id += 1;
        }
        return id;
    }
}
