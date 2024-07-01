package org.gimnasio.controller;

import org.gimnasio.model.TipoMembresia;
import org.gimnasio.service.MembresiaService;
import org.gimnasio.view.MultiLineTableCellRenderer;
import org.gimnasio.view.TiposMembresiaView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;


public class TiposMembresiaController extends WindowController implements ActionListener, MouseListener {
    private TiposMembresiaView view;
    private MembresiaService membresiaService;
    private TipoMembresia tipoMembresiaSeleccionado;
    private List<TipoMembresia> listaTiposMembresia;

    public TiposMembresiaController(){
        view = new TiposMembresiaView();
        membresiaService = new MembresiaService();
        view.btnAgregar.addActionListener(this);
        view.btnModificar.addActionListener(this);
        view.btnEliminar.addActionListener(this);
        view.jtTiposMembresia.addMouseListener(this);
        view.jtTiposMembresia.setDefaultRenderer(String.class, new MultiLineTableCellRenderer());
        cargarTiposMembresia();
        view.setVisible(true);
    }

    private void cargarTiposMembresia(){
        DefaultTableModel model = (DefaultTableModel) view.jtTiposMembresia.getModel();
        model.setRowCount(0);
        try{
            listaTiposMembresia = membresiaService.obtenerTiposMembresia();
            for (TipoMembresia tipoMembresia : listaTiposMembresia) {
                model.addRow(new Object[]{
                        tipoMembresia.getNombre(),
                        tipoMembresia.getDescripcion(),
                        tipoMembresia.getDuracion(),
                        tipoMembresia.getPrecio()
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void cargarTipoMembresiaEnFormulario(TipoMembresia tipoMembresia){
        view.txtNombreMembresia.setText(tipoMembresia.getNombre());
        view.txtDescripcion.setText(tipoMembresia.getDescripcion());
        view.jspDuracion.setValue(tipoMembresia.getDuracion());
        view.jspPrecio.setValue(tipoMembresia.getPrecio());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == view.btnAgregar){
            if(validarCampos()){
                agregarTipoDeMembresia();
            }
        } else if (e.getSource() == view.btnModificar) {
            if(tipoMembresiaSeleccionado != null){
                if(validarCampos()){
                    modificarTipoMembresia();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Debes seleccionar un tipo de membresía");
            }
        } else if (e.getSource() == view.btnEliminar) {
            if(tipoMembresiaSeleccionado != null){
                eliminarTipoMembresia();
            } else {
                JOptionPane.showMessageDialog(null, "Debes seleccionar un tipo de membresía");
            }
        }
    }

    private boolean validarCampos(){
        if(view.txtNombreMembresia.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "El campo nombre de membresía es obligatorio");
            return false;
        }
        if(view.txtDescripcion.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "El campo descripción es obligatorio");
            return false;
        }
        if((int) view.jspDuracion.getValue() == 0){
            JOptionPane.showMessageDialog(null, "La duración debe ser mayor a 0");
            return false;
        }
        if((double) view.jspPrecio.getValue() == 0){
            JOptionPane.showMessageDialog(null, "El precio debe ser mayor a 0");
            return false;
        }
        return true;
    }

    private void limpiarFormulario(){
        view.txtNombreMembresia.setText("");
        view.txtDescripcion.setText("");
        view.jspDuracion.setValue(0);
        view.jspPrecio.setValue(0);
    }

    private void agregarTipoDeMembresia(){
        TipoMembresia nuevoTipoMembresia = new TipoMembresia();
        nuevoTipoMembresia.setNombre(view.txtNombreMembresia.getText());
        nuevoTipoMembresia.setDescripcion(view.txtDescripcion.getText());
        nuevoTipoMembresia.setDuracion((int) view.jspDuracion.getValue());
        nuevoTipoMembresia.setPrecio((double) view.jspPrecio.getValue());
        try{
            membresiaService.agregarTipoMembresia(nuevoTipoMembresia);
            cargarTiposMembresia();
            limpiarFormulario();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void modificarTipoMembresia(){
        tipoMembresiaSeleccionado.setNombre(view.txtNombreMembresia.getText());
        tipoMembresiaSeleccionado.setDescripcion(view.txtDescripcion.getText());
        tipoMembresiaSeleccionado.setDuracion((int) view.jspDuracion.getValue());
        tipoMembresiaSeleccionado.setPrecio((double) view.jspPrecio.getValue());
        try{
            membresiaService.modificarTipoMembresia(tipoMembresiaSeleccionado);
            cargarTiposMembresia();
            limpiarFormulario();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void eliminarTipoMembresia(){
        try{
            membresiaService.eliminarTipoMembresia(tipoMembresiaSeleccionado);
            cargarTiposMembresia();
            limpiarFormulario();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == view.jtTiposMembresia){
            int filaSeleccionada = view.jtTiposMembresia.getSelectedRow();
            tipoMembresiaSeleccionado = listaTiposMembresia.get(filaSeleccionada);
            cargarTipoMembresiaEnFormulario(tipoMembresiaSeleccionado);
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

    public static void main(String[] args) {
        new TiposMembresiaController();
    }
}
