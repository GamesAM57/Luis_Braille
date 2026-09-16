package arrayminmax01;
import java.util.*;
public class ArrayMinMax01 {
    public static void main(String[] args) {
	int array[] = new int[15];
	
	array = generarValores(array);
	System.out.println(Arrays.toString(array));
	System.out.println("El número mayor: "+mayor(array));
	System.out.println("El número menor: "+menor(array));
	
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
    
    static int mayor(int array[]){
	int pos = 0;
	int mayor = array[0];
	while (pos < array.length){
	    mayor = array[pos] < mayor ? mayor : array[pos];
	    pos++;
	}
	return mayor;
    }
    
    static int menor(int array[]){
	int pos = 0;
	int menor = array[0];
	while (pos < array.length){
	    menor = array[pos] > menor ? menor : array[pos];
	    pos++;
	}
	return menor;
    }
  }
