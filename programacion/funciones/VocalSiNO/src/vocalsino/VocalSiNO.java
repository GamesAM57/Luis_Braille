package vocalsino;
public class VocalSiNO {
    public static void main(String[] args) {
	char letra = 'a';
	System.out.println("La letra "+letra+" es vocal?: "+vocal(letra));
    }
    
    static boolean vocal(char l){
	if (l=='a'||l=='e'||l=='i'||l=='o'||l=='u'||l=='A'||l=='E'||l=='I'||l=='O'||l=='U'){
	    return(true);
	} else {
	    return(false);
	}
    }
}
