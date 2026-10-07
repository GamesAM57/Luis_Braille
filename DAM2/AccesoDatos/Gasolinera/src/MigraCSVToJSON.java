import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class MigraCSVToJSON {

    static Path directorio = Path.of("datosJSON");
    static Path fichClientes = Path.of("clientes.json");
    static Path fichPagos = Path.of("pagos.json");

    public static void menuMigracion(Terminal terminal) throws IOException {
        if(comprobacionFicherosJSON()){
            terminal.mostrar("Existen ficheros JSON creados.");
        } else {
            String op = terminal.pedirStringObligatorio("Indica si o no para la migración CSV a JSON: ").toLowerCase();
            GestionFicherosCSV csv = new GestionFicherosCSV(terminal);
            GestionFicherosJSON json = new GestionFicherosJSON(terminal);
            if(op.equals("si")){
                json.setClientes(csv.getClientes());
                json.setPagos(csv.getPagos());

                terminal.mostrar("Se han migrado "+json.getClientes().size()+" clientes y "+json.getPagos().size()+" pagos a los ficheros:");
                terminal.mostrar("- "+fichClientes.toString());
                terminal.mostrar("- "+fichPagos.toString());

            } else if (op.equals("no")){
                terminal.mostrar("0 clientes y 0 pagos migrados.");
            } else {
                terminal.mostrar("No has indicado una opción valida para la migración.");
            }
        }
    }

    public static boolean comprobacionFicherosJSON(){


        return Files.exists(directorio)&&Files.exists(fichClientes)&&Files.exists(fichPagos);
    }
}
