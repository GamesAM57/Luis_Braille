package max3num;
import java.util.*;
public class Max3Num {
    public static void main(String[] args) {
	Scanner intro = new Scanner(System.in);
	int n1, n2, n3;
	
	System.out.println("Indica el primer número: ");
	n1 = intro.nextInt();
	System.out.println("Indica el segundo número: ");
	n2 = intro.nextInt();
	System.out.println("Indica el tercer número: ");
	n3 = intro.nextInt();
	int max = maxNum(n1, n2);
	System.out.println("El mayor número es: " + maxNum(n3, max));
	
    }
    
    static int maxNum(int n1, int n2){
	return n1>n2 ? n1:n2;
    }
    
}
