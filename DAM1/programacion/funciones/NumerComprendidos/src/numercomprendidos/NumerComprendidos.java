package numercomprendidos;
import java.util.*;
public class NumerComprendidos {
    public static void main(String[] args) {
	Scanner intro = new Scanner(System.in);
	int n1, n2;
	
	System.out.println("Indica el primer número: ");
	n1 = intro.nextInt();
	System.out.println("Indica el segundo número: ");
	n2 = intro.nextInt();
	
	mostrarNum(n1, n2);
    }
    
    static void mostrarNum(int num1, int num2){
	
	/*
	int mayor = a > b ? a : b;
	int menor = a < b ? a : b;
	*/
	
	int may, menor;
	if (num1 - num2 >= 0){
	    may = num1;
	    menor = num2;
	} else {
	    may = num2;
	    menor = num1;
	}
	
	while(may >= menor){
	    System.out.println(may);
	    may --;
	}
    }
}
