package mansionzombie;

public class GestorJuego {
    
    public static void combatir(Jugador j, Habitacion h){
        Zombie z = new Zombie(h.getHabActual());
        int ronda = 1;
        
        int tiradaJugador;
        int tiradaZombie;
        
        while(j.isVivo() && z.isVivo()){
            Terminal.mostrar("""
                             Ronda "+ronda+":
                              -Vidas jugador: "+j.getPuntosVida()
                              -Vidas zombie: "+z.getPuntosVida()
                             """);
            
            tiradaJugador = j.dado();
            
            System.out.println("Has tirado "+tiradaJugador+", más "+j.getArmas()+" de las armas que tienes.");
            tiradaJugador += j.getArmas();
            System.out.println("Total de daño: "+tiradaJugador);
            z.recibirAtaque(tiradaJugador);
            
            if(z.isVivo()){
                tiradaZombie = z.dado();
                System.out.println("Zombie te ataca con "+tiradaZombie+", pero tienes "+j.getProtecciones()+" protecciones.");
                tiradaZombie = Math.max(tiradaZombie-j.getProtecciones(), 0);
                j.recibirAtaque(tiradaZombie);
                System.out.println("Te ha hecho "+tiradaZombie+" de daño.");
            } else {
                System.out.println("Has matado al zombie.");
            }
            
            if(j.isVivo()){
                ronda++;
            }
        }
    }
    
    public static void buscar(Habitacion h, Jugador j){
        int tirada = dadoBusqueda();
        
        if(tirada >= 1 && tirada <= 75){
            Terminal.mostrar("Cuidado has hecho ruido...");
            tirada = dadoBusqueda();
            if(tirada >= 1 && tirada <= 40){
                Terminal.mostrar("Has tenido suerte, no hay zombies.");
            } else if(tirada >= 41 && tirada <= 80){
                Terminal.mostrar("Ha aparecido un zombie.");
                h.setContZombie(h.getContZombie()+1);
            } else if(tirada >= 81 && tirada <= 100){
                Terminal.mostrar("Han aparecido dos zombies.");
                h.setContZombie(h.getContZombie()+2);
            }
        } else if (tirada >= 76 && tirada <= 90){
            Terminal.mostrar("Has encontrado un botiquin.");
            if(j.isBotiquin()){
                Terminal.mostrar("Ya tienes uno.");
            } else {
                Terminal.mostrar("Botiquin añadido.");
                j.setBotiquin(true);
            }  
        } else if (tirada >= 91 && tirada <= 95){
            Terminal.mostrar("Has encontrado una proteccion.");
            j.setProtecciones(j.getProtecciones()+1);
        } else if (tirada >= 96 && tirada <= 100){
            Terminal.mostrar("Has encontrado un arma.");
            j.setArmas(j.getArmas()+1);
        }
    }
    
    private static int dadoBusqueda(){
        return (int) ((Math.random()*100)+1);
    }
}
