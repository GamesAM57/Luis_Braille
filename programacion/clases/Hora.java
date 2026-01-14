package ejercicio7_6_clases;
public class Hora {
    private byte hora;
    private byte minuto;
    private byte segundo;
    
    void setHora(byte n){
	if(n>= 0 && n <= 23){
	    hora = n;
	} else {
	    hora = 0;
	}
    }
    
    void setMinuto(byte n){
	if(n>= 0 && n <= 60){
	    minuto = n;
	} else {
	    minuto = 0;
	}
    }
    
    void setSegundo(byte n){
	if(n>= 0 && n <= 60){
	    segundo = n;
	} else {
	    segundo = 0;
	}
    }
    
    void incrementaSegundo(){
	if(segundo <= 58){
	    segundo ++;
	} else if( segundo == 59 ){
	    segundo = 0;
	    if(minuto <= 58){
		minuto ++;
	    } else if ( minuto == 59){
		minuto = 0;
		if (hora <= 22){
		    hora ++;
		} else {
		    hora = 0;
		}
	    }
	}
    }
    
    void getHora(){
	System.out.println(hora+":"+minuto+":"+segundo);
    }
}
