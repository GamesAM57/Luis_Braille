package calcdistancia;
import java.util.*;
public class CalcDistancia {
    public static void main(String[] args) {
	Scanner intro = new Scanner(System.in);
	double x1, x2, y1, y2;
	System.out.println("Indica el primer numero: ");
	x1 = intro.nextDouble();
	System.out.println("Indica el segundo numero: ");
	x2 = intro.nextDouble();
	System.out.println("Indica el tercer numero: ");
	y1 = intro.nextDouble();
	System.out.println("Indica el cuarto numero: ");
	y2 = intro.nextDouble();
	System.out.println("La distancia euclídea es: " + distancia(x1,x2,y1,y2));
    }
    static double distancia(double x1, double x2, double y1, double y2){
	double distance = Math.sqrt(Math.pow((x1-x2), 2)+Math.pow((y1-y2), 2));
	return distance;
    }
}
