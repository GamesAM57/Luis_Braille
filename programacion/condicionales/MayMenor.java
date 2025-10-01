import java.util.*;

public class MayMenor{
	public static void main(String[] arg){
		Scanner intro = new Scanner(System.in);
		int num1, num2;
		
		System.out.print("Introduce el primer numero: ");
		num1 = intro.nextInt();
		System.out.print("Introduce el segundo numero: ");
		num2 = intro.nextInt();
		if (num1 < num2)
			System.out.print("El número " + num2 + " es mayor.");
		else if (num1 > num2)
			System.out.print("El número " + num1 + " es mayor.");
		else
			System.out.print("Son iguales.");
	}
}