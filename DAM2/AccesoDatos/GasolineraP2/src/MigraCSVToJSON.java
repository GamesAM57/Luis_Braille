import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class MigraCSVToJSON {

    static Path dirJSON = Path.of("datosJSON");
    static Path clientesJSON = Path.of("clientes.json");
    static Path pagosJSON = Path.of("pagos.json");

    public static void menuMigracion(Terminal terminal) throws IOException {
        if(comprobacionFicheros(dirJSON, clientesJSON, pagosJSON)){
            terminal.mostrar("Existen ficheros JSON creados.");
        } else {
            String op = terminal.pedirStringObligatorio("Indica si o no para la migración CSV a JSON: ").toLowerCase();

            if(op.equals("si")){
                Path dirCSV = Path.of(terminal.pedirString("Indica el nombre del directorio CSV: "));
                Path clientesCSV = Path.of(terminal.pedirString("Indica el nombre del fichero clientes CSV: "));
                Path pagosCSV = Path.of(terminal.pedirString("Indica el nombre del fichero pagos CSV: "));
                if(comprobacionFicheros(dirCSV, clientesCSV, pagosCSV)){
                    GestionFicherosJSON json = new GestionFicherosJSON(terminal);
                    GestionFicherosCSV csv = new GestionFicherosCSV(dirCSV, clientesCSV, pagosCSV, terminal);

                    json.setClientes(csv.getClientes());
                    json.setPagos(csv.getPagos());

                    terminal.mostrar("Se han migrado "+json.getClientes().size()+" clientes y "+json.getPagos().size()+" pagos a los ficheros:");
                    terminal.mostrar("- "+ clientesJSON.toString());
                    terminal.mostrar("- "+ pagosJSON.toString());
                } else {
                    terminal.mostrar("No existen los ficheros o directorios CSV.");
                }


            } else if (op.equals("no")){
                terminal.mostrar("0 clientes y 0 pagos migrados.");
            } else {
                terminal.mostrar("No has indicado una opción valida para la migración.");
            }
        }
    }

    public static boolean comprobacionFicheros(Path directorio, Path fichClientes, Path fichPagos){
        return Files.exists(directorio)&&Files.exists(fichClientes)&&Files.exists(fichPagos);
    }
}
