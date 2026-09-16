package horasum1;
import java.util.*;

public class HoraSum1 {

    public static void main(String[] args) {
	Scanner intro = new Scanner(System.in);
	int h, m, s;
	
	System.out.println("Introduce la hora: hh");
	h = intro.nextInt();
	System.out.println("Introduce los minutos: mm");
	m = intro.nextInt();
	System.out.println("Introduce los segundos: ss");
	s = intro.nextInt();
	if (s < 59 && s >= 0 ){
	    s ++;
	} else if (s == 59){
	    s = 00;
	    if (m < 59 && m >= 0 ) {
		m ++;
	    } else if (m == 59){
		m = 00;
		if (h < 23 && s >= 0 ){
		    h ++;
		} else if (h == 23)
		    h = 00;
		else {
		    System.out.println("No es correcta la hora.");
		}
	    } else {
		System.out.println("No es correcta la hora.");
	    }
	} else {
	    System.out.println("No es correcta la hora.");
	}
	
	
	System.out.println("Hora actual +1 segunod: ["+h+":"+m+":"+s+"]");
    }
    
}
