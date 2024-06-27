package org.gimnasio.controller;

import org.gimnasio.model.Socio;
import org.gimnasio.service.SocioService;
import org.gimnasio.view.ListaSociosView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;

public class ListaSociosController extends WindowController implements ActionListener, ItemListener, MouseListener, WindowListener {
    private ListaSociosView view;
    private SocioService service;
    private Socio socioSeleccionado;
    private PaginaPrincipalController paginaPrincipalController;

    public ListaSociosController() {
        this.view = new ListaSociosView();
        this.service = new SocioService();
        paginaPrincipalController = PaginaPrincipalController.getInstance();
        view.ButtonBuscar.addActionListener(this);
        view.cmbCriterioBusqueda.addItemListener(this);
        view.jtSocios.addMouseListener(this);
        view.ButtonEliminar.addActionListener(this);
        view.ButtonModificar.addActionListener(this);
        view.setVisible(true);
        cargarLista();
    }

    private void cargarLista() {
        try {
            List <Socio> listaSocios = service.obtenerTodosSocios();
            cargarDatosTabla(listaSocios);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar los datos: " + e.getMessage());
        }
    }

    public void cargarDatosTabla(List<Socio> listaSocios) {
        DefaultTableModel model = (DefaultTableModel) view.jtSocios.getModel();
        model.setRowCount(0);
        for (Socio socio : listaSocios) {
            model.addRow(new Object[] {
                    socio.getCedula(),
                    socio.getNombre(),
                    socio.getApellido(),
                    socio.getEmail(),
                    socio.getNumeroTelefono(),
                    socio.getDireccion(),
                    socio.getFechaNacimiento()
            });
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            view.txtBusqueda.setText("");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.ButtonBuscar) {
            try {
                if(view.cmbCriterioBusqueda.getSelectedIndex() == 0 && service.validarBusqueda(view)) {
                    vaciarFormulario();
                    socioSeleccionado = service.buscarSocioPorId(Integer.parseInt(view.txtBusqueda.getText()));
                    cargarSocioEnFormulario(socioSeleccionado);
                } else if(view.cmbCriterioBusqueda.getSelectedIndex() == 1 && service.validarBusqueda(view) ) {
                    vaciarFormulario();
                    socioSeleccionado = service.buscarSocioPorCedula(view.txtBusqueda.getText());
                    cargarSocioEnFormulario(socioSeleccionado);
                } else if(view.cmbCriterioBusqueda.getSelectedIndex() == 2 && service.validarBusqueda(view)){
                    vaciarFormulario();
                    socioSeleccionado = service.buscarSocioPorApellido(view.txtBusqueda.getText());
                    cargarSocioEnFormulario(socioSeleccionado);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al buscar el socio: " + ex.getMessage());
            }
        }else if(e.getSource() == view.ButtonEliminar){
            try {
                int confirmacion = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar el socio y su membresía asociada?", "Eliminar Socio", JOptionPane.YES_NO_OPTION);
                if (confirmacion == JOptionPane.YES_OPTION && socioSeleccionado != null) {
                    service.eliminarSocio(socioSeleccionado);
                    vaciarFormulario();
                    JOptionPane.showMessageDialog(null, "Socio eliminado correctamente");
                } else {
                    JOptionPane.showMessageDialog(null, "No se ha seleccionado ningún socio");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al eliminar el socio: " + ex.getMessage());
            } finally {
                cargarLista();
            }
        }else if (e.getSource() == view.ButtonModificar) {
            try {
                int confirmacion = JOptionPane.showConfirmDialog(null, "¿Está seguro de modificar el socio?", "Modificar Socio", JOptionPane.YES_NO_OPTION);
                if (socioSeleccionado != null && confirmacion == JOptionPane.YES_OPTION) {
                    socioSeleccionado.setCedula(view.txtCedula.getText());
                    socioSeleccionado.setNombre(view.txtNombre.getText());
                    socioSeleccionado.setApellido(view.txtApellido.getText());
                    socioSeleccionado.setEmail(view.txtEmail.getText());
                    socioSeleccionado.setNumeroTelefono(view.txtTelefono.getText());
                    socioSeleccionado.setDireccion(view.txtDireccion.getText());
                    if(service.validarSocio(socioSeleccionado) == true){
                        service.actualizarSocio(socioSeleccionado);
                        JOptionPane.showMessageDialog(null, "Socio modificado correctamente");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No se ha seleccionado ningún socio");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al modificar el socio: " + ex.getMessage());
            } finally {
                cargarLista();
            }
        }
    }

    public void cargarSocioEnFormulario(Socio socio) {
        if (socio == null) {
            JOptionPane.showMessageDialog(null, "No se encontró el socio");
            return;
        } else {
            JOptionPane.showMessageDialog(null, "Socio encontrado");
            view.txtCedula.setText(socio.getCedula());
            view.txtNombre.setText(socio.getNombre());
            view.txtApellido.setText(socio.getApellido());
            view.txtEmail.setText(socio.getEmail());
            view.txtTelefono.setText(socio.getNumeroTelefono());
            view.txtDireccion.setText(socio.getDireccion());
        }
    }


    public void vaciarFormulario() {
        view.txtCedula.setText("");
        view.txtNombre.setText("");
        view.txtApellido.setText("");
        view.txtEmail.setText("");
        view.txtTelefono.setText("");
        view.txtDireccion.setText("");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == view.jtSocios) {
            int filaSeleccionada = view.jtSocios.getSelectedRow();
            if (filaSeleccionada != -1) {
                try {
                    vaciarFormulario();
                    socioSeleccionado = service.buscarSocioPorCedula(view.jtSocios.getValueAt(filaSeleccionada, 0).toString());
                    cargarSocioEnFormulario(socioSeleccionado);
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

    public static void main(String[] args) {
        new ListaSociosController();
    }
}
