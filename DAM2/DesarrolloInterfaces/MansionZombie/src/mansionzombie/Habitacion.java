package mansionzombie;

public class Habitacion {
    public static int habActual = 1;
    
    private int busquedas;
    private int contZombie;
    public static int habitacionesMax;

    public Habitacion() {
        busquedas = 3;
        contZombie = 1;
    }

    public int getBusquedas() {
        return busquedas;
    }

    public void setBusquedas(int busquedas) {
        this.busquedas = busquedas;
    }

    public int getHabActual() {
        return habActual;
    }

    public void setHabActual(int habActual) {
        this.habActual = habActual;
    }
    
    public boolean hayZombies(){
        return contZombie > 0;
    }

}