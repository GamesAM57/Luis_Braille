import java.util.Scanner;
public class Scanner01 {
	public static void main(String[] args) {
		Scanner intro = new Scanner(System.in);
		
		System.out.println("Introduce tu edad.");
		int edad = intro.nextInt();
		System.out.print("Tu edad es: " + edad);
	}
}