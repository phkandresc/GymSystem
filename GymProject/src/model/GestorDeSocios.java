package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GestorDeSocios implements IGestorDeSocios {
    private DatabaseManager dbManager;

    public GestorDeSocios() {
        this.dbManager = new DatabaseManager();
    }

    @Override
    public void agregarSocio(Socio socio) {
        try (Connection connection = dbManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO socios (nombre, apellido, cedula, direccion, email, numero_telefono, fecha_nacimiento) VALUES (?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, socio.getNombre());
            statement.setString(2, socio.getApellido());
            statement.setString(3, socio.getCedula());
            statement.setString(4, socio.getDireccion());
            statement.setString(5, socio.getEmail());
            statement.setString(6, socio.getNumeroTelefono());
            statement.setDate(7, new java.sql.Date(socio.getFechaNacimiento().getTime()));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void agregarMembresia(Membresia membresia) {
        try (Connection connection = dbManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO membresias (id_socio, tipo, fecha_inicio, fecha_fin, costo, estado) VALUES (?, ?, ?, ?, ?, ?)");
            statement.setString(1,"1");
            statement.setString(2, membresia.getTipo());
            statement.setString(3, membresia.getFechaInicio());
            statement.setString(4, membresia.getFechaFin());
            statement.setDouble(5, membresia.getCosto());
            statement.setString(6, membresia.getEstado());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }


}
