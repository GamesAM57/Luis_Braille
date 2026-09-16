package for1;
import java.util.Scanner;
public class For1 {
    public static void main(String[] args) {
	Scanner intro = new Scanner(System.in);
	int num;
	
	System.out.println("Indica cuantas veces: ");
	num = intro.nextInt();
	
	for (int i = 1; i <= num; i++){
	    System.out.println("Eco");
	}
    }
}
