package excepcionessuma;
import java.util.*;
public class ExcepcionesSuma {
    public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	int n1, n2;
	//Llamamos a la función para solicitar y validar el primer número.
	n1 = SolicitarValidar(sc);
	//Llamamos a la función para solicitar y validar el segundo número.
	n2 = SolicitarValidar(sc);
	
	System.out.println("La suma de los numeros es: "+suma(n1, n2));
	sc.close();
    }
    
    static int SolicitarValidar(Scanner sc){
	int num = 0;
	boolean valido = false;
	    
	while (valido == false){
	    System.out.println("Indica un número: ");
	    try {
		num = sc.nextInt();
		valido = true;
	    }catch (InputMismatchException e){
		System.out.println("Has indicado un número incorrecto.");
		sc.nextLine(); //Limpia buffer scanner
	    }  
	}
	return num;
    }
    
    static int suma(int n1, int n2){
	return n1+n2;
    }
}
