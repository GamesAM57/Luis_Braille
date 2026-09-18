package mansionzombie;

import java.util.Scanner;

public class Menu {
    
    static Scanner sc = new Scanner(System.in);
    
    public static int menuDificultades(){
        int num = 0;
        
        do {
            System.out.println("Elige el número de la dificultad:");
            System.out.println("\t1. Fácil(5 habitaciones).");
            System.out.println("\t2. Dificil(10 habitaciones).");
            num = sc.nextInt();
        } while (num < 1 || num > 2);
        
        
        return (num == 1) ? 5 : 10;
    }
    
    public static void resumenJuego(int numHab){
        System.out.println("Has entrado en la habitación: "+numHab+".");
        
    }
}
