import java.util.*;

public class IfSimple01{
	public static void main(String[] arg){
		Scanner intro = new Scanner(System.in);
		int num1, num2;
		
		System.out.print("Introduce el primer número entero: ");
		num1 = intro.nextInt();
		System.out.print("Introduce el segundo número entero: ");
		num2 = intro.nextInt();
		if (num1 == num2)
			System.out.print("Son iguales.");
		else
			System.out.print("No son iguales.");
	}
}