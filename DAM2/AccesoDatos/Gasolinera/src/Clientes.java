import java.util.Scanner;

public class Clientes {
    private int id;
    private String nombre;
    private String telefono;
    private String matricula;
    Scanner sc = new Scanner(System.in);

    public Clientes(int id, String nombre, String telefono, String matricula) {
        this.id = id;
        setNombre(nombre);
        setTelefono(telefono);
        setMatricula(matricula);
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        while(nombre == "" || nombre == null){
            System.out.println("El nombre no puede estar vacío, indicalo otra vez:");
            nombre = sc.nextLine();
        }
        this.nombre = nombre.strip();
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        while(telefono == "" || telefono == null){
            System.out.println("El telefono no puede estar vacío, indicalo otra vez:");
            telefono = sc.nextLine();
        }
        this.telefono = telefono.strip();
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        while(matricula == "" || matricula == null){
            System.out.println("La matricula no puede estar vacía, indicala otra vez:");
            matricula = sc.nextLine();
        }
        //Hay que añadir para validar la matricula.
        this.matricula = matricula.strip().toUpperCase();
    }

    @Override
    public String toString() {
        return id +"\t\t"+nombre+"\t\t"+telefono+"\t\t"+matricula;
    }
}
