package muestranpares;
import java.util.*;
public class MuestraNPares {
    public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	int n;
	System.out.println("Indica el numero de pares que quieres mostrar:");
	n = sc.nextInt();
	muestraPares(n);
    }
    
    static void muestraPares(int n){
	int cont = 2;
	while(n>=1){
	    System.out.println(cont);
	    cont += 2;
	    n --;
	}
    }
}
