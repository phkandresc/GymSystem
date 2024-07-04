package org.gimnasio.daos;

import org.gimnasio.model.DBConexion;
import org.gimnasio.model.Espacio;
import org.gimnasio.model.Horario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HorariosDAO implements CRUD<Horario> {

    @Override
    public List<Horario> obtenerListaDeDatos() throws SQLException {
        PreparedStatement ps = null;
        List<Horario> listaHorarios = new ArrayList<>();
        Connection connection = DBConexion.getConnection();
        try {

            ps = connection.prepareStatement("SELECT * FROM horarios");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Horario horario = new Horario();
                horario.setId(rs.getInt("id"));
                horario.setClaseProgramada(new ClaseDAO().buscarDatoPorId(rs.getInt("id_clase_programada")));
                horario.setDiaSemana(rs.getString("dia_semana"));
                horario.setHoraInicio(rs.getTime("hora_inicio"));
                horario.setHoraFin(rs.getTime("hora_fin"));
                horario.setFechaInicio(rs.getDate("fecha_inicio"));
                horario.setFechaFin(rs.getDate("fecha_fin"));
                listaHorarios.add(horario);
            }

        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            if (ps != null){
                ps.close();
            }
            if (connection != null) {
                connection.close();
            }
            DBConexion.closeConnection(connection);
        }
        return listaHorarios;
    }

    @Override
    public boolean agregarDato(Horario dato) throws SQLException {
        PreparedStatement ps = null;
        Connection connection = DBConexion.getConnection();
        try {

            ps = connection.prepareStatement("INSERT INTO horarios (id_clase_programada, dia_semana, hora_inicio, hora_fin, fecha_inicio, fecha_fin, id_espacio) VALUES (?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, dato.getClaseProgramada().getId());
            ps.setString(2, dato.getDiaSemana());
            ps.setTime(3, dato.getHoraInicio());
            ps.setTime(4, dato.getHoraFin());
            ps.setDate(5, dato.getFechaInicio());
            ps.setDate(6, dato.getFechaFin());
            ps.setInt(7, dato.getEspacio().getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        } finally {
            if (ps != null){
                ps.close();
            }
            if (connection != null) {
                connection.close();
            }
            DBConexion.closeConnection(connection);
        }
    }

    @Override
    public boolean actualizarDato(Horario dato) throws SQLException {
        return false;
    }

    @Override
    public boolean eliminarDato(Horario dato) throws SQLException {
        return false;
    }

    @Override
    public Horario buscarDatoPorId(int id) throws SQLException {
        PreparedStatement ps = null;
        Horario horario = null;
        Connection connection = DBConexion.getConnection();
        try {

            ps = connection.prepareStatement("SELECT * FROM horarios WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                horario = new Horario();
                horario.setId(rs.getInt("id"));
                horario.setClaseProgramada(new ClaseDAO().buscarDatoPorId(rs.getInt("id_clase_programada")));
                horario.setDiaSemana(rs.getString("dia_semana"));
                horario.setHoraInicio(rs.getTime("hora_inicio"));
                horario.setHoraFin(rs.getTime("hora_fin"));
                horario.setFechaInicio(rs.getDate("fecha_inicio"));
                horario.setFechaFin(rs.getDate("fecha_fin"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if (ps != null){
                ps.close();
            }
            if (connection != null) {
                connection.close();
            }
            DBConexion.closeConnection(connection);
        }
        return horario;
    }

    public List<Horario> obtenerListaDeHorariosPorEspacio(int idEspacio) throws SQLException {
        PreparedStatement ps = null;
        List<Horario> listaHorarios = new ArrayList<>();
        Connection connection = DBConexion.getConnection();
        try {

            ps = connection.prepareStatement("SELECT * FROM horarios WHERE id_espacio = ?");
            ps.setInt(1, idEspacio);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Horario horario = new Horario();
                horario.setId(rs.getInt("id"));
                horario.setClaseProgramada(new ClaseDAO().buscarDatoPorId(rs.getInt("id_clase_programada")));
                horario.setDiaSemana(rs.getString("dia_semana"));
                horario.setHoraInicio(rs.getTime("hora_inicio"));
                horario.setHoraFin(rs.getTime("hora_fin"));
                horario.setFechaInicio(rs.getDate("fecha_inicio"));
                horario.setFechaFin(rs.getDate("fecha_fin"));
                listaHorarios.add(horario);
            }

        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null){
                ps.close();
            }
            if (connection != null) {
                connection.close();
            }
            DBConexion.closeConnection(connection);

        }
        return listaHorarios;
    }

    public List<Horario> obtenerListaDeHorariosPorEspacioYDia(int idEspacio, String dia) throws SQLException {
        PreparedStatement ps = null;
        List<Horario> listaHorarios = new ArrayList<>();
        Connection connection = DBConexion.getConnection();
        try {

            ps = connection.prepareStatement("SELECT * FROM horarios WHERE id_espacio = ? AND dia_semana = ?");
            ps.setInt(1, idEspacio);
            ps.setString(2, dia);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Horario horario = new Horario();
                horario.setId(rs.getInt("id"));
                horario.setClaseProgramada(new ClaseDAO().buscarDatoPorId(rs.getInt("id_clase_programada")));
                horario.setDiaSemana(rs.getString("dia_semana"));
                horario.setHoraInicio(rs.getTime("hora_inicio"));
                horario.setHoraFin(rs.getTime("hora_fin"));
                horario.setFechaInicio(rs.getDate("fecha_inicio"));
                horario.setFechaFin(rs.getDate("fecha_fin"));
                listaHorarios.add(horario);
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
        return listaHorarios;
    }
}
