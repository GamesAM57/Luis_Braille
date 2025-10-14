package javaapplication16;
import java.util.*;
public class JavaApplication16 {
    public static void main(String[] args) {
	Scanner intro = new Scanner(System.in);
	int num;
	
	System.out.println("Introduce un número, 0 para salir: ");
	num = intro.nextInt();
	
	while (num != 0){
	    if (num % 2 == 0){
		System.out.println("El número es par.");
	    } else {
		System.out.println("El número es impar.");
	    }
	    if (num >= 0){
		System.out.println("El número es positivo.");
	    } else {
		System.out.println("El número es negativo");
	    }
	    System.out.println("El cuadrado de " + num + " es " + num * num);
	}
    }
    
}
