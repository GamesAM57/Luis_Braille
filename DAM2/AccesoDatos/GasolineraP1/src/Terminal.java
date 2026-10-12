import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.FormatFlagsConversionMismatchException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Terminal {

    private final Scanner sc = new Scanner(System.in);

    public int menu(){
        int op = 99;
        System.out.println("=== GESTION DE GASOLINERA ===");
        System.out.println("1. Dar de alta cliente");
        System.out.println("2. Listar clientes");
        System.out.println("3. Buscar clientes");
        System.out.println("4. Procesar un pago de repostaje");
        System.out.println("5. Consultar pagos");
        System.out.println("0. Salir");
        System.out.println("=============================");
        try{
            mostrar("Opcion: ");
            op = sc.nextInt();
        } catch (InputMismatchException e){
            mostrar("Tiene que ser un enetro.");
        }
        limpiarScanner();
        return op;
    }

    public String pedirString(String mensaje){
        System.out.print(mensaje);
        return sc.nextLine();
    }

    public String pedirStringObligatorio(String mensaje){
        String dato = pedirString(mensaje);
        while(dato == null || dato.isBlank()){
            System.out.println("Has indicado un texto vacío, indicalo otra vez:");
            dato = pedirString(mensaje);
        }
        return dato;
    }

    public int pedirEntero(String mensaje){
        boolean entero = false;
        int n = 0;

        while (!entero){
            try {
                n = Integer.parseInt(pedirString(mensaje));
                entero = true;
            } catch (NumberFormatException e) {
               mostrar("Tiene que ser un entero.");
            }
        }
        return n;
    }

    public double pedirDouble(String mensaje){
        boolean decimal = false;

        double n = -1;

        while(n<0 || !comprobarDecimalesCorrecto(n) || !decimal){
            try {
                n = Double.parseDouble(pedirString(mensaje).replace(',', '.'));
                decimal=true;
            } catch (InputMismatchException e){
                mostrar("Tiene que ser un decimal.");
            }
            if(!comprobarDecimalesCorrecto(n))
                n = Double.parseDouble(pedirString("Maximo dos decimales.\n"+mensaje));
            else
                n = Double.parseDouble(pedirString("Numero no puede ser negativo.\n"+mensaje));
        }
        return n;
    }

    public String pedirFecha(String mensaje){
        String fecha = pedirString(mensaje);
        DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        boolean fechaCorrecta = false;

        if(fecha == null || fecha.isBlank()){
            fecha = LocalDate.now().format(FORMATO);
        } else {
            while(!fechaCorrecta){
                try {
                    LocalDate.parse(fecha, FORMATO);
                    fechaCorrecta = true;
                } catch (DateTimeParseException e) {
                    fecha = pedirStringObligatorio("Error formato fecha. "+mensaje);
                }
            }
        }
        return fecha;
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

    public boolean comprobarDecimalesCorrecto(double n){
        return Math.round(n * 100) / 100.0 == n;
    }
}
