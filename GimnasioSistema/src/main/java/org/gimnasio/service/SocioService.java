package org.gimnasio.service;

import org.gimnasio.daos.SocioDAO;
import org.gimnasio.model.Socio;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class SocioService {
    private SocioDAO socioDAO;

    public SocioService() {
        this.socioDAO = new SocioDAO();
    }

    public boolean registrarSocio(Socio socio) throws SQLException {
        try {
            if (socioDAO.agregarDato(socio)) {
                enviarEmailBienvenida(buscarSocioPorCedula(socio.getCedula()));
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar el socio: " + e.getMessage());
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error al registrar el socio: " + e.getMessage());
        }
        return false;
    }

    public void actualizarSocio(Socio socio){
        try {
            if (socioDAO.actualizarDato(socio)) {
                JOptionPane.showMessageDialog(null, "Socio actualizado correctamente");
            }
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el socio: " + e.getMessage());
        }
    }

    public void eliminarSocio(Socio socio) throws Exception {
        socioDAO.eliminarDato(socio);
    }

    public Socio buscarSocioPorCedula(String numCedula) throws Exception {
        return socioDAO.buscarSocioPorCedula(numCedula);
    }

    public Socio buscarSocioPorId(int id) throws Exception {
        return socioDAO.buscarDatoPorId(id);
    }

    public Socio buscarSocioPorApellido(String nombre) throws Exception {
        return socioDAO.buscarSocioPorApellido(nombre);
    }

    public List<Socio> obtenerTodosSocios() throws Exception {
        return socioDAO.obtenerListaDeDatos();
    }

    public List<Socio> obtenerSociosPorCriterio(String criterio, String valor) throws Exception {
        return socioDAO.obtenerSociosPorCriterio(criterio, valor);
    }

    public List<Socio> obtenerSociosPorMesRegistro(String mes) throws Exception {
        try {
            return socioDAO.obtenerSociosPorMesRegistro(mes);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener los socios por mes de registro: " + e.getMessage());
            return null;
        }
    }

    private void enviarEmailBienvenida(Socio socioNuevo) {
        EmailService emailService = new EmailService();
        emailService.enviarCorreoNuevoSocio(socioNuevo.getEmail(), socioNuevo.getNombre(), socioNuevo.getApellido(), String.valueOf(socioNuevo.getId()));
    }

}
