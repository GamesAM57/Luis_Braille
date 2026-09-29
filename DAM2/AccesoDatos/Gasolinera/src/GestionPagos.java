import java.util.*;

public class GestionPagos {
    public static void registrarPago(Scanner sc, LinkedList<Clientes> listaClientes, LinkedList<Pagos> listaPagos){
        int idCliente;

        if (listaClientes.isEmpty())
            System.out.println("No existen clientes, primero tienes que dar uno de alta.");
        else {
            GestionClientes.listarClientes(listaClientes);

            do{
                System.out.println("Indica id de cliente: ");
                idCliente = sc.nextInt();
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

                System.out.print("Indica fecha en formato DD/MM/YYYY o vacío para fecha hoy: ");
                sc.nextLine();
                String fecha = sc.nextLine();
                System.out.print("Indica el importe a repostar: ");
                double importe =sc.nextDouble();
                sc.nextLine();
                System.out.print("Indica los litros a repostar: ");
                double litros =sc.nextDouble();
                sc.nextLine();
                System.out.print("Indica el combustible utilizado: ");
                String combustible =sc.nextLine();

                Pagos p = new Pagos(Pagos.siguienteIdPago, idCliente, fecha, importe, litros, combustible);
                System.out.println("Pago "+p.getId()+" registrado para "+c.getNombre()+": "+p.getImporte()+" euros.");
                Pagos.siguienteIdPago += 1;
                listaPagos.add(p);
            } else
                System.out.println("No existe el cliente.");
        }
    }

    public static void consultarPagos(LinkedList<Pagos> listaPagos, LinkedList<Clientes> listaClientes){
        if(listaPagos.isEmpty()){
            System.out.println("No existen pagos registrados.");
        } else {
            System.out.println("ID\tCLIENTE\tFECHA\tIMPORTE\tLITROS\tCOMBUSTIBLE");
            Collections.sort(listaPagos);
            for(Pagos p : listaPagos){

                Clientes c = GestionClientes.obtenerClientePorId(p.getIdCliente(), listaClientes);
                if(c.getId() != 0) {
                    String fechaFormateada = Pagos.FORMAT.format(p.getFechaRepostaje());
                    System.out.println(p.getId() + "\t" + c.getNombre() + "\t" + fechaFormateada + "\t" + p.getImporte() + " €\t" + p.getLitros() + "\t" + p.getCombusitble());
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
        }

        return id;
    }
}
