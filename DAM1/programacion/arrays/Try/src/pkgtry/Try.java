package pkgtry;
import java.util.*;
public class Try {
    public static void main(String[] args) {
	int t[] = {1,2,3,4,5,7,8,9,10};
	int t2[]=new int[t.length+1];
	int nuevo = 6;
	int indiceInsert = Arrays.binarySearch(t, nuevo);
	if(indiceInsert < 0){
	    indiceInsert = (indiceInsert * -1) - 1;
	}
	System.out.println(Arrays.toString(t2));
	System.arraycopy(t, 0, t2, 0, indiceInsert);
	System.out.println(Arrays.toString(t2));
	t2[indiceInsert] = nuevo;
	System.out.println(Arrays.toString(t2));
	System.arraycopy(t, indiceInsert, t2, indiceInsert+1, t.length-(indiceInsert));
	System.out.println(Arrays.toString(t2));
    }
    
}
