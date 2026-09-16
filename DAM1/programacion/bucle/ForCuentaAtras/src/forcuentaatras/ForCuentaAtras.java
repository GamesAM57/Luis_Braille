package forcuentaatras;

import java.util.Scanner;

public class ForCuentaAtras {
    public static void main(String[] args) {
	Scanner intro = new Scanner(System.in);
	int num; 
	
	System.out.println("Indica un número: ");
	
	for (num = intro.nextInt(); num >= 0; num --){
	    System.out.println(num);
	}
    }
    
}
