package arbolmasalto;
import java.util.*;
public class ArbolMasAlto {
    public static void main(String[] args) {
	Scanner intro = new Scanner(System.in);
	int cm, etiqueta, cmAlto, etiquetaAlto;
	System.out.println("Introduce la altura en cm: ");
	cm = intro.nextInt();
	etiqueta = 0;
	cmAlto = 0;
	etiquetaAlto = 0;
	
	while(cm != -1){
	    cmAlto = cm > cmAlto ? cm : cmAlto;
	    etiquetaAlto = cm > cmAlto ? etiqueta : etiquetaAlto;
	    etiqueta ++;
	    System.out.println("Introduce la altura en cm: ");
	    cm = intro.nextInt();
	}
	System.out.println("El arbol con etiqueta "+etiquetaAlto+" mide "+cmAlto+".");
    } 
}
