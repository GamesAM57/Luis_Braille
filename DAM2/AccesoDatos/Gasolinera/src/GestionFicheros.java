import java.util.ArrayList;

public class GestionFicheros {
    public static ArrayList<Clientes> getClientes(){
        System.out.println("Obteniendo lista de clientes.");

        ArrayList<Clientes> listaClientes = new ArrayList<Clientes>();
        return listaClientes;
    }

    public static void setClientes(ArrayList<Clientes> listaClientes){
        System.out.println("Actualizando clientes en fichero.");
    }

    public static ArrayList<Pagos> getPagos(){
        System.out.println("Obteniendo lista de pagos.");

        ArrayList<Pagos> listaPagos = new ArrayList<Pagos>();
        return listaPagos;
    }

    public static void setPagos(ArrayList<Pagos> listaPagos){
        System.out.println("Actualizando pagos en fichero.");
    }
}
