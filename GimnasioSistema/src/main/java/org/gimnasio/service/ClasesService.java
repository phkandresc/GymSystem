package org.gimnasio.service;

import org.gimnasio.daos.*;
import org.gimnasio.model.*;

import javax.swing.*;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ClasesService {
    public static final Logger LOGGER = Logger.getLogger(ClasesService.class.getName());
    private ClaseDAO claseDAO;

    public ClasesService() {
        this.claseDAO = new ClaseDAO();
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

    public List<Instructor> obtenerListaDeInstructores(){
        List<Instructor> lista = new ArrayList<>();
        try {
            lista = new InstructorDAO().obtenerListaDeDatos();
        }catch (SQLException e){
            LOGGER.severe(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al obtener la lista de instructores: " + e.getMessage());
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
}
