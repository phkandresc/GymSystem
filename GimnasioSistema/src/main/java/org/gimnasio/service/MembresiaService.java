package org.gimnasio.service;

import org.gimnasio.daos.FacturaDAO;
import org.gimnasio.daos.MembresiaDAO;
import org.gimnasio.daos.PagosDAO;
import org.gimnasio.daos.TipoMembresiaDAO;
import org.gimnasio.model.*;

import javax.swing.*;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

public class MembresiaService {
    private MembresiaDAO membresiaDAO;
    private TipoMembresiaDAO tipoMembresiaDAO;
    private PagosDAO pagosDAO;
    private FacturaDAO facturaDAO;

    public MembresiaService() {
        this.membresiaDAO = new MembresiaDAO();
        this.tipoMembresiaDAO = new TipoMembresiaDAO();
        this.pagosDAO = new PagosDAO();
        this.facturaDAO = new FacturaDAO();
    }

    public boolean registrarMembresia(Socio socio, TipoMembresia tipoMembresia) throws SQLException {
        try {
            Date fechaInicio = obtenerFechaInicio();
            Date fechaFin = obtenerFechaFin(tipoMembresia.getDuracion());

            Membresia nuevaMembresia = new Membresia.Builder()
                    .socio(socio)
                    .tipoMembresia(tipoMembresia)
                    .idGimnasio(1)
                    .fechaInicio(fechaInicio)
                    .fechaFin(fechaFin)
                    .build();
                nuevaMembresia = membresiaDAO.agregarMembresia(nuevaMembresia);
                Pago pagoRegistrado = registrarPagoMembresia(nuevaMembresia);
                if (pagoRegistrado != null) {
                    generarFactura(pagoRegistrado);
                    JOptionPane.showMessageDialog(null, "Membresía registrada con éxito");
                    return true;
                }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar la membresía: " + e.getMessage());

        }
        return false;
    }

    public Membresia obtenerMembresiaRecienAgregada() throws SQLException {
        return membresiaDAO.obtenerMembresiaRecienAgregada();
    }
    public List<TipoMembresia> obtenerTiposMembresia() throws SQLException {
        return tipoMembresiaDAO.obtenerListaDeDatos();
    }

    private Date obtenerFechaInicio() {
        Calendar calendar = Calendar.getInstance();
        return new Date(calendar.getTimeInMillis());
    }

    private Date obtenerFechaFin(int duracion) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, duracion);
        return new Date(calendar.getTimeInMillis());
    }

    private Pago registrarPagoMembresia(Membresia membresia) throws SQLException {
        try {
            Pago pago = new Pago.Builder()
                    .socio(membresia.getSocio())
                    .membresia(membresia)
                    .monto(membresia.getTipoMembresia().getPrecio())
                    .fechaPago(membresia.getFechaInicio())
                    .metodoPago("Efectivo")
                    .tipoPago("membresia")
                    .build();
            if(pagosDAO.agregarDato(pago)){
                return pagosDAO.buscarPagoPorMembresia(membresia.getId());
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error al registrar el pago: " + e.getMessage());
        }
        return null;
    }

    private void generarFactura(Pago pago) {
        double subtotal = pago.getMonto() - pago.getMonto()*0.15;
        double iva = pago.getMonto()*0.15;

        Factura factura = new Factura.Builder()
                .pago(pago)
                .numeroFactura("F" + pago.getId())
                .fechaEmision(pago.getFechaPago())
                .detalle(pago.getMembresia().getTipoMembresia().getDescripcion())
                .subtotal(subtotal)
                .iva(iva)
                .total(pago.getMonto())
                .build();
        try {
            if(facturaDAO.agregarDato(factura)){
                PdfGenerator.generatePdf(factura, factura.getNumeroFactura()+"-FACTURA.pdf");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al generar la factura: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
