package areavolumencirculo;
import java.util.*; 
public class AreaVolumenCilindro {
    public static void main(String[] args) {
	Scanner intro = new Scanner(System.in);
	double radio, altura;
	int op;
	
	System.out.println("Indica 1 para área o 2 para volumen: ");
	op = intro.nextInt();
	System.out.println("Indica la altura en cm: ");
	altura = intro.nextDouble();
	System.out.println("Indica el radio en cm: ");
	radio = intro.nextDouble();
	
	calcCilindro(op, altura, radio);
    } 
    
    static void calcCilindro(int op, double altura, double radio){
	final double PI = Math.PI;
	double resultado;
	switch (op){
	    case 1 -> {
		resultado = (2 * PI) * radio * (altura + radio);
		System.out.println("El área del cilindro " + resultado);
	    }
	    case 2 ->{
		resultado = PI * Math.pow(radio, 2) * altura;
		System.out.println("El volumen del cilindro " + resultado);
	    }
	    default ->{
		System.out.println("No has indicado un número correcto.");
	    }
	}
    }
}
