package switch02;
import java.util.*;
public class Switch02 {

    public static void main(String[] args) {
	Scanner intro = new Scanner(System.in);
	int num;
	
	System.out.println("Introduce un número del 1-12:");
	num = intro.nextInt();
	
	switch(num){
	    case 1, 3, 5, 7, 8, 10, 12 -> System.out.println("Tiene 31 días."); 
	    case 4, 6, 9, 11 -> System.out.println("Tiene 30 días.");
	    case 2 -> System.out.println("Tiene 28 días.");
	}
    }
    
}
