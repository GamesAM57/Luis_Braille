package recursividad01;
import java.util.*;
public class Recursividad01 {
    public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	double base;
	int exp;
	
	System.out.println("Indica la base: ");
	base = sc.nextDouble();
	
	System.out.println("Indica el exponente: ");
	exp = sc.nextInt();
	if (exp <00)
	    System.out.println("No has indicado un exponente correcto");
	else
	    potencias(base, exp);
    }
    
    static void potencias(double base, int exp){
	if(exp == 1){
	    System.out.println(base);
	} else {
	    base *= base;
	    exp --;
	    potencias(base, exp);
	}
    }
}
