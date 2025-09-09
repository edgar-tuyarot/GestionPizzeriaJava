package main.java.com.gestorpizzeria.models;


public class Cliente {
    private int id;
    private String nombre;
    private String telefono;
    private String direccion;


    public Cliente(int id,String nombre, String telefono, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "\033[0;1m main.java.com.gestorpizzeria.models.Cliente:\033[0;0m " + id + "\n"+
                "\033[0;1m Nombre:\033[0;0m " + nombre + '\n' +
                "\033[0;1m Telefono:\033[0;0m " + telefono + '\n'+
                "\033[0;1m Direccion:\033[0;0m "+direccion+"\n";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
