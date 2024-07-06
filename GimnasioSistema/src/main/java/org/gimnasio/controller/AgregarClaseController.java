package org.gimnasio.controller;

import org.gimnasio.model.Clase;
import org.gimnasio.model.Espacio;
import org.gimnasio.model.TipoClase;
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

    private List<Espacio> espacios;
    private TipoClase tipoClaseSeleccionado;

    private Espacio espacioSeleccionado;

    public AgregarClaseController(){
        clasesService = new ClasesService();
        view = new AgregarClaseView();
        view.cmbTipoClase.addItemListener(this);
        view.cmbInstructor.addItemListener(this);
        view.cmbEspacio.addItemListener(this);
        view.btnCrear.addActionListener(this);
        view.jtClases.addMouseListener(this);
        cargarClasesEnTabla();
        view.setVisible(true);
        cargarTiposDeClases();
        cargarInstructores();
        cargarEspacios();
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
                        clase.getEspacio().getNombre()
                });
            }

            // Aplicar el TableCellRenderer personalizado
            TableColumnModel columnModel = view.jtClases.getColumnModel();
            for (int i = 0; i < columnModel.getColumnCount(); i++) {
                columnModel.getColumn(i).setCellRenderer(new MultiLineCellRenderer2());
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

    }

    private void cargarEspacios(){
        view.cmbEspacio.removeAllItems();
        espacios = clasesService.obtenerListaDeEspacios();
        for (Espacio espacio : espacios) {
            view.cmbEspacio.addItem(espacio.getNombre());
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.btnCrear){
            if(clasesService.validarCuposDeClase((int) view.jsCupos.getValue(), espacioSeleccionado.getCapacidad())){
                if(validarCampos()){
                    if(clasesService.agregarClase(crearClase())){
                        vaciarCampos();
                        cargarClasesEnTabla();
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
        } else if (e.getSource() == view.cmbEspacio){
            espacioSeleccionado = espacios.get(view.cmbEspacio.getSelectedIndex());
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
        clase.setEspacio(espacioSeleccionado);
        return clase;
    }

    private void mostrarMensaje(String mensaje){
        JOptionPane.showMessageDialog(null, mensaje);
    }

    private void vaciarCampos(){
        view.txtNombre.setText("");
        view.txtDescripcion.setText("");
        view.jsCosto.setValue(0.0);
        view.jsCupos.setValue(0);
    }

    public static void main(String[] args) {
        new AgregarClaseController();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

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
