package org.gimnasio.daos;

import org.gimnasio.model.DBConexion;
import org.gimnasio.model.Reserva;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ReservasDAO implements CRUD<Reserva> {
    @Override
    public List<Reserva> obtenerListaDeDatos() throws SQLException {
        return List.of();
    }

    @Override
    public boolean agregarDato(Reserva dato) throws SQLException {
        PreparedStatement ps = null;
        Connection conn = DBConexion.getConnection();
        try {
            ps = conn.prepareStatement("INSERT INTO reservas (id_clase_programada, id_socio) VALUES (?, ?)");
            ps.setInt(1, dato.getClaseProgramada().getId());
            ps.setInt(2, dato.getSocio().getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
            DBConexion.closeConnection(conn);
        }
        return false;
    }

    @Override
    public boolean actualizarDato(Reserva dato) throws SQLException {
        return false;
    }

    @Override
    public boolean eliminarDato(Reserva dato) throws SQLException {
        return false;
    }

    @Override
    public Reserva buscarDatoPorId(int id) throws SQLException {
        return null;
    }

    public int obtenerCuposReservados(int idClase) throws SQLException {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            conn = DBConexion.getConnection();
            ps = conn.prepareStatement("SELECT COUNT(*) AS total FROM reservas WHERE id_clase_programada = ?");
            ps.setInt(1, idClase);
            rs = ps.executeQuery();
            if (rs.next()) { // Mueve el cursor del ResultSet a la primera fila (si existe)
                int cupos = rs.getInt("total"); // Usa el nombre de la columna para mayor claridad
                return cupos;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
            DBConexion.closeConnection(conn);
        }
        return 0; // Retorna 0 si no se encontraron datos o si ocurre una excepción
    }
}
