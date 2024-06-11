package DAO;

import model.DBConexion;
import model.Socio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class SocioDAO {
    public static final Logger LOGGER = Logger.getLogger(SocioDAO.class.getName());
    private List<Socio> listaSocios = new ArrayList<Socio>();

    public void registrarSocio(Socio socio) {
        String sql = "INSERT INTO socios (cedula, nombre, apellido, email, numero_telefono, direccion, fecha_nacimiento) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBConexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, socio.getCedula());
            statement.setString(2, socio.getNombre());
            statement.setString(3, socio.getApellido());
            statement.setString(4, socio.getEmail());
            statement.setString(5, socio.getNumeroTelefono());
            statement.setString(6, socio.getDireccion());
            statement.setDate(7, socio.getFechaNacimiento());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        } finally {
            DBConexion.closeConnection();
        }
    }

    public Socio buscarSocio(String cedula) {
        Socio socio = new Socio();
        String sql = "SELECT * FROM socios WHERE cedula = ?";
        try (Connection connection = DBConexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, cedula);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                socio.setId(resultSet.getInt("id"));
                socio.setCedula(resultSet.getString("cedula"));
                socio.setNombre(resultSet.getString("nombre"));
                socio.setApellido(resultSet.getString("apellido"));
                socio.setEmail(resultSet.getString("email"));
                socio.setNumeroTelefono(resultSet.getString("numero_telefono"));
                socio.setDireccion(resultSet.getString("direccion"));
                socio.setFechaNacimiento(resultSet.getDate("fecha_nacimiento"));
            }
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        } finally {
            DBConexion.closeConnection();
        }
        return socio;

    }

    public void eliminarSocio(String cedula) {
        String sql = "DELETE FROM socios WHERE cedula = ?";
        try (Connection connection = DBConexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, cedula);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        } finally {
            DBConexion.closeConnection();
        }
    }

    public void actualizarSocio(Socio socio) {
        String sql = "UPDATE socios SET nombre = ?, apellido = ?, email = ?, numero_telefono = ?, direccion = ?, fecha_nacimiento = ? WHERE cedula = ?";
        try (Connection connection = DBConexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, socio.getNombre());
            statement.setString(2, socio.getApellido());
            statement.setString(3, socio.getEmail());
            statement.setString(4, socio.getNumeroTelefono());
            statement.setString(5, socio.getDireccion());
            statement.setDate(6, socio.getFechaNacimiento());
            statement.setString(7, socio.getCedula());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        } finally {
            DBConexion.closeConnection();
        }
    }

    //Metodo para obtener todos los socios
    public List<Socio> obtenerTodosSocios() {
        String sql = "SELECT * FROM socios";
        try (Connection connection = DBConexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Socio socio = new Socio();
                socio.setId(resultSet.getInt("id"));
                socio.setCedula(resultSet.getString("cedula"));
                socio.setNombre(resultSet.getString("nombre"));
                socio.setApellido(resultSet.getString("apellido"));
                socio.setEmail(resultSet.getString("email"));
                socio.setNumeroTelefono(resultSet.getString("numero_telefono"));
                socio.setDireccion(resultSet.getString("direccion"));
                socio.setFechaNacimiento(resultSet.getDate("fecha_nacimiento"));
                listaSocios.add(socio);
            }
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        } finally {
            DBConexion.closeConnection();
        }
        return listaSocios;
    }
}
