import java.util.*;

public class GestionPagos {
    public static void registrarPago(Scanner sc){
        int idCliente;

        if (Main.listaClientes.isEmpty())
            System.out.println("No existen clientes, primero tienes que dar uno de alta.");
        else {
            GestionClientes.listarClientes();

            do{
                System.out.println("Indica id de cliente: ");
                idCliente = sc.nextInt();
            } while(idCliente < 0);

            Iterator<Clientes> it = Main.listaClientes.iterator();
            boolean existe = false;
            Clientes c = new Clientes();

            while(it.hasNext() && !existe){
                c = it.next();
                if (c.getId() == idCliente)
                    existe = true;
            }

            if(existe){

                System.out.print("Indica fecha en formato DD/MM/YYYY o vacío para fecha hoy: ");
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
            } else
                System.out.println("No existe el cliente.");
        }
    }

    public static void consultarPagos(){
        System.out.println("Listando pagos.");
        
    }
}
