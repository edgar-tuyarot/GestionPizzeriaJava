import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Pedido {
    private int id;
    private int cliente_id;
    private double total;
    private String fecha;
    private String estado;

    public Pedido(int id,int cliente_id,String estado, double total ) {
        this.id = id;
        this.total = total;
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String fechaFormateada = ahora.format(formato);
        this.fecha = fechaFormateada;
        this.estado = "Pendiente";
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

    @Override
    public String toString() {
        return "\033[0;1m Pedido \033[0;0m \n" +
                "-----------------------------\n"+
                "\033[0;1m Total:\033[0;0m $" + total + "\n"+
                "-----------------------------\n"+
                "\033[0;1m Fecha:\033[0;0m" + fecha + '\n' +
                "-----------------------------\n"+
                "\033[0;1m Estado:\033[0;0m " + estado + '\n';
    }


}
