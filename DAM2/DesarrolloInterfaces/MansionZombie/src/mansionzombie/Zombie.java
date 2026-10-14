package mansionzombie;

public class Zombie extends Personaje{
    
    public Zombie(int habActual) {
        super(generarPuntos(habActual), generarPuntos(habActual));
    }
    
    public static int generarPuntos(int habActual){
        return (int) (Math.random()*2)+2+(habActual-1); 
    }
    
    public boolean isVivo(){
        return getPuntosVida()>0;
    }

    @Override
    public String toString() {
        return "Zombie{" + "puntosVida=" + getPuntosVida() + ", puntosAtaque=" + getPuntosAtaque() + '}';
    }
    
    
    
}
