import java.util.Scanner;

public class Edad2 {
	public static void main(String[] arg){
		Scanner intro = new Scanner(System.in);
		int actual;
		int nacimiento;
		int edad;
		
		System.out.println("Introduce tu año de nacimiento");
		nacimiento = intro.nextInt();
		System.out.println("Itroduce el año actual");
		actual = intro.nextInt();
		edad = actual - nacimiento;
		System.out.print("Tienes o vas a tener este años " + edad + " años");
	}
}