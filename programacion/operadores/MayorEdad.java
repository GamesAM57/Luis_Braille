import java.util.*;

public class MayorEdad{
	public static void main(String[] arg){
		Scanner intro = new Scanner(System.in);
		int edad;
		boolean mayEdad;
		
		System.out.print("Introduce tu edad: S");
		edad = intro.nextInt();
		mayEdad = edad >= 18;
		System.out.println("Mayoría edad: " + mayEdad);
	}
}