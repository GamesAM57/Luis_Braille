import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;

public class GestionFicherosJSON implements InterfazFicherosJSON{

    Path dirFicheros;
    Path fichClientes;
    Path fichPagos;
    Terminal terminal;

    final String F = "}";
    final String S1 = ",";
    final String S2 = ":";

    public GestionFicherosJSON(Terminal terminal) throws IOException {
        dirFicheros = Path.of("datosJSON");
        fichClientes = dirFicheros.resolve("clientes.json");
        fichPagos = dirFicheros.resolve("pagos.json");

        if(!Files.exists(dirFicheros))
            Files.createDirectories(dirFicheros);
        if(!Files.exists(fichClientes)) {
            Files.createFile(fichClientes);
            primerasLineas(fichClientes);
        } else {

        }
        if(!Files.exists(fichPagos)){
            Files.createFile(fichPagos);
            primerasLineas(fichPagos);
        } else {

        }
        this.terminal = terminal;
    }

    public void primerasLineas(Path path){
        try (BufferedWriter bf = Files.newBufferedWriter(path, StandardCharsets.UTF_8, StandardOpenOption.CREATE)){
            bf.write("{\n [");
        } catch (IOException e) {
            System.out.println("Error al preparar el fichero "+path.toString()+". "+e.getMessage());
        }
    }

    public void prepararFich(Path path){
        try (BufferedWriter bf = Files.newBufferedWriter(path, StandardCharsets.UTF_8, StandardOpenOption.CREATE); BufferedReader leer = Files.newBufferedReader(path)){
            leer.readLine();
            leer.readLine();
            String fila = leer.readLine();

            while(fila!=null&&!fila.contains("]")){
                bf.write(fila);
                bf.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error al preparar el fichero "+path.toString()+". "+e.getMessage());
        }
    }

    public void cerrarFichClientes(){
        try (BufferedWriter bf = Files.newBufferedWriter(fichClientes, StandardCharsets.UTF_8, StandardOpenOption.CREATE)){
            bf.newLine();
            bf.write(" ]\n}");
        } catch (IOException e) {
            System.out.println("Error al cerrar el fichero "+fichClientes.toString()+". "+e.getMessage());
        }
    }

    public void cerrarFichPagos(){
        try (BufferedWriter bf = Files.newBufferedWriter(fichPagos, StandardCharsets.UTF_8, StandardOpenOption.CREATE_NEW)){
            bf.newLine();
            bf.write(" ]\n}");
        } catch (IOException e) {
            System.out.println("Error al cerrar el fichero "+fichPagos.toString()+". "+e.getMessage());
        }
    }

    @Override
    public LinkedList<Pagos> getPagos() {
        LinkedList<Pagos> listaPagos = new LinkedList<>();
        try(BufferedReader bf = Files.newBufferedReader(fichPagos)){
            bf.readLine();
            bf.readLine();
            String fila = bf.readLine();

            while (fila!=null&&!fila.contains("]")){
                fila = fila.substring(1, fila.indexOf(F));
                String[] t = fila.split(S1);

                Pagos p = new Pagos(Integer.parseInt(t[1].split(S2)[0]), Integer.parseInt(t[1].split(S2)[0]), t[2].split(S2)[0], Double.parseDouble(t[3].split(S2)[0]), Double.parseDouble(t[4].split(S2)[0]), t[5].split(S2)[0], terminal);
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
            bf.readLine();
            String fila = bf.readLine();

            while (fila!=null&&!fila.contains("]")){
                fila = fila.substring(1, fila.indexOf(F));
                String[] t = fila.split(S1);

                Clientes c = new Clientes(Integer.parseInt(t[0].split(S2)[0]), t[1].split(S2)[0], t[2].split(S2)[0], t[3].split(S2)[0]);
                listaClientes.add(c);

                fila = bf.readLine();
            }

        } catch (IOException e) {
            terminal.mostrar("Error al obtener clientes. " + e.getMessage());
        }
        return  listaClientes;
    }

    @Override
    public void setPagos(Pagos p) {
        try(BufferedWriter bf = Files.newBufferedWriter(fichPagos, StandardCharsets.UTF_8, StandardOpenOption.APPEND)){
            bf.newLine();
            bf.write("  {\"id\": \""+p.getId()+"\",\"cliente\": \""+GestionClientes.obtenerClientePorId(p.getIdCliente(), getClientes()).getNombre()+"\",\"fecha\": \""+p.getFechaRepostaje()+"\",\"importe\": \""+p.getImporte()+"\",\"litros\": \""+p.getLitros()+"\",\"combustible\": \""+p.getCombusitble()+"\"},");
        } catch (IOException e){
            terminal.mostrar("Error al guardar pagos. "+e.getMessage());
        }
    }

    @Override
    public void setClientes(Clientes c) {
        try(BufferedWriter bf = Files.newBufferedWriter(fichPagos, StandardCharsets.UTF_8, StandardOpenOption.APPEND)){
            bf.newLine();
            bf.write("  {\"id\": \""+c.getId()+"\",\"nombre\": \""+c.getNombre()+"\",\"telefono\": \""+c.getTelefono()+"\",\"matricula\": \""+c.getMatricula()+"\"},");
        } catch (IOException e){
            terminal.mostrar("Error al guardar clientes. "+e.getMessage());
        }
    }
}
