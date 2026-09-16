import java.util.*;

public class MayMenor{
	public static void main(String[] arg){
		Scanner intro = new Scanner(System.in);
		int a, b, mayor, menor;
		
		System.out.print("Introduce el primer número: ");
		a = intro.nextInt();
		System.out.print("Introduce el segundo número: ");
		b = intro.nextInt();
		
		mayor = a > b ? a:b;
		menor = a < b ? a:b;
		System.out.print("Los números son: " + mayor + ", " + menor);
	}
}