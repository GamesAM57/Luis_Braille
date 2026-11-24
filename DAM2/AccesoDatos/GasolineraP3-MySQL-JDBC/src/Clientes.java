import java.util.Locale;

public class Clientes implements Comparable<Clientes> {
    public static int siguienteIdCliente;
    private int id;
    private String nombre;
    private String telefono;
    private String matricula;

    public Clientes() {
    }

    public Clientes(int id, String nombre, String telefono, String matricula) {
        this.id = id;
        setNombre(nombre);
        this.nombre = nombre.trim();
        this.telefono = telefono.trim();
        this.matricula = matricula.trim().toUpperCase();
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setNombre(String nombre) {
        this.nombre = Character.toUpperCase(nombre.charAt(0))+nombre.substring(1);
    }

    @Override
    public String toString() {
        return id +"\t\t"+nombre+"\t\t"+telefono+"\t\t"+matricula;
    }

    @Override
    public int compareTo(Clientes o) {
        int i = this.nombre.toLowerCase().compareTo(o.getNombre().toLowerCase());
        if (i == 0){
            i = this.id-o.getId();
        }
        return i;
    }
}
