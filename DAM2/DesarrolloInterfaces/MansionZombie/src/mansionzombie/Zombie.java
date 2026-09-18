package mansionzombie;

public class Zombie {
    private int puntosVida;
    private int puntosAtaque;

    public Zombie(int habActual) {
        puntosVida = generarPuntos(habActual);
        puntosAtaque = generarPuntos(habActual);
    }
    
    public int generarPuntos(int habActual){
        return (int) (Math.random()*2)+2+(habActual-1); // cambiar habitación por el 0
    }

    public int getPuntosVida() {
        return puntosVida;
    }

    public void setPuntosVida(int puntosVida) {
        this.puntosVida = puntosVida;
    }

    public int getPuntosAtaque() {
        return puntosAtaque;
    }

    public void setPuntosAtaque(int puntosAtaque) {
        this.puntosAtaque = puntosAtaque;
    }

    @Override
    public String toString() {
        return "Zombie{" + "puntosVida=" + puntosVida + ", puntosAtaque=" + puntosAtaque + '}';
    }
    
    
    
}
