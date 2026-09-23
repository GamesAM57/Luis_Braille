import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class GestionFicherosCSV implements GestionFicheros {

    //Añadir cosntructor, tiene que throw la excepción ya que el que llame al método es responsable de tratarlo y sino no seguir ejecutando.

    @Override
    public LinkedList<Pagos> getPagos() {

        //leemos con buffered reader.

        return new LinkedList<>();
    }

    @Override
    public LinkedList<Clientes> getClientes() {

        return new LinkedList<Clientes>();
    }

    @Override
    public void setPagos(LinkedList<Pagos> p) {

    }

    @Override
    public void setClientes(LinkedList<Clientes> c) {


    }
}
