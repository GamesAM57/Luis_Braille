package arrayminmax02;
import java.util.*;
public class ArrayMinMax02 {
    public static void main(String[] args) {
	int array[] = new int[15];
	
	array = generarValores(array);
	System.out.println(Arrays.toString(array));
	Arrays.sort(array);
	System.out.println("El num mayor: "+array[array.length - 1]);
	System.out.println("El num menor: "+array[0]);
    }
    static int[] generarValores(int array[]){
	int pos = 0;
	while (pos < array.length){
	    int n = (int)(Math.random()* 100) +1;
	    array[pos] = n;
	    pos++;
	}
	return array;
    }
}
