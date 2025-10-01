import java.util.*;

public class BoolParIm{
	public static void main(String[] arg){
		Scanner intro = new Scanner(System.in);
		int num;
		boolean bool;
		
		bool = true;
		System.out.print("Introduce un número: ");
		num = intro.nextInt();
		if (num % 2 != 0){
			bool = false;
		}
		System.out.print("Es par? " + bool);
	}
}