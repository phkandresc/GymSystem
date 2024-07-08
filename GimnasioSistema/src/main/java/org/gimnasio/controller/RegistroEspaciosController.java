package org.gimnasio.controller;

import org.gimnasio.daos.EspaciosDAO;
import org.gimnasio.model.Espacio;
import org.gimnasio.render.MultiLineTableCellRenderer;
import org.gimnasio.view.RegistroEspaciosView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;

public class RegistroEspaciosController extends WindowController implements ActionListener, MouseListener {

    private final RegistroEspaciosView view;
    private Espacio espacioSeleccionado;
    private List<Espacio> espacios;
    private EspaciosDAO espaciosDAO;

    public RegistroEspaciosController(){
        view = new RegistroEspaciosView();
        espaciosDAO = new EspaciosDAO();
        view.btnCrear.addActionListener(this);
        view.btnModificar.addActionListener(this);
        view.btnEliminar.addActionListener(this);
        view.jtEspacios.setDefaultRenderer(Object.class, new MultiLineTableCellRenderer());
        view.jtEspacios.addMouseListener(this);
        view.jPanel1.addMouseListener(this);
        view.btnModificar.setEnabled(false);
        view.btnEliminar.setEnabled(false);
        cargarEspaciosEnTabla();
        view.setVisible(true);
    }

    private void cargarEspaciosEnTabla(){
        try {
            espacios = espaciosDAO.obtenerListaDeDatos();
            DefaultTableModel model = (DefaultTableModel) view.jtEspacios.getModel();
            model.setRowCount(0);
            for (Espacio espacio : espacios) {
                model.addRow(new Object[]{espacio.getNombre(), espacio.getCapacidad(), espacio.getDescripcion()});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void actualizarEstadoBotones() {
        boolean hayEspacioSeleccionado = espacioSeleccionado != null;
        view.btnModificar.setEnabled(hayEspacioSeleccionado);
        view.btnEliminar.setEnabled(hayEspacioSeleccionado);
        view.btnCrear.setEnabled(!hayEspacioSeleccionado);
    }

    private void deseleccionarEspacio() {
        // Paso 1: Limpia la selección de la tabla.
        view.jtEspacios.clearSelection();

        // Paso 2: Resetea los campos de texto y el spinner a sus valores por defecto.
        view.txtNombreEspacio.setText("");
        view.jsCapacidad.setValue(0); // Asumiendo que 0 es un valor por defecto adecuado.
        view.txtDescripcionEspacio.setText("");
        view.txtUbicacionEspacio.setText("");

        // Paso 3: Establece espacioSeleccionado a null.
        espacioSeleccionado = null;

        // Paso 4: Actualiza el estado de los botones (si es necesario).
        // Por ejemplo, deshabilitar botones que requieren que un espacio esté seleccionado.
        view.btnModificar.setEnabled(false);
        view.btnEliminar.setEnabled(false);
        view.btnCrear.setEnabled(true);
    }

// Asegúrate de llamar a este método en el evento o acción que desencadene la deselección.



    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == view.btnCrear){
            Espacio espacio = new Espacio();
            espacio.setNombre(view.txtNombreEspacio.getText());
            espacio.setCapacidad((int) view.jsCapacidad.getValue());
            espacio.setDescripcion(view.txtDescripcionEspacio.getText());
            espacio.setUbicacion(view.txtUbicacionEspacio.getText());
            try {
                if (espaciosDAO.agregarDato(espacio)) {
                    JOptionPane.showMessageDialog(view, "Espacio creado correctamente");
                    cargarEspaciosEnTabla();
                } else {
                    JOptionPane.showMessageDialog(view, "Error al crear el espacio");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if(e.getSource() == view.btnModificar){
            if(espacioSeleccionado != null){
                espacioSeleccionado.setNombre(view.txtNombreEspacio.getText());
                espacioSeleccionado.setCapacidad((int) view.jsCapacidad.getValue());
                espacioSeleccionado.setDescripcion(view.txtDescripcionEspacio.getText());
                espacioSeleccionado.setUbicacion(view.txtUbicacionEspacio.getText());
                try {
                    if (JOptionPane.showConfirmDialog(view, "¿Estás seguro de que deseas actualizar el espacio?", "Confirmación", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
                        return;
                    }else {
                        if (espaciosDAO.actualizarDato(espacioSeleccionado)) {
                            JOptionPane.showMessageDialog(view, "Espacio actualizado correctamente");
                            cargarEspaciosEnTabla();
                        } else {
                            JOptionPane.showMessageDialog(view, "Error al actualizar el espacio");
                        }
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        } else if(e.getSource() == view.btnEliminar){
            if(espacioSeleccionado != null){
                try {
                    if(JOptionPane.showConfirmDialog(view, "¿Estás seguro de que deseas eliminar el espacio?", "Confirmación", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION){
                        return;
                    }else {
                        try {
                            if (espaciosDAO.eliminarDato(espacioSeleccionado)) {
                                JOptionPane.showMessageDialog(view, "Espacio eliminado correctamente");
                                cargarEspaciosEnTabla();
                            } else {
                                JOptionPane.showMessageDialog(view, "Error al eliminar el espacio");
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();

                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        int fila = view.jtEspacios.getSelectedRow();
        if (fila != -1) {
            espacioSeleccionado = espacios.get(fila);
            view.txtNombreEspacio.setText(espacioSeleccionado.getNombre());
            view.jsCapacidad.setValue(espacioSeleccionado.getCapacidad());
            view.txtDescripcionEspacio.setText(espacioSeleccionado.getDescripcion());
            view.txtUbicacionEspacio.setText(espacioSeleccionado.getUbicacion());
        }else {
            espacioSeleccionado = null;
        }
        actualizarEstadoBotones();
        if (e.getSource() == view.jPanel1 ){
            deseleccionarEspacio();
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
        new RegistroEspaciosController();
    }
}
