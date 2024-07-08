package org.gimnasio.controller;

import org.gimnasio.daos.EquiposDAO;
import org.gimnasio.daos.EspaciosDAO;
import org.gimnasio.model.Equipo;
import org.gimnasio.model.Espacio;
import org.gimnasio.render.MultiLineTableCellRenderer;
import org.gimnasio.view.RegistroEquiposView;
import org.gimnasio.view.RegistroEspaciosView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;

public class RegistroEquiposController extends WindowController implements ItemListener, ActionListener, MouseListener {
    private RegistroEquiposView view;
    private EquiposDAO equiposDAO;
    private Equipo equipoSeleccionado;
    private Espacio espacioSeleccionado;
    private List<Espacio> espacios;
    private List<Equipo> equipos;

    public RegistroEquiposController(){
        view = new RegistroEquiposView();
        equiposDAO = new EquiposDAO();
        view.btnCrear.addActionListener(this);
        view.btnModificar.addActionListener(this);
        view.btnEliminar.addActionListener(this);
        view.jtEquipos.addMouseListener(this);
        view.cmbEspacio.addItemListener(this);
        view.jtEquipos.setDefaultRenderer(Object.class, new MultiLineTableCellRenderer());
        view.bg.addMouseListener(this);
        cargarEquipos();
        cargarEspacios();
        view.btnModificar.setEnabled(false);
        view.btnEliminar.setEnabled(false);
        view.setVisible(true);
    }

    private void cargarEquipos(){
        try {
            equipos = equiposDAO.obtenerListaDeDatos();
            DefaultTableModel model = (DefaultTableModel) view.jtEquipos.getModel();
            model.setRowCount(0);
            for (Equipo equipo : equipos){
                model.addRow(new Object[]{equipo.getNombre(), equipo.getDescripcion(), equipo.getEstado(), equipo.getEspacio().getNombre()});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.btnCrear){
            if(Validator.validarTextFields(new JTextField[]{view.txtNombre, view.txtDescripcion})){
                Equipo equipo = new Equipo();
                equipo.setNombre(view.txtNombre.getText());
                equipo.setDescripcion(view.txtDescripcion.getText());
                equipo.setEstado(view.cmbEstado.getSelectedItem().toString().toLowerCase());
                equipo.setEspacio(espacioSeleccionado);
                try {
                    if (equiposDAO.agregarDato(equipo)){
                        JOptionPane.showMessageDialog(view, "Equipo agregado correctamente");
                        cargarEquipos();
                        limpiarCampos();
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, "Error al agregar el equipo");
                    limpiarCampos();
                }
            }else{
                return;
            }

        } else if (e.getSource() == view.btnModificar){
            equipoSeleccionado.setNombre(view.txtNombre.getText());
            equipoSeleccionado.setDescripcion(view.txtDescripcion.getText());
            equipoSeleccionado.setEstado(view.cmbEstado.getSelectedItem().toString().toLowerCase());
            equipoSeleccionado.setEspacio(espacioSeleccionado);
            try {
                if(equiposDAO.actualizarDato(equipoSeleccionado)){
                    JOptionPane.showMessageDialog(view, "Equipo actualizado correctamente");
                    cargarEquipos();
                    limpiarCampos();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Error al actualizar el equipo");
                limpiarCampos();
            }
        } else if (e.getSource() == view.btnEliminar){
            try {
                if (equiposDAO.eliminarDato(equipoSeleccionado)){
                    JOptionPane.showMessageDialog(view, "Equipo eliminado correctamente");
                    cargarEquipos();
                    limpiarCampos();
                    view.btnCrear.setEnabled(true);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Error al eliminar el equipo");
                limpiarCampos();
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == view.jtEquipos){
            int filaSeleccionada = view.jtEquipos.getSelectedRow();
            if (filaSeleccionada >= 0) {
                equipoSeleccionado = equipos.get(filaSeleccionada);
                view.txtNombre.setText(equipoSeleccionado.getNombre());
                view.txtDescripcion.setText(equipoSeleccionado.getDescripcion());
                if (equipoSeleccionado.getEstado().equals("disponible")) {
                    view.cmbEstado.setSelectedItem("Disponible");
                }else if (equipoSeleccionado.getEstado().equals("en mantenimiento")) {
                    view.cmbEstado.setSelectedItem("En mantenimiento");
                } else if (equipoSeleccionado.getEstado().equals("prestado")) {
                    view.cmbEstado.setSelectedItem("Prestado");
                } else if (equipoSeleccionado.getEstado().equals("fuera de servicio")){
                    view.cmbEstado.setSelectedItem("Fuera de servicio");
                }
                view.cmbEspacio.setSelectedItem(equipoSeleccionado.getEspacio().getNombre());
                view.btnModificar.setEnabled(true);
                view.btnEliminar.setEnabled(true);
                view.btnCrear.setEnabled(false);
            }
        } else if (e.getSource() == view.bg){
            equipoSeleccionado = null;
            limpiarCampos();
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
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == view.cmbEspacio){
            espacioSeleccionado = espacios.get(view.cmbEspacio.getSelectedIndex());
        }
    }

    private void limpiarCampos(){
        view.txtNombre.setText("");
        view.txtDescripcion.setText("");
        view.cmbEstado.setSelectedIndex(0);
        view.cmbEspacio.setSelectedIndex(0);
        view.btnModificar.setEnabled(false);
        view.btnEliminar.setEnabled(false);
    }


    public static void main(String[] args) {
        new RegistroEquiposController();
    }
}
