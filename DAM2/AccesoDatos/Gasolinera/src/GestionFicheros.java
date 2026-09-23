import java.util.Collection;

public interface GestionFicheros {
    public Collection<Pagos> getPagos();
    public Collection<Clientes>  getClientes();
    public void setPagos(Collection<Pagos> p);
    public void setClientes(Collection<Clientes> c);
}
