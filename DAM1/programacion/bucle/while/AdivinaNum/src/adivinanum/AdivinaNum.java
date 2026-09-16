package adivinanum;
import java.util.*;
public class AdivinaNum {
    public static void main(String[] args) {
	Scanner intro = new Scanner(System.in);
	int numAleat = (int) (Math.random() * 100 + 1);
	int num;
	
	System.out.println("Adivina el número o -1 para rendirte: ");
	num = intro.nextInt();
	
	while(num != numAleat && num != -1){
	    if (num > numAleat) {
		System.out.println("Menos");
	    } else {
		System.out.println("Mas");
	    }
	    System.out.println("Adivina el número o -1 para rendirte: ");
	    num = intro.nextInt();
	}
	
	if (num != -1) {
	    System.out.println("Lo has adivinado!!");	    
	} else {
	    System.out.println("Te has rendido !!");
	}
    }
}
