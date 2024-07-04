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
}
