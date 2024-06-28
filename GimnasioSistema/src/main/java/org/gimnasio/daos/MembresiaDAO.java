package org.gimnasio.daos;

import org.gimnasio.model.DBConexion;
import org.gimnasio.model.Membresia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class MembresiaDAO implements CRUD<Membresia>{
    private static final Logger LOGGER = Logger.getLogger(MembresiaDAO.class.getName());
    private final String REGISTRAR_MEMBRESIA = "INSERT INTO membresias (id_socio, id_tipomembresia, id_gimnasio, fecha_inicio, fecha_fin) VALUES (?, ?, ?, ?, ?)";
    private final String ELIMINAR_MEMBRESIA = "DELETE FROM membresias WHERE id = ?";
    private final String OBTENER_MEMBRESIAS = "SELECT * FROM membresias";
    private final String OBTENERMEMBRESIA_PORSOCIO = "SELECT * FROM membresias WHERE id_socio = ?";

    public List<Membresia> obtenerMembresiasPorIdSocio(int idSocio) throws SQLException {
        List<Membresia> membresias = new ArrayList<>();
        PreparedStatement ps = null;
        Connection conexion = DBConexion.getConnection();
        try {
            ps = conexion.prepareStatement(OBTENERMEMBRESIA_PORSOCIO);
            ps.setInt(1, idSocio);
            membresias = crearListaMembresias(ps.executeQuery());
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        } finally {
            if (ps != null) {
                ps.close();
            }

            if (conexion != null) {
                conexion.close();
            }
            DBConexion.closeConnection(conexion);
        }
        return membresias;
    }

    @Override
    public List<Membresia> obtenerListaDeDatos() throws SQLException {
        List<Membresia> membresias = new ArrayList<>();
        PreparedStatement ps = null;
        Connection conexion = DBConexion.getConnection();
        try {
            ps = conexion.prepareStatement(OBTENER_MEMBRESIAS);
            membresias = crearListaMembresias(ps.executeQuery());
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        } finally {
            if (ps != null) {
                ps.close();
            }

            if (conexion != null) {
                conexion.close();
            }
            DBConexion.closeConnection(conexion);
        }
        return membresias;
    }

    @Override
    public boolean agregarDato(Membresia dato) throws SQLException {
        PreparedStatement ps = null;
        Connection conexion = DBConexion.getConnection();
        try {
            ps = conexion.prepareStatement(REGISTRAR_MEMBRESIA);
            ps.setInt(1, dato.getSocio().getId());
            ps.setInt(2, dato.getTipoMembresia().getId());
            ps.setInt(3, dato.getIdGimnasio());
            ps.setDate(4, dato.getFechaInicio());
            ps.setDate(5, dato.getFechaFin());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
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
    public boolean actualizarDato(Membresia dato) throws SQLException {
        return false;
    }

    @Override
    public boolean eliminarDato(Membresia dato) throws SQLException {
        PreparedStatement ps = null;
        Connection conexion = DBConexion.getConnection();
        try {
            ps = conexion.prepareStatement(ELIMINAR_MEMBRESIA);
            ps.setInt(1, dato.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
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
    public Membresia buscarDatoPorId(int id) throws SQLException {
        return null;
    }

    public List<Membresia> crearListaMembresias(ResultSet rs){
        List<Membresia> membresias = new ArrayList<>();
        try {
            while (rs.next()) {
                Membresia membresia = new Membresia();
                membresia.setId(rs.getInt("id"));
                membresia.setSocio(new SocioDAO().buscarDatoPorId(rs.getInt("id_socio")));
                membresia.setTipoMembresia(new TipoMembresiaDAO().buscarDatoPorId(rs.getInt("id_tipomembresia")));
                membresia.setIdGimnasio(rs.getInt("id_gimnasio"));
                membresia.setFechaInicio(rs.getDate("fecha_inicio"));
                membresia.setFechaFin(rs.getDate("fecha_fin"));
                membresias.add(membresia);
            }
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }
        return membresias;
    }
}
