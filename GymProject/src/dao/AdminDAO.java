package dao;

import model.Admin;
import model.DBConexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminDAO {
    private static final Logger LOGGER = Logger.getLogger(AdminDAO.class.getName());

    public Admin buscarPorUsuario(String usuario) {

        String sql = "SELECT * FROM administradores WHERE usuario = ?";

        try (Connection conn = DBConexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, usuario);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Admin admin = new Admin();
                admin.setUsuario(rs.getString("usuario"));
                admin.setContrasena(rs.getString("contrasena"));
                return admin;
            } else {
                return null;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al buscar admin: " + e.getMessage(), e);
            return null;
        }
    }

}
