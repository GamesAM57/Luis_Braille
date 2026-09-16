package mediafor;
import java.util.Scanner;
public class MediaFor {
    public static void main(String[] args) {
	Scanner intro = new Scanner(System.in);
	float suma;
	suma = 0;
	for (int i = 1; i <= 10; i++){
	    System.out.println("Introduce un numero (" + i + ")");
	    suma += intro.nextFloat();
	}
	suma /= 10;
	System.out.println("La media es: " + suma);
    }   
}