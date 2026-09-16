package numeroprimo;
import java.util.*;
public class NumeroPrimo {
    public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	boolean resultado;
	int n;
	System.out.println("Indica el numero primo:");
	n = sc.nextInt();
	resultado = esPrimo(n);
	
	if(resultado == true)
	    System.out.println("Es primo");
	else
	    System.out.println("No es primo");
    }
    
    static boolean esPrimo(int n){
	if(n<=1)
	    return false;
	else if(n%1==0&&n%n==0){
	    for(int i = 2; i <= 100; i++){
		if(n%i==0 && i != n)
		    return false;
	    }
	    return true;
	}
	else
	    return false;	
    }
}
