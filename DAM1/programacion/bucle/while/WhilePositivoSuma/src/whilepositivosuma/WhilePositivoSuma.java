package whilepositivosuma;
import java.util.*;
public class WhilePositivoSuma {
    public static void main(String[] args) {
	Scanner intro = new Scanner(System.in);
	int num, suma, cont;

	System.out.println("Introduce un número positivo y entero: ");
	num = intro.nextInt();
	
	while(num>=0){
	    suma = 0;
	    cont = 1;
	    while (cont<=num){
		if (cont%2==0){
		    suma += cont;
		}
		cont ++;
	    }
	    System.out.println("La suma total de los números es: " + suma);
	    System.out.println("Introduce un número positivo y entero o negativo para terminar: ");
	    num = intro.nextInt();
	}
	System.out.println("Has finalizado el programa.");
    }
}
