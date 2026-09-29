import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;

public class Pagos implements Comparable<Pagos>{
    public static int siguienteIdPago; //1 por defecto
    private int id;
    private int idCliente;
    private LocalDate fechaRepostaje;
    public static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private double importe;
    private double litros;
    private String combusitble;
    Scanner sc = new Scanner(System.in);


    public Pagos(int id, int idCliente, String fechaRepostaje, double importe, double litros, String combusitble) {
        this.id = id;
        this.idCliente = idCliente;
        setFechaRepostaje(fechaRepostaje);
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


    public LocalDate getFechaRepostaje() {
        return fechaRepostaje;
    }

    public void setFechaRepostaje(String fechaRepostaje) {
        if(fechaRepostaje == ""){
            this.fechaRepostaje = LocalDate.now();
        } else {
            boolean valida = false;
            do{
                try {
                    this.fechaRepostaje = LocalDate.parse(fechaRepostaje, FORMAT);
                    valida = true;
                } catch (DateTimeParseException e) {
                    System.out.println("Fecha inválida. Debe tener el formato dd/mm/yyyy y ser una fecha real.");
                    System.out.print("Introduce la fecha (dd/mm/yyyy): ");
                    fechaRepostaje = sc.nextLine();
                }
            } while(!valida);
        }

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

    @Override
    public int compareTo(Pagos o) {
        int n = o.getFechaRepostaje().compareTo(this.fechaRepostaje);
        if(n==0){
            n = o.getId()-this.getId();
        }
        return n;
    }
}
