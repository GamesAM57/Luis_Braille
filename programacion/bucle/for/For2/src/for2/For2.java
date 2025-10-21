package for2;

import java.util.Scanner;

public class For2 {
    public static void main(String[] args) {
	Scanner intro = new Scanner(System.in);
	int num; 
	
	System.out.println("Indica un numero: ");
	num = intro.nextInt();
	
	for (int i = 0; i <= num; i++){
	    System.out.println(i);
	}
    }
    
}
