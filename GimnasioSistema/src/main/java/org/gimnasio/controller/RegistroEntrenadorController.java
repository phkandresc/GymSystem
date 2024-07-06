package org.gimnasio.controller;

import org.gimnasio.model.Entrenador;
import org.gimnasio.render.MultiLineTableCellRenderer;
import org.gimnasio.service.ClasesService;
import org.gimnasio.view.RegistroEntrenadorView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class RegistroEntrenadorController extends WindowController implements MouseListener, ActionListener {
    private RegistroEntrenadorView view;
    private ClasesService service;
    private Entrenador entrenadorSeleccionado;

    public RegistroEntrenadorController() {
        this.view = new RegistroEntrenadorView();
        this.service = new ClasesService();

        view.jPanel1.addMouseListener(this);
        view.jtEntrenadores.addMouseListener(this);
        view.jtEntrenadores.setDefaultRenderer(String.class, new MultiLineTableCellRenderer());
        cargarEntrenadores();
        view.btnCrear.addActionListener(this);
        view.btnModificar.addActionListener(this);
        view.btnEliminar.addActionListener(this);
        view.setVisible(true);
    }

    private void cargarEntrenadores() {
        DefaultTableModel model = (DefaultTableModel) view.jtEntrenadores.getModel();
        model.setRowCount(0);
        List<Entrenador> listaEntrenadores = service.obtenerListaDeEntrenadores();
        for (Entrenador entrenador : listaEntrenadores) {
            model.addRow(new Object[]{
                    entrenador.getCedula(),
                    entrenador.getNombre(),
                    entrenador.getApellido(),
                    entrenador.getEmail(),
                    entrenador.getEspecialidad(),
                    entrenador.getEstado()});
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == view.jtEntrenadores){
            int fila = view.jtEntrenadores.getSelectedRow();
            if (fila != -1){
               try {
                   entrenadorSeleccionado = service.buscarEntrenadorPorCedula(view.jtEntrenadores.getValueAt(fila, 0).toString());
                   cargarEntrenadorEnFormulario();
                   view.btnEliminar.setEnabled(true);
                   view.btnModificar.setEnabled(true);
                   view.btnCrear.setEnabled(false);
               }catch (Exception ex){
                   System.out.println("Error al buscar el entrenador: " + ex.getMessage());
               }
            }
        } else if(e.getSource() == view.jPanel1){
            cargarEntrenadores();
            entrenadorSeleccionado = null;
            view.btnEliminar.setEnabled(false);
            view.btnModificar.setEnabled(false);
            view.txtCedula.setText("");
            view.txtNombre.setText("");
            view.txtApellido.setText("");
            view.txtEmail.setText("");
            view.txtDireccion.setText("");
            view.txtEspecialidad.setText("");
            view.txtTelefono.setText("");
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

    private void cargarEntrenadorEnFormulario(){
        view.txtCedula.setText(entrenadorSeleccionado.getCedula());
        view.txtNombre.setText(entrenadorSeleccionado.getNombre());
        view.txtApellido.setText(entrenadorSeleccionado.getApellido());
        view.txtEmail.setText(entrenadorSeleccionado.getEmail());
        view.txtDireccion.setText(entrenadorSeleccionado.getDireccion());
        view.txtEspecialidad.setText(entrenadorSeleccionado.getEspecialidad());
        view.txtTelefono.setText(entrenadorSeleccionado.getNumeroTelefono());
    }

    public static void main(String[] args) {
        new RegistroEntrenadorController();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == view.btnCrear){
            if(Validator.validarTextFields(new javax.swing.JTextField[]{view.txtCedula, view.txtNombre, view.txtApellido, view.txtEmail, view.txtDireccion, view.txtEspecialidad, view.txtTelefono})){
                Entrenador entrenador = new Entrenador();
                entrenador.setCedula(view.txtCedula.getText());
                entrenador.setNombre(view.txtNombre.getText());
                entrenador.setApellido(view.txtApellido.getText());
                entrenador.setEmail(view.txtEmail.getText());
                entrenador.setDireccion(view.txtDireccion.getText());
                entrenador.setEspecialidad(view.txtEspecialidad.getText());
                entrenador.setNumeroTelefono(view.txtTelefono.getText());
                if(service.agregarEntrenador(entrenador)){
                    cargarEntrenadores();
                    vaciarFormulario();
                }
            }else {
                JOptionPane.showMessageDialog(null, "Todos los campos son requeridos");
            }

        } else if(e.getSource() == view.btnModificar){
            if (entrenadorSeleccionado == null){
                JOptionPane.showMessageDialog(null, "Debe seleccionar un entrenador");
                return;
            }
            if(Validator.validarTextFields(new javax.swing.JTextField[]{view.txtCedula, view.txtNombre, view.txtApellido, view.txtEmail, view.txtDireccion, view.txtEspecialidad, view.txtTelefono})){
                entrenadorSeleccionado.setCedula(view.txtCedula.getText());
                entrenadorSeleccionado.setNombre(view.txtNombre.getText());
                entrenadorSeleccionado.setApellido(view.txtApellido.getText());
                entrenadorSeleccionado.setEmail(view.txtEmail.getText());
                entrenadorSeleccionado.setDireccion(view.txtDireccion.getText());
                entrenadorSeleccionado.setEspecialidad(view.txtEspecialidad.getText());
                entrenadorSeleccionado.setNumeroTelefono(view.txtTelefono.getText());
                if(service.actualizarEntrenador(entrenadorSeleccionado)){
                    cargarEntrenadores();
                    vaciarFormulario();
                    view.btnEliminar.setEnabled(false);
                    view.btnModificar.setEnabled(false);
                    view.btnCrear.setEnabled(true);
                    entrenadorSeleccionado = null;
                }
            }

        } else if(e.getSource() == view.btnEliminar){
            if(service.eliminarEntrenador(entrenadorSeleccionado)){
                cargarEntrenadores();
                vaciarFormulario();
                view.btnEliminar.setEnabled(false);
                view.btnModificar.setEnabled(false);
                view.btnCrear.setEnabled(true);
                entrenadorSeleccionado = null;
            }
        }
    }

    private void vaciarFormulario(){
        view.txtCedula.setText("");
        view.txtNombre.setText("");
        view.txtApellido.setText("");
        view.txtEmail.setText("");
        view.txtDireccion.setText("");
        view.txtEspecialidad.setText("");
        view.txtTelefono.setText("");
    }
}
