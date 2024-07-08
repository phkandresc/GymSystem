package org.gimnasio.daos;

import org.gimnasio.model.DBConexion;
import org.gimnasio.model.Equipo;
import org.gimnasio.model.Espacio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EquiposDAO implements CRUD<Equipo> {
    @Override
    public List<Equipo> obtenerListaDeDatos() throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = DBConexion.getConnection();
        List<Equipo> equipos = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM equipos");
            rs = ps.executeQuery();
            while (rs.next()) {
                Equipo equipo = new Equipo();
                equipo.setId(rs.getInt("id"));
                equipo.setNombre(rs.getString("nombre"));
                equipo.setDescripcion(rs.getString("descripcion"));
                equipo.setEstado(rs.getString("estado"));
                equipo.setEspacio(new EspaciosDAO().buscarDatoPorId(rs.getInt("id_espacio")));
                equipos.add(equipo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
            DBConexion.closeConnection(con);
        }
        return equipos;
    }

    @Override
    public boolean agregarDato(Equipo dato) throws SQLException {
        PreparedStatement ps = null;
        Connection con = DBConexion.getConnection();
        try {
            ps = con.prepareStatement("INSERT INTO equipos (nombre, descripcion, estado, id_espacio) VALUES (?, ?, ?, ?)");
            ps.setString(1, dato.getNombre());
            ps.setString(2, dato.getDescripcion());
            ps.setString(3, dato.getEstado());
            ps.setInt(4, dato.getEspacio().getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (ps != null) ps.close();
            if (con != null) con.close();
            DBConexion.closeConnection(con);
        }
    }

    @Override
    public boolean actualizarDato(Equipo dato) throws SQLException {
        PreparedStatement ps = null;
        Connection con = DBConexion.getConnection();
        try {
            ps = con.prepareStatement("UPDATE equipos SET nombre = ?, descripcion = ?, estado = ?, id_espacio = ? WHERE id = ?");
            ps.setString(1, dato.getNombre());
            ps.setString(2, dato.getDescripcion());
            ps.setString(3, dato.getEstado());
            ps.setInt(4, dato.getEspacio().getId());
            ps.setInt(5, dato.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (ps != null) ps.close();
            if (con != null) con.close();
            DBConexion.closeConnection(con);
        }
    }

    @Override
    public boolean eliminarDato(Equipo dato) throws SQLException {
        PreparedStatement ps = null;
        Connection con = DBConexion.getConnection();
        try {
            ps = con.prepareStatement("DELETE FROM equipos WHERE id = ?");
            ps.setInt(1, dato.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (ps != null) ps.close();
            if (con != null) con.close();
            DBConexion.closeConnection(con);
        }
    }

    @Override
    public Equipo buscarDatoPorId(int id) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = DBConexion.getConnection();
        Equipo equipo = null;
        try {
            ps = con.prepareStatement("SELECT * FROM equipos WHERE id = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                equipo = new Equipo();
                equipo.setId(rs.getInt("id"));
                equipo.setNombre(rs.getString("nombre"));
                equipo.setDescripcion(rs.getString("descripcion"));
                equipo.setEstado(rs.getString("estado"));
                equipo.setEspacio(new EspaciosDAO().buscarDatoPorId(rs.getInt("id_espacio")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
            DBConexion.closeConnection(con);
        }
        return equipo;
    }

    public List<Equipo> obtenerEquiposPorEspacio(Espacio espacio) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = DBConexion.getConnection();
        List<Equipo> equipos = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM equipos WHERE id_espacio = ?");
            ps.setInt(1, espacio.getId());
            rs = ps.executeQuery();
            while (rs.next()) {
                Equipo equipo = new Equipo();
                equipo.setId(rs.getInt("id"));
                equipo.setNombre(rs.getString("nombre"));
                equipo.setDescripcion(rs.getString("descripcion"));
                equipo.setEstado(rs.getString("estado"));
                equipo.setEspacio(espacio);
                equipos.add(equipo);
            }
            return equipos;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
            DBConexion.closeConnection(con);
        }
    }
}
