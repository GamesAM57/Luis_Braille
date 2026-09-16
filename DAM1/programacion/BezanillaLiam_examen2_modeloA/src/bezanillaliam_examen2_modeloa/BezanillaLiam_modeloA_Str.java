package bezanillaliam_examen2_modeloa;
import java.util.*;
public class BezanillaLiam_modeloA_Str {
    static Scanner intro = new Scanner(System.in);
    /*en el main mostramos los mensajes y llamamos a las diferentes funciones*/
    public static void main(String[] args) {
	String nomCompleto = leeCadena();
	String[] ap = nomCompleto.split("\\s+"); //separamos por espacios, si hay 5 cuenta las 5 como separador
	String ultimosDos = obtieneparteApe1(ap[1]);
	System.out.println("");
	System.out.println("El nombre de usuario es: "+ultimosDos+obtieneParteNumero(nomCompleto));
    }
    
    /*lee la cadena y le quita los espacios de los dos lados*/
    static String leeCadena(){
	System.out.println("Introduce nombre y dos apellidos separados por espacios: ");
	System.out.println("");
	String nomComp = intro.nextLine();
	nomComp = nomComp.strip(); // así quitamos los espacios
	return nomComp; //devolvemos cadena limpia
    }
     /*En este lo que hacemos es extraer las últimas dos letras y las guardamos en un array que luego pasamos a un string en mayusculas*/
    static String obtieneparteApe1(String ap){
	char ultimosDos[] = new char[2];
	if(ap.length() >= 2){ 
	    ultimosDos[1] = ap.charAt(ap.length()-1);
	    ultimosDos[0] = ap.charAt(ap.length()-2);
	} else {
	    ultimosDos[0] = 'x'; //comprobamos si tiene menos de 2 letras y rellena la izquierda con una x
	    ultimosDos[1] = ap.charAt(ap.length()-1);
	}
	return String.valueOf(ultimosDos).toUpperCase(); //cambiamos el char array a una cadena y lo cambiamos a mayúsculas
    }
    
    /*Contamos cuantos dígitos tiene, si tiene menos de 1 (menor a nueve) ponemos un 0 a la izquierda*/
    static String obtieneParteNumero(String nom){
	int n = nom.length();
	String numCadena = new String();
	if(n < 10){ //si tiene menos de 1 (menor a nueve) ponemos un 0 a la izquierda
	    numCadena = numCadena.valueOf(0); // pasamos numero a string y concatenamos
	    numCadena = numCadena + numCadena.valueOf(n); // pasamos numero a string y concatenamos
	} else {
	    /*Como tenemos varios digitos si dividimos entre 10 conseguimos el primero
	    si sacamos el modulo de la division entre 10 sacamos el segundo digito
	    los pasamos a string.
	    */
	    numCadena = numCadena.valueOf(n / 10); 
	    numCadena = numCadena + numCadena.valueOf(n % 10);
	}
	return numCadena;
    }
}
