package org.gimnasio.daos;

import org.gimnasio.model.DBConexion;
import org.gimnasio.model.TipoMembresia;

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
            DBConexion.closeConnection(conexion);
        }
        return tiposMembresia;
    }

    public boolean agregarTipoDeMembresia(TipoMembresia nuevoTipoMembresia) throws SQLException {
        PreparedStatement ps = null;
        Connection conexion = DBConexion.getConnection();
        String sql = "INSERT INTO tipos_membresia (nombre, descripcion, duracion, precio) VALUES (?, ?, ?, ?)";

        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, nuevoTipoMembresia.getNombre());
            ps.setString(2, nuevoTipoMembresia.getDescripcion());
            ps.setInt(3, nuevoTipoMembresia.getDuracion());
            ps.setDouble(4, nuevoTipoMembresia.getPrecio());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
            return false;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
                DBConexion.closeConnection(conexion);
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, e.getMessage());
            }
        }
    }

}
