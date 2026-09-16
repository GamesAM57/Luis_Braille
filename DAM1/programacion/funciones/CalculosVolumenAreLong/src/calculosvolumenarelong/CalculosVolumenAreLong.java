package calculosvolumenarelong;
import java.util.*;
public class CalculosVolumenAreLong {
    public static void main(String[] args) {
	menu();
	System.out.println("Has terminado el programa.");
    }
    
    static void menu(){
	Scanner intro = new Scanner(System.in);
	int op = 0;
	double radio = 0;
	do{
		System.out.println("Indica la opcion deseada:");
		System.out.println("1. Longitud de la circunferencia.");
		System.out.println("2. Área del círculo.");
		System.out.println("3. Área de la esfera.");
		System.out.println("4. Área del cilindor.");
		System.out.println("5. Volumen de la esfera.");
		System.out.println("6. Volumen del cilindro.");
		System.out.println("7. Nuevo radio.");
		System.out.println("8. Terminar.");
		System.out.println("Inserta el número de la opción:");
		op = intro.nextInt();
		if(op!=8 && op <= 7){
		    System.out.println("Indica el radio:");
		    radio = intro.nextDouble();
		    calculo(radio, op);
		} else if (op!=8 && op > 7){
		    System.out.println("Has indicado un número no correcto.");
		    System.out.println("-----------------------------------");
		}
	}while(op != 8);
    }
    
    static void calculo(double r, int op){
	Scanner intro = new Scanner(System.in);
	final double PI = Math.PI;
	double result;
	switch(op){
	    case 1 ->{
		result = 2*PI*r;
		System.out.println("La longitud es: "+result);
		System.out.println("-----------------------------------");
	    }
	    case 2 ->{
		result = PI*(r*r);
		System.out.println("El área del círculo es: "+result);
		System.out.println("-----------------------------------");
	    }
	    case 3 ->{
		result = 4*PI*(r*r);
		System.out.println("La longitud es: "+result);
		System.out.println("-----------------------------------");
	    }
	    case 4 ->{
		System.out.println("Indica la altura: ");
		double alt = intro.nextDouble();
		result = 2*PI*r*(r+alt);
		System.out.println("La longitud es: "+result);
		System.out.println("-----------------------------------");
	    }
	    case 5 ->{
		result = (4/3)*PI*Math.pow(r, 3);
		System.out.println("La longitud es: "+result);
		System.out.println("-----------------------------------");
	    }
	    case 6 ->{
		System.out.println("Indica la altura: ");
		double alt = intro.nextDouble();
		result = PI*(r*r)*alt;
		System.out.println("La longitud es: "+result);
		System.out.println("-----------------------------------");
	    }
	    case 7 ->{
		System.out.println("Indica el nuevo radio.");
		r = intro.nextDouble();
		System.out.println("-----------------------------------");
	    }
	    default ->{
		System.out.println("No has indicado un número correcto.");
		System.out.println("-----------------------------------");
		break;
	    }
	}
    }
    
}
