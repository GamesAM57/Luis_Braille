import java.util.LinkedList;

public interface GestionFicheros {
    LinkedList<Pagos> getPagos();
    LinkedList<Clientes>  getClientes();
    void setPagos(LinkedList<Pagos> listaPagos);
    void setClientes(LinkedList<Clientes> listaClientes);
}
