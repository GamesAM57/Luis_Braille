package ej1hoja;
import java.util.*;
public class Ej6_v2 {
    public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	System.out.println("Introduce la contraseña a adivinar");
	String password = sc.nextLine();
	System.out.println("Introduce un intento");
	String intento = sc.nextLine();
	while(password.equals(intento) == false){
	    int i = 0;
	    while(i < password.length()){
		char c1 = password.charAt(i);
		char c2 = intento.charAt(i);
		if (c1 == c2){
		    System.out.print(c1);
		} else {
		    System.out.print("*");
		}
		i++;
	    }
	    System.out.print("\n");
	    System.out.println("Introduce un intento");
	    intento = sc.nextLine();
	}
	System.out.println("Has acertado");
    }
}
