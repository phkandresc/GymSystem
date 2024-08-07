package org.gimnasio.daos;

import org.gimnasio.model.DBConexion;
import org.gimnasio.model.Espacio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EspaciosDAO implements CRUD<Espacio>{

    @Override
    public List<Espacio> obtenerListaDeDatos() throws SQLException {
        PreparedStatement ps = null;
        List<Espacio> listaEspacios = new ArrayList<>();
        Connection connection = DBConexion.getConnection();

        try {
            ps = connection.prepareStatement("SELECT * FROM espacios");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Espacio espacio = new Espacio();
                espacio.setId(rs.getInt("id"));
                espacio.setNombre(rs.getString("nombre"));
                espacio.setDescripcion(rs.getString("descripcion"));
                espacio.setCapacidad(rs.getInt("capacidad"));
                espacio.setUbicacion(rs.getString("ubicacion"));
                espacio.setGimnasio(new GimnasioDAO().obtenerGimnasioPorId(rs.getInt("id_gimnasio")));
                listaEspacios.add(espacio);
            }
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            if (ps != null){
                ps.close();
            }
            if(connection != null){
                connection.close();
            }
            DBConexion.closeConnection(connection);
        }
        return listaEspacios;
    }

    @Override
    public boolean agregarDato(Espacio dato) throws SQLException {
        PreparedStatement ps = null;
        Connection connection = DBConexion.getConnection();
        try {

            ps = connection.prepareStatement("INSERT INTO espacios (nombre, descripcion, capacidad, ubicacion, id_gimnasio) VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, dato.getNombre());
            ps.setString(2, dato.getDescripcion());
            ps.setInt(3, dato.getCapacidad());
            ps.setString(4, dato.getUbicacion());
            ps.setInt(5, 1);
            ps.executeUpdate();
            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        } finally {
            if (ps != null){
                ps.close();
            }
            if(connection != null){
                connection.close();
            }
            DBConexion.closeConnection(connection);
        }
    }

    @Override
    public boolean actualizarDato(Espacio dato) throws SQLException {
        PreparedStatement ps = null;
        Connection connection = DBConexion.getConnection();
        try {

            ps = connection.prepareStatement("UPDATE espacios SET nombre = ?, descripcion = ?, capacidad = ?, ubicacion = ?, id_gimnasio = ? WHERE id = ?");
            ps.setString(1, dato.getNombre());
            ps.setString(2, dato.getDescripcion());
            ps.setInt(3, dato.getCapacidad());
            ps.setString(4, dato.getUbicacion());
            ps.setInt(5, 1);
            ps.setInt(6, dato.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        } finally {
            if (ps != null){
                ps.close();
            }
            if(connection != null){
                connection.close();
            }
            DBConexion.closeConnection(connection);
        }
    }

    @Override
    public boolean eliminarDato(Espacio dato) throws SQLException {
        PreparedStatement ps = null;
        Connection connection = DBConexion.getConnection();
        try {
            ps = connection.prepareStatement("DELETE FROM espacios WHERE id = ?");
            ps.setInt(1, dato.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        } finally {
            if (ps != null){
                ps.close();
            }
            if(connection != null){
                connection.close();
            }
            DBConexion.closeConnection(connection);
        }
    }

    @Override
    public Espacio buscarDatoPorId(int id) throws SQLException {
        PreparedStatement ps = null;
        Espacio espacio = new Espacio();
        Connection connection = DBConexion.getConnection();
        try {

            ps = connection.prepareStatement("SELECT * FROM espacios WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                espacio.setId(rs.getInt("id"));
                espacio.setNombre(rs.getString("nombre"));
                espacio.setDescripcion(rs.getString("descripcion"));
                espacio.setCapacidad(rs.getInt("capacidad"));
                espacio.setUbicacion(rs.getString("ubicacion"));
                espacio.setGimnasio(new GimnasioDAO().obtenerGimnasioPorId(rs.getInt("id_gimnasio")));
            }
    }catch (SQLException e){
        e.printStackTrace();
    } finally {
        if (ps != null){
            ps.close();
        }
        if(connection != null){
            connection.close();
        }
        DBConexion.closeConnection(connection);
    }
        return espacio;
    }
}
