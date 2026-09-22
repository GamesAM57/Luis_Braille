import java.util.Collection;

public interface GestionFicheros {
    public Collection<Pagos> getPagos();
    public Collection<Clientes>  getClientes();
    public void setPagos(Pagos p);
    public void setClientes(Clientes c);
}
