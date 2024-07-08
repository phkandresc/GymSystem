package org.gimnasio.daos;

import org.gimnasio.model.DBConexion;
import org.gimnasio.model.Socio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ControlAccesoDAO {

    public boolean registrarAcceso(Socio socio) throws SQLException {
        PreparedStatement ps = null;
        Connection con = DBConexion.getConnection();
        String sql = "INSERT INTO control_acceso (id_socio) VALUES (?)";
        if(new MembresiaDAO().verificarMembresiaActivaDeSocio(socio)){
            try {
                ps = con.prepareStatement(sql);
                ps.setInt(1, socio.getId());
                ps.executeUpdate();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            } finally {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
                DBConexion.closeConnection(con);
            }
        }else {
            return false;
        }
    }


}
