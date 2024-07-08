package org.gimnasio.controller;

import org.gimnasio.daos.ControlAccesoDAO;
import org.gimnasio.model.Socio;
import org.gimnasio.service.SocioService;
import org.gimnasio.view.ControlAccesoView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlAccesoController extends WindowController implements ActionListener {
    private ControlAccesoView view;
    private Socio socioSeleccionado;
    private SocioService serviceSocio;

    public ControlAccesoController() {
        view = new ControlAccesoView();
        view.btnBuscar.addActionListener(this);
        view.btnIngresar.addActionListener(this);
        serviceSocio = new SocioService();
        view.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.btnBuscar) {
            buscarSocio();
        } else if (e.getSource() == view.btnIngresar) {
            ControlAccesoDAO controlAccesoDAO = new ControlAccesoDAO();
            if(socioSeleccionado == null){
                JOptionPane.showMessageDialog(null, "Debe seleccionar un socio");
                return;
            }else {
                try {
                    if(controlAccesoDAO.registrarAcceso(socioSeleccionado)){
                        JOptionPane.showMessageDialog(null, "Ingreso registrado con éxito");
                    }else {
                        JOptionPane.showMessageDialog(null, "El socio no tiene una membresía activa");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al registrar el ingreso");
                }
            }
        }
    }

    private void buscarSocio() {
        String textoBusqueda = view.txtBusqueda.getText();
        try {
            if(view.cmbCriterioBusqueda.getSelectedIndex() == 0) {
                vaciarFormulario();
                socioSeleccionado = serviceSocio.buscarSocioPorId(Integer.parseInt(textoBusqueda));
                cargarSocioEnFormulario(socioSeleccionado);
            } else if(view.cmbCriterioBusqueda.getSelectedIndex() == 1) {
                vaciarFormulario();
                socioSeleccionado = serviceSocio.buscarSocioPorCedula(textoBusqueda);
                cargarSocioEnFormulario(socioSeleccionado);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar el socio");
        }
    }

    private void vaciarFormulario(){
        view.txtNombre.setText("");
        view.txtApellido.setText("");
        view.txtDireccion.setText("");
        view.txtEmail.setText("");
        view.txtTelefono.setText("");
    }

    private void cargarSocioEnFormulario(Socio socio){
        if (socio == null){
            JOptionPane.showMessageDialog(null, "No se ha encontrado ningún socio con ese criterio de búsqueda");
            return;
        } else {
            view.txtNombre.setText(socio.getNombre());
            view.txtApellido.setText(socio.getApellido());
            view.txtDireccion.setText(socio.getDireccion());
            view.txtEmail.setText(socio.getEmail());
            view.txtTelefono.setText(socio.getNumeroTelefono());
        }
    }

}
