package mansionzombie;

public class Main {

    public static void main(String[] args) {
        
        Jugador j = new Jugador();
        
        int habitaciones = Menu.menuDificultades();
        
        while(Habitacion.habActual <= habitaciones && j.getPuntosAtaque() > 0){
                Habitacion h = new Habitacion();
                
                resumenJuego(i, j);
            
                
            
            
            
            
            Habitacion.habActual  ++;
        }
        
        if (j.getVidasActuales() > 0){
            System.out.println("Has ganado");
        } else {
            System.out.println("Has sido derrotado. GAME OVER.");
        }
        
        
    }
    
    static void resumenJuego(int numHab, Jugador j){
        System.out.println("=================================================");
        System.out.println("Has entrado en la habitación: "+numHab+".");
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
