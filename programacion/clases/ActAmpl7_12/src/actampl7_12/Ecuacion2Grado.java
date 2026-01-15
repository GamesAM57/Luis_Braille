package actampl7_12;
public class Ecuacion2Grado {
    private double a;
    private double b;
    private double c;
    
    Ecuacion2Grado(double a, double b, double c){
	this.a = a;
	this.b = b;
	this.c = c;
    }
    
    void setA(double n){
	a = n;
    }
    void setB(double n){
	b = n;
    }
    void setC(double n){
	c = n;
    }
    
    double discriminante(){
	return Math.pow(b, 2) - 4*a*c;
    }
    
    boolean espositivoDiscriminante(){
	if(discriminante() >= 0){
	    return true;
	} else
	    return false;
    }
    
    double[] solucion(){
	double res[] = new double[2];
	if(espositivoDiscriminante()){
	    res[0] = (-b+Math.sqrt(discriminante()))/(2*a);
	    res[1] = (-b-Math.sqrt(discriminante()))/(2*a);
	} else {
	    res[0]=Double.NaN;
	    res[1]=Double.NaN;
	}
	return res;
    }
}
