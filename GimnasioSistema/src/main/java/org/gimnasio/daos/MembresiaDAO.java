package org.gimnasio.daos;

import org.gimnasio.model.DBConexion;
import org.gimnasio.model.Membresia;
import org.gimnasio.model.Socio;

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
    private final String REVISAR_ESTADO_MEMBRESIAS = "SELECT id, id_socio, id_tipomembresia, id_gimnasio, fecha_inicio, fecha_fin, estado FROM membresias WHERE estado = 'activo'";
    private final String ACTUALIZAR_ESTADO_MEMBRESIA = "UPDATE membresias SET estado = ? WHERE id = ?";
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
        try {
            PreparedStatement ps = DBConexion.getConnection().prepareStatement("SELECT * FROM membresias WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new Membresia.Builder()
                        .id(rs.getInt("id"))
                        .socio(new SocioDAO().buscarDatoPorId(rs.getInt("id_socio")))
                        .tipoMembresia(new TipoMembresiaDAO().buscarDatoPorId(rs.getInt("id_tipomembresia")))
                        .idGimnasio(rs.getInt("id_gimnasio"))
                        .fechaInicio(rs.getDate("fecha_inicio"))
                        .fechaFin(rs.getDate("fecha_fin"))
                        .build();
            }
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }
        return null;
    }

    private List<Membresia> crearListaMembresias(ResultSet rs){
        List<Membresia> membresias = new ArrayList<>();
        try {
            while (rs.next()) {
                Membresia membresia = new Membresia.Builder()
                        .id(rs.getInt("id"))
                        .socio(new SocioDAO().buscarDatoPorId(rs.getInt("id_socio")))
                        .tipoMembresia(new TipoMembresiaDAO().buscarDatoPorId(rs.getInt("id_tipomembresia")))
                        .idGimnasio(rs.getInt("id_gimnasio"))
                        .fechaInicio(rs.getDate("fecha_inicio"))
                        .fechaFin(rs.getDate("fecha_fin"))
                        .estado(rs.getString("estado"))
                        .build();
                membresias.add(membresia);
            }
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }
        return membresias;
    }

    //obtener Membresia recien agregada
    public Membresia obtenerMembresiaRecienAgregada() throws SQLException {
        Membresia membresia = null;
        PreparedStatement ps = null;
        Connection conexion = DBConexion.getConnection();
        try {
            ps = conexion.prepareStatement("SELECT * FROM membresias ORDER BY id DESC LIMIT 1");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                membresia = new Membresia.Builder()
                        .id(rs.getInt("id"))
                        .socio(new SocioDAO().buscarDatoPorId(rs.getInt("id_socio")))
                        .tipoMembresia(new TipoMembresiaDAO().buscarDatoPorId(rs.getInt("id_tipomembresia")))
                        .idGimnasio(rs.getInt("id_gimnasio"))
                        .fechaInicio(rs.getDate("fecha_inicio"))
                        .fechaFin(rs.getDate("fecha_fin"))
                        .build();
            }
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
        return membresia;
    }

    public Membresia agregarMembresia(Membresia membresia) throws SQLException {
        PreparedStatement ps = null;
        Connection conexion = DBConexion.getConnection();
        try {
            ps = conexion.prepareStatement(REGISTRAR_MEMBRESIA);
            ps.setInt(1, membresia.getSocio().getId());
            ps.setInt(2, membresia.getTipoMembresia().getId());
            ps.setInt(3, membresia.getIdGimnasio());
            ps.setDate(4, membresia.getFechaInicio());
            ps.setDate(5, membresia.getFechaFin());
            ps.executeUpdate();
            membresia = obtenerMembresiaRecienAgregada();
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
        return membresia;
    }

    public List<Membresia> obtenerMembresiasActivas() throws SQLException {
        PreparedStatement ps = null;
        Connection conexion = DBConexion.getConnection();
        List<Membresia> membresiasActivas = new ArrayList<>();
        try {
            ps = conexion.prepareStatement(REVISAR_ESTADO_MEMBRESIAS);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Membresia membresia = new Membresia.Builder()
                        .id(rs.getInt("id"))
                        .socio(new SocioDAO().buscarDatoPorId(rs.getInt("id_socio")))
                        .tipoMembresia(new TipoMembresiaDAO().buscarDatoPorId(rs.getInt("id_tipomembresia")))
                        .idGimnasio(rs.getInt("id_gimnasio"))
                        .fechaInicio(rs.getDate("fecha_inicio"))
                        .fechaFin(rs.getDate("fecha_fin"))
                        .estado(rs.getString("estado"))
                        .build();
                membresiasActivas.add(membresia);
            }
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
        return membresiasActivas;
    }

    public void actualizarEstadoMembresia(int idMembresia, String estado) throws SQLException {
        PreparedStatement ps = null;
        Connection conexion = DBConexion.getConnection();
        try {
            ps = conexion.prepareStatement(ACTUALIZAR_ESTADO_MEMBRESIA);
            ps.setString(1, estado);
            ps.setInt(2, idMembresia);
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
            DBConexion.closeConnection(conexion);
        }
    }

    public List<Membresia> obtenerMembresiasPorEstado(String criterio) throws SQLException {
        List<Membresia> membresias = new ArrayList<>();
        PreparedStatement ps = null;
        Connection conexion = DBConexion.getConnection();
        ResultSet rs = null;
        try {
            ps = conexion.prepareStatement("SELECT * FROM membresias WHERE estado = ?");
            ps.setString(1, criterio);
            rs = ps.executeQuery();
            while (rs.next()) {
                Membresia membresia = new Membresia.Builder()
                        .id(rs.getInt("id"))
                        .socio(new SocioDAO().buscarDatoPorId(rs.getInt("id_socio")))
                        .tipoMembresia(new TipoMembresiaDAO().buscarDatoPorId(rs.getInt("id_tipomembresia")))
                        .idGimnasio(rs.getInt("id_gimnasio"))
                        .fechaInicio(rs.getDate("fecha_inicio"))
                        .fechaFin(rs.getDate("fecha_fin"))
                        .estado(rs.getString("estado"))
                        .build();
                membresias.add(membresia);
            }
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

    public boolean verificarMembresiaActivaDeSocio(Socio socio) throws SQLException {
        PreparedStatement ps = null;
        Connection conexion = DBConexion.getConnection();
        ResultSet rs = null;
        try {
            ps = conexion.prepareStatement("SELECT * FROM membresias WHERE id_socio = ? AND estado = 'activo'");
            ps.setInt(1, socio.getId());
            rs = ps.executeQuery();
            return rs.next();
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

    public boolean eliminarMembresiasDeSocio(Socio socio) throws SQLException {
        PreparedStatement ps = null;
        Connection conexion = DBConexion.getConnection();
        try {
            ps = conexion.prepareStatement("DELETE FROM membresias WHERE id_socio = ?");
            ps.setInt(1, socio.getId());
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
}
