package edadescentro;
import java.util.*;
public class EdadesCentro {
    public static void main(String[] args) {
	Scanner intro = new Scanner(System.in);
	int edad, cont, mayEdad, sumEdad;

	cont = 0;
	mayEdad = 0;
	sumEdad = 0;
	
	System.out.println("Introduce la edad del num, negativo para terminar: ");
	edad = intro.nextInt();
	
	while (edad >= 0){
	    cont ++;
	    sumEdad += edad;
	    if (edad >= 18){
		mayEdad ++;
	    }
	    System.out.println("Introduce la edad del num, negativo para terminar: ");
	    edad = intro.nextInt();
	}
	
	System.out.println("Total de edad: " + sumEdad + "\nLa media es: " + sumEdad/cont);
	System.out.println("Total de alumnos: " + cont + "\nAlumnos mayores de edad: " + mayEdad);
    }   
}