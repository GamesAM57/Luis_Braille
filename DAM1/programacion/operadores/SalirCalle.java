import java.util.*;

public class SalirCalle{
	public static void main(String[] arg){
		Scanner intro = new Scanner(System.in);
		boolean lloviendo, tareasTerm, biblio;
		
		System.out.print("Indica true o false si está lloviendo: ");
		lloviendo = intro.nextBoolean();
		System.out.print("Indica true o false si has terminado tus tareas: ");
		tareasTerm = intro.nextBoolean();
		System.out.print("Indica true o false si tienes que ir a la biblioteca: ");
		biblio = intro.nextBoolean();
		
		if ((lloviendo && tareasTerm) || biblio){
			System.out.print("Permiso salir cale: " + true);
		} else {
			System.out.print("Permiso salir cale: " + false);
		}
	}
}