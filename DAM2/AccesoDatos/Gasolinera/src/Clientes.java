import java.util.Scanner;

public class Clientes implements Comparable<Clientes> {
    public static int siguienteIdCliente;
    private int id;
    private String nombre;
    private String telefono;
    private String matricula;
    Scanner sc = new Scanner(System.in);

    public Clientes() {
    }

    public Clientes(int id, String nombre, String telefono, String matricula) {
        this.id = id;
        setNombre(nombre.trim());
        setTelefono(telefono.trim());
        setMatricula(matricula.trim());
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        while(isVacio(nombre)){
            System.out.println("El nombre no puede estar vacío, indicalo otra vez:");
            nombre = sc.nextLine();
        }
        this.nombre = nombre.strip();
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        while(isVacio(telefono)){
            System.out.println("El telefono no puede estar vacío, indicalo otra vez:");
            telefono = sc.nextLine();
        }
        this.telefono = telefono.strip();
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        while( isVacio(matricula)){
            System.out.println("La matricula no puede estar vacía, indicala otra vez:");
            matricula = sc.nextLine();
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
