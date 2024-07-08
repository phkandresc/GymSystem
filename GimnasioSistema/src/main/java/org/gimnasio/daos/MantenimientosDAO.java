package org.gimnasio.daos;

import org.gimnasio.model.Mantenimiento;
import org.gimnasio.model.DBConexion;
import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MantenimientosDAO implements CRUD<Mantenimiento>{
    @Override
    public List<Mantenimiento> obtenerListaDeDatos() throws SQLException {
        List<Mantenimiento> mantenimientos = new ArrayList<>();
        Connection conexion = DBConexion.getConnection();
        PreparedStatement ps = null;
        try {
            String query = "SELECT * FROM mantenimientos";
            ps = conexion.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Mantenimiento mantenimiento = new Mantenimiento();
                mantenimiento.setId(rs.getInt("id"));
                mantenimiento.setEquipo(new EquiposDAO().buscarDatoPorId(rs.getInt("id_equipo")));
                mantenimiento.setDescripcion(rs.getString("descripcion"));
                mantenimiento.setFecha(rs.getDate("fecha_mantenimiento"));
                mantenimiento.setEncargado(rs.getString("encargado"));
                mantenimiento.setEstado(rs.getString("estado"));
                mantenimientos.add(mantenimiento);
            }
            return mantenimientos;
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
    public boolean agregarDato(Mantenimiento dato) throws SQLException {
        PreparedStatement ps = null;
        Connection conexion = DBConexion.getConnection();
        String query = "INSERT INTO mantenimientos (id_equipo, descripcion, fecha_mantenimiento, encargado, estado) VALUES (?, ?, ?, ?, ?)";
        try {
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
    public boolean actualizarDato(Mantenimiento dato) throws SQLException {
        return false;
    }

    @Override
    public boolean eliminarDato(Mantenimiento dato) throws SQLException {
        return false;
    }

    @Override
    public Mantenimiento buscarDatoPorId(int id) throws SQLException {
        return null;
    }

    public List<Mantenimiento> obtenerMantenimientosPorEquipo(int idEquipo) throws SQLException {
        List<Mantenimiento> mantenimientos = new ArrayList<>();
        Connection conexion = DBConexion.getConnection();
        PreparedStatement ps = null;
        try {
            String query = "SELECT * FROM mantenimientos WHERE id_equipo = ?";
            ps = conexion.prepareStatement(query);
            ps.setInt(1, idEquipo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Mantenimiento mantenimiento = new Mantenimiento();
                mantenimiento.setId(rs.getInt("id"));
                mantenimiento.setEquipo(new EquiposDAO().buscarDatoPorId(rs.getInt("id_equipo")));
                mantenimiento.setDescripcion(rs.getString("descripcion"));
                mantenimiento.setFecha(rs.getDate("fecha_mantenimiento"));
                mantenimiento.setEncargado(rs.getString("encargado"));
                mantenimiento.setEstado(rs.getString("estado"));
                mantenimientos.add(mantenimiento);
            }
            return mantenimientos;
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
}
