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

    public GestionFicherosCSV() throws IOException {
        dirFicheros = Path.of("datos");

        fichClientes = dirFicheros.resolve("clientes.csv");
        fichPagos = dirFicheros.resolve("pagos.csv");

        if(!Files.exists(dirFicheros))
            Files.createDirectories(dirFicheros);
        if(!Files.exists(fichClientes))
            Files.createFile(fichClientes);
        if(!Files.exists(fichPagos))
            Files.createFile(fichPagos);
    }

    @Override
    public LinkedList<Pagos> getPagos() {
        LinkedList<Pagos> listaPagos = new LinkedList<>();
        try(BufferedReader bf = Files.newBufferedReader(fichPagos)){
            bf.readLine();
            String fila = bf.readLine();

            while (fila!=null){
                String t[] = fila.split(";");

                Pagos p = new Pagos(Integer.parseInt(t[0]), Integer.parseInt(t[1]), t[2], Double.parseDouble(t[3]), Double.parseDouble(t[4]), t[5]);
                listaPagos.add(p);

                fila = bf.readLine();
            }

        } catch (IOException e) {
            System.out.println("Error al obtener pagos. "+e.getMessage());
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
                String t[] = fila.split(";");

                Clientes c = new Clientes(Integer.parseInt(t[0]), t[1], t[2], t[3]);
                listaClientes.add(c);

                fila = bf.readLine();
            }

        } catch (IOException e) {
            System.out.println("Error al obtener clientes. "+e.getMessage());
        }

        return  listaClientes;
    }

    @Override
    public void setPagos(LinkedList<Pagos> listaPagos) {
        try(BufferedWriter bf = Files.newBufferedWriter(fichPagos, StandardCharsets.UTF_8, StandardOpenOption.CREATE)){
            bf.write("ID;CLIENTE;FECHA;IMPORTE;LITROS;COMBUSTIBLE");
            bf.newLine();
            for(Pagos p : listaPagos){
                String fechaFormateada = Pagos.FORMAT.format(p.getFechaRepostaje());
                bf.write(p.getId()+";"+p.getIdCliente()+";"+fechaFormateada+";"+p.getImporte()+";"+p.getLitros()+";"+p.getCombusitble());
                bf.newLine();
            }
        } catch (IOException e){
            System.out.println("Error al guardar pagos. "+e.getMessage());
        }
    }

    @Override
    public void setClientes(LinkedList<Clientes> listaClientes) {
        try(BufferedWriter bf = Files.newBufferedWriter(fichClientes, StandardCharsets.UTF_8, StandardOpenOption.CREATE)){
            bf.write("ID;NOMBRE;TELEFONO;MATRICULA");
            bf.newLine();
            for(Clientes c : listaClientes){
                bf.write(c.getId()+";"+c.getNombre()+";"+c.getTelefono()+";"+c.getMatricula());
                bf.newLine();
            }
        } catch (IOException e){
            System.out.println("Error al guardar clientes. "+e.getMessage());
        }

    }
}
