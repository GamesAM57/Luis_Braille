package aforoconcierto;
import java.util.*;
public class AforoConcierto {
    public static void main(String[] args) {
	Scanner intro = new Scanner(System.in);
	int aforoMax, ventas;
	float precio;
	
	System.out.println("Introduce el aforo máximo:");
	aforoMax = intro.nextInt();
	System.out.println("Introduce las entradas vendidas:");
	ventas = intro.nextInt();
	System.out.println("Introduce el precio de la entrada.");
	precio = intro.nextFloat();
	
	if ((aforoMax * 0.2 )>= ventas){
	    System.out.println("Se cancela el concierto.");
	} else if (aforoMax * 0.5 >= ventas) {
	    precio  = precio / 2;
	    System.out.println("El precio de las entradas es: " + precio);
	} else {
	    System.out.println("El precio de las entradas es: " + precio);
	}
    } 
}
