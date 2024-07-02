package org.gimnasio.daos;

import org.gimnasio.model.TipoClase;

import java.sql.SQLException;
import java.util.List;

import org.gimnasio.model.DBConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class TipoClaseDAO implements CRUD<TipoClase>{
    public static final Logger LOGGER = Logger.getLogger(TipoClaseDAO.class.getName());

    @Override
    public List<TipoClase> obtenerListaDeDatos() throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection con = DBConexion.getConnection();
            ps = con.prepareStatement("SELECT * FROM tipos_clase");
            rs = ps.executeQuery();
            List<TipoClase> lista = new ArrayList<>();
            while (rs.next()) {
                TipoClase tipoClase = new TipoClase();
                tipoClase.setId(rs.getInt("id"));
                tipoClase.setNombre(rs.getString("nombre"));
                tipoClase.setDescripcion(rs.getString("descripcion"));
                lista.add(tipoClase);
            }
            return lista;
        } catch (SQLException ex) {
            LOGGER.severe("Error al obtener la lista de tipo de clases: " + ex.getMessage());
            throw ex;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
        }
    }

    @Override
    public boolean agregarDato(TipoClase dato) throws SQLException {
        return false;
    }

    @Override
    public boolean actualizarDato(TipoClase dato) throws SQLException {
        return false;
    }

    @Override
    public boolean eliminarDato(TipoClase dato) throws SQLException {
        return false;
    }

    @Override
    public TipoClase buscarDatoPorId(int id) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection con = DBConexion.getConnection();
            ps = con.prepareStatement("SELECT * FROM tipos_clase WHERE id = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                TipoClase tipoClase = new TipoClase();
                tipoClase.setId(rs.getInt("id"));
                tipoClase.setNombre(rs.getString("nombre"));
                tipoClase.setDescripcion(rs.getString("descripcion"));
                return tipoClase;
            }
            return null;
        } catch (SQLException ex) {
            LOGGER.severe("Error al buscar el tipo de clase por id: " + ex.getMessage());
            throw ex;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
        }
    }
}
