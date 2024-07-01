package org.gimnasio.service;

import org.gimnasio.daos.ClaseDAO;
import org.gimnasio.model.Clase;

import javax.swing.*;
import java.util.logging.Logger;

public class ClasesService {
    public static final Logger LOGGER = Logger.getLogger(ClasesService.class.getName());
    private ClaseDAO claseDAO;

    public ClasesService() {
        this.claseDAO = new ClaseDAO();
    }

    public boolean agregarClase(Clase nuevaClase) {
        try {
            JOptionPane.showMessageDialog(null, "Clase registrada con éxito");
            return claseDAO.agregarDato(nuevaClase);
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al registrar la clase: " + e.getMessage());
            return false;
        }
    }
}
