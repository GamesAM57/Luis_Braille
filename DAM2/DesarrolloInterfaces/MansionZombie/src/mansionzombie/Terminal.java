package mansionzombie;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Terminal {
    private static Scanner sc = new Scanner(System.in);
    
    public static void mostrar(String mensaje){
        System.out.println(mensaje);
    }
    
    public static int pedirInt(){
        boolean valido = false;
        int num = 0;
        while(!valido){
            String cadena = sc.nextLine();
            try{
                num = Integer.parseInt(cadena);
                valido = true;
            } catch (InputMismatchException | NumberFormatException e){
                mostrar("No has indicado un numero entero.");
                mostrar("Indicalo otra vez:");
            }
        }  
        return num;
    }
}
