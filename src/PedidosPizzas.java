public class PedidosPizzas {
    private int id,pedido_id,pizza_id,cantidad;

    public PedidosPizzas(int id, int pedido_id, int pizza_id, int cantidad) {
        this.id = id;
        this.pedido_id = pedido_id;
        this.pizza_id = pizza_id;
        this.cantidad = cantidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPedido_id() {
        return pedido_id;
    }

    public void setPedido_id(int pedido_id) {
        this.pedido_id = pedido_id;
    }

    public int getPizza_id() {
        return pizza_id;
    }

    public void setPizza_id(int pizza_id) {
        this.pizza_id = pizza_id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
