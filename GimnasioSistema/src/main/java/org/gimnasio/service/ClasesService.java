package org.gimnasio.service;

import org.gimnasio.daos.*;
import org.gimnasio.model.*;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ClasesService {
    public static final Logger LOGGER = Logger.getLogger(ClasesService.class.getName());
    private ClaseDAO claseDAO;
    private EntrenadoresDAO entrenadoresDAO;
    private ReservasDAO reservasDAO;
    private PagosDAO pagosDAO;
    private FacturaDAO facturaDAO;

    public ClasesService() {
        this.claseDAO = new ClaseDAO();
        this.entrenadoresDAO = new EntrenadoresDAO();
        this.reservasDAO = new ReservasDAO();
        this.pagosDAO = new PagosDAO();
        this.facturaDAO = new FacturaDAO();
    }

    public boolean agregarClase(Clase clase) {
        try {
            claseDAO.agregarDato(clase);
            JOptionPane.showMessageDialog(null, "Clase agregada correctamente");
            return true;
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al agregar la clase: " + e.getMessage());
        }
        return false;
    }

    public boolean actualizarClase(Clase clase) {
        try {
            claseDAO.actualizarDato(clase);
            JOptionPane.showMessageDialog(null, "Clase actualizada correctamente");
            return true;
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al actualizar la clase: " + e.getMessage());
        }
        return false;
    }

    public boolean eliminarClase(Clase clase) {
        try {
            claseDAO.eliminarDato(clase);
            JOptionPane.showMessageDialog(null, "Clase eliminada correctamente");
            return true;
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al eliminar la clase: " + e.getMessage());
        }
        return false;
    }

    public Clase buscarClasePorNombre(String nombre) {
        Clase clase = null;
        try {
            clase = claseDAO.buscarDatoPorNombre(nombre);
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al buscar la clase: " + e.getMessage());
        }
        return clase;
    }

    public boolean validarHora(Time horaInicio, Time horaFin){
        Time horaAperturaGimnasio;
        Time horaCierreGimnasio;
        try {
            horaAperturaGimnasio = new GimnasioDAO().obtenerGimnasioPorId(1).getHorario_apertura();
            horaCierreGimnasio = new GimnasioDAO().obtenerGimnasioPorId(1).getHorario_cierre();
            if (horaInicio.before(horaFin)){
                if (horaInicio.equals(horaAperturaGimnasio) || horaInicio.after(horaAperturaGimnasio)) {
                    if (horaFin.equals(horaCierreGimnasio) || horaFin.before(horaCierreGimnasio)) {
                        return true;
                    } else {
                        JOptionPane.showMessageDialog(null, "La hora de fin de la clase debe ser antes de la hora de cierre del gimnasio");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "La hora de inicio de la clase debe ser después de la hora de apertura del gimnasio");
                }
            }
        }catch (SQLException e){
            LOGGER.severe(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al obtener la hora de apertura del gimnasio: " + e.getMessage());
        }
        return false;
    }

    public boolean validarCuposDeClase(int cuposClase, int capacidadEspacio){
        if (cuposClase <= capacidadEspacio){
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "La cantidad de cupos de la clase no puede ser mayor a la capacidad del espacio");
        }
        return false;
    }

    public List<TipoClase> obtenerListaDeTipoClases(){
        List<TipoClase> lista = new ArrayList<>();
        try {
            lista = new TipoClaseDAO().obtenerListaDeDatos();
        }catch (SQLException e){
            LOGGER.severe(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al obtener la lista de tipo de clases: " + e.getMessage());
        }
        return lista;
    }

    public List<Espacio> obtenerListaDeEspacios(){
        List<Espacio> lista = new ArrayList<>();
        try {
            lista = new EspaciosDAO().obtenerListaDeDatos();
        }catch (SQLException e){
            LOGGER.severe(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al obtener la lista de espacios: " + e.getMessage());
        }
        return lista;
    }

    public List<Clase> obtenerListaDeClases(){
        List<Clase> lista = new ArrayList<>();
        try {
            lista = claseDAO.obtenerListaDeDatos();
        }catch (SQLException e){
            LOGGER.severe(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al obtener la lista de clases: " + e.getMessage());
        }
        return lista;
    }

    public List<Entrenador> obtenerListaDeEntrenadores(){
        List<Entrenador> lista = null;
        try {
            lista = entrenadoresDAO.obtenerListaDeDatos();
        }catch (SQLException e){
            LOGGER.severe(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al obtener la lista de entrenadores: " + e.getMessage());
        }
        return lista;
    }

    public Entrenador buscarEntrenadorPorCedula(String cedula){
        Entrenador entrenador = null;
        try {
            entrenador = entrenadoresDAO.buscarDatoPorCedula(cedula);
        }catch (SQLException e){
            LOGGER.severe(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al buscar el entrenador: " + e.getMessage());
        }
        return entrenador;
    }

    public boolean agregarEntrenador(Entrenador entrenador){
        try {
            entrenadoresDAO.agregarDato(entrenador);
            JOptionPane.showMessageDialog(null, "Entrenador agregado correctamente");
            return true;
        }catch (SQLException e){
            LOGGER.severe(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al agregar el entrenador: " + e.getMessage());
        }
        return false;
    }

    public boolean actualizarEntrenador(Entrenador entrenador){
        try {
            entrenadoresDAO.actualizarDato(entrenador);
            JOptionPane.showMessageDialog(null, "Entrenador actualizado correctamente");
            return true;
        }catch (SQLException e){
            LOGGER.severe(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al actualizar el entrenador: " + e.getMessage());
        }
        return false;
    }

    public boolean eliminarEntrenador(Entrenador entrenador){
        try {
            entrenadoresDAO.eliminarDato(entrenador);
            JOptionPane.showMessageDialog(null, "Entrenador eliminado correctamente");
            return true;
        }catch (SQLException e){
            LOGGER.severe(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al eliminar el entrenador: " + e.getMessage());
        }
        return false;
    }

    public List<Horario> obtenerListaDeHorariosPorClase(int idClase) {
        List<Horario> lista = new ArrayList<>();
        try {
            lista = new HorariosDAO().obtenerListaDeHorariosPorClase(idClase);
            if (lista.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No hay horarios para la clase seleccionada");
            }
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al obtener la lista de horarios de la clase: " + e.getMessage());
        }
        return lista;
    }


    public boolean realizarReservaEnClase(Socio socio, Clase clase){
        Reserva reserva = new Reserva();
        reserva.setSocio(socio);
        reserva.setClaseProgramada(clase);
        try {
            if(cuposDisponibles(clase)){
                reservasDAO.agregarDato(reserva);
                JOptionPane.showMessageDialog(null, "Reserva realizada con éxito");
                Pago pagoClase = registrarPagoClase(socio, clase);
                if(pagoClase != null){
                    generarFacturaPagoClase(pagoClase);
                    JOptionPane.showMessageDialog(null, "Pago registrado con éxito");
                    return true;
                }
            }
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al realizar la reserva: " + e.getMessage());
        }
        return false;
    }

    private boolean cuposDisponibles(Clase clase){
        int cuposDisponibles = clase.getCupos();
        try {
            int cuposOcupados = reservasDAO.obtenerCuposReservados(clase.getId());
            if (cuposDisponibles > cuposOcupados){
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "No hay cupos disponibles para la clase seleccionada");
            }
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al obtener los cupos ocupados de la clase: " + e.getMessage());
        }
        return false;
    }

    private Pago registrarPagoClase(Socio socio, Clase clase){
        try {
            Pago pago = new Pago.Builder()
                    .socio(socio)
                    .clase(clase)
                    .monto(clase.getCosto())
                    .fechaPago(new java.sql.Date(System.currentTimeMillis()))
                    .metodoPago("Efectivo")
                    .tipoPago("clase")
                    .build();
            if(pagosDAO.agregarDato(pago)){
                return pagosDAO.buscarPagoPorClase(clase.getId());
            }
            JOptionPane.showMessageDialog(null, "Pago registrado con éxito");
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al registrar el pago: " + e.getMessage());
        }
        return null;
    }

    private void generarFacturaPagoClase(Pago pago){
        double subtotal = pago.getMonto() - pago.getMonto()*0.15;
        double iva = pago.getMonto()*0.15;

        //Crea la factura
        Factura factura = new Factura.Builder()
                .pago(pago)
                .numeroFactura("F" + pago.getId())
                .fechaEmision(pago.getFechaPago())
                .detalle(pago.getClase().getNombre())
                .subtotal(subtotal)
                .iva(iva)
                .total(pago.getMonto())
                .build();

        try {

            if(facturaDAO.agregarDato(factura)){
                JOptionPane.showMessageDialog(null, "Factura generada con éxito");
                //Genera el archivo PDF de la factura
                String rutaFacturaPDF = PdfGenerator.generarFacturaPDF(factura);
                //Envía el correo electrónico con la factura adjunta
                EmailService.enviarCorreoReservaClase(pago.getSocio(), pago.getClase(), obtenerListaDeHorariosPorClase(pago.getClase().getId()), rutaFacturaPDF);
            }

        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al generar la factura: " + e.getMessage());
        }catch (IOException e){
            LOGGER.severe(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
