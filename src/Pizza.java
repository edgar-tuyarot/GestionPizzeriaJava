import java.util.List;


public class Pizza {
    private int id;
    private String nombre;
    private String tamanio;
    private double precio;
    private List<String> ingredientes;

    public Pizza(int id,String nombre, String tamanio, double precio, List<String> ingredientes) {
        this.id = id;
        this.nombre = nombre;
        this.tamanio = tamanio;
        this.precio = precio;
        this.ingredientes = ingredientes;
    }

    @Override
    public String toString() {
        return  nombre + " (" + tamanio + ")\n"+
                "$" + precio + "\n" +
                ingredientes+"\n";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTamaño() {
        return tamanio;
    }

    public String getTamanio() {
        return tamanio;
    }

    public void setTamaño(String tamaño) {
        this.tamanio = tamaño;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public List<String> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<String> ingredientes) {
        this.ingredientes = ingredientes;
    }

}
