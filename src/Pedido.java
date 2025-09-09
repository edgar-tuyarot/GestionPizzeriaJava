import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Pedido {
    private int id;
    private int cliente_id;
    private double total;
    private String fecha;
    private String estado;
    private Cliente cliente;
    private List<Pizza> pizzas;

    public Pedido(int id,int cliente_id,String fechaFormateada, String estado, double total ) {
        this.id = id;
        this.total = total;
        this.fecha = fechaFormateada;
        this.estado = "Pendiente";
        this.cliente_id = cliente_id;
    }

    public int getCliente_id() {
        return cliente_id;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return  "-----------------------------\n"+
                "\033[0;1m Pedido n°: \033[0;0m " + this.id +"\n"+
                "\033[0;1m Para: \033[0;0m"+ cliente.getNombre()+"\n"+
                "\033[0;1m Direccion: \033[0;0m"+ cliente.getDireccion()+"\n"+
                "\033[0;1m Telefono: \033[0;0m"+cliente.getTelefono()+"\n"+
                "\033[0;1m Fecha:\033[0;0m" + fecha + '\n' +
                "\033[0;1m Total:\033[0;0m $" + total + "\n"+
                "\033[0;1m Estado:\033[0;0m " + estado + '\n'+
                "\033[0;1m "+pizzas+
                "-----------------------------\n";


    }


}
