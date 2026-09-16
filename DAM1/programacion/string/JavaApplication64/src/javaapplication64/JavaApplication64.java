package javaapplication64;
import java.util.*;
public class JavaApplication64 {
    public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	System.out.println("Indica una frase separada entre espacios.");
	String frase = sc.nextLine();
	System.out.println("Indica una palabra");
	String pal = sc.next();
	String frase2[] = frase.split("\\s+");
	System.out.println(Arrays.toString(frase2));
	int i = 0;
	int cont = 0;
	while (i < frase2.length){
	    if(frase2[i] == pal){
		cont ++;
	    }
	    i++;
	}
	System.out.println("Coincidencias " + cont);
    }
    
}
