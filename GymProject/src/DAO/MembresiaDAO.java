package DAO;

import model.Membresia;
import model.DBConexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class MembresiaDAO {
    private static final Logger LOGGER = Logger.getLogger(MembresiaDAO.class.getName());

    //Registrar membresia
    public void registrarMembresia(Membresia membresia) throws SQLException {
        PreparedStatement ps = null;
        Connection conexion = DBConexion.getConnection();

        String sql = "INSERT INTO membresias (id_socio, id_tipomembresia, id_gimnasio, fecha_inicio, fecha_fin) VALUES (?, ?, ?, ?, ?)";

        try {
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, membresia.getIdSocio());
            ps.setInt(2, membresia.getIdTipoMembresia());
            ps.setInt(3, membresia.getIdGimnasio());
            ps.setDate(4, membresia.getFechaInicio());
            ps.setDate(5, membresia.getFechaFin());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        } finally {
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
