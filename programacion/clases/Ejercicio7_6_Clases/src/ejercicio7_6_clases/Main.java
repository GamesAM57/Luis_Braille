package ejercicio7_6_clases;
import java.util.*;
public class Main {
    public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	Hora hora = new Hora();
	int n;
	
	System.out.print("Hora: ");
	hora.setHora(sc.nextByte());
	System.out.print("Minuto: ");
	hora.setMinuto(sc.nextByte());
	System.out.print("Segundo: ");
	hora.setSegundo(sc.nextByte());
	
	System.out.print("Cuantos segundos quieres mostrar: ");
	n = sc.nextInt();
	
	while(n > 0){
	    hora.incrementaSegundo();
	    hora.getHora();
	    n --;
	}
    }
}
