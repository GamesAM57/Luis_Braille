package mansionzombie;

public class Main {

    public static void main(String[] args) {
        
        Jugador j = new Jugador();
               
        Habitacion.habitacionesMax = Menu.menuDificultades();
        
        while(Habitacion.habActual <= Habitacion.habitacionesMax && j.isVivo()){
            Habitacion h = new Habitacion();
                
            Menu.gestorMenu(j, h);
  
            if(j.isVivo())
                Habitacion.habActual  ++;
        }
        
        if (j.isVivo()){
            Terminal.mostrar("VICTORIA.");
        } else {
            Terminal.mostrar("Has sido derrotado. GAME OVER.");
        }
    }
}
