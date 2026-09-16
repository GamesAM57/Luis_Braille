package ventatiendas;
public class Tienda {
    String direccion;
    int chicle;
    int cocacola;
    int donut; 
    static int globChicle;
    static int globCocacola;
    static int globDonut;
    
    Tienda(String nom){
	direccion = nom;
    }
    
    void comprarChicle(int n){
	chicle += n;
	globChicle += n;
    }
    
    void comprarCocaCola(int n){
	cocacola += n;
	globCocacola += n;
    }
    
    void comprarDonut(int n){
	donut += n;
	globDonut += n;
    }
    
    void consultarVentasParciales(){
	System.out.println("EN LA TIENDA DE "+direccion+" LAS VENTAS SON:");
	System.out.println("CHICLES = "+chicle);
	System.out.println("COCACOLAS = "+cocacola);
	System.out.println("DONUTS = "+donut);
    }
    
    static void consultarVentasTotales(){
	System.out.println("EN TOTAL LAS TIENDAS HAN VENDIDO:");
	System.out.println("TOTAL CHICLES = "+globChicle);
	System.out.println("TOTAL COCACOLAS = "+globCocacola);
	System.out.println("TOTAL DONUTS = "+globDonut);
    }
}
