
package lecturaescrituraficheros;

public class Alumnos {
    private double nota;
    private String nombre;

    public Alumnos(double nota, String nombre) {
        this.nota = nota;
        this.nombre = nombre;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre + ": "+nota;
    }
    
    
}
