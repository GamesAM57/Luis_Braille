package funcion1;
import java.util.*;
public class Funcion1 {
    public static void main(String[] args) {
	Scanner intro = new Scanner(System.in);
	int num;
	
	System.out.println("Indica el num de veces: ");
	num = intro.nextInt();
	
	eco_print(num);
    }
    
    static void eco_print(int num){
	while (num > 0){
	    System.out.println("Eco...");
	    num --;
	}
    }
    
}
