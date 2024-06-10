package DAO;

import model.Administrador;
import model.DBConexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AdministradorDAO {
    private static final Logger LOGGER = Logger.getLogger(AdministradorDAO.class.getName());

    public boolean iniciarSesion(Administrador admin) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conexion = DBConexion.getConnection();

        String sql = "SELECT id, usuario, contrasena FROM administradores WHERE usuario = ?";

        try{
            ps = conexion.prepareStatement(sql);
            ps.setString(1, admin.getUsuario());
            rs = ps.executeQuery();

            if (rs.next()) {
                if(admin.getContrasena().equals(rs.getString("contrasena"))) {
                    return true;
                }else {
                    return false;
                }
            }
            return false;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
            return false;
        } finally {
            if (rs != null) {
                rs.close();
            }

            if (ps != null) {
                ps.close();
            }

            if (conexion != null) {
                conexion.close();
            }
            DBConexion.closeConnection();
        }
    }
}
