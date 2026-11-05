// Crear una clase Bar que compruebe el funcionamiento del sistema productor
// consumidor entre el camarero el cliente y el recurso compartido vaso.


package p0;


public class Bar {

    public static void main(String[] args) {
        
        // Creación del recurso compartido.
        Vaso vaso = new Vaso();
        
        // Creación de los hilos:
        
        // EL PRODUCTOR.
        HiloCamarero camarero = new HiloCamarero(vaso);
        
        // EL CONSUMIDOR.
        HiloCliente cliente1 = new HiloCliente(vaso);
        HiloCliente cliente2 = new HiloCliente(vaso);
        HiloCliente cliente3 = new HiloCliente(vaso);
        
        vaso.aniadirCliente(cliente1.getName()); 
        vaso.aniadirCliente(cliente2.getName()); 
        vaso.aniadirCliente(cliente3.getName()); 
        
        // Arranque de los hilos (no importa el orden).
        camarero.start();
        cliente1.start();
        cliente2.start();
        cliente3.start();
                
        
        // Encolamiento de ambos con respecto al main.
        try {
            camarero.join();
            cliente1.join(); 
            cliente2.join(); 
            cliente3.join(); 
             
            
        } catch(InterruptedException ie) {
            System.out.println("ERROR DE ENCOLAMIENTO");
            ie.printStackTrace();
        }
                
        System.out.println("\n\n --- MAIN DICE QUE SE TERMINO --- \n");
    }    

}