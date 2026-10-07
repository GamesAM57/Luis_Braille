import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;
import java.util.LinkedList;

public class GestionFicherosJSON implements GestionFicheros{

    Path dirFicheros;
    Path fichClientes;
    Path fichPagos;
    Terminal terminal;

    final String INICIO_FICHERO = "{\n [\n";
    final String FINAL_FICHERO = " \n]\n}";
    final String F = "}";
    final String S1 = ",";
    final String S2 = ":";

    public GestionFicherosJSON(Terminal terminal) throws IOException {
        dirFicheros = Path.of("datosJSON");
        fichClientes = dirFicheros.resolve("clientes.json");
        fichPagos = dirFicheros.resolve("pagos.json");

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
            bf.readLine();
            String fila = bf.readLine();

            while (fila!=null&&!fila.contains("]")){
                fila = fila.substring(1, fila.indexOf(F));
                String[] t = fila.split(S1);

                Pagos p = new Pagos(Integer.parseInt(obtenerValor(t[0])), Integer.parseInt(obtenerValor(t[1])), obtenerValor(t[2]), Double.parseDouble(obtenerValor(t[3])), Double.parseDouble(obtenerValor(t[4])), obtenerValor(t[5]), terminal);
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

                Clientes c = new Clientes(Integer.parseInt(obtenerValor(t[0])), obtenerValor(t[1]), obtenerValor(t[2]), obtenerValor(t[3]));
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
            bf.write(INICIO_FICHERO);

            String lineaPagos = listaPagos.stream().map(this::pagoToJSON).reduce(
                    (String s1, String s2) -> {return s1+",\n"+s2;}
            ).orElse("");
            bf.write(lineaPagos);

            bf.write(FINAL_FICHERO);
        } catch (IOException e){
            terminal.mostrar("Error al guardar pagos. "+e.getMessage());
        }
    }

    @Override
    public void setUnPago(Pagos p) {
        try(BufferedWriter bf = Files.newBufferedWriter(fichPagos, StandardCharsets.UTF_8, StandardOpenOption.CREATE)){
            LinkedList<Pagos> listaPagos = getPagos();
            listaPagos.add(p);
            bf.write(INICIO_FICHERO);

            String lineaPagos = listaPagos.stream().map(this::pagoToJSON).reduce(
                    (String s1, String s2) -> {return s1+",\n"+s2;}
            ).orElse("");
            bf.write(lineaPagos);

            bf.write(FINAL_FICHERO);
        } catch (IOException e){
            terminal.mostrar("Error al guardar pagos. "+e.getMessage());
        }
    }

    @Override
    public void setClientes(LinkedList<Clientes> listaClientes) {
        try(BufferedWriter bf = Files.newBufferedWriter(fichClientes, StandardCharsets.UTF_8, StandardOpenOption.CREATE)){
            bf.write(INICIO_FICHERO);

            String lineaClientes = listaClientes.stream().map(this::clienteToJSON).reduce(
                    (String s1, String s2) -> {return s1+",\n"+s2;}
            ).orElse("");

            bf.write(lineaClientes);
            bf.write(FINAL_FICHERO);
        } catch (IOException e){
            terminal.mostrar("Error al guardar clientes. "+e.getMessage());
        }
    }

    @Override
    public void setUnCliente(Clientes c) {
        try(BufferedWriter bf = Files.newBufferedWriter(fichClientes, StandardCharsets.UTF_8, StandardOpenOption.CREATE)){
            LinkedList<Clientes> listaClientes = getClientes();
            listaClientes.add(c);
            bf.write(INICIO_FICHERO);

            String lineaClientes = listaClientes.stream().map(this::clienteToJSON).reduce(
                    (String s1, String s2) -> {return s1+",\n"+s2;}
            ).orElse("");

            bf.write(lineaClientes);
            bf.write(FINAL_FICHERO);
        } catch (IOException e){
            terminal.mostrar("Error al guardar clientes. "+e.getMessage());
        }
    }

    public String pagoToJSON(Pagos p){ return "  {\"id\": "+p.getId()+",\"cliente\": "+p.getIdCliente()+",\"fecha\": \""+p.getFechaRepostaje()+"\",\"importe\": "+p.getImporte()+",\"litros\": "+p.getLitros()+",\"combustible\": \""+p.getCombusitble()+"\"}"; }

    public String clienteToJSON(Clientes c){return "  {\"id: "+c.getId()+",nombre\": \""+c.getNombre()+"\",\"telefono\": \""+c.getTelefono()+"\",\"matricula\": \""+c.getMatricula()+"\"}";};

    public String obtenerValor(String valor){
        return valor.split(S2)[1].trim().replace("\"", "");
    }
}
