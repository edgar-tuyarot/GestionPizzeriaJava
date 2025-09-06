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
        return "\033[0;1m Pedido \033[0;0m \n" +
                "-----------------------------\n"+
                cliente.toString() +
                "-----------------------------\n"+
                "\033[0;1m Pizzas:\033[0;0m \n" + pizzas +"\n"
                +"-------------------------\n"+
                "\033[0;1m Total:\033[0;0m $" + total + "\n"+
                "-----------------------------\n"+
                "\033[0;1m Fecha:\033[0;0m" + fecha + '\n' +
                "-----------------------------\n"+
                "\033[0;1m Estado:\033[0;0m " + estado + '\n';
    }


}
