import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;

public class GestionFicherosCSV implements GestionFicheros {
    Path dirFicheros;
    Path fichClientes;
    Path fichPagos;
    final String S = ",";
    final String F = ";";
    Terminal terminal;

    public GestionFicherosCSV(Terminal terminal) throws IOException {
        dirFicheros = Path.of("datos");
        fichClientes = dirFicheros.resolve("clientes.csv");
        fichPagos = dirFicheros.resolve("pagos.csv");

        if(!Files.exists(dirFicheros))
            Files.createDirectories(dirFicheros);
        if(!Files.exists(fichClientes))
            Files.createFile(fichClientes);
        if(!Files.exists(fichPagos))
            Files.createFile(fichPagos);
        this.terminal = terminal;
    }

    @Override
    public LinkedList<Pagos> getPagos() {
        LinkedList<Pagos> listaPagos = new LinkedList<>();
        try(BufferedReader bf = Files.newBufferedReader(fichPagos)){
            bf.readLine();
            String fila = bf.readLine();

            while (fila!=null){
                fila = fila.substring(0, fila.indexOf(F));
                String[] t = fila.split(S);

                Pagos p = new Pagos(Integer.parseInt(t[0]), Integer.parseInt(t[1]), t[2], Double.parseDouble(t[3]), Double.parseDouble(t[4]), t[5], terminal);
                listaPagos.add(p);

                fila = bf.readLine();
            }
        } catch (IOException e) {
            terminal.mostrar("Error al obtener pagos. "+e.getMessage());
        }
        return  listaPagos;
    }

    @Override
    public LinkedList<Clientes> getClientes() {
        LinkedList<Clientes> listaClientes = new LinkedList<>();
        try(BufferedReader bf = Files.newBufferedReader(fichClientes)){
            bf.readLine();
            String fila = bf.readLine();

            while (fila!=null){
                fila = fila.substring(0, fila.indexOf(F));
                String[] t = fila.split(S);

                Clientes c = new Clientes(Integer.parseInt(t[0]), t[1], t[2], t[3]);
                listaClientes.add(c);

                fila = bf.readLine();
            }

        } catch (IOException e) {
            terminal.mostrar("Error al obtener clientes. " + e.getMessage());
        }
        return  listaClientes;
    }

    @Override
    public void setPagos(LinkedList<Pagos> listaPagos) {
        try(BufferedWriter bf = Files.newBufferedWriter(fichPagos, StandardCharsets.UTF_8, StandardOpenOption.CREATE)){
            bf.write("ID"+S+"CLIENTE"+S+"FECHA"+S+"IMPORTE"+S+"LITROS"+S+"COMBUSTIBLE"+F);
            for(Pagos p : listaPagos){
                bf.newLine();
                bf.write(p.getId()+S+p.getIdCliente()+S+p.getFechaRepostaje()+S+p.getImporte()+S+p.getLitros()+S+p.getCombusitble()+F);
            }
        } catch (IOException e){
            terminal.mostrar("Error al guardar pagos. "+e.getMessage());
        }
    }

    @Override
    public void setClientes(LinkedList<Clientes> listaClientes) {
        try(BufferedWriter bf = Files.newBufferedWriter(fichClientes, StandardCharsets.UTF_8, StandardOpenOption.CREATE)){
            bf.write("ID"+S+"NOMBRE"+S+"TELEFONO"+S+"MATRICULA"+F);
            for(Clientes c : listaClientes){
                bf.newLine();
                bf.write(c.getId()+S+c.getNombre()+S+c.getTelefono()+S+c.getMatricula()+F);
            }
        } catch (IOException e){
            terminal.mostrar("Error al guardar clientes. "+e.getMessage());
        }
    }

    @Override
    public void setUnPago(Pagos p) {
        LinkedList<Pagos> listaPagos = getPagos();
        listaPagos.add(p);
        setPagos(listaPagos);
    }

    @Override
    public void setUnCliente(Clientes c) {
        LinkedList<Clientes> listaClientes = getClientes();
        listaClientes.add(c);
        setClientes(listaClientes);
    }
}
