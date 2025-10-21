package dowhilecalc;
import java.util.*;
public class DoWhileCalc {
/*Hay que corregir este, 
    el bucle do while no 
    fucniona correctamente
*/
    public static void main(String[] args) {
	Scanner intro = new Scanner(System.in);
	int suma, resta, multi, op, num1, num2;
	
	op = 5;
	num1 = 0;
	num2 = 0;
	
	do {
	    switch (op) {
		case 1 ->{
		    suma = num1 + num2;
		    System.out.println("La suma es: " +  suma);
		}
		case 2 ->{
		    resta = num1 - num2;
		    System.out.println("La resta es: " +  resta); 
		}
		case 3 ->{
		    multi = num1 * num2;
		    System.out.println("La multi es: " +  multi);
		}
		default ->{
		    System.out.println("Indica operación deseada.: ");
		    System.out.println("1. Sumar");
		    System.out.println("2. Resta");
		    System.out.println("3. Multi"); 
		    op = intro.nextInt();
		    System.out.println("Indica el primer operando:");
		    num1 = intro.nextInt();
		    System.out.println("Indica el segundo operando:");
		    num2 = intro.nextInt();
		}
	    }  
	} while (op < 1 || op > 3);
    }    
}
