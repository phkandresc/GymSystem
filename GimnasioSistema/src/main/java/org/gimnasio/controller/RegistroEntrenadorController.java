package org.gimnasio.controller;

import org.gimnasio.model.Entrenador;
import org.gimnasio.render.MultiLineTableCellRenderer;
import org.gimnasio.service.ClasesService;
import org.gimnasio.view.RegistroEntrenadorView;

import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class RegistroEntrenadorController implements MouseListener {
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
}
