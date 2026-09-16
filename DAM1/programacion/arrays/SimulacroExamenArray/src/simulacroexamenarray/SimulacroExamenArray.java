package simulacroexamenarray;
import java.util.*;
public class SimulacroExamenArray {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
	int array[] = leeFavoritos();
	System.out.println(Arrays.toString(array));
	int numFav = calculaNumSuerte(array);
	System.out.println("Num suerte es "+numFav);
    }
    
    static int[] leeFavoritos(){
	int numPos = 0;
	int i = 0;
	System.out.println("Indica cuantos números vas a indicar");
	numPos = sc.nextInt();
	int t[] = new int[numPos];
	while(i < t.length){
	    System.out.println("Indica el numero para la pos " +i);
	    t[i] = sc.nextInt();
	    i ++;
	}
	Arrays.sort(t);
	return t;
    }
    
    static int calculaNumSuerte(int[] numerosFavoritos){
	int n1;
	int n2;
	while(numerosFavoritos.length > 1){
	    int posBorrado = (int) (Math.random()*numerosFavoritos.length-1);
	    n1 = numerosFavoritos[posBorrado];
	    numerosFavoritos = borrar(numerosFavoritos, posBorrado);
	    posBorrado = (int) (Math.random()*numerosFavoritos.length-1);
	    n2 = numerosFavoritos[posBorrado];
	    numerosFavoritos = borrar(numerosFavoritos, posBorrado);
	    n1 = (n1+n2)/2;
	    numerosFavoritos = insertarOrdenado(numerosFavoritos, n1);
	}
	return numerosFavoritos[0] ;
    }
    
    static int[] borrar(int t[], int n){
	System.arraycopy(t, n+1, t, n, t.length-(n+1));
	return Arrays.copyOf(t, t.length-1);
	
    }
    
    static int[] insertarOrdenado(int t[], int nuevo){
	int indiceInsert = Arrays.binarySearch(t, nuevo);
	int t2[]=new int[t.length+1];
	if(indiceInsert < 0){
	    indiceInsert = (indiceInsert * -1) - 1;
	}
//	System.out.println(Arrays.toString(t2));
	System.arraycopy(t, 0, t2, 0, indiceInsert);
//	System.out.println(Arrays.toString(t2));
	t2[indiceInsert] = nuevo;
//	System.out.println(Arrays.toString(t2));
	System.arraycopy(t, indiceInsert, t2, indiceInsert+1, t.length-(indiceInsert));
//	System.out.println(Arrays.toString(t2));
	return t2;
    }
}
