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

public class TipoMembresiaDAO implements CRUD<TipoMembresia>{
    private static final Logger LOGGER = Logger.getLogger(TipoMembresiaDAO.class.getName());
    private final String BUSCAR_TIPOMEMBRESIA_POR_ID = "SELECT * FROM tipos_membresia WHERE id = ?";
    private final String OBTENER_TIPOMEMBRESIAS = "SELECT * FROM tipos_membresia";
    private final String AGREGAR_TIPOMEMBRESIA = "INSERT INTO tipos_membresia (nombre, descripcion, duracion, precio) VALUES (?, ?, ?, ?)";

    @Override
    public List<TipoMembresia> obtenerListaDeDatos() throws SQLException {
        PreparedStatement ps = null;
        Connection conexion = DBConexion.getConnection();
        List<TipoMembresia> tiposMembresia = new ArrayList<>();
        try {
            ps = conexion.prepareStatement(OBTENER_TIPOMEMBRESIAS);
            ResultSet rs = ps.executeQuery();
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

    @Override
    public boolean agregarDato(TipoMembresia dato) throws SQLException {
        PreparedStatement ps = null;
        Connection conexion = DBConexion.getConnection();
        try {
            ps = conexion.prepareStatement(AGREGAR_TIPOMEMBRESIA);
            ps.setString(1, dato.getNombre());
            ps.setString(2, dato.getDescripcion());
            ps.setInt(3, dato.getDuracion());
            ps.setDouble(4, dato.getPrecio());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        } finally {
            if (ps != null) {
                ps.close();
            }

            if (conexion != null) {
                conexion.close();
            }
            DBConexion.closeConnection(conexion);
        }
        return false;
    }

    @Override
    public boolean actualizarDato(TipoMembresia dato) throws SQLException {
        return false;
    }

    @Override
    public boolean eliminarDato(TipoMembresia dato) throws SQLException {
        return false;
    }

    @Override
    public TipoMembresia buscarDatoPorId(int id) throws SQLException {
        PreparedStatement ps = null;
        Connection conexion = DBConexion.getConnection();
        TipoMembresia tipoMembresia = new TipoMembresia();
        try {
            ps = conexion.prepareStatement(BUSCAR_TIPOMEMBRESIA_POR_ID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                tipoMembresia.setId(rs.getInt("id"));
                tipoMembresia.setNombre(rs.getString("nombre"));
                tipoMembresia.setDescripcion(rs.getString("descripcion"));
                tipoMembresia.setDuracion(rs.getInt("duracion"));
                tipoMembresia.setPrecio(rs.getDouble("precio"));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        } finally {
            if (ps != null) {
                ps.close();
            }

            if (conexion != null) {
                conexion.close();
            }
            DBConexion.closeConnection(conexion);
        }
        return tipoMembresia;
    }
}
