package org.gimnasio.daos;

import org.gimnasio.model.DBConexion;
import org.gimnasio.model.Socio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class SocioDAO implements CRUD<Socio> {
    public static final Logger LOGGER = Logger.getLogger(SocioDAO.class.getName());
    private final String SELECCIONAR_TODOS_SOCIOS = "SELECT * FROM socios";
    private final String BUSCARSOCIO_PORAPELLIDO = "SELECT * FROM socios WHERE apellido = ?";
    private final String BUSCARSOCIO_PORCEDULA = "SELECT * FROM socios WHERE cedula = ?";
    private final String BUSCARSOCIO_PORID = "SELECT * FROM socios WHERE id = ?";
    private final String ELIMINAR_SOCIO = "DELETE FROM socios WHERE id = ?";
    private final String ACTUALIZAR_SOCIO = "UPDATE socios SET nombre = ?, apellido = ?, email = ?, numero_telefono = ?, direccion = ?, fecha_nacimiento = ? WHERE id = ?";
    private final String REGISTRAR_SOCIO = "INSERT INTO socios (cedula, nombre, apellido, email, numero_telefono, direccion, fecha_nacimiento) VALUES (?, ?, ?, ?, ?, ?, ?)";


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

    @Override
    public List<Socio> obtenerListaDeDatos() throws SQLException {
        List<Socio> listaSocios = new ArrayList<>();
        Connection conexion = DBConexion.getConnection();
        try (PreparedStatement statement = conexion.prepareStatement(SELECCIONAR_TODOS_SOCIOS);
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

    @Override
    public boolean agregarDato(Socio dato) throws SQLException {
        Connection connection = DBConexion.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(REGISTRAR_SOCIO)) {
            statement.setString(1, dato.getCedula());
            statement.setString(2, dato.getNombre());
            statement.setString(3, dato.getApellido());
            statement.setString(4, dato.getEmail());
            statement.setString(5, dato.getNumeroTelefono());
            statement.setString(6, dato.getDireccion());
            statement.setDate(7, dato.getFechaNacimiento());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        } finally {
            DBConexion.closeConnection(connection);
        }
        return false;
    }

    @Override
    public boolean actualizarDato(Socio dato) throws SQLException {
        Connection connection = DBConexion.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(ACTUALIZAR_SOCIO)) {
            statement.setString(1, dato.getNombre());
            statement.setString(2, dato.getApellido());
            statement.setString(3, dato.getEmail());
            statement.setString(4, dato.getNumeroTelefono());
            statement.setString(5, dato.getDireccion());
            statement.setDate(6, dato.getFechaNacimiento());
            statement.setString(7, dato.getCedula());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        } finally {
            DBConexion.closeConnection(connection);
        }
        return false;
    }

    @Override
    public boolean eliminarDato(Socio dato) throws SQLException {
        Socio socioEliminar = buscarDatoPorId(dato.getId());
        Connection connection = DBConexion.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(ELIMINAR_SOCIO)) {
            statement.setInt(1, socioEliminar.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        } finally {
            DBConexion.closeConnection(connection);
        }
        return false;
    }

    @Override
    public Socio buscarDatoPorId(int id) throws SQLException {
        Socio socio = null;
        Connection connection = DBConexion.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(BUSCARSOCIO_PORID)) {
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
}
