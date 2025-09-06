import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Pedido {
    private Cliente cliente;
    private List<Pizza> pizzas;
    private double total;
    private String fecha;
    private String estado;

    public Pedido(Cliente cliente, List<Pizza> pizzas, double total ) {
        this.cliente = cliente;
        this.pizzas = pizzas;
        this.total = total;



        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String fechaFormateada = ahora.format(formato);
        this.fecha = fechaFormateada;
        this.estado = "Pendiente";
    }

    @Override
    public String toString() {
        return "Pedido\n" +
                "-----------------------------\n"+
                cliente.toString() +
                "-----------------------------\n"+
                "*Pizzas:* " + pizzas +"\n"
                +"-------------------------\n"+
                "*Total:* $" + total + "\n"+
                "-----------------------------\n"+
                "*Fecha:* " + fecha + '\n' +
                "-----------------------------\n"+
                "*Estado:* " + estado + '\n';
    }
}
