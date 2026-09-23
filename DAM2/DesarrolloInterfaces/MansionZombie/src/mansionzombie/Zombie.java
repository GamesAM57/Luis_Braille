package mansionzombie;

public class Zombie extends Personaje{
    
    public Zombie(int habActual) {
        super(generarPuntos(habActual), generarPuntos(habActual));
    }
    
    public static int generarPuntos(int habActual){
        return (int) (Math.random()*2)+2+(habActual-1); 
    }

    @Override
    public String toString() {
        return "Zombie{" + "puntosVida=" + puntosVida + ", puntosAtaque=" + puntosAtaque + '}';
    }
    
    
    
}
