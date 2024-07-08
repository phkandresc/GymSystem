package org.gimnasio.service;

import org.gimnasio.daos.HorariosDAO;
import org.gimnasio.model.Espacio;
import org.gimnasio.model.Horario;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class HorarioService {
    public static final Logger LOGGER = Logger.getLogger(HorarioService.class.getName());
    private HorariosDAO horarioDAO;

    public HorarioService() {
        this.horarioDAO = new HorariosDAO();
    }

    public List<Horario> obtenerListaDeHorariosPorEspacio(int idEspacio){
        try {
            return horarioDAO.obtenerListaDeHorariosPorEspacio(idEspacio);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener la lista de horarios: " + e.getMessage());
            LOGGER.severe(e.getMessage());
        }
        return null;
    }

    public List<Horario> obtenerListaDeHorariosPorEspacioYDia(int idEspacio, String dia){
        try {
            return horarioDAO.obtenerListaDeHorariosPorEspacioYDia(idEspacio, dia);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener la lista de horarios: " + e.getMessage());
            LOGGER.severe(e.getMessage());
        }
        return null;
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

    public boolean agregarHorario(Horario horario){
        try {
            horarioDAO.agregarDato(horario);
            JOptionPane.showMessageDialog(null, "Horario agregado correctamente");
            return true;
        }catch (SQLException e){
            LOGGER.severe(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al agregar el horario: " + e.getMessage());
        }
        return false;
    }

    public boolean modificarHorario(Horario horario){
        try {
            horarioDAO.actualizarDato(horario);
            JOptionPane.showMessageDialog(null, "Horario actualizado correctamente");
            return true;
        }catch (SQLException e){
            LOGGER.severe(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al actualizar el horario: " + e.getMessage());
        }
        return false;
    }

    public boolean eliminarHorario(Horario horario){
        try {
            horarioDAO.eliminarDato(horario);
            JOptionPane.showMessageDialog(null, "Horario eliminado correctamente");
            return true;
        }catch (SQLException e){
            LOGGER.severe(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al eliminar el horario: " + e.getMessage());
        }
        return false;
    }

}
