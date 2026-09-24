import java.util.LinkedList;

public interface GestionFicheros {
    public LinkedList<Pagos> getPagos();
    public LinkedList<Clientes>  getClientes();
    public void setPagos(LinkedList<Pagos> listaPagos);
    public void setClientes(LinkedList<Clientes> listaClientes);
}
