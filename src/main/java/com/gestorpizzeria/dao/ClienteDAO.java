package main.java.com.gestorpizzeria.dao;

import main.java.com.gestorpizzeria.models.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public Cliente insertar(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO clientes(nombre, telefono, direccion) VALUES(?, ?, ?)";
        try (Connection conn = SQLiteConexion.conectar(); PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setString(1, cliente.getNombre());
                ps.setString(2, cliente.getTelefono());
                ps.setString(3, cliente.getDireccion());
                ps.executeUpdate();

                // Obtener el ID generado
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        int idGenerado = rs.getInt(1);

                        // Consultar la fila completa
                        String selectSQL = "SELECT * FROM clientes WHERE id = ?";
                        try (PreparedStatement selectStmt = conn.prepareStatement(selectSQL)) {
                            selectStmt.setInt(1, idGenerado);
                            try (ResultSet result = selectStmt.executeQuery()) {
                                if (result.next()) {
                                    Cliente clienteInsertado = new Cliente(
                                            result.getInt("id"),
                                            result.getString("nombre"),
                                            result.getString("telefono"),
                                            result.getString("direccion")
                                    );


                                    // Si tenés más campos, los agregás acá
                                    return clienteInsertado;
                                }
                            }
                        }
                    }


                }
            }
        return null;
    }

    public List<Cliente> listar() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        try (Connection conn = SQLiteConexion.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                clientes.add(new Cliente(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("telefono"),
                        rs.getString("direccion")
                ));
            }
        }
        return clientes;
    }
}
