package DataAccessObject;

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

    public static int obtenerNumeroSocios() throws SQLException {
        int numeroSocios = 0;
        String sql = "SELECT COUNT(*) FROM socios";
        Connection connection = DBConexion.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                numeroSocios = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        } finally {
            DBConexion.closeConnection(connection);
        }
        return numeroSocios;
    }

    public void registrarSocio(Socio socio) throws SQLException {
        String sql = "INSERT INTO socios (cedula, nombre, apellido, email, numero_telefono, direccion, fecha_nacimiento) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection connection = DBConexion.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
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
            DBConexion.closeConnection(connection);
        }
    }

    public Socio buscarSocioPorCedula(String cedula) throws SQLException {
        Socio socio = null;
        String sql = "SELECT * FROM socios WHERE cedula = ?";
        Connection connection = DBConexion.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, cedula);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                socio = new Socio();
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
            DBConexion.closeConnection(connection);
        }
        return socio;

    }

    public void eliminarSocio(Socio socio) throws SQLException {
        String sqlMembresia = "DELETE FROM membresias WHERE id_socio = ?";
        String sqlSocio = "DELETE FROM socios WHERE id = ?";
        Connection connection = DBConexion.getConnection();

        try (PreparedStatement statementMembresia = connection.prepareStatement(sqlMembresia);
             PreparedStatement statementSocio = connection.prepareStatement(sqlSocio)) {
            statementMembresia.setInt(1, socio.getId());
            statementMembresia.executeUpdate();

            statementSocio.setInt(1, socio.getId());
            statementSocio.executeUpdate();
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        } finally {
            DBConexion.closeConnection(connection);
        }
    }


    public void actualizarSocio(Socio socio) throws SQLException {
        String sql = "UPDATE socios SET nombre = ?, apellido = ?, email = ?, numero_telefono = ?, direccion = ?, fecha_nacimiento = ? WHERE cedula = ?";
        Connection connection = DBConexion.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
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
            DBConexion.closeConnection(connection);
        }
    }

    public List<Socio> obtenerTodosSocios() throws SQLException {
        List<Socio> listaSocios = new ArrayList<Socio>();
        String sql = "SELECT * FROM socios";
        Connection conexion = DBConexion.getConnection();
        try (PreparedStatement statement = conexion.prepareStatement(sql);
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
            DBConexion.closeConnection(conexion);
        }
        return listaSocios;
    }

    public Socio buscarSocioPorId(int id) throws SQLException {
        Socio socio = null;
        String sql = "SELECT * FROM socios WHERE id = ?";
        Connection connection = DBConexion.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                socio = new Socio();
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
            DBConexion.closeConnection(connection);
        }
        return socio;
    }

    public Socio buscarSocioPorApellido(String apellido) throws SQLException {
        Socio socio = null;
        String sql = "SELECT * FROM socios WHERE apellido = ?";
        Connection connection = DBConexion.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, apellido);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                socio = new Socio();
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
            DBConexion.closeConnection(connection);
        }
        return socio;
    }
}
