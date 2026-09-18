import java.util.Date;
import java.util.Scanner;

public class Pagos {
    private int id;
    private int idCliente;
    private Date fechaRepostaje;
    private double importe;
    private double litros;
    private String combusitble;
    Scanner sc = new Scanner(System.in);


    public Pagos(int id, int idCliente, Date fechaRepostaje, double importe, double litros, String combusitble) {
        this.id = id;
        this.idCliente = idCliente; //como validar??
        setFechaRepostaje(fechaRepostaje);//Mirar formato
        setImporte(importe);
        setLitros(litros);
        setCombusitble(combusitble);
    }

    public int getId() {
        return id;
    }


    public int getIdCliente() {
        return idCliente;
    }


    public Date getFechaRepostaje() {
        return fechaRepostaje;
    }

    public void setFechaRepostaje(Date fechaRepostaje) {
        if(fechaRepostaje == null) //mirar formato fecha
            this.fechaRepostaje = new Date();
        else
            this.fechaRepostaje = fechaRepostaje;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        while(importe <= 0) { //hay que validar lo de los decimales
            System.out.println("Has introducido un importe negativo, introducelo otra vez, importe mayor a 0:");
            sc.nextLine();
            importe = sc.nextDouble();
        }
        this.importe = importe;
    }

    public double getLitros() {
        return litros;
    }

    public void setLitros(double litros) {
        while(litros <= 0 ) { //falta como validar los decimales
            System.out.println("Has indicado litros incorrectos, introduce un número mayor a 0:");
            sc.nextLine();
            litros = sc.nextDouble();
        }
        this.litros = litros;

    }

    public String getCombusitble() {
        return combusitble;
    }

    public void setCombusitble(String combusitble) {
        while(combusitble == null || combusitble == ""){
            System.out.println("Has dejado el combusitble en blanco, introduce texto:");
            combusitble = sc.nextLine();
        }

        this.combusitble = combusitble;
    }

    @Override
    public String toString() {
        //Ver como cambio id cliente a nombre, llamar a cliente get nombre?
        return id+"\t\t"+idCliente+"\t\t"+fechaRepostaje+"\t\t"+importe+" €\t\t"+litros+"\t\t"+combusitble;
    }
}
