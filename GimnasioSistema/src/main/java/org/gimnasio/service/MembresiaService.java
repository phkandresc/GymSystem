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
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
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
            //Establece fechaInicio y fechaFin para la membresía
            Date fechaInicio = obtenerFechaInicio();
            Date fechaFin = obtenerFechaFin(tipoMembresia.getDuracion());

            //Crea la membresia
            Membresia nuevaMembresia = new Membresia.Builder()
                    .socio(socio)
                    .tipoMembresia(tipoMembresia)
                    .idGimnasio(1)
                    .fechaInicio(fechaInicio)
                    .fechaFin(fechaFin)
                    .build();
            //Registra la membresía en la base de datos, y obtiene el objeto con el id generado
            nuevaMembresia = membresiaDAO.agregarMembresia(nuevaMembresia);
            //Registra el pago de la membresía
            Pago pagoRegistrado = registrarPagoMembresia(nuevaMembresia);
            //Si el pago se registró correctamente, genera la factura
            if (pagoRegistrado != null) {
                //Genera la factura y envía el correo electronico con la factura adjunta
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

        //Crea la factura
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
            //Registra la factura en la base de datos
            if(facturaDAO.agregarDato(factura)){
                //Genera el archivo PDF de la factura
                String rutaFacturaPDF = PdfGenerator.generarFacturaPDF(factura);
                //Envía el correo electrónico con la factura adjunta
                EmailService.enviarCorreoRegistroMembresia(pago.getSocio(), pago.getMembresia(), pago.getMembresia().getTipoMembresia(), rutaFacturaPDF);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al generar la factura: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void verificarYEnviarAlertas() {
        try {
            List<Membresia> membresias = membresiaDAO.obtenerMembresiasActivas();
            LocalDate today = LocalDate.now();

            for (Membresia membresia : membresias) {
                long daysUntilExpiry = ChronoUnit.DAYS.between(today, membresia.getFechaFin().toLocalDate());

                if (daysUntilExpiry <= 7 && daysUntilExpiry >= 0) {
                    enviarAlerta(membresia.getSocio().getId(), "Tu membresía está por caducar en " + daysUntilExpiry + " días.");
                    membresiaDAO.actualizarEstadoMembresia(membresia.getId(), "por caducar");
                } else if (daysUntilExpiry < 0) {
                    // Membresía vencida
                    enviarAlerta(membresia.getSocio().getId(), "Tu membresía ha vencido.");
                    membresiaDAO.actualizarEstadoMembresia(membresia.getId(), "inactivo");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void enviarAlerta(int idSocio, String mensaje) {
        // Implementa el envío de la alerta (correo, notificación, etc.)
        System.out.println("Enviando alerta a socio " + idSocio + ": " + mensaje);
    }

    public void agregarTipoMembresia(TipoMembresia tipoMembresia) throws SQLException {
        try {
            if(tipoMembresiaDAO.agregarDato(tipoMembresia)){
                JOptionPane.showMessageDialog(null, "Tipo de membresía agregado con éxito");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar el tipo de membresía: " + e.getMessage());
        }
    }

    public void modificarTipoMembresia(TipoMembresia tipoMembresia) throws SQLException {
        try {
            if(tipoMembresiaDAO.actualizarDato(tipoMembresia)){
                JOptionPane.showMessageDialog(null, "Tipo de membresía modificado con éxito");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar el tipo de membresía: " + e.getMessage());
        }
    }

    public void eliminarTipoMembresia(TipoMembresia tipoMembresia) throws SQLException {
        try {
            if(tipoMembresiaDAO.eliminarDato(tipoMembresia)){
                JOptionPane.showMessageDialog(null, "Tipo de membresía eliminado con éxito");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el tipo de membresía: " + e.getMessage());
        }
    }
}
