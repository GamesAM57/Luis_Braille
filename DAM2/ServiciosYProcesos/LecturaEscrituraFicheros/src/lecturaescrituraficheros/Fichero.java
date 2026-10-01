
package lecturaescrituraficheros;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.*;
import java.util.LinkedList;


public class Fichero {
    Path fichNotas;

    public Fichero() throws IOException {
        fichNotas = Path.of("notas.txt");
        
        if(!Files.exists(fichNotas))
            Files.createFile(fichNotas);
    }
    
    
    public LinkedList<Alumnos> leerNotas(){
        LinkedList<Alumnos> lista = new LinkedList<>();
        
        try (BufferedReader bf = Files.newBufferedReader(fichNotas)){
            String linea = bf.readLine();
            
            while(!linea.isEmpty()){
                String datosAlumno[] = linea.split(" ");
                
                Alumnos a = new Alumnos(Double.parseDouble(datosAlumno[1]), datosAlumno[0]);
                lista.add(a);
                linea = bf.readLine();
            }
        } catch (IOException ex) {
            System.getLogger(Fichero.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
        return lista;
    }
    
}
