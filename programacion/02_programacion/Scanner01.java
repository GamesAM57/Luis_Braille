import java.util.Scanner;
public class Scanner01 {
	public static void main(String[] args) {
		Scanner intro = new Scanner(System.in); //Creo un objeto que recopila información de la terminal.
		
		System.out.println("Introduce tu edad.");
		int edad = intro.nextInt(); //El objeto creado arriba solo recopila numeros.
		System.out.print("Tu edad es: " + edad);
	}
}