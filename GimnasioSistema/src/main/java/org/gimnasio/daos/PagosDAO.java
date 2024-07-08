package org.gimnasio.daos;

import org.gimnasio.model.DBConexion;
import org.gimnasio.model.Pago;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

public class PagosDAO implements CRUD<Pago>{
    private static final Logger LOGGER = Logger.getLogger(PagosDAO.class.getName());

    private final String REGISTRAR_PAGOMEMBRESIA = "INSERT INTO pagos (id_socio, id_membresia, monto, fecha_pago, metodo_pago, tipo_pago) VALUES (?, ?, ?, ?, ?, ?)";
    private final String REGISTRAR_PAGOCLASE = "INSERT INTO pagos (id_socio, id_clase, monto, fecha_pago, metodo_pago, tipo_pago) VALUES (?, ?, ?, ?, ?, ?)";
    private final String BUSCAR_PAGO_POR_ID_MEMBRESIA = "SELECT * FROM pagos WHERE id_membresia = ?";
    private final String BUSCAR_PAGO_POR_ID = "SELECT * FROM pagos WHERE id = ?";

    @Override
    public List<Pago> obtenerListaDeDatos() throws SQLException {
        return List.of();
    }

    @Override
    public boolean agregarDato(Pago dato) throws SQLException {
        PreparedStatement ps = null;
        try {
            if (dato.getClase() == null) {
                ps = DBConexion.getConnection().prepareStatement(REGISTRAR_PAGOMEMBRESIA);
                ps.setInt(1, dato.getSocio().getId());
                ps.setInt(2, dato.getMembresia().getId());
                ps.setDouble(3, dato.getMonto());
                ps.setDate(4, dato.getFechaPago());
                ps.setString(5, dato.getMetodoPago());
                ps.setString(6, dato.getTipoPago());
                ps.executeUpdate();

            }else if(dato.getMembresia() == null){
                ps = DBConexion.getConnection().prepareStatement(REGISTRAR_PAGOCLASE);
                ps.setInt(1, dato.getSocio().getId());
                ps.setInt(2, dato.getClase().getId());
                ps.setDouble(3, dato.getMonto());
                ps.setDate(4, dato.getFechaPago());
                ps.setString(5, dato.getMetodoPago());
                ps.setString(6, dato.getTipoPago());
                ps.executeUpdate();

            }
            return true;
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
            return false;
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    @Override
    public boolean actualizarDato(Pago dato) throws SQLException {
        return false;
    }

    @Override
    public boolean eliminarDato(Pago dato) throws SQLException {
        return false;
    }

    @Override
    public Pago buscarDatoPorId(int id) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Pago pago = null;
        try {
            ps = DBConexion.getConnection().prepareStatement(BUSCAR_PAGO_POR_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                pago = new Pago.Builder()
                        .id(rs.getInt("id"))
                        .socio(new SocioDAO().buscarDatoPorId(rs.getInt("id_socio")))
                        .membresia(new MembresiaDAO().buscarDatoPorId(rs.getInt("id_membresia")))
                        .monto(rs.getDouble("monto"))
                        .fechaPago(rs.getDate("fecha_pago"))
                        .metodoPago(rs.getString("metodo_pago"))
                        .tipoPago(rs.getString("tipo_pago"))
                        .build();
            }
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
        }
        return pago;
    }

    public Pago buscarPagoPorMembresia(int idMembresia) throws SQLException {
        Pago pago = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = DBConexion.getConnection().prepareStatement(BUSCAR_PAGO_POR_ID_MEMBRESIA);
            ps.setInt(1, idMembresia);
            rs = ps.executeQuery();
            if (rs.next()) {
                pago = new Pago.Builder()
                        .id(rs.getInt("id"))
                        .socio(new SocioDAO().buscarDatoPorId(rs.getInt("id_socio")))
                        .membresia(new MembresiaDAO().buscarDatoPorId(rs.getInt("id_membresia")))
                        .monto(rs.getDouble("monto"))
                        .fechaPago(rs.getDate("fecha_pago"))
                        .metodoPago(rs.getString("metodo_pago"))
                        .tipoPago(rs.getString("tipo_pago"))
                        .build();
            }
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
        }
        return pago;
    }

    public Pago buscarPagoPorClase(int idClase) throws SQLException {
        Pago pago = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = DBConexion.getConnection().prepareStatement("SELECT * FROM pagos WHERE id_clase = ?");
            ps.setInt(1, idClase);
            rs = ps.executeQuery();
            if (rs.next()) {
                pago = new Pago.Builder()
                        .id(rs.getInt("id"))
                        .socio(new SocioDAO().buscarDatoPorId(rs.getInt("id_socio")))
                        .clase(new ClaseDAO().buscarDatoPorId(rs.getInt("id_clase")))
                        .monto(rs.getDouble("monto"))
                        .fechaPago(rs.getDate("fecha_pago"))
                        .metodoPago(rs.getString("metodo_pago"))
                        .tipoPago(rs.getString("tipo_pago"))
                        .build();
            }
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            DBConexion.closeConnection(DBConexion.getConnection());
        }
        return pago;
    }
}
