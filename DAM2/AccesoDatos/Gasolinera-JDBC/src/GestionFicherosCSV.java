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
    final String SEPARADOR = ",";
    final String FINAL_DE_LINEA = ";";
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

    public GestionFicherosCSV(Path dirFicheros, Path fichClientes, Path fichPagos, Terminal terminal) {
        this.dirFicheros = dirFicheros;
        this.fichClientes = fichClientes;
        this.fichPagos = fichPagos;
        this.terminal = terminal;
    }


    @Override
    public LinkedList<Pagos> getPagos() {
        LinkedList<Pagos> listaPagos = new LinkedList<>();
        try(BufferedReader bf = Files.newBufferedReader(fichPagos)){
            bf.readLine();
            String fila = bf.readLine();

            while (fila!=null){
                fila = fila.substring(0, fila.indexOf(FINAL_DE_LINEA));
                String[] t = fila.split(SEPARADOR);

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
                fila = fila.substring(0, fila.indexOf(FINAL_DE_LINEA));
                String[] t = fila.split(SEPARADOR);

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
            bf.write("ID"+ SEPARADOR +
                    "CLIENTE"+ SEPARADOR +
                    "FECHA"+ SEPARADOR +
                    "IMPORTE"+ SEPARADOR +
                    "LITROS"+ SEPARADOR +
                    "COMBUSTIBLE"+ FINAL_DE_LINEA);
            for(Pagos p : listaPagos){
                bf.newLine();
                bf.write(p.getId()+ SEPARADOR +p.getIdCliente()+ SEPARADOR +p.getFechaRepostaje()+ SEPARADOR +p.getImporte()+ SEPARADOR +p.getLitros()+ SEPARADOR +p.getCombusitble()+ FINAL_DE_LINEA);
            }
        } catch (IOException e){
            terminal.mostrar("Error al guardar pagos. "+e.getMessage());
        }
    }

    @Override
    public void setClientes(LinkedList<Clientes> listaClientes) {
        try(BufferedWriter bf = Files.newBufferedWriter(fichClientes, StandardCharsets.UTF_8, StandardOpenOption.CREATE)){
            bf.write("ID"+ SEPARADOR +"NOMBRE"+ SEPARADOR +"TELEFONO"+ SEPARADOR +"MATRICULA"+ FINAL_DE_LINEA);
            for(Clientes c : listaClientes){
                bf.newLine();
                bf.write(c.getId()+ SEPARADOR +c.getNombre()+ SEPARADOR +c.getTelefono()+ SEPARADOR +c.getMatricula()+ FINAL_DE_LINEA);
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
