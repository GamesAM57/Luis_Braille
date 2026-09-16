package diffminutos;
import java.util.*;
public class DiffMinutos {
    public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	int h1, m1, h2, m2;
	System.out.println("Indicame la primera hora: ");
	h1 = sc.nextInt();
	System.out.println("Indicame el primer minuto: ");
	m1 = sc.nextInt();
	System.out.println("Indicame la primera hora: ");
	h2 = sc.nextInt();
	System.out.println("Indicame el primer minuto: ");
	m2 = sc.nextInt();
	if (h1 >= 0 && h1 <= 23 && h2 >= 0 && h2 <= 23 && m1 >= 0 && m1 <=59 &&  m1 >= 0 && m2 <=59)
	    System.out.println("La diferencia de minutos es: "+diferenciaMin(h1, m1, h2, m2));
	else
	    System.out.println("Has indicado un numero incorrecto, utiliza formato 24h");
    }
    
    static int diferenciaMin(int hora1, int minuto1, int hora2, int minuto2){
	int difHora = 60*(may(hora1, hora2) - menor(hora1,hora2));
	int diferencia = difHora +(minuto1-minuto2);
	return diferencia;
    }
    
    static int may(int n1, int n2){
	if(n1 > n2){
	    return n1;
	} else {
	    return n2;
	}
    }
    
    static int menor(int n1, int n2){
	if(n1 < n2){
	    return n1;
	} else {
	    return n2;
	}
    }
}
