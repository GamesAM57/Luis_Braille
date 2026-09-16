package fechasum1;
import java.util.*;
public class FechaSum1 {
    public static void main(String[] args) {
	Scanner intro = new Scanner(System.in);
	int dia, mes, anyo;
	
	System.out.println("Introduce un día: dd");
	dia = intro.nextInt();
	System.out.println("Introduce un mes: mm");
	mes = intro.nextInt();
	System.out.println("Introduce un anyo: YYYY");
	anyo = intro.nextInt();
	
	switch(mes){
		case 1, 3, 5, 7, 8, 10, 12 ->{
		    if(dia < 31)
			dia ++;
		    else if (dia == 31){
			dia = 1;
			if (mes < 12){
			    mes ++;
			} else if (mes == 12){
			    mes = 1;
			    anyo ++;
			    System.out.println("Mañana será: " + dia + "/" +mes+"/"+anyo+".");
			} else {
			    System.out.println("Fecha incorrecta.");
			}
		    } else {
			System.out.println("Fecha incorrecta.");
		    }			
		}
		case 4, 6, 9, 11 ->{
		    if(dia < 30)
			dia ++;
		    else if (dia == 30){
			dia = 1;
			if (mes < 12){
			    mes ++;
			} else if (mes == 12){
			    mes = 1;
			    anyo ++;
			    System.out.println("Mañana será: " + dia + "/" +mes+"/"+anyo+".");
			} else {
			    System.out.println("Fecha incorrecta.");
			}
		    } else {
			System.out.println("Fecha incorrecta.");
		    }	
		}
		case 2 ->{
		   if(dia < 28)
			dia ++;
		    else if (dia == 28){
			dia = 1;
			if (mes < 12){
			    mes ++;
			} else if (mes == 12){
			    mes = 1;
			    anyo ++;
			} else {
			    System.out.println("Fecha incorrecta.");
			    System.out.println("Mañana será: " + dia + "/" +mes+"/"+anyo+".");
			}
		    } else {
			System.out.println("Fecha incorrecta.");
		    }	
		}
	    }
	    
    }
    
}
