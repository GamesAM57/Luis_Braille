package ej1hoja;
import java.util.*;
public class Ej7 {
    public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	System.out.println("Introduce una frase: ");
	String frase = sc.nextLine();
	int i = 0, cont = 0;
	while(i < frase.length()){
	    char letra = frase.charAt(i);
	    if(Character.isWhitespace(letra)) {
		cont ++;
	    }
	    i++;
	}
	System.out.println("Hay "+cont+" espacios.");
    }
}
