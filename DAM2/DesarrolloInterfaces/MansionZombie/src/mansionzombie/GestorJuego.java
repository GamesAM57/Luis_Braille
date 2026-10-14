package mansionzombie;

public class GestorJuego {
    static void gestorMenu(Jugador j, Habitacion h){
        
        boolean salir  = false;
        
        while(!salir && j.isVivo()){
            int op = Menu.mostrarMenu(j, h);
        
            if(op == 1 && j.isVivo() && h.hayZombies()){
                combatir(j, h);
            } else if(op == 2 && j.isVivo() && !h.hayZombies() && h.getBusquedas() > 0){
                System.out.println("Vas a buscar");
            } else if(op == 3 && j.isVivo() && !h.hayZombies() && j.isBotiquin()){
                System.out.println("Vas a curarte.");
            } else if(op == 4 && j.isVivo() && !h.hayZombies()){
                salir = true;
            } else {
                System.out.println("Has indicado una opción incorrecta.");
            }
        }
    }
    
    private static void combatir(Jugador j, Habitacion h){
        Zombie z = new Zombie(h.getHabActual());
        int ronda = 1;
        
        while(j.isVivo() && z.isVivo()){
            System.out.println("Ronda "+ronda+": ");
            System.out.println(" -Vidas jugador: "+j.getVidasActuales());
            System.out.println(" -Vidas zombie: "+z.getPuntosVida());
            
            //generar puntos ataque con fórmula y contando armas
            //hacer daño a zombie sin generar negativos.
            //mostrar resultado ataque
            
            if(z.isVivo()){
                //generar puntos ataque zombie con fórmula y contando protección jugador
                //hacer daño sin generar negativos
                //mostrar resultado ataque
            }
        }
    }
}
