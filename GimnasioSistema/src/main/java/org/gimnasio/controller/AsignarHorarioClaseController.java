package org.gimnasio.controller;

import org.gimnasio.model.Espacio;
import org.gimnasio.model.Horario;
import org.gimnasio.service.ClasesService;
import org.gimnasio.service.HorarioService;
import org.gimnasio.view.AsignarHorarioClaseView;
import org.gimnasio.view.MultiLineAndColorCellRenderer;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.*;

public class AsignarHorarioClaseController implements ActionListener, ItemListener {
    private AsignarHorarioClaseView view;
    private HorarioService horarioService;
    private ClasesService clasesService;
    private List<Espacio> espacios;
    private Espacio espacioSeleccionado;

    public AsignarHorarioClaseController(){
        this.view = new AsignarHorarioClaseView();
        this.horarioService = new HorarioService();
        this.clasesService = new ClasesService();
        view.cmbEspacios.addItemListener(this);
        view.jtHorarios.setDefaultRenderer(Object.class, new MultiLineAndColorCellRenderer());
        cargarEspacios();
        view.setVisible(true);
    }

    private void cargarHorarios(int idEspacio) {
        List<Horario> horarios = horarioService.obtenerListaDeHorariosPorEspacio(idEspacio);
        Map<String, List<Horario>> horariosPorDia = new HashMap<>();

        // Inicializar listas para cada día de la semana
        for (String dia : new String[]{"lunes", "martes", "miércoles", "jueves", "viernes", "sábado", "domingo"}) {
            horariosPorDia.put(dia, new ArrayList<>());
        }

        // Agrupar horarios por día de la semana
        for (Horario horario : horarios) {
            List<Horario> horariosDelDia = horariosPorDia.get(horario.getDiaSemana().toLowerCase());
            if (horariosDelDia != null) {
                horariosDelDia.add(horario);
            }
        }

        // Ordenar horarios por hora de inicio dentro de cada día
        for (List<Horario> horariosDelDia : horariosPorDia.values()) {
            horariosDelDia.sort(Comparator.comparing(Horario::getHoraInicio));
        }

        DefaultTableModel model = (DefaultTableModel) view.jtHorarios.getModel();
        model.setRowCount(0); // Limpia la tabla

        // Asume que la tabla tiene 7 columnas, una para cada día de la semana
        Object[] fila = new Object[7]; // Inicializa una fila con 7 columnas

        // Llenar el modelo de la tabla con horarios ordenados
        for (Map.Entry<String, List<Horario>> entrada : horariosPorDia.entrySet()) {
            int columnaDia = obtenerColumnaDia(entrada.getKey()); // Convertir día a índice de columna
            for (Horario horario : entrada.getValue()) {
                fila[columnaDia] = horario.getHoraInicio() + " - " + horario.getHoraFin() + "\n" + horario.getClaseProgramada().getNombre();
                model.addRow(fila);
                fila = new Object[7]; // Reinicia el objeto fila para el próximo horario
            }
        }

        view.jtHorarios.setModel(model); // Actualiza la tabla con el nuevo modelo
    }

    /*private void cargarHorarios(int idEspacio) {
        List<Horario> horarios = horarioService.obtenerListaDeHorariosPorEspacio(idEspacio);
        DefaultTableModel model = (DefaultTableModel) view.jtHorarios.getModel();
        model.setRowCount(0); // Limpia la tabla

        // Asume que la tabla tiene 7 columnas, una para cada día de la semana
        Object[] fila = new Object[7]; // Inicializa una fila con 7 columnas

        for (Horario horario : horarios) {
            int columnaDia = obtenerColumnaDia(horario.getDiaSemana()); // Implementa este método
            if (columnaDia >= 0) { // Verifica que el día sea válido
                fila[columnaDia] = horario.getHoraInicio() + " - " + horario.getHoraFin() + "\n"+horario.getClaseProgramada().getNombre(); // Configura la hora
                model.addRow(fila); // Añade la fila al modelo
                fila = new Object[7]; // Reinicia el objeto fila para el próximo horario
            }
        }

        view.jtHorarios.setModel(model); // Actualiza la tabla con el nuevo modelo
    }*/

    // Método auxiliar para convertir el día de la semana a índice de columna
    private int obtenerColumnaDia(String diaSemana) {
        switch (diaSemana.toLowerCase()) {
            case "lunes": return 0;
            case "martes": return 1;
            case "miércoles": return 2;
            case "jueves": return 3;
            case "viernes": return 4;
            case "sábado": return 5;
            case "domingo": return 6;
            default: return -1; // Día no válido
        }
    }





    private void cargarEspacios(){
        view.cmbEspacios.removeAllItems();
        espacios = clasesService.obtenerListaDeEspacios();
        for (Espacio espacio : espacios) {
            view.cmbEspacios.addItem(espacio.getNombre());
        }

    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED){
            espacioSeleccionado = espacios.get(view.cmbEspacios.getSelectedIndex());
            cargarHorarios(espacioSeleccionado.getId());
        }
    }

    public static void main(String[] args) {
        new AsignarHorarioClaseController();
    }
}
