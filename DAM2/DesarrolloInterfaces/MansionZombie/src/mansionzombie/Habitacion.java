package mansionzombie;

public class Habitacion {
    private int busquedas;
    private int habActual;
    private int contZombie;

    public Habitacion(int habActual) {
        busquedas = 3;
        this.habActual = habActual;
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

}