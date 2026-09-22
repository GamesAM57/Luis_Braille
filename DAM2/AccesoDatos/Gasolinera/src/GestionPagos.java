import java.util.ArrayList;

public class GestionPagos {
    public static Pagos registrarPago(){
        System.out.println("Creación de pago.");

        return null;
    }

    public static void consultarPagos(ArrayList<Pagos> listaPagos){
        System.out.println("Listando pagos.");
        //Validar si está vacía la lista.
        //Sacar cabecera y mirar lo del nombre cliente.
        if(listaPagos.isEmpty())
            System.out.println("La lista es vacía.");
        else {
            for(Pagos p : listaPagos){
                System.out.println(p.toString());
            }
        }

    }
}
