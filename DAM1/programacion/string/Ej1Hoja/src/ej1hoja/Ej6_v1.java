package ej1hoja;
import java.util.*;
public class Ej6_v1 {
    public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	System.out.println("Introduce la contraseña a adivinar");
	String password = sc.nextLine();
	System.out.println("Introduce un intento");
	String intento = sc.nextLine();
	while(password.equals(intento) == false){
	    int n = password.compareTo(intento);
	    if(n > 0){
		System.out.println("la palabra es mayor");
	    } else if(n < 0){
		System.out.println("la palabra es menor");
	    }
	    System.out.println("Introduce un intento");
	    intento = sc.nextLine();
	}
	System.out.println("Has acertado");   
    }
}
