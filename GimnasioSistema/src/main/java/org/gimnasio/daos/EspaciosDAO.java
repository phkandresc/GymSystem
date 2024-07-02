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
        try {
            Connection connection = DBConexion.getConnection();
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
        }
        return listaEspacios;
    }

    @Override
    public boolean agregarDato(Espacio dato) throws SQLException {
        PreparedStatement ps = null;
        try {
            Connection connection = DBConexion.getConnection();
            ps = connection.prepareStatement("INSERT INTO espacios (nombre, descripcion, capacidad, ubicacion, id_gimnasio) VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, dato.getNombre());
            ps.setString(2, dato.getDescripcion());
            ps.setInt(3, dato.getCapacidad());
            ps.setString(4, dato.getUbicacion());
            ps.setInt(5, dato.getGimnasio().getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        } finally {
            if (ps != null){
                ps.close();
            }
        }
    }

    @Override
    public boolean actualizarDato(Espacio dato) throws SQLException {
        return false;
    }

    @Override
    public boolean eliminarDato(Espacio dato) throws SQLException {
        return false;
    }

    @Override
    public Espacio buscarDatoPorId(int id) throws SQLException {
        PreparedStatement ps = null;
        Espacio espacio = new Espacio();
        try {
            Connection connection = DBConexion.getConnection();
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
    }
        return espacio;
    }
}
