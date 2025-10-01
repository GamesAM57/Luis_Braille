import java.util.*;

public class OperacionesV2 {
	public static void main(String[] arg){
		float num1, num2, sum, res, mul, div, resDiv;
		Scanner intro = new Scanner(System.in);

		System.out.print("Introduce el primer operador: ");
		num1 = intro.nextInt();
		System.out.print("Introduce el segundo operador: ");
		num2 = intro.nextInt();

		sum = num1 + num2;
		res = num1 - num2;
		mul = num1 * num2;
		div = num1 / num2;
		resDiv = num1 % num2;

		System.out.println("La suma de " + num1 + " y " + num2 + " es " + sum + ".");
		System.out.println("La resta de " + num1 + " y " + num2 + " es " + res + ".");
		System.out.println("La multiplicación de " + num1 + " y " + num2 + " es " + mul + ".");
		System.out.println("La división de " + num1 + " y " + num2 + " es " + div + ".");
		System.out.println("El resto de la división de " + num1 + " y " + num2 + " es " + resDiv + ".");
	}
}