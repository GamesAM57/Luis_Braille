package numinvertido;
import java.util.*;
public class NumInvertido {
    public static void main(String[] args) {
	Scanner intro = new Scanner(System.in);
	int num;
	
	System.out.println("Indica un número: ");
	num = intro.nextInt();
	while(num > 0){
	    System.out.print(num % 10);
	    num /= 10;
	}
    }

}
