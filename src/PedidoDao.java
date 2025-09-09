import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PedidoDao {

    public Pedido insertar(Pedido pedido) throws SQLException {
        String sql = "INSERT INTO pedidos(cliente_id, fecha, estado, total) VALUES(?, ?, ?, ?)";
        try (Connection conn = SQLiteConexion.conectar(); PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, pedido.getId());
            ps.setString(2, pedido.getFecha());
            ps.setString(3, pedido.getEstado());
            ps.setDouble(4, pedido.getTotal());
            ps.executeUpdate();

            // Obtener el ID generado
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int idGenerado = rs.getInt(1);

                    // Consultar la fila completa
                    String selectSQL = "SELECT * FROM pedidos WHERE id = ?";
                    try (PreparedStatement selectStmt = conn.prepareStatement(selectSQL)) {
                        selectStmt.setInt(1, idGenerado);
                        try (ResultSet result = selectStmt.executeQuery()) {
                            if (result.next()) {
                                Pedido pedidoAgregado = new Pedido(
                                        result.getInt("id"),
                                        result.getInt("cliente_id"),
                                        result.getString("estado"),
                                        result.getDouble("cantidad")

                                );


                                // Si tenés más campos, los agregás acá
                                return pedidoAgregado;
                            }
                        }
                    }
                }


            }
        }
        return null;
    }

    public List<Pizza> listar() throws SQLException {
        List<Pizza> pizzas = new ArrayList<>();
        String sql = "SELECT * FROM pizzas";
        try (Connection conn = SQLiteConexion.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                pizzas.add(new Pizza(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("tamanio"),
                        rs.getDouble("precio"),
                        rs.getString("ingredientes")

                ));

            }
        }
        return pizzas;
    }
}