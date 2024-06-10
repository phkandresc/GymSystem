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
        String sql = "INSERT INTO socios (nombre, apellido, cedula, direccion, email, numeroTelefono, fechaNacimiento) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, socio.getNombre());
            pstmt.setString(2, socio.getApellido());
            pstmt.setString(3, socio.getCedula());
            pstmt.setString(4, socio.getDireccion());
            pstmt.setString(5, socio.getEmail());
            pstmt.setString(6, socio.getNumeroTelefono());
            pstmt.setDate(7, new java.sql.Date(socio.getFechaNacimiento().getTime()));
            pstmt.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al insertar socio: " + e.getMessage(), e);
        }
    }
}
