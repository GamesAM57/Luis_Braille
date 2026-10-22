package mansionzombie;

import java.util.LinkedList;

public class Menu {
    
    public static int menuDificultades(){
        int num = 0;
        
        do {
            Terminal.mostrar("""
                             Elige el número de la dificultad:
                             \t1. Fácil(5 habitaciones).
                             \t2. Dificil(10 habitaciones).
                             """);
            num = Terminal.pedirInt();
        } while (num < 1 || num > 2);
        
        
        return (num == 1) ? 5 : 10;
    }
    
    static void gestorMenu(Jugador j, Habitacion h){
        
        boolean salir = false;
        
        while(!salir){ 
            if(j.isVivo()){
                resumenJuego(h, j);
                int op = mostrarMenu(j, h);
                if(op == 1 && j.isVivo() && h.hayZombies()){
                    Terminal.mostrar("Iniciando combate ...");
                    GestorJuego.combatir(j, h);
                    if(j.isVivo())
                        h.setContZombie(h.getContZombie()-1);
                } else if (op == 2 && j.isVivo() && !h.hayZombies() && h.getBusquedas() > 0){
                    Terminal.mostrar("Buscando en la habitacion...");
                    GestorJuego.buscar(h, j);
                    h.setBusquedas(h.getBusquedas()-1);
                } else if (op == 3 && j.isVivo() && !h.hayZombies() && j.isBotiquin()){
                    Terminal.mostrar("Curando...");
                    j.curar();
                } else if(op == 4 && j.isVivo() && !h.hayZombies()){
                    salir = true;
                } else if (j.isVivo()) {
                    Terminal.mostrar("Has indicado una opción inválida.");
                }
            } else {
                salir = true;
            }
            
        }
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
            Terminal.mostrar(op);
        }
        
        return Terminal.pedirInt();
    }
    
    static void resumenJuego(Habitacion h, Jugador j){
        Terminal.mostrar("================================================="
                + "\nHabitación: "+h.getHabActual()+" de "+Habitacion.habitacionesMax+" tiene "+h.getContZombie()+" zombie(s)."
                + "\nTienes "+j.getPuntosVida()+" vidas restantes de 20.");
        
        if(j.isBotiquin())
            Terminal.mostrar("Tienes un botiquín.");
        else
            Terminal.mostrar("No tienes botiquín");
        
        Terminal.mostrar("Tienes "+j.getArmas()+" armas."
                + "\nTienes "+j.getProtecciones()+" protecciones."
                        + "\n================================================="
                        + "\nElije entre las siguientes opciones:");
    }
}
