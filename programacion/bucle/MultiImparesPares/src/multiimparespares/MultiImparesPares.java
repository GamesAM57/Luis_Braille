package multiimparespares;
public class MultiImparesPares {
    public static void main(String[] args) {
	for(int i = 1; i <= 10; i++){
	    if (i%2==0){
		continue;
	    }
	    for(int j = 0; j <= 10; j++){
		if(j%2!=0){
		    continue;
		}
		System.out.println(i+" * "+j+" = "+i*j);
	    }
	    System.out.println("---------------------------");
	}
    }
}
