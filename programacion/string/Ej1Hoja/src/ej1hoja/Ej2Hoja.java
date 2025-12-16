package ej1hoja;
import java.util.*;
public class Ej2Hoja {
     public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	String palabra = sc.nextLine();
	int i = 0, contVoc = 0, contCons = 0;
	while(i < palabra.length()){
	    char letra = palabra.charAt(i);
	    if(Character.isLetter(letra)){
		switch(letra){
		    case 'a', 'e', 'i', 'o', 'u' ->
			contVoc ++;
		    default ->
			contCons ++;
		}
	    }
	    i++;
	}
	 System.out.println("Vocales: "+contVoc);
	 System.out.println("Consonantes: "+contCons);
	 
	
    }
}
