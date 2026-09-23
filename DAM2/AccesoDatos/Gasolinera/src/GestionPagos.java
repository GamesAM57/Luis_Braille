import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class GestionPagos {
    public static void registrarPago(Scanner sc){
        GestionClientes.listarClientes();
        boolean clienteExiste = false;
        int id = 0;
        while(!clienteExiste){
            System.out.print("ID del cliente: ");
            sc.nextLine();
        }


    }

    public static Collection<Pagos> consultarPagos(){
        System.out.println("Listando pagos.");


        return new ArrayList<Pagos>();
    }
}
