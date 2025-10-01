import java.util.Scanner;
public class Edad {
	public static void main(String[] args) {
		Scanner intro = new Scanner(System.in); //Creo un objeto que recopila información de la terminal.
		int edad;
		
		System.out.println("Introduce tu edad.");
		edad = intro.nextInt(); //El objeto creado arriba solo recopila numeros por consola.
		edad ++;
		System.out.print("Tu edad el año que viene es: " + edad);
	}
}