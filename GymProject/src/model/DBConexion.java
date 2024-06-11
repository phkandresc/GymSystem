package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConexion {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/db_gimnasio";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = "kevinandres123";
    private static final String ACCESOREMOTO_URL = "jdbc:mysql://192.168.0.104:3306/db_gimnasio";
    private static final String ACCESOREMOTO_USER = "AccesoRemoto";
    private static final String ACCESOREMOTO_PASSWORD = "kevinandres123";

    private static Connection conexion = null;
    private static final Logger LOGGER = Logger.getLogger(DBConexion.class.getName());

    private DBConexion() {
    }

    public static synchronized Connection getConnection() throws SQLException {
        if (conexion == null || conexion.isClosed()) {
            try {
                conexion = DriverManager.getConnection(ACCESOREMOTO_URL, ACCESOREMOTO_USER, ACCESOREMOTO_PASSWORD);
                System.out.println("Conectado a BDD");
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
                System.out.println("Desconectado a BDD");
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Error al cerrar la conexión con la base de datos: " + e.getMessage(), e);
            }
        }
    }
}
