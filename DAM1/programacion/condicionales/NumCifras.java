import java.util.*;

public class NumCifras{
	public static void main(String[] arg){
		Scanner intro = new Scanner(System.in);
		int num;
		
		System.out.print("Introduce un número entre 0 y 99999: ");
		num = intro.nextInt();
		
		if (num < 0 || num > 99999) {
			System.out.print("Número no entre 0 y 99999.");
		} else if (num < 10) {
			System.out.print("Una cifra.");
		} else if (num < 100) {
			System.out.print("Dos cifras.");
		} else if (num < 1000) {
			System.out.print("Tres cifras.");
		} else if (num < 10000) {
			System.out.print("Cuatro cifras.");
		} else if (num < 100000) {
			System.out.print("Cinco cifras.");
		}
	}
}