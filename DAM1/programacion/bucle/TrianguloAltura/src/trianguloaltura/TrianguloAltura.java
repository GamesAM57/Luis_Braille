package trianguloaltura;
import java.util.*;
public class TrianguloAltura {
    public static void main(String[] args) {
	Scanner intro = new Scanner(System.in);
	
	System.out.println("Indica un número: ");
	int num=intro.nextInt();
	
	for(int fila = 1; fila <= num; fila ++){
	    for(int i = 1; i <= num - fila; i++){
		System.out.print(" ");
	    }
	    for (int i = 1; i <= fila - 1; i++){
		System.out.print("* ");
	    }
	    System.out.println("*");
	    
	}
    }
}
