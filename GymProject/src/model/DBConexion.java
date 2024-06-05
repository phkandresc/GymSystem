package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConexion {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/db_gimnasio";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = "andresdomenica";

    private static Connection conexion = null;
    private static final Logger LOGGER = Logger.getLogger(DBConexion.class.getName());

    private DBConexion() {
    }

    public static Connection getConnection() throws SQLException {
        if (conexion == null || conexion.isClosed()) {
            try {
                conexion = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Error al conectar con la base de datos: " + e.getMessage(), e);
                throw e;
            }
        }
        return conexion;
    }

    public static void closeConnection() {
        if (conexion != null) {
            try {
                conexion.close();
                conexion = null;
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Error al cerrar la conexión con la base de datos: " + e.getMessage(), e);
            }
        }
    }
}
