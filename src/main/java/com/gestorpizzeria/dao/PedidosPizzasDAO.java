package main.java.com.gestorpizzeria.dao;

import main.java.com.gestorpizzeria.models.PedidosPizzas;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidosPizzasDAO {

    public PedidosPizzas insertar(PedidosPizzas pedidoPizzas) throws SQLException {
        String sql = "INSERT INTO pedidos_pizzas(pedido_id, pizza_id,cantidad) VALUES(?, ?, ?)";
        try (Connection conn = SQLiteConexion.conectar(); PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, pedidoPizzas.getPedido_id());
            ps.setInt(2, pedidoPizzas.getPizza_id());
            ps.setInt(3, pedidoPizzas.getCantidad());

            ps.executeUpdate();

            // Obtener el ID generado
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int idGenerado = rs.getInt(1);

                    // Consultar la fila completa
                    String selectSQL = "SELECT * FROM pedidos_pizzas WHERE id = ?";
                    try (PreparedStatement selectStmt = conn.prepareStatement(selectSQL)) {
                        selectStmt.setInt(1, idGenerado);
                        try (ResultSet result = selectStmt.executeQuery()) {
                            if (result.next()) {
                                PedidosPizzas pedidosPizzasAgregado = new PedidosPizzas(
                                        result.getInt("id"),
                                        result.getInt("pedido_id"),
                                        result.getInt("pizza_id"),
                                        result.getInt("cantidad")

                                );


                                // Si tenés más campos, los agregás acá
                                return pedidosPizzasAgregado;
                            }
                        }
                    }
                }


            }
        }
        return null;
    }

    public List<PedidosPizzas> listar() throws SQLException {
        List<PedidosPizzas> pizzasPedidas = new ArrayList<>();
        String sql = "SELECT * FROM pedidos_pizzas";
        try (Connection conn = SQLiteConexion.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                pizzasPedidas.add(new PedidosPizzas(
                        rs.getInt("id"),
                        rs.getInt("pedido_id"),
                        rs.getInt("pizza_id"),
                        rs.getInt("cantidad")


                ));

            }
        }
        return pizzasPedidas;
    }
}