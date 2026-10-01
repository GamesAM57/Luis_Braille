public class Clientes implements Comparable<Clientes> {
    public static int siguienteIdCliente;
    private int id;
    private String nombre;
    private String telefono;
    private String matricula;
    private Terminal terminal;

    public Clientes() {
    }

    public Clientes(int id, String nombre, String telefono, String matricula, Terminal terminal) {
        this.id = id;
        setNombre(nombre.trim());
        setTelefono(telefono.trim());
        setMatricula(matricula.trim());
        this.terminal = terminal;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        while(isVacio(nombre)){
            nombre = terminal.pedirString("El nombre no puede estar vacío, indicalo otra vez:");
        }
        this.nombre = nombre.strip();
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        while(isVacio(telefono)){
            telefono = terminal.pedirString("El telefono no puede estar vacío, indicalo otra vez:");
        }
        this.telefono = telefono.strip();
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        while( isVacio(matricula)){
            matricula = terminal.pedirString("La matricula no puede estar vacía, indicala otra vez:");
        }
        this.matricula = matricula.strip().toUpperCase();
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

    public boolean isVacio(String s){
        return s == null || s.isBlank();
    }
}
