import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Pagos implements Comparable<Pagos>{
    public static int siguienteIdPago;
    private final int id;
    private final int idCliente;
    public static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private String fechaRepostaje;
    private double importe;
    private double litros;
    private String combusitble;
    Terminal terminal;

    public Pagos(int id, int idCliente, String fechaRepostaje, double importe, double litros, String combusitble, Terminal terminal) {
        this.id = id;
        this.idCliente = idCliente;
        this.fechaRepostaje = fechaRepostaje;
        this.importe = importe;
        this.litros = litros;
        this.combusitble = combusitble;
        this.terminal = terminal;
    }

    public int getId() {
        return id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public double getImporte() {
        return importe;
    }

    public String getFechaRepostaje() {
        return fechaRepostaje;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public double getLitros() {
        return litros;
    }

    public void setLitros(double litros) {
        this.litros = litros;
    }

    public String getCombusitble() {
        return combusitble;
    }

    public void setCombusitble(String combusitble) {
        this.combusitble = combusitble;
    }

    public void setFechaRepostaje(String fechaRepostaje) {
        this.fechaRepostaje = fechaRepostaje;
    }

    @Override
    public String toString() {
        return id+"\t\t"+idCliente+"\t\t"+fechaRepostaje+"\t\t"+importe+" €\t\t"+litros+"\t\t"+combusitble;
    }

    @Override
    public int compareTo(Pagos o) {
        int n = o.formatFecha().compareTo(this.formatFecha());
        if(n==0){
            n = o.getId()-this.getId();
        }
        return n;
    }

    public LocalDate formatFecha(){
        return LocalDate.parse(fechaRepostaje, FORMAT);
    }

}
