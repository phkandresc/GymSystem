package org.gimnasio.model;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConexion {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/db_gimnasio";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = "kevinandres123";
    private static final String ACCESOREMOTO_URL = "jdbc:mysql://192.168.0.25:3306/db_gimnasio";
    private static final String ACCESOREMOTO_USER = "AccesoRemoto";
    private static final String ACCESOREMOTO_PASSWORD = "kevinandres123";

    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;
    private static final Logger LOGGER = Logger.getLogger(DBConexion.class.getName());

    static {
        config.setJdbcUrl(ACCESOREMOTO_URL);
        config.setUsername(ACCESOREMOTO_USER);
        config.setPassword(ACCESOREMOTO_PASSWORD);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        ds = new HikariDataSource(config);
    }

    private DBConexion() {}

    public static Connection getConnection() throws SQLException {
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al obtener la conexión: " + e.getMessage(), e);
            throw e;
        }
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Error al cerrar la conexión: " + e.getMessage(), e);
            }
        }
    }
}
