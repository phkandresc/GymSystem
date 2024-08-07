package org.gimnasio.daos;

import org.gimnasio.model.Clase;
import org.gimnasio.model.DBConexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClaseDAO implements CRUD<Clase> {
    @Override
    public List<Clase> obtenerListaDeDatos() throws SQLException {
        List<Clase> listaClases = new ArrayList<>();
        PreparedStatement ps = null;
        Connection connection = DBConexion.getConnection();
        ps = connection.prepareStatement("SELECT * FROM clases");
        try {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Clase clase = new Clase();
                clase.setId(rs.getInt("id"));
                clase.setTipoClase(new TipoClaseDAO().buscarDatoPorId(rs.getInt("id_tipo_clase")));
                clase.setNombre(rs.getString("nombre"));
                clase.setDescripcion(rs.getString("descripcion"));
                clase.setCosto(rs.getDouble("costo"));
                clase.setCupos(rs.getInt("cupos"));
                clase.setInstructor(new EntrenadoresDAO().buscarDatoPorId(rs.getInt("id_entrenador")));
                listaClases.add(clase);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (connection != null) {
                connection.close();
            }
            DBConexion.closeConnection(connection);
        }
        return listaClases;
    }

    @Override
    public boolean agregarDato(Clase dato) throws SQLException {
        PreparedStatement ps = null;
        Connection connection = DBConexion.getConnection();
        try {

            ps = connection.prepareStatement("INSERT INTO clases (id_tipo_clase, nombre, descripcion, costo, cupos, id_entrenador) VALUES (?, ?, ?, ?, ?, ?)");
            ps.setInt(1, dato.getTipoClase().getId());
            ps.setString(2, dato.getNombre());
            ps.setString(3, dato.getDescripcion());
            ps.setDouble(4, dato.getCosto());
            ps.setInt(5, dato.getCupos());
            ps.setInt(6, dato.getInstructor().getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (connection != null) {
                connection.close();
            }
            DBConexion.closeConnection(connection);
        }
    }

    @Override
    public boolean actualizarDato(Clase dato) throws SQLException {
        PreparedStatement ps = null;
        Connection connection = DBConexion.getConnection();
        try {

            ps = connection.prepareStatement("UPDATE clases SET id_tipo_clase = ?, nombre = ?, descripcion = ?, costo = ?, cupos = ?, id_entrenador = ? WHERE id = ?");
            ps.setInt(1, dato.getTipoClase().getId());
            ps.setString(2, dato.getNombre());
            ps.setString(3, dato.getDescripcion());
            ps.setDouble(4, dato.getCosto());
            ps.setInt(5, dato.getCupos());
            ps.setInt(6, dato.getInstructor().getId());
            ps.setInt(7, dato.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (connection != null) {
                connection.close();
            }
            DBConexion.closeConnection(connection);
        }
    }

    @Override
    public boolean eliminarDato(Clase dato) throws SQLException {
        PreparedStatement ps = null;
        Connection connection = DBConexion.getConnection();
        try {
            ps = connection.prepareStatement("DELETE FROM clases WHERE id = ?");
            ps.setInt(1, dato.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (connection != null) {
                connection.close();
            }
            DBConexion.closeConnection(connection);
        }
    }

    @Override
    public Clase buscarDatoPorId(int id) throws SQLException {
        PreparedStatement ps = null;
        Clase clase = null;
        Connection connection = DBConexion.getConnection();

        try {
            ps = connection.prepareStatement("SELECT * FROM clases WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                clase = new Clase();
                clase.setId(rs.getInt("id"));
                clase.setTipoClase(new TipoClaseDAO().buscarDatoPorId(rs.getInt("id_tipo_clase")));
                clase.setNombre(rs.getString("nombre"));
                clase.setDescripcion(rs.getString("descripcion"));
                clase.setCosto(rs.getDouble("costo"));
                clase.setCupos(rs.getInt("cupos"));
                clase.setInstructor(new EntrenadoresDAO().buscarDatoPorId(rs.getInt("id_entrenador")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (connection != null) {
                connection.close();
            }
            DBConexion.closeConnection(connection);
        }
        return clase;
    }

    public boolean cuposDisponibles(Clase clase) throws SQLException {
        PreparedStatement ps = null;
        Connection connection = DBConexion.getConnection();
        try {
            ps = connection.prepareStatement("SELECT COUNT(*) FROM reservas WHERE id_clase = ?");
            ps.setInt(1, clase.getId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) < clase.getCupos();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Clase buscarDatoPorNombre(String nombre) throws SQLException {
        PreparedStatement ps = null;
        Clase clase = null;
        Connection connection = DBConexion.getConnection();
        try {
            ps = connection.prepareStatement("SELECT * FROM clases WHERE nombre = ?");
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                clase = new Clase();
                clase.setId(rs.getInt("id"));
                clase.setTipoClase(new TipoClaseDAO().buscarDatoPorId(rs.getInt("id_tipo_clase")));
                clase.setNombre(rs.getString("nombre"));
                clase.setDescripcion(rs.getString("descripcion"));
                clase.setCosto(rs.getDouble("costo"));
                clase.setCupos(rs.getInt("cupos"));
                clase.setInstructor(new EntrenadoresDAO().buscarDatoPorId(rs.getInt("id_entrenador")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clase;
    }
}