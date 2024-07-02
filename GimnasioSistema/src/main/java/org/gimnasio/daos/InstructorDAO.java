package org.gimnasio.daos;

import org.gimnasio.model.DBConexion;
import org.gimnasio.model.Instructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InstructorDAO implements CRUD<Instructor> {
    @Override
    public List<Instructor> obtenerListaDeDatos() throws SQLException {
        PreparedStatement ps = null;
        List<Instructor> listaInstructores = new ArrayList<>();
        try {
            Connection connection = DBConexion.getConnection();
            ps = connection.prepareStatement("SELECT * FROM instructores");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Instructor instructor = new Instructor();
                instructor.setId(rs.getInt("id"));
                instructor.setNombre(rs.getString("nombre"));
                instructor.setDescripcion(rs.getString("descripcion"));
                listaInstructores.add(instructor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaInstructores;
    }

        @Override
        public boolean agregarDato (Instructor dato) throws SQLException {
            return false;
        }

        @Override
        public boolean actualizarDato (Instructor dato) throws SQLException {
            return false;
        }

        @Override
        public boolean eliminarDato (Instructor dato) throws SQLException {
            return false;
        }

        @Override
        public Instructor buscarDatoPorId (int id) throws SQLException {
            PreparedStatement ps = null;
            try {
                Connection connection = DBConexion.getConnection();
                ps = connection.prepareStatement("SELECT * FROM instructores WHERE id = ?");
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    Instructor instructor = new Instructor();
                    instructor.setId(rs.getInt("id"));
                    instructor.setNombre(rs.getString("nombre"));
                    instructor.setDescripcion(rs.getString("descripcion"));
                    return instructor;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

