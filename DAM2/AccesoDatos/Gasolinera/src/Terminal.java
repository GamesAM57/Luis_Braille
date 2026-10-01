import java.util.Scanner;

public class Terminal {

    private final Scanner sc = new Scanner(System.in);

    public int menu(){
        System.out.println("=== GESTION DE GASOLINERA ===");
        System.out.println("1. Dar de alta cliente");
        System.out.println("2. Listar clientes");
        System.out.println("3. Buscar clientes");
        System.out.println("4. Procesar un pago de repostaje");
        System.out.println("5. Consultar pagos");
        System.out.println("0. Salir");
        System.out.println("=============================");
        System.out.println("Indique una opción: ");
        return sc.nextInt();
    }

    public String pedirString(String mensaje){
        System.out.print(mensaje);
        return sc.nextLine();
    }

    public int pedirEntero(String mensaje){
        return Integer.parseInt(pedirString(mensaje));
    }

    public double pedirDouble(String mensaje){
        return Double.parseDouble(pedirString(mensaje));
    }

    public void limpiarScanner(){
        sc.nextLine();
    }

    public void cerrarScanner(){
        sc.close();
    }

    public void mostrar(String mensaje){
        System.out.println(mensaje);
    }
}
