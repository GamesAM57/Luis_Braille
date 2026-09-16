package ej1hoja;
import java.util.*;
public class Ej3hoja {
    public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	System.out.println("Introduce una frase: ");
	String frase = sc.nextLine();
	char letra = 'j';
	int i = 0, cont = 0;
	while(i < frase.length()){
	    if(letra == frase.charAt(i)){
		cont ++;
	    }
	    i++;
	}
	System.out.println("Cadena: "+frase+" / Letra: "+"\'"+letra+"\'");
	System.out.println("Se ha encontrado " + cont+" veces.");
    }
}
