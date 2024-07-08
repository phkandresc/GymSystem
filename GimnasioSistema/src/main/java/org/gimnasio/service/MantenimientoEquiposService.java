package org.gimnasio.service;

import org.gimnasio.daos.EquiposDAO;
import org.gimnasio.daos.MantenimientosDAO;
import org.gimnasio.daos.ReparacionesDAO;
import org.gimnasio.model.Equipo;
import org.gimnasio.model.Espacio;
import org.gimnasio.model.Mantenimiento;
import org.gimnasio.model.Reparacion;

import javax.swing.*;
import java.util.List;

public class MantenimientoEquiposService {
    private MantenimientosDAO mantenimientosDAO;
    private ReparacionesDAO reparacionesDAO;

    public MantenimientoEquiposService() {
        mantenimientosDAO = new MantenimientosDAO();
        reparacionesDAO = new ReparacionesDAO();
    }

    public void crearReparacion(Reparacion reparacion) {
        try {
            if (reparacionesDAO.agregarDato(reparacion)) {
                JOptionPane.showMessageDialog(null, "Reparación creada correctamente");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al crear la reparación");
            e.printStackTrace();
        }
    }

    public void crearMantenimiento(Mantenimiento mantenimiento) {
        try {
            if (mantenimientosDAO.agregarDato(mantenimiento)) {
                JOptionPane.showMessageDialog(null, "Mantenimiento creado correctamente");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al crear el mantenimiento");
            e.printStackTrace();
        }
    }

    public boolean guardarMantenimiento(Mantenimiento mantenimiento) {
        try {
            JOptionPane.showMessageDialog(null, "Mantenimiento guardado correctamente");
            return mantenimientosDAO.agregarDato(mantenimiento);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al guardar el mantenimiento");
            e.printStackTrace();
            return false;
        }
    }

    public boolean guardarReparacion(Reparacion reparacion) {
        try {
            JOptionPane.showMessageDialog(null, "Reparación guardada correctamente");
            return reparacionesDAO.agregarDato(reparacion);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al guardar la reparación");
            e.printStackTrace();
            return false;
        }
    }

    public List<Mantenimiento> obtenerMantenimientosPorEquipo(int idEquipo) {
        try {
            return mantenimientosDAO.obtenerMantenimientosPorEquipo(idEquipo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Reparacion> obtenerReparacionesPorEquipo(int idEquipo) {
        try {
            return reparacionesDAO.obtenerReparacionesPorEquipo(idEquipo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Equipo> obtenerEquiposPorEspacio(Espacio espacio) {
        try {
            return new EquiposDAO().obtenerEquiposPorEspacio(espacio);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
