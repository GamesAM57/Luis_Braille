package letradni;
import java.util.*;
public class LetraDNI {
    public static void main(String[] args) {
	Scanner intro = new Scanner(System.in);
	int dni, valor_dni;
	
	System.out.println("Introduce los 8 números de tu dni: ");
	dni = intro.nextInt();
	
	valor_dni = 0;
	if (dni <= 99999999) {
	    valor_dni = dni % 23;
	     
	}
	
	char letra = switch(valor_dni){
		case 0 -> 'T';
		case 1 -> 'R';
		case 2 -> 'W';
		case 3 -> 'A';
		case 4 -> 'G';
		case 5 -> 'M';
		case 6 -> 'Y';
		case 7 -> 'F';
		case 8 -> 'P';
		case 9 -> 'D';
		case 10 -> 'X';
		case 11 -> 'B';
		case 12 -> 'N';
		case 13 -> 'J';
		case 14 -> 'Z';
		case 15 -> 'S';
		case 16 -> 'Q';
		case 17 -> 'V';
		case 18 -> 'H';
		case 19 -> 'L';
		case 20 -> 'C';
		case 21 -> 'K';
		case 22 -> 'E';
		default -> {
		    System.out.print("Valor incorrecto.");
		    yield 'i';}
	   };
	
	System.out.println("Tu dni es: " + dni + letra);
    }
}
