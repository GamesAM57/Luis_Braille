package sueldo01;
import java.util.*;
public class Sueldo01 {
    public static void main(String[] args) {
	Scanner intro = new Scanner(System.in);
	float sueldo;
	
	System.out.println("Introduce tu sueldo: ");
	sueldo = intro.nextFloat();
	
	if (sueldo >= 0 && sueldo < 1000){
	    System.out.println("MUY MALO");
	} else if (sueldo >= 1000 && sueldo < 1500) {
	    System.out.println("MALO");
	} else if (sueldo >= 1500 && sueldo < 3000) {
	    System.out.println("BUENO");
	} else if (sueldo >= 3000){
	    System.out.println("MUY BUENO");
	} else {
	    System.out.println("No se reconoce el sueldo.");
	}
    } 
}
