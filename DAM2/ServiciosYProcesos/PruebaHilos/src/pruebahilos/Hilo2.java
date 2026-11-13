package pruebahilos;


public class Hilo2 extends Thread{

    public Hilo2(String nom) {
        super(nom);
        this.start();
    }
    
    

    @Override
    public void run() {
        System.out.println("Hilo "+Thread.currentThread().getName()); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
}
