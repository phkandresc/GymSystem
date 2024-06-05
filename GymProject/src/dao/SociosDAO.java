package dao;

import model.Socio;
import model.DBConexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SociosDAO {
    private static final Logger LOGGER = Logger.getLogger(SociosDAO.class.getName());

    public void insertarSocio(Socio socio) {
        String sql = "INSERT INTO socios (id, membresia_id, tarjeta_rfid_id) VALUES (?, ?, ?)";
        try (Connection conn = DBConexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al insertar socio: " + e.getMessage(), e);
        }
    }
}
