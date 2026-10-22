package mansionzombie;

public class Jugador extends Personaje{
    private final int VIDAS_MAX = 20;
    private boolean botiquin;
    private int armas;
    private int protecciones;

    public Jugador() {
        super(20, 4);
        botiquin = false;
        armas = 0;
        protecciones = 0;
    }

    public boolean isBotiquin() {
        return botiquin;
    }

    public void setBotiquin(boolean botiquin) {
        this.botiquin = botiquin;
    }

    public int getArmas() {
        return armas;
    }

    public void setArmas(int armas) {
        this.armas = armas;
    }

    public int getProtecciones() {
        return protecciones;
    }

    public void setProtecciones(int protecciones) {
        this.protecciones = protecciones;
    }
    
    public void curar(){
        if(isBotiquin()){
            setPuntosVida(Math.min(getPuntosVida()+4, VIDAS_MAX));
            setBotiquin(false);
        }
    }
    
    @Override
    public String toString() {
        return "Jugador{" + "vidasActuales=" + getPuntosVida() + ", puntosAtaque=" + getPuntosAtaque() + ", botiquin=" + botiquin + ", armas=" + armas + ", protecciones=" + protecciones + '}';
    }    
}
