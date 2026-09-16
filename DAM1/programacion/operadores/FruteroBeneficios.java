import java.util.*;

public class FruteroBeneficios{
	public static void main(String[] arg){
		Scanner intro = new Scanner(System.in);
		final float MANZANAS_PRC = 2.35f;
		final float PERAS_PRC = 1.95f;
		float manzanas_ventas, peras_ventas;
		
		System.out.print("Indica cuantos kg de manzanas has tenido: ");
		manzanas_ventas = intro.nextFloat();
		System.out.print("Indica cuantos kg de peras has tenido: ");
		peras_ventas = intro.nextFloat();
		
		manzanas_ventas *= MANZANAS_PRC;
		peras_ventas *= PERAS_PRC;
		System.out.print("Beneficio manzanas: " + manzanas_ventas);
		System.out.print("Beneficio peras: " + peras_ventas);
	}
}