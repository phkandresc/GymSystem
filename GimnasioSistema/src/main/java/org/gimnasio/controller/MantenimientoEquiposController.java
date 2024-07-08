package org.gimnasio.controller;

import org.gimnasio.daos.EspaciosDAO;
import org.gimnasio.model.Equipo;
import org.gimnasio.model.Espacio;
import org.gimnasio.model.Mantenimiento;
import org.gimnasio.model.Reparacion;
import org.gimnasio.service.MantenimientoEquiposService;
import org.gimnasio.view.MantenimientoEquiposView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;

public class MantenimientoEquiposController extends WindowController implements ActionListener, ItemListener, MouseListener {
    private MantenimientoEquiposView view;
    private Equipo equipoSeleccionado;
    private List<Equipo> equipos;
    private Espacio espacioSeleccionado;
    private List<Espacio> espacios;
    private MantenimientoEquiposService service;

    public MantenimientoEquiposController() {
        this.view = new MantenimientoEquiposView();
        this.service = new MantenimientoEquiposService();
        view.btnCrear.addActionListener(this);
        view.jtReparaciones.addMouseListener(this);
        view.jtMantenimientos.addMouseListener(this);
        view.jtEquipos.addMouseListener(this);
        view.cmbEspacio.addItemListener(this);
        view.cmbProcedimiento.addItemListener(this);
        view.cmbEstado.addItemListener(this);
        cargarEspacios();
        espacioSeleccionado = espacios.get(0);
        cargarEquiposPorEspacios();
        view.setVisible(true);
    }

    private void cargarEspacios() {
        try {
            espacios = new EspaciosDAO().obtenerListaDeDatos();
            for (Espacio espacio : espacios) {
                view.cmbEspacio.addItem(espacio.getNombre());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cargarEquiposPorEspacios(){
        try {
            equipos = service.obtenerEquiposPorEspacio(espacioSeleccionado);
            DefaultTableModel model = (DefaultTableModel) view.jtEquipos.getModel();
            model.setRowCount(0);
            for (Equipo equipo : equipos) {
                model.addRow(new Object[]{equipo.getNombre(), equipo.getDescripcion(), equipo.getEstado(), equipo.getEspacio().getNombre()});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean validarCampos(){
        if(view.dcFechaProcedimiento.getDate() == null){
            JOptionPane.showMessageDialog(view, "Debe seleccionar una fecha");
            return false;
        }else if(view.txtEncargado.getText().isEmpty()){
            JOptionPane.showMessageDialog(view, "Debe ingresar un encargado");
            return false;
        }else if(view.txtDescripcion.getText().isEmpty()){
            JOptionPane.showMessageDialog(view, "Debe ingresar una descripcion");
            return false;
        }else if(view.cmbProcedimiento.getSelectedIndex() == 0){
            JOptionPane.showMessageDialog(view, "Debe seleccionar un procedimiento");
            return false;
        }else if(view.cmbEstado.getSelectedIndex() == 0){
            JOptionPane.showMessageDialog(view, "Debe seleccionar un estado");
            return false;
        } else if (equipoSeleccionado == null) {
            JOptionPane.showMessageDialog(view, "Debe seleccionar un equipo");
            return false;
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.btnCrear) {
            if(validarCampos()){
                try {
                    String procedimiento = view.cmbProcedimiento.getSelectedItem().toString();
                    String estado = view.cmbEstado.getSelectedItem().toString();
                    String encargado = view.txtEncargado.getText();
                    String descripcion = view.txtDescripcion.getText();

                    if (procedimiento.equals("Reparacion")) {
                        Reparacion reparacion = new Reparacion();
                        reparacion.setEquipo(equipoSeleccionado);
                        reparacion.setDescripcion(descripcion);
                        reparacion.setEncargado(encargado);
                        reparacion.setEstado(estado);
                        reparacion.setFecha(new java.sql.Date(view.dcFechaProcedimiento.getDate().getTime()));
                        service.crearReparacion(reparacion);
                        cargarReparacionesDelEquipo();
                    } else {
                        Mantenimiento mantenimiento = new Mantenimiento();
                        mantenimiento.setEquipo(equipoSeleccionado);
                        mantenimiento.setDescripcion(descripcion);
                        mantenimiento.setEncargado(encargado);
                        mantenimiento.setEstado(estado);
                        mantenimiento.setFecha(new java.sql.Date(view.dcFechaProcedimiento.getDate().getTime()));
                        service.crearMantenimiento(mantenimiento);
                        cargarMantenimientosDelEquipo();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == view.cmbEspacio) {
            espacioSeleccionado = espacios.get(view.cmbEspacio.getSelectedIndex());
            cargarEquiposPorEspacios();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == view.jtEquipos) {
            int fila = view.jtEquipos.getSelectedRow();
            if (fila != -1) {
                equipoSeleccionado = equipos.get(fila);
                view.txtEquipoSeleccionado.setText(equipoSeleccionado.getNombre());
                cargarReparacionesDelEquipo();
                cargarMantenimientosDelEquipo();
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

    private void cargarReparacionesDelEquipo() {
        try {
            List<Reparacion> reparaciones = service.obtenerReparacionesPorEquipo(equipoSeleccionado.getId());
            DefaultTableModel model = (DefaultTableModel) view.jtReparaciones.getModel();
            model.setRowCount(0);
            for (Reparacion reparacion : reparaciones) {
                model.addRow(new Object[]{reparacion.getEquipo().getNombre(), reparacion.getDescripcion(), reparacion.getFecha().toString(), reparacion.getEncargado(), reparacion.getEstado()});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cargarMantenimientosDelEquipo() {
        try {
            List<Mantenimiento> mantenimientos = service.obtenerMantenimientosPorEquipo(equipoSeleccionado.getId());
            DefaultTableModel model = (DefaultTableModel) view.jtMantenimientos.getModel();
            model.setRowCount(0);
            for (Mantenimiento mantenimiento : mantenimientos) {
                model.addRow(new Object[]{mantenimiento.getEquipo().getNombre(), mantenimiento.getDescripcion(), mantenimiento.getFecha().toString(), mantenimiento.getEncargado(), mantenimiento.getEstado()});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new MantenimientoEquiposController();
    }
}
