package canicas;
public class Canicas {
    static int numCanica;
    String nomJugador;
    
    Canicas(String nomJugador){
	this.nomJugador = nomJugador;
	numCanica = 0;
	
    }
    
    void compra(int n){
	System.out.println(nomJugador+" ha comprado "+n+" canicas.");
	numCanica += n;
    }
    
    void mirar(){
	System.out.println(nomJugador+" mira la bolsa y ve "+numCanica+" canicas.");
    }
    
    void quitar(int n){
	if (numCanica-n >= 0){
	    System.out.println(nomJugador+" ha quitado "+n+" canicas.");
	    numCanica -= n;
	}else{
	    System.out.println("No hay suficientes canicas.");
	}
	
    }
}
