import java.util.*;

public class IfSimple01{
	public static void main(String[] arg){
		Scanner intro = new Scanner(System.in);
		int num;
		
		System.out.print("Introduce un número entero: ");
		num = intro.nextInt();
		if (num%2==0)
			System.out.print("El número " + num + " es par.");
		else
			System.out.print("El número " + num + " es impar.");
	}
}