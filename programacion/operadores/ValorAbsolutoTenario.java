import java.util.*;

public class ValorAbsolutoTenario{
	public static void main(String[] arg){
		Scanner intro = new Scanner(System.in);
		int num;
		
		System.out.print("Introduce un número entero: ");
		num = intro.nextInt();
		num = num < 0 ? num *= -1 : num; //calculo con operador tenario
		System.out.print("Tu valor absoluto: " + num);
	}
}