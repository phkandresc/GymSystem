package org.gimnasio.controller;

import org.gimnasio.model.Clase;
import org.gimnasio.model.Entrenador;
import org.gimnasio.model.Espacio;
import org.gimnasio.model.TipoClase;
import org.gimnasio.render.MultiLineTableCellRenderer;
import org.gimnasio.service.ClasesService;
import org.gimnasio.view.AgregarClaseView;
import org.gimnasio.render.MultiLineCellRenderer2;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.*;
import java.util.List;

public class AgregarClaseController extends WindowController implements MouseListener,ActionListener, ItemListener {
    private ClasesService clasesService;
    private AgregarClaseView view;
    private List<TipoClase> tipoClases;
    private List<Entrenador> entrenadores;
    private TipoClase tipoClaseSeleccionado;
    private Clase claseSeleccionada;
    private Entrenador entrenadorSeleccionado;

    public AgregarClaseController(){
        clasesService = new ClasesService();
        view = new AgregarClaseView();
        view.cmbTipoClase.addItemListener(this);
        view.cmbInstructor.addItemListener(this);
        view.btnCrear.addActionListener(this);
        view.btnEliminar.addActionListener(this);
        view.btnModificar.addActionListener(this);
        view.jtClases.setDefaultRenderer(Object.class, new MultiLineTableCellRenderer());
        view.jtClases.addMouseListener(this);
        cargarClasesEnTabla();
        view.setVisible(true);
        cargarTiposDeClases();
        cargarInstructores();
        view.btnEliminar.setEnabled(false);
        view.btnModificar.setEnabled(false);
        view.bg.addMouseListener(this);
    }

    private void cargarClasesEnTabla(){
        List<Clase> listaClases;
        DefaultTableModel model = (DefaultTableModel) view.jtClases.getModel();
        model.setRowCount(0);
        try{
            listaClases = clasesService.obtenerListaDeClases();
            for (Clase clase : listaClases) {
                model.addRow(new Object[]{
                        clase.getTipoClase().getNombre(),
                        clase.getNombre(),
                        clase.getDescripcion(),
                        clase.getCosto(),
                        clase.getCupos(),
                        clase.getInstructor().getNombre(),
                });
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void cargarTiposDeClases(){
        tipoClases = clasesService.obtenerListaDeTipoClases();
        for (TipoClase tipoClase : tipoClases) {
            view.cmbTipoClase.addItem(tipoClase.getNombre());
        }
    }

    private void cargarInstructores(){
        view.cmbInstructor.removeAllItems();
        entrenadores = clasesService.obtenerListaDeEntrenadores();
        for (Entrenador entrenador : entrenadores) {
            view.cmbInstructor.addItem(entrenador.getNombre()+" "+entrenador.getApellido());
        }
    }




    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.btnCrear){
            if(validarCampos()){
                if(clasesService.agregarClase(crearClase())) {
                    vaciarCampos();
                    cargarClasesEnTabla();
                }
            }
        } else if (e.getSource() == view.btnEliminar){
            if(claseSeleccionada != null){
                if(clasesService.eliminarClase(claseSeleccionada)){
                    vaciarCampos();
                    cargarClasesEnTabla();
                    view.btnCrear.setEnabled(true);
                    view.btnEliminar.setEnabled(false);
                    view.btnModificar.setEnabled(false);
                }
            }
        } else if (e.getSource() == view.btnModificar){
            if(claseSeleccionada != null){
                if(validarCampos()){
                    claseSeleccionada.setTipoClase(tipoClaseSeleccionado);
                    claseSeleccionada.setNombre(view.txtNombre.getText());
                    claseSeleccionada.setDescripcion(view.txtDescripcion.getText());
                    claseSeleccionada.setCosto((double) view.jsCosto.getValue());
                    claseSeleccionada.setCupos((int) view.jsCupos.getValue());
                    claseSeleccionada.setInstructor(entrenadorSeleccionado);

                    if(clasesService.actualizarClase(claseSeleccionada)){
                        vaciarCampos();
                        cargarClasesEnTabla();
                        view.btnCrear.setEnabled(true);
                        view.btnEliminar.setEnabled(false);
                        view.btnModificar.setEnabled(false);
                    }
                }
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == view.cmbTipoClase){
            tipoClaseSeleccionado = tipoClases.get(view.cmbTipoClase.getSelectedIndex());
        } else if (e.getSource() == view.cmbInstructor){
            entrenadorSeleccionado = entrenadores.get(view.cmbInstructor.getSelectedIndex());
        }
    }

    private boolean validarCampos(){
        if (view.txtNombre.getText().isEmpty()){
            mostrarMensaje("El campo nombre de la clase es obligatorio");
            return false;
        }
        if (view.txtDescripcion.getText().isEmpty()){
            mostrarMensaje("El campo descripción es obligatorio");
            return false;
        }
        return true;
    }

    private Clase crearClase(){
        Clase clase = new Clase();
        clase.setTipoClase(tipoClaseSeleccionado);
        clase.setNombre(view.txtNombre.getText());
        clase.setDescripcion(view.txtDescripcion.getText());
        clase.setCosto((double) view.jsCosto.getValue());
        clase.setCupos((int) view.jsCupos.getValue());
        clase.setInstructor(entrenadorSeleccionado);
        return clase;
    }

    private void mostrarMensaje(String mensaje){
        JOptionPane.showMessageDialog(null, mensaje);
    }

    private void vaciarCampos(){
        view.txtNombre.setText("");
        view.txtDescripcion.setText("");
    }

    public static void main(String[] args) {
        new AgregarClaseController();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == view.jtClases){
            int filaSeleccionada = view.jtClases.getSelectedRow();
            if (filaSeleccionada != -1){
                claseSeleccionada = clasesService.buscarClasePorNombre(view.jtClases.getValueAt(filaSeleccionada, 1).toString());
                if (claseSeleccionada != null){
                    view.txtNombre.setText(claseSeleccionada.getNombre());
                    view.txtDescripcion.setText(claseSeleccionada.getDescripcion());
                    view.jsCosto.setValue(claseSeleccionada.getCosto());
                    view.jsCupos.setValue(claseSeleccionada.getCupos());
                    view.cmbTipoClase.setSelectedItem(claseSeleccionada.getTipoClase().getNombre());
                    view.cmbInstructor.setSelectedItem(claseSeleccionada.getInstructor().getNombre()+" "+claseSeleccionada.getInstructor().getApellido());
                    view.btnCrear.setEnabled(false);
                    view.btnEliminar.setEnabled(true);
                    view.btnModificar.setEnabled(true);
                }
            }
        }else if(e.getSource() == view.bg){
            vaciarCampos();
            claseSeleccionada = null;
            view.btnCrear.setEnabled(true);
            view.btnEliminar.setEnabled(false);
            view.btnModificar.setEnabled(false);
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
}
