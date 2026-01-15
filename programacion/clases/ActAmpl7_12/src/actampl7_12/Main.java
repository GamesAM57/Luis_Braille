package actampl7_12;
public class Main {
    static Ecuacion2Grado p1 = new Ecuacion2Grado(1, -3, 2);
    public static void main(String arg[]){
	
	muestraSolucion(p1.solucion());
	p1.setA(1);
	p1.setB(2);
	p1.setC(1);
	muestraSolucion(p1.solucion());
	p1.setA(1);
	p1.setB(0);
	p1.setC(1);
	muestraSolucion(p1.solucion());
    }
    
    static void muestraSolucion(double t[]){
	if(p1.discriminante() > 0){
	    System.out.println("Solucion 1: "+t[0]);
	    System.out.println("Solucion 2: "+t[1]);
	} else if (p1.discriminante() == 0){
	    System.out.println("Única solución: "+t[0]);
	} else {
	    System.out.println("No hay solución.");
	}
    }
}
