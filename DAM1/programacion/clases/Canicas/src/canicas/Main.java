package canicas;
public class Main {
    public static void main(String[] args) {
	Canicas jugador1 = new Canicas("Luisa");
	Canicas jugador2 = new Canicas("Juana");
	jugador1.compra(10);
	jugador2.mirar();
	jugador2.quitar(3);
	jugador1.mirar();
	jugador2.quitar(9);
    }
}
