package bezanillaliam_examen2_modeloa;
import java.util.*;
public class BezanillaLiam_modeloA_Arr {
    static Scanner intro = new Scanner(System.in);
    /*En el main solicitamos el numero de posiciones, validando que es un valor positivo con el while
    También recorremos el array con el numero de posiciones indicadas por el usuario rellenando cada posición
    hasta que termine, arra.length.
    Cada numero que pedimos validamos que esté entre 0 y 9
    Llamamos a las diferentes funciones para sacar el min y max y ver la frecuencia.
    También mostramos los mensajes de respuesta.    
    */
    public static void main(String[] args) {
	System.out.print("Dime cuántos elementos debe tener el array (valor positivo): ");
	int numPos = intro.nextInt();
	while(numPos < 0){
	    System.out.print("Dime cuántos elementos debe tener el array (valor positivo): ");
	    numPos = intro.nextInt();
	    
	}
	int array[] = new int[numPos];
	int i  = 0, n;
	while(i<array.length){
	    System.out.print("Dame el elemento "+numPos+" del array (de [0a 9]): ");
	    n = intro.nextInt();
	    while(n < 0 || n > 9){
		System.out.println("Valor incorrecto (debe estar entre[0 y 9]");
		System.out.print("Dame el elemento "+numPos+" del array (de [0a 9]): ");
		n = intro.nextInt();
	    }
	    array[i] = n;
	    i ++;
	}
	System.out.println("");
	System.out.println("Datos: "+Arrays.toString(array));
	int t[] = maxMin(array);
	System.out.println("Valor minimo: "+t[0]); //Está ordenado de mayor a menor entonces el menor es la primera posición.
	System.out.println("Valor maximo: "+t[t.length-1]); //Está ordenado de mayor a menor entonces el mayor es la última posición.
	System.out.println("Frecuencia: "+Arrays.toString(frecuencias(array)));
    }
    
    /*Para ver el mayor y menor ordenamos el array de menor a mayor la primera posicion es el num menor y la ultima el mayor
    como solo podemos retornar un valor y tenemos que devolver dos numeros lo haremos con un array de dos posiciones.    
    */
    static int[] maxMin(int t[]){
	Arrays.sort(t);
	int t2[] = new int[2];
	t2[0] = t[0];
	t2[1] = t[t.length-1];
	return t2;
    }
    
    /*
    este array encuentra el numero de numeros que hay, creamos un array de 10 pos para mostrar todos los numeros
    */
    static int[] frecuencias(int t[]){
	int frecuencias[] = new int[10];
	int i = 0, numero=0;
	while (i < t.length || i < frecuencias.length){ //recorremos el array t hasta que acabe o acabe el de frecuencias, significando que hemos contado todos los numeros
	    int cont = 0;
	    if(Arrays.binarySearch(t, i) >= 0){ //por cada vuelta primero miraremos si existe, si binary search devuelve un negativo no existe el numero y pasamos al siguiente
		for(int n : t){
		    if(i == n){
			cont ++;
		    }
		}
	    }
	    frecuencias[i] = cont;
	    i ++;
	}
	return frecuencias;
    }
}
