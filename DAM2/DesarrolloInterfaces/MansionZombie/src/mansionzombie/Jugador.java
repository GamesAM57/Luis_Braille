package mansionzombie;

public class Jugador {
    private final int VIDAS_MAX = 20;
    private int vidasActuales;
    private int puntosAtaque;
    private boolean botiquin;
    private int armas;
    private int protecciones;

    public Jugador() {
        vidasActuales = 20;
        puntosAtaque = 4;
        botiquin = false;
        armas = 0;
        protecciones = 0;
    }

    public int getVidasActuales() {
        return vidasActuales;
    }

    public void setVidasActuales(int vidasActuales) {
        this.vidasActuales = vidasActuales;
    }

    public int getPuntosAtaque() {
        return puntosAtaque;
    }

    public void setPuntosAtaque(int puntosAtaque) {
        this.puntosAtaque = puntosAtaque;
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

    @Override
    public String toString() {
        return "Jugador{" + "vidasActuales=" + vidasActuales + ", puntosAtaque=" + puntosAtaque + ", botiquin=" + botiquin + ", armas=" + armas + ", protecciones=" + protecciones + '}';
    }
    
    
    
    
}
