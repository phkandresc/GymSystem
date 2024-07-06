package org.gimnasio.daos;

import org.gimnasio.model.DBConexion;
import org.gimnasio.model.Entrenador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class EntrenadoresDAO implements CRUD<Entrenador>{
    private static final Logger LOGGER = Logger.getLogger(EntrenadoresDAO.class.getName());

    @Override
    public List<Entrenador> obtenerListaDeDatos() throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = DBConexion.getConnection();
        List<Entrenador> entrenadores = new ArrayList<>();
        try{
            ps = conn.prepareStatement("SELECT * FROM entrenadores");
            rs = ps.executeQuery();
            while(rs.next()){
                Entrenador entrenador = new Entrenador();
                entrenador.setId(rs.getInt("id"));
                entrenador.setCedula(rs.getString("cedula"));
                entrenador.setNombre(rs.getString("nombre"));
                entrenador.setApellido(rs.getString("apellido"));
                entrenador.setEmail(rs.getString("email"));
                entrenador.setNumeroTelefono(rs.getString("numero_telefono"));
                entrenador.setDireccion(rs.getString("direccion"));
                entrenador.setEspecialidad(rs.getString("especialidad"));
                entrenador.setFechaContratacion(rs.getDate("fecha_contratacion"));
                entrenador.setEstado(rs.getString("estado"));
                entrenadores.add(entrenador);
            }
            return entrenadores;

        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
            return null;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if(rs != null){
                rs.close();
            }
            if(conn != null){
                conn.close();
            }
        }

    }

    @Override
    public boolean agregarDato(Entrenador dato) throws SQLException {
        PreparedStatement ps = null;
        Connection conn = DBConexion.getConnection();
        try{
            ps = conn.prepareStatement("INSERT INTO entrenadores (cedula, nombre, apellido, email, numero_telefono, direccion, especialidad, fecha_contratacion) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, dato.getCedula());
            ps.setString(2, dato.getNombre());
            ps.setString(3, dato.getApellido());
            ps.setString(4, dato.getEmail());
            ps.setString(5, dato.getNumeroTelefono());
            ps.setString(6, dato.getDireccion());
            ps.setString(7, dato.getEspecialidad());
            ps.setDate(8, dato.getFechaContratacion());
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
            return false;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if(conn != null){
                conn.close();
            }
        }

    }

    @Override
    public boolean actualizarDato(Entrenador dato) throws SQLException {
        PreparedStatement ps = null;
        Connection conn = DBConexion.getConnection();
        try{
            ps = conn.prepareStatement("UPDATE entrenadores SET cedula = ?, nombre = ?, apellido = ?, email = ?, numero_telefono = ?, direccion = ?, especialidad = ? WHERE id = ?");
            ps.setString(1, dato.getCedula());
            ps.setString(2, dato.getNombre());
            ps.setString(3, dato.getApellido());
            ps.setString(4, dato.getEmail());
            ps.setString(5, dato.getNumeroTelefono());
            ps.setString(6, dato.getDireccion());
            ps.setString(7, dato.getEspecialidad());
            ps.setInt(8, dato.getId());
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
            return false;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if(conn != null){
                conn.close();
            }
        }
    }

    @Override
    public boolean eliminarDato(Entrenador dato) throws SQLException {
        PreparedStatement ps = null;
        Connection conn = DBConexion.getConnection();
        try{
            ps = conn.prepareStatement("DELETE FROM entrenadores WHERE id = ?");
            ps.setInt(1, dato.getId());
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
            return false;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if(conn != null){
                conn.close();
            }
        }
    }

    @Override
    public Entrenador buscarDatoPorId(int id) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = DBConexion.getConnection();
        Entrenador entrenador = null;
        try{
            ps = conn.prepareStatement("SELECT * FROM entrenadores WHERE id = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if(rs.next()){
                entrenador = new Entrenador();
                entrenador.setId(rs.getInt("id"));
                entrenador.setCedula(rs.getString("cedula"));
                entrenador.setNombre(rs.getString("nombre"));
                entrenador.setApellido(rs.getString("apellido"));
                entrenador.setEmail(rs.getString("email"));
                entrenador.setNumeroTelefono(rs.getString("numero_telefono"));
                entrenador.setDireccion(rs.getString("direccion"));
                entrenador.setEspecialidad(rs.getString("especialidad"));
                entrenador.setFechaContratacion(rs.getDate("fecha_contratacion"));
            }
            return entrenador;

        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
            return null;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if(rs != null){
                rs.close();
            }
            if(conn != null){
                conn.close();
            }
        }

    }

    public Entrenador buscarDatoPorCedula(String cedula) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = DBConexion.getConnection();
        Entrenador entrenador = null;
        try{
            ps = conn.prepareStatement("SELECT * FROM entrenadores WHERE cedula = ?");
            ps.setString(1, cedula);
            rs = ps.executeQuery();
            if(rs.next()){
                entrenador = new Entrenador();
                entrenador.setId(rs.getInt("id"));
                entrenador.setCedula(rs.getString("cedula"));
                entrenador.setNombre(rs.getString("nombre"));
                entrenador.setApellido(rs.getString("apellido"));
                entrenador.setEmail(rs.getString("email"));
                entrenador.setNumeroTelefono(rs.getString("numero_telefono"));
                entrenador.setDireccion(rs.getString("direccion"));
                entrenador.setEspecialidad(rs.getString("especialidad"));
                entrenador.setFechaContratacion(rs.getDate("fecha_contratacion"));
                entrenador.setEstado(rs.getString("estado"));
            }
            return entrenador;

        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
            return null;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if(rs != null){
                rs.close();
            }
            if(conn != null){
                conn.close();
            }
        }

    }
}
