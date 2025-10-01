import java.util.*;

public class MayMenor{
	public static void main(String[] arg){
		Scanner intro = new Scanner(System.in);
		float num;
		
		System.out.print("Introduce un número: ");
		num = intro.nextFloat();
		
		if (num < 1 && num > -1 && num != 0)
			System.out.print("El número " + num + " es casi 0.");
		else
			System.out.print("El número " + num + " no es casi 0");
	}
}