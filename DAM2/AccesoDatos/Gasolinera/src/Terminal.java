import java.util.Scanner;

public class Terminal {

    public static Scanner sc = new Scanner(System.in);

    public static int menu(){
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

    public static String pedirString(String op){
        String dato = "";
        switch (op){
            case "nombre" -> {
                System.out.println("Introduce tu nombre.");
                limpiarScanner();
                dato = sc.nextLine();
            }
            case "telefono" -> {
                System.out.println("Introduce tu telefono.");
                limpiarScanner();
                dato = sc.nextLine();
            }

        }

        return dato;
    };

    public static void limpiarScanner(){
        sc.nextLine();
    }

    public static void cerrarScanner(){
        sc.close();
    }
}
