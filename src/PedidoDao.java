import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PedidoDao {

    public Pedido insertar(Pedido pedido) throws SQLException {
        String sql = "INSERT INTO pedidos(cliente_id, fecha, estado, total) VALUES(?, ?, ?, ?)";
        try (Connection conn = SQLiteConexion.conectar(); PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, pedido.getCliente_id());
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
                                        result.getString("fecha"),
                                        result.getString("estado"),
                                        result.getDouble("total")


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

    public List<Pedido> listar() throws SQLException {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM pedidos";
        try (Connection conn = SQLiteConexion.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                pedidos.add(new Pedido(
                        rs.getInt("id"),
                        rs.getInt("cliente_id"),
                        rs.getString("fecha"),
                        rs.getString("estado"),
                        rs.getDouble("total")


                ));

            }
        }
        return pedidos;
    }

    public boolean actualizar(Pedido pedido)throws SQLException {
        String sql = "UPDATE pedidos SET total = ? WHERE id = ?";

        try (Connection conn = SQLiteConexion.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            //Valor del total el 1, indica que valor reemplaza en la consulta SQL
            ps.setDouble(1, pedido.getTotal());
            ps.setInt(2, pedido.getId()); // ID del pedido a actualizar

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        }


    }
}