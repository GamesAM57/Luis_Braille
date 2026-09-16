package tablamulti;
import java.util.Scanner;
public class TablaMulti {
    public static void main(String[] args) {
	Scanner intro = new Scanner(System.in);
	int num;
	
	System.out.println("Indica un número: ");
	num = intro.nextInt();
	
	for(int i = 0; i <= 10; i++){
	    System.out.println(num * i);
	}
    }
    
}
