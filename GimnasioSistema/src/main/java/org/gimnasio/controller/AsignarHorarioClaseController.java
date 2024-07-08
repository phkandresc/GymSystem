package org.gimnasio.controller;

import org.gimnasio.model.Clase;
import org.gimnasio.model.Espacio;
import org.gimnasio.model.Horario;
import org.gimnasio.render.MultiLineTableCellRenderer;
import org.gimnasio.service.ClasesService;
import org.gimnasio.service.HorarioService;
import org.gimnasio.view.AsignarHorarioClaseView;
import org.gimnasio.render.MultiLineAndColorCellRenderer;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.*;

public class AsignarHorarioClaseController extends WindowController implements ActionListener, ItemListener, MouseListener, ChangeListener {
    private AsignarHorarioClaseView view;
    private HorarioService horarioService;
    private ClasesService clasesService;
    private List<Espacio> espacios;
    private List<Clase> clases;
    List <Horario> horariosDeClase;
    private Horario horarioClaseSeleccionado;
    private Espacio espacioSeleccionado;
    private Clase claseSeleccionada;

    public AsignarHorarioClaseController(){
        this.view = new AsignarHorarioClaseView();
        this.horarioService = new HorarioService();
        this.clasesService = new ClasesService();
        view.cmbEspacios.addItemListener(this);
        view.cmbEspacioClase.addItemListener(this);
        view.cmbClase.addItemListener(this);
        view.jtHorarios.setDefaultRenderer(Object.class, new MultiLineAndColorCellRenderer());
        view.jtHorarioClase.setDefaultRenderer(Object.class, new MultiLineTableCellRenderer());
        view.jtHorarioClase.addMouseListener(this);
        view.jspHoraInicio.addChangeListener(this);
        view.jspHoraFin.addChangeListener(this);
        view.btnAsignarHorario.addActionListener(this);
        view.btnModificarHorario.addActionListener(this);
        view.btnEliminarHorario.addActionListener(this);
        view.btnModificarHorario.setEnabled(false);
        view.btnEliminarHorario.setEnabled(false);
        cargarEspacios();
        cargarClases();
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

    private void cargarHorarioDeClase(int idClase){
        horariosDeClase = horarioService.obtenerListaDeHorariosPorClase(idClase);
        DefaultTableModel model = (DefaultTableModel) view.jtHorarioClase.getModel();
        model.setRowCount(0); // Limpia la tabla

        // Agregar a tabla con columnas: Día, Hora de inicio, Hora fin
        for (Horario horario : horariosDeClase) {
            model.addRow(new Object[]{horario.getDiaSemana(), horario.getHoraInicio(), horario.getHoraFin(), horario.getEspacio().getNombre()});
        }

        view.jtHorarioClase.setModel(model);
    }

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

    private int obtenerColumnaEspacio(String nombreEspacio){
        for (int i = 0; i < espacios.size(); i++) {
            if (espacios.get(i).getNombre().equals(nombreEspacio)){
                return i;
            }
        }
        return -1;
    }

    private void cargarEspacios(){
        view.cmbEspacios.removeAllItems();
        espacios = clasesService.obtenerListaDeEspacios();
        for (Espacio espacio : espacios) {
            view.cmbEspacios.addItem(espacio.getNombre());
            view.cmbEspacioClase.addItem(espacio.getNombre());
        }

    }

    private void cargarClases(){
        view.cmbClase.removeAllItems();
        int idEspacio = espacios.get(view.cmbEspacioClase.getSelectedIndex()).getId();
        clases = clasesService.obtenerListaDeClases();
        for (Clase clase : clases) {
            view.cmbClase.addItem(clase.getNombre());
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == view.btnAsignarHorario){
            if (horarioClaseSeleccionado == null){
                if(horasValidas()){
                    Horario horario = new Horario();
                    horario.setDiaSemana(view.cmbDia.getSelectedItem().toString());
                    horario.setEspacio(espacioSeleccionado);
                    horario.setClaseProgramada(claseSeleccionada);
                    horario.setHoraInicio(convertirHora((Date) view.jspHoraInicio.getValue()));
                    horario.setHoraFin(convertirHora((Date) view.jspHoraFin.getValue()));
                    horario.setFechaInicio(new java.sql.Date(new Date().getTime()));
                    horario.setFechaFin(new java.sql.Date(new Date().getTime()));
                    horarioService.agregarHorario(horario);
                    cargarHorarioDeClase(horario.getClaseProgramada().getId());
                    limpiarFormulario();
                }
            }
        } else if (e.getSource() == view.btnModificarHorario){
            if(horasValidas()){
                horarioClaseSeleccionado.setDiaSemana(view.cmbDia.getSelectedItem().toString());
                horarioClaseSeleccionado.setEspacio(espacioSeleccionado);
                horarioClaseSeleccionado.setClaseProgramada(claseSeleccionada);
                horarioClaseSeleccionado.setHoraInicio(convertirHora((Date) view.jspHoraInicio.getValue()));
                horarioClaseSeleccionado.setHoraFin(convertirHora((Date) view.jspHoraFin.getValue()));
                horarioClaseSeleccionado.setFechaInicio(new java.sql.Date(new Date().getTime()));
                horarioClaseSeleccionado.setFechaFin(new java.sql.Date(new Date().getTime()));
                horarioService.modificarHorario(horarioClaseSeleccionado);
                cargarHorarioDeClase(horarioClaseSeleccionado.getClaseProgramada().getId());
                limpiarFormulario();
            }
        } else if (e.getSource() == view.btnEliminarHorario){
            horarioService.eliminarHorario(horarioClaseSeleccionado);
            cargarHorarioDeClase(horarioClaseSeleccionado.getClaseProgramada().getId());
            limpiarFormulario();
        }
    }

    private Time convertirHora(Date hora){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String horaString = sdf.format(hora);
        return Time.valueOf(horaString);
    }

    private boolean horasValidas(){
        Date horaInicio = (Date) view.jspHoraInicio.getValue();
        Date horaFin = (Date) view.jspHoraFin.getValue();
        if (horaInicio.after(horaFin)){
            JOptionPane.showMessageDialog(null, "La hora de inicio no puede ser mayor a la hora de fin");
            return false;
        }
        return true;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            if (e.getSource() == view.cmbEspacios) {
                int idEspacio = espacios.get(view.cmbEspacios.getSelectedIndex()).getId();
                cargarHorarios(idEspacio);
            }else if (e.getSource() == view.cmbEspacioClase){
                espacioSeleccionado = espacios.get(view.cmbEspacioClase.getSelectedIndex());
                verificarCambiosYActivarBoton();
            }else if (e.getSource() == view.cmbClase){
                claseSeleccionada = clases.get(view.cmbClase.getSelectedIndex());
                horariosDeClase = horarioService.obtenerListaDeHorariosPorClase(claseSeleccionada.getId());
                cargarClaseEnFormulario();
                cargarHorarioDeClase(claseSeleccionada.getId());
            }else if(e.getSource() == view.cmbDia){
                verificarCambiosYActivarBoton();
            }
        }
    }

    private void cargarClaseEnFormulario(){
        view.txtInstructor.setText(claseSeleccionada.getInstructor().getNombre());
    }


    public static void main(String[] args) {
        new AsignarHorarioClaseController();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == view.jtHorarioClase) {
            int filaSeleccionada = view.jtHorarioClase.getSelectedRow();
            if (filaSeleccionada != -1) {
                try {
                    horarioClaseSeleccionado = horariosDeClase.get(filaSeleccionada);
                    view.cmbDia.setSelectedIndex(obtenerColumnaDia(horarioClaseSeleccionado.getDiaSemana()));
                    view.jspHoraInicio.setValue(horarioClaseSeleccionado.getHoraInicio());
                    view.jspHoraFin.setValue(horarioClaseSeleccionado.getHoraFin());
                    view.cmbEspacioClase.setSelectedIndex(obtenerColumnaEspacio(horarioClaseSeleccionado.getEspacio().getNombre()));
                    view.btnAsignarHorario.setEnabled(false);
                    JOptionPane.showMessageDialog(null, "Horario seleccionado:\n" + horarioClaseSeleccionado.getDiaSemana() + " " + horarioClaseSeleccionado.getHoraInicio() + " - " + horarioClaseSeleccionado.getHoraFin());
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == view.jspHoraInicio){
            verificarCambiosYActivarBoton();
        }else if (e.getSource() == view.jspHoraFin){
            verificarCambiosYActivarBoton();
        }
    }

    private void verificarCambiosYActivarBoton() {
        boolean cambioDetectado = false;

        // Verificar cambios para cada componente
        if(horarioClaseSeleccionado == null){
            return;
        }
        if (!view.cmbDia.getSelectedItem().toString().equals(horarioClaseSeleccionado.getDiaSemana())) {
            cambioDetectado = true;
        } else if (!view.jspHoraInicio.getValue().equals(horarioClaseSeleccionado.getHoraInicio())) {
            cambioDetectado = true;
        } else if (!view.jspHoraFin.getValue().equals(horarioClaseSeleccionado.getHoraFin())) {
            cambioDetectado = true;
        } else if (!view.cmbEspacioClase.getSelectedItem().toString().equals(horarioClaseSeleccionado.getEspacio().getNombre())) {
            cambioDetectado = true;
        }

        // Activar el botón si se detectó un cambio
        view.btnModificarHorario.setEnabled(cambioDetectado);
        view.btnAsignarHorario.setEnabled(!cambioDetectado);
        view.btnEliminarHorario.setEnabled(!cambioDetectado);
    }

    private void limpiarFormulario(){
        view.cmbDia.setSelectedIndex(0);
        view.cmbEspacioClase.setSelectedIndex(0);
        view.btnModificarHorario.setEnabled(false);
        view.btnAsignarHorario.setEnabled(true);
        view.btnEliminarHorario.setEnabled(false);
    }
}
