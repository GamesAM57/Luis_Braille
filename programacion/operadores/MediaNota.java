import java.util.Scanner;

public class MediaNota{
	public static void main(String[] arg){
		Scanner intro = new Scanner(System.in);
		float nota1;
		float nota2;
		float resultado;
		
		System.out.println("Introduce la nota 1:");
		nota1 = intro.nextFloat();
		System.out.println("Introduce la nota 2:");
		nota2 = intro.nextFloat();
		resultado = (nota1 + nota2) / 2;
		System.out.println("Has sacado de media " + resultado);
	}
}