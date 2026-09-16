package ventatiendas;
public class PruebaTienda {
    
    public static void main(String arg[]){
	Tienda t1 = new Tienda("Sol");
	Tienda t2 = new Tienda("Atocha");
    
	t1.comprarChicle(12);
	t1.comprarCocaCola(8);
	t1.comprarDonut(10);
	
	t2.comprarChicle(15);
	t2.comprarCocaCola(10);
	t2.comprarDonut(7);
	
	t1.consultarVentasParciales();
	t2.consultarVentasParciales();
	Tienda.consultarVentasTotales();
    }
    
   
}
