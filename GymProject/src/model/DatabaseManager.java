package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/db_gimnasio";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = "andresdomenica";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
    }

    public static void main(String[] args) {
        DatabaseManager dbManager = new DatabaseManager();
        try (Connection conn = dbManager.getConnection()) {
            System.out.println("Connected to database");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
