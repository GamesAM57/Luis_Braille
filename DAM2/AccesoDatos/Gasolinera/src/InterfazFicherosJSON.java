import java.util.LinkedList;

public interface InterfazFicherosJSON {
    LinkedList<Pagos> getPagos();
    LinkedList<Clientes>  getClientes();
    void setPagos(Pagos p);
    void setClientes(Clientes c);
}
