package DAO;

import model.TipoMembresia;
import model.DBConexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TipoMembresiaDAO {
    private static final Logger LOGGER = Logger.getLogger(TipoMembresiaDAO.class.getName());
    private List<TipoMembresia> tiposMembresia = new ArrayList<TipoMembresia>();

    //Obtener tipos de membresia
    public List<TipoMembresia> obtenerTiposMembresia() throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conexion = DBConexion.getConnection();

        String sql = "SELECT * FROM tipos_membresia";

        try {
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                TipoMembresia tipoMembresia = new TipoMembresia();
                tipoMembresia.setId(rs.getInt("id"));
                tipoMembresia.setNombre(rs.getString("nombre"));
                tipoMembresia.setDescripcion(rs.getString("descripcion"));
                tipoMembresia.setDuracion(rs.getInt("duracion"));
                tipoMembresia.setPrecio(rs.getDouble("precio"));
                tiposMembresia.add(tipoMembresia);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
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
        return tiposMembresia;
    }

}
