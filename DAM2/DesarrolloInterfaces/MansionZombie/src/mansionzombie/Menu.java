package mansionzombie;

import java.util.LinkedList;
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
    
    static int mostrarMenu(Jugador j, Habitacion h){
        LinkedList<String> opciones = new LinkedList<>();
        
        if(j.isVivo() && h.hayZombies()){
            opciones.add("1. Combatir con zombie.");
        }
        if(j.isVivo() && !h.hayZombies() && h.getBusquedas() > 0){
            opciones.add("2. Buscar en la habitación.");
        }
        if(j.isVivo() && !h.hayZombies() && j.isBotiquin()){
            opciones.add("3. Curarse con botiquin disponible.");
        }
        if(j.isVivo() && !h.hayZombies() && h.getHabActual() < Habitacion.habitacionesMax){
            opciones.add("4. Siguiente habitación.");
        } else if (j.isVivo() && !h.hayZombies() && h.getHabActual() == Habitacion.habitacionesMax) {
            opciones.add("4. Salir de la mansión.");
        }
        
        for(String op : opciones){
            System.out.println(op);
        }
        
        return sc.nextInt();
    }
    
    static void resumenJuego(int numHab, Jugador j){
        System.out.println("=================================================");
        System.out.println("Has entrado en la habitación: "+numHab+" de "+Habitacion.habitacionesMax+".");
        System.out.println("Tienes "+j.getVidasActuales()+" vidas restantes de 20.");
        if(j.isBotiquin())
            System.out.println("Tienes un botiquín.");
        else
            System.out.println("No tienes botiquín");
        System.out.println("Tienes "+j.getArmas()+" armas.");
        System.out.println("Tienes "+j.getProtecciones()+" protecciones.");
        System.out.println("=================================================");
    }
}
