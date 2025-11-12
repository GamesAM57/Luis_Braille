package javaapplication54;
import java.util.*;
public class JavaApplication54 {
    public static void main(String[] args) {
	int intento;
	try{
	    intento = 5 /0;
	} catch (ArithmeticException e){
	    System.out.println("No se puede dividir entre 0");
	}
    }
    
}
