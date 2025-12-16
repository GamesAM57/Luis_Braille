package arraybonoloto01;
import java.util.*;
public class ArrayBonoLoto01 {
    public static void main(String[] args) {
	int array[] = new int[6];
	int apuesta[] = {1, 3, 11, 13, 33, 29};
	Arrays.sort(apuesta);
	generarValores(array);
	System.out.println(Arrays.toString(array));
	System.out.println(Arrays.toString(apuesta));
	System.out.println("El numero de aciertos es: "+contarAciertos(array, apuesta));
	
    }
    
    static int[] generarValores(int array[]){
	int pos = 0;
	while (pos < array.length){
	    int n = (int)(Math.random()* 49) +1;
	    array[pos] = n;
	    pos++;
	}
	Arrays.sort(array);
	return array;
    }
    
    static int contarAciertos(int bono[], int apu[]){
	int cont = 0;
	for(int n : apu){
	    if(Arrays.binarySearch(bono, n)>=0)
		cont ++;
	}
	return cont;
    }
}
