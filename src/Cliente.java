import java.util.List;

public class Cliente {
    private String nombre;
    private String telefono;
    private String direccion;

    public Cliente(String nombre, String telefono,String direccion) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "\033[0;1m Cliente\033[0;0m \n" +
                "\033[0;1m Nombre:\033[0;0m " + nombre + '\n' +
                "\033[0;1m Telefono:\033[0;0m " + telefono + '\n'+
                "\033[0;1m Direccion:\033[0;0m "+direccion+"\n";
    }
}
