
package lecturaescrituraficheros;

import java.io.IOException;
import java.util.*;

public class Main{

    public static void main(String[] args) {
        try {
            Fichero f = new Fichero();
            LinkedList<Alumnos> lista = f.leerNotas();
        } catch (IOException ex) {
            System.getLogger(Main.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
       
    }
    
}
