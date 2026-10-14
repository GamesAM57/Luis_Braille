package mansionzombie;

public class Main {

    public static void main(String[] args) {
        
        Jugador j = new Jugador();
        
        Habitacion.habitacionesMax = Menu.menuDificultades();
        
        while(Habitacion.habActual <= Habitacion.habitacionesMax && j.isVivo()){
                Habitacion h = new Habitacion();
                
                Menu.resumenJuego(Habitacion.habActual, j);
                
                GestorJuego.gestorMenu(j, h);
                
                
            if(j.isVivo())
                Habitacion.habActual  ++;

        }
        
        if (j.isVivo()){
            System.out.println("Has ganado");
        } else {
            System.out.println("Has sido derrotado. GAME OVER.");
        }
        
        
    }
}
