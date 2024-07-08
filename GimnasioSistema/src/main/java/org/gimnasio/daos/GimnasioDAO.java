package org.gimnasio.daos;

import org.gimnasio.model.DBConexion;
import org.gimnasio.model.Gimnasio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GimnasioDAO {

    public Gimnasio obtenerGimnasioPorId(int id) throws SQLException {
        Gimnasio gimnasio = null;
        String sql = "SELECT * FROM gimnasios WHERE id = ?";
        Connection connection = DBConexion.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    gimnasio = new Gimnasio();
                    gimnasio.setId(resultSet.getInt("id"));
                    gimnasio.setNombre(resultSet.getString("nombre"));
                    gimnasio.setDireccion(resultSet.getString("direccion"));
                    gimnasio.setTelefono(resultSet.getString("telefono"));
                    gimnasio.setEmail(resultSet.getString("email"));
                    gimnasio.setHorario_apertura(resultSet.getTime("horario_apertura"));
                    gimnasio.setHorario_cierre(resultSet.getTime("horario_cierre"));
                }
            }
        }
        DBConexion.closeConnection(connection);
        return gimnasio;
    }

    public int numeroEquiposRegistrados() throws SQLException {
        int numeroEquipos = 0;
        String sql = "SELECT COUNT(*) FROM equipos";
        Connection connection = DBConexion.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    numeroEquipos = resultSet.getInt(1);
                }
            }
        }
        DBConexion.closeConnection(connection);
        return numeroEquipos;
    }

    public int numeroEntrenadoresRegistrados() throws SQLException{
        int numeroEntrenadores = 0;
        String sql = "SELECT COUNT(*) FROM entrenadores";
        Connection connection = DBConexion.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    numeroEntrenadores = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBConexion.closeConnection(connection);
        return numeroEntrenadores;
    }

    public int numeroClasesRegistradas() throws SQLException{
        int numeroClases = 0;
        String sql = "SELECT COUNT(*) FROM clases";
        Connection connection = DBConexion.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    numeroClases = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBConexion.closeConnection(connection);
        return numeroClases;
    }

    public Double obtenerIngresosTotales() throws SQLException {
        Double ingresos = 0.0;
        String sql = "SELECT SUM(tm.precio) AS suma_total_precio_membresias " +
                "FROM membresias m " +
                "JOIN tipos_membresia tm ON m.id_tipomembresia = tm.id " +
                "WHERE m.estado = 'activo';";
        Connection connection = DBConexion.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    ingresos = resultSet.getDouble("suma_total_precio_membresias");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ingresos;
    }
}
