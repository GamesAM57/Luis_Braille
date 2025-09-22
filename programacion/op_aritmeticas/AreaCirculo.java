import java.util.Scanner;

public class AreaCirculo{
	public static void main(String[] arg){
		Scanner intro = new Scanner(System.in);
		float radio;
		final float PI = 3.14f;
		float resultado;
		
		System.out.println("Introduce el radio de la circumferencia:");
		radio = intro.nextFloat();
		resultado = PI * (radio * radio);
		System.out.println("El area de una circumferencia es " + resultado);
	}
}