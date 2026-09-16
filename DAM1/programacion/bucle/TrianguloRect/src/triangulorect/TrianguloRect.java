package triangulorect;
import java.util.*;
public class TrianguloRect {
    public static void main(String[] args) {
	Scanner intro = new Scanner(System.in);
	
	System.out.println("Indica un número: ");
	
	for(int num = intro.nextInt(); num > 0; num--){
	    for (int i = 1; i <= num; i++) {
		System.out.print("*");
	    }
	    System.out.print('\n');
	}
    }
}
