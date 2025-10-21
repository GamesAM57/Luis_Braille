package contadornave;
import java.util.*;
public class ContadorNave {
    public static void main(String[] args) {
	Scanner intro = new Scanner(System.in);
	int num;
	
	System.out.println("Introduce un número: ");
	num = intro.nextInt();
	
	while (num >= 0){
	    System.out.println(num);
	    num--;
	}
	System.out.println("Despego");
    }
}
