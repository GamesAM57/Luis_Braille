package ej1hoja;
import java.util.*;
public class Ej1Hoja {
    public static void main(String[] args) {
	char letras[]={'A', 'z', 'w', 'X', 'b', 'g', 'H', 'q', 'p', 'N'};
	for (char c : letras){
	    System.out.print(Character.toUpperCase(c));
	}
	System.out.println(" ");
	for (char c : letras){
	    System.out.print(Character.toLowerCase(c));
	}
	System.out.println(" ");
    }
    
}
