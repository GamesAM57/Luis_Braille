package calculadoraavanzada;
import java.util.*;
public class CalculadoraAvanzada {
    public static void main(String[] args) {
	Scanner intro = new Scanner(System.in);
	double n1, n2;
	int op = menu();
	
	while (op < 5){
	    System.out.println("Indica el primero operando: ");
	    n1 = intro.nextDouble();
	    System.out.println("Indica el primero operando: ");
	    n2 = intro.nextDouble();
	    System.out.println("El resutlado es: "+calculadora(n1, n2, op));
	    op = menu();
	}
	System.out.println("Has terminado el programa.");
	
    }
       
    static int menu(){
	Scanner intro = new Scanner(System.in);
	System.out.println("Elige que quieres hacer:");
	System.out.println("\t1. Sumar.");
	System.out.println("\t2. Restar.");
	System.out.println("\t3. Dividir.");
	System.out.println("\t4. Multiplicar.");
	System.out.println("\t5+. Salir");
	return(intro.nextInt());
    }    
    static double calculadora(double n1, double n2, int op){
	switch (op){
	    case 1 ->{
		return(n1+n2);
	    }
	    case 2 ->{
		return(n1-n2);
	    }
	    case 3 ->{
		return(n1*n2);
	    }
	    case 4 ->{
		if (n2 == 0){
		    System.out.println("No se puede dividir entre 0");
		    return(0);
		} else{
		    return(n1/n2);
		}
	    }
	    default ->{
		System.out.println("No es correcto");
		return(0);
	    }
	}
    }
}

