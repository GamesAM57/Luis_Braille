package fechacorrectabisiesto;
import java.util.*;
public class FechaCorrectaBisiesto {
    public static void main(String[] args) {
	Scanner intro = new Scanner(System.in);
        int dia, mes, anyo;
	
	System.out.println("Indica un año válido YYYY");
	anyo = intro.nextInt();
	
	
	if(anyo != 0 && anyo < 2026){
	    System.out.println("Indica un mes válido mm");
	    mes = intro.nextInt();
	    System.out.println("Indica un dia válido dd");
	    dia = intro.nextInt();
	    switch(mes){
		case 1, 3, 5, 7, 8, 10, 12 ->{
		    if(dia <= 31)
			System.out.println("Fecha correcta.");
		    else
			System.out.println("Fecha incorrecta.");
		}
		case 4, 6, 9, 11 ->{
		    if(dia <= 30)
			System.out.println("Fecha correcta.");
		    else
			System.out.println("Fecha incorrecta.");
		}
		case 2 ->{
		    if (anyo%4==0 && (anyo%100!=0 || anyo%400==0)){
			if (dia <= 29) {
			    System.out.println("Fecha correcta.");
			} else{
			   System.out.println("Fecha incorrecta."); 
			}
		    } else if(dia <= 28)
			System.out.println("Fecha correcta.");
		    else
			System.out.println("Fecha incorrecta.");
		}
	    }
	} else {
	    System.out.println("El año introducido no es correcto.");
	}
    }
    
}
