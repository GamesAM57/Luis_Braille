import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Pagos implements Comparable<Pagos>{
    public static int siguienteIdPago;
    private final int id;
    private final int idCliente;
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
        if(fechaRepostaje.isEmpty()){
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
        while(importe <= 0 || comprobarDecimales(importe)) {
            if(importe<=0)
                System.out.println("Has introducido un importe negativo, introducelo otra vez, importe mayor a 0:");
            else
                System.out.println("Tienes que indicar un número con máximo dos decimales:");

            importe = sc.nextDouble();
        }
        this.importe = importe;
    }

    public double getLitros() {
        return litros;
    }

    public void setLitros(double litros) {
        while(litros <= 0 || comprobarDecimales(litros)) {
            if(importe<=0)
                System.out.println("Has indicado litros incorrectos, introduce un número mayor a 0:");
            else
                System.out.println("Tienes que indicar un número con máximo dos decimales:");

            litros = sc.nextDouble();
        }
        this.litros = litros;
    }

    public String getCombusitble() {
        return combusitble;
    }

    public void setCombusitble(String combusitble) {
        while(combusitble == null || combusitble.isEmpty()){
            System.out.println("Has dejado el combusitble en blanco, introduce texto:");
            combusitble = sc.nextLine();
        }

        this.combusitble = combusitble;
    }

    @Override
    public String toString() {
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

    public boolean comprobarDecimales (double n){
        return Math.round(n * 100) / 100.0 != importe;
    }
}
