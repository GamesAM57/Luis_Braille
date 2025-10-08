package temperatura01;
import java.util.*;
public class Temperatura01 {
    public static void main(String[] args) {
	Scanner intro = new Scanner(System.in);
	int temp;
	
	System.out.println("Introduce una temperatura: ");
	temp = intro.nextInt();
	
	if (temp < 12){
	    System.out.println("MUCHO FRIO");
	} else if (temp < 22){
	    System.out.println("FRIO");
	} else if (temp < 32) {
	    System.out.println("CALOR");
	} else if (temp >= 32){
	    System.out.println("MUCHO CALOR");
	} else {
	    System.out.println("Temperatura no reconocida.");
	}
    }
}
