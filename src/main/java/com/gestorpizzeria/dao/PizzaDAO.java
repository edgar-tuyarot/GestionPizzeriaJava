package main.java.com.gestorpizzeria.dao;

import main.java.com.gestorpizzeria.models.Pizza;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PizzaDAO {

    public Pizza insertar(Pizza pizza) throws SQLException {
        String sql = "INSERT INTO pizzas(nombre, ingredientes, precio, tamanio) VALUES(?, ?, ?, ?)";
        try (Connection conn = SQLiteConexion.conectar(); PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, pizza.getNombre());
            ps.setString(2, pizza.getIngredientes());
            ps.setDouble(3, pizza.getPrecio());
            ps.setString(4, pizza.getTamanio());
            ps.executeUpdate();

            // Obtener el ID generado
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int idGenerado = rs.getInt(1);

                    // Consultar la fila completa
                    String selectSQL = "SELECT * FROM pizzas WHERE id = ?";
                    try (PreparedStatement selectStmt = conn.prepareStatement(selectSQL)) {
                        selectStmt.setInt(1, idGenerado);
                        try (ResultSet result = selectStmt.executeQuery()) {
                            if (result.next()) {
                                Pizza pizzaAgregada = new Pizza(
                                        result.getInt("id"),
                                        result.getString("nombre"),
                                        result.getString("tamanio"),
                                        result.getDouble("precio"),
                                        result.getString("ingredientes")

                                );


                                // Si tenés más campos, los agregás acá
                                return pizzaAgregada;
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
