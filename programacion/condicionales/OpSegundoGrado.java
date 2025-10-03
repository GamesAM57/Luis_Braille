import java.util.*;

public class TresNumOrden{
	public static void main(String[] arg){
		Scanner intro = new Scanner(System.in);
		float a, b, c;
		float cuad_b, res1, res2;
		
		res1 = 0;
		res2 = 0;
		System.out.print("Proporciona el valor de a: ");
		a = intro.nextFloat();
		System.out.print("Proporciona el valor de a: ");
		b = intro.nextFloat();
		System.out.print("Proporciona el valor de a: ");
		c = intro.nextFloat();
		
		cuad_b = (b*b)-4*a*c;
		if (cuad_b < 0) {
			System.out.print("No existen resultados reales.");
		} else if (cuad_b == 0) {
			System.out.print("No existen resultados reales.");
		} else {
			cuad_b = (float)Math.sqrt(cuad_b);
			res1 = ((b*-1)-cuad_b)/(2*a);
			res2 = ((b*-1)+cuad_b)/(2*a);
		}
		System.out.print("Resultado 1: " + res1 + ", resultado 2: " + res2 + ".");
		
		
		
	}
}