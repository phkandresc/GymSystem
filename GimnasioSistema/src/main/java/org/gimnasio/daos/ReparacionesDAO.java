package org.gimnasio.daos;


import org.gimnasio.model.DBConexion;
import org.gimnasio.model.Reparacion;

import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ReparacionesDAO implements CRUD<Reparacion>{
    @Override
    public List<Reparacion> obtenerListaDeDatos() throws SQLException {
        List<Reparacion> reparaciones = new ArrayList<>();
        Connection conexion = DBConexion.getConnection();
        PreparedStatement ps = null;
        try {
            String query = "SELECT * FROM reparaciones";
            ps = conexion.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Reparacion reparacion = new Reparacion();
                reparacion.setId(rs.getInt("id"));
                reparacion.setEquipo(new EquiposDAO().buscarDatoPorId(rs.getInt("id_equipo")));
                reparacion.setDescripcion(rs.getString("descripcion"));
                reparacion.setFecha(rs.getDate("fecha_reparacion"));
                reparacion.setEncargado(rs.getString("encargado"));
                reparacion.setEstado(rs.getString("estado"));
                reparaciones.add(reparacion);
            }
            return reparaciones;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (ps != null) {
                ps.close();
            }
            DBConexion.closeConnection(conexion);
        }
    }

    @Override
    public boolean agregarDato(Reparacion dato) throws SQLException {
        Connection conexion = DBConexion.getConnection();
        PreparedStatement ps = null;
        try {
            String query = "INSERT INTO reparaciones (id_equipo, descripcion, fecha_reparacion, encargado, estado) VALUES (?, ?, ?, ?, ?)";
            ps = conexion.prepareStatement(query);
            ps.setInt(1, dato.getEquipo().getId());
            ps.setString(2, dato.getDescripcion());
            ps.setDate(3, dato.getFecha());
            ps.setString(4, dato.getEncargado());
            ps.setString(5, dato.getEstado());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (ps != null) {
                ps.close();
            }
            DBConexion.closeConnection(conexion);
        }
    }

    @Override
    public boolean actualizarDato(Reparacion dato) throws SQLException {
        return false;
    }

    @Override
    public boolean eliminarDato(Reparacion dato) throws SQLException {
        return false;
    }

    @Override
    public Reparacion buscarDatoPorId(int id) throws SQLException {
        return null;
    }

    public List<Reparacion> obtenerReparacionesPorEquipo(int idEquipo) throws SQLException {
        List<Reparacion> reparaciones = new ArrayList<>();
        Connection conexion = DBConexion.getConnection();
        PreparedStatement ps = null;
        try {
            String query = "SELECT * FROM reparaciones WHERE id_equipo = ?";
            ps = conexion.prepareStatement(query);
            ps.setInt(1, idEquipo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Reparacion reparacion = new Reparacion();
                reparacion.setId(rs.getInt("id"));
                reparacion.setEquipo(new EquiposDAO().buscarDatoPorId(rs.getInt("id_equipo")));
                reparacion.setDescripcion(rs.getString("descripcion"));
                reparacion.setFecha(rs.getDate("fecha_reparacion"));
                reparacion.setEncargado(rs.getString("encargado"));
                reparacion.setEstado(rs.getString("estado"));
                reparaciones.add(reparacion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
            DBConexion.closeConnection(conexion);
        }
        return reparaciones;
    }
}
