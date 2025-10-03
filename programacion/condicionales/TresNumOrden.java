import java.util.*;

public class TresNumOrden{
	public static void main(String[] arg){
		Scanner intro = new Scanner(System.in);
		int num1, num2, num3;
		
		System.out.print("Introduce el primer número entero: ");
		num1 = intro.nextInt();
		System.out.print("Introduce el primer número entero: ");
		num2 = intro.nextInt();
		System.out.print("Introduce el primer número entero: ");
		num3 = intro.nextInt();
		
		if (num1 > num2 && num1 > num3){
			if (num2 > num3){
				System.out.print(num1 + ", " + num2 + ", " + num3);
			} else {
				System.out.print(num1 + ", " + num3 + ", " + num2);
			}
		} else if (num2 > num1 && num2 > num3){
			if (num1 > num3){
				System.out.print(num2 + ", " + num1 + ", " + num3);
			} else {
				System.out.print(num2 + ", " + num3 + ", " + num1);
			}
		} else if (num3 > num1 && num3 > num2){
			if (num1 > num2){
				System.out.print(num3 + ", " + num1 + ", " + num2);
			} else {
				System.out.print(num3 + ", " + num2 + ", " + num1);
			}
		}
	}
}