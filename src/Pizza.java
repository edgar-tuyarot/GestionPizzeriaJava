import java.util.List;

public class Pizza {
    private String nombre;
    private String tamaño;
    private double precio;
    private List<String> ingredientes;

    public Pizza(String nombre, String tamaño, double precio, List<String> ingredientes) {
        this.nombre = nombre;
        this.tamaño = tamaño;
        this.precio = precio;
        this.ingredientes = ingredientes;
    }

    @Override
    public String toString() {
        return nombre + " (" + tamaño + ") - $" + precio + " | Ingredientes: " + ingredientes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTamaño() {
        return tamaño;
    }

    public void setTamaño(String tamaño) {
        this.tamaño = tamaño;
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
