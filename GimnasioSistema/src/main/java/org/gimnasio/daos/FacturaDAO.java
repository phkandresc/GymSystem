package org.gimnasio.daos;

import org.gimnasio.model.DBConexion;
import org.gimnasio.model.Factura;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

public class FacturaDAO implements CRUD<Factura>{
    private static final Logger LOGGER = Logger.getLogger(FacturaDAO.class.getName());
    private final String REGISTRAR_FACTURA = "INSERT INTO facturas (id_pago, numero_factura, fecha_emision, detalle, subtotal, iva, total) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private final String BUSCAR_FACTURA_POR_ID = "SELECT * FROM facturas WHERE id = ?";

    @Override
    public List<Factura> obtenerListaDeDatos() throws SQLException {
        return List.of();
    }

    @Override
    public boolean agregarDato(Factura dato) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = DBConexion.getConnection().prepareStatement(REGISTRAR_FACTURA);
            ps.setInt(1, dato.getPago().getId());
            ps.setString(2, dato.getNumeroFactura());
            ps.setDate(3, dato.getFechaEmision());
            ps.setString(4, dato.getDetalle());
            ps.setDouble(5, dato.getSubtotal());
            ps.setDouble(6, dato.getIva());
            ps.setDouble(7, dato.getTotal());
            ps.executeUpdate();
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
    public boolean actualizarDato(Factura dato) throws SQLException {
        return false;
    }

    @Override
    public boolean eliminarDato(Factura dato) throws SQLException {
        return false;
    }

    @Override
    public Factura buscarDatoPorId(int id) throws SQLException {
        Factura factura = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = DBConexion.getConnection().prepareStatement(BUSCAR_FACTURA_POR_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                factura = new Factura.Builder()
                        .id(rs.getInt("id"))
                        .pago(new PagosDAO().buscarDatoPorId(rs.getInt("id_pago")))
                        .numeroFactura(rs.getString("numero_factura"))
                        .fechaEmision(rs.getDate("fecha_emision"))
                        .detalle(rs.getString("detalle"))
                        .subtotal(rs.getDouble("subtotal"))
                        .iva(rs.getDouble("iva"))
                        .total(rs.getDouble("total"))
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
        return factura;
    }
}
