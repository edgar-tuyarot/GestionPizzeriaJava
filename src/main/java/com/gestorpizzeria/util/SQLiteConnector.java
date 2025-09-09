package main.java.com.gestorpizzeria.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteConnector {
    private static final String URL = "jdbc:sqlite:data/pizzeria.db";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}