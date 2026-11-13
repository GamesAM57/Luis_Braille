
package pruebahilos;

public class PruebaHilos {

    public static void main(String[] args) {
        Hilo2 uno, dos;
        uno = new Hilo2("Jamaica");
        dos = new Hilo2("Fiji");
        
        
        try {
            
            uno.join();
            
        } catch (InterruptedException ex) {
            System.getLogger(PruebaHilos.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
        
        
        try {
            
            dos.join();
            
        } catch (InterruptedException ex) {
            System.getLogger(PruebaHilos.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
        
        System.out.println("Fin del main.");
        
    }
    
}
