package org.gimnasio.service;

import org.gimnasio.daos.HorariosDAO;
import org.gimnasio.model.Espacio;
import org.gimnasio.model.Horario;

import javax.swing.*;
import java.sql.SQLException;
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

}
