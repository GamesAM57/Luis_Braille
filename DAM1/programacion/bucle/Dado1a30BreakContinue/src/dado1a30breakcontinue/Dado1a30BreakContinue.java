package dado1a30breakcontinue;
public class Dado1a30BreakContinue {
    public static void main(String[] args) {
	int cont = 0;
	while (true){
	    int num = (int) (Math.random()* 30) + 1;
	    
	    if (num >= 10 && num <=20 && num != 15){
		continue;
	    } else if (num == 15){
		break;
	    } 
	    cont ++;
	    if (cont % 5 == 0){
		System.out.println(num);
	    } else {
		System.out.print(num+"\t");
	    }
	}
	System.out.println("");
	System.out.println("Num tiradas: " + cont);
    } 
}
