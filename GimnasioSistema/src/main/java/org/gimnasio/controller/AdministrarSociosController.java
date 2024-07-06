package org.gimnasio.controller;

import org.gimnasio.model.Socio;
import org.gimnasio.service.SocioService;
import org.gimnasio.view.AdministrarSociosView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

public class AdministrarSociosController extends WindowController implements ActionListener, ItemListener {
    private final AdministrarSociosView view;
    private final SocioService socioService;
    private Socio socioSeleccionado;

    public AdministrarSociosController(Socio socioSeleccionado) {
        this.socioSeleccionado = socioSeleccionado;

        this.view = new AdministrarSociosView();
        cargarSocioEnFormulario();
        this.socioService = new SocioService();
        view.ButtonModificar.addActionListener(this);
        view.ButtonEliminar.addActionListener(this);
        view.ButtonBuscar.addActionListener(this);
        view.TextFieldBusqueda.addActionListener(this);
        view.cmbCriterioBusqueda.addItemListener(this);
        view.TextFieldBusqueda.addActionListener(this);
        view.setVisible(true);
    }

    public AdministrarSociosController() {
        this.view = new AdministrarSociosView();
        this.socioService = new SocioService();
        view.ButtonModificar.addActionListener(this);
        view.ButtonEliminar.addActionListener(this);
        view.ButtonBuscar.addActionListener(this);
        view.TextFieldBusqueda.addActionListener(this);
        view.cmbCriterioBusqueda.addItemListener(this);
        view.TextFieldBusqueda.addActionListener(this);
        view.setVisible(true);
    }

    private void cargarSocioEnFormulario(){
        view.txtCedula.setText(socioSeleccionado.getCedula());
        view.txtNombre.setText(socioSeleccionado.getNombre());
        view.txtApellido.setText(socioSeleccionado.getApellido());
        view.txtEmail.setText(socioSeleccionado.getEmail());
        view.txtTelefono.setText(socioSeleccionado.getNumeroTelefono());
        view.txtDireccion.setText(socioSeleccionado.getDireccion());
        cargarFotoSocio(socioSeleccionado);
    }

    private void cargarFotoSocio(Socio socioSeleccionado){
        try {
            ImageIcon foto = new ImageIcon(socioSeleccionado.getFotoPerfil());
            view.lblFotoPerfil.setIcon(foto);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar la foto del socio: " + ex.getMessage());
        }
    }

    private void eliminarSocio() {
        try {
            int confirmacion = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar el socio y su membresía asociada?", "Eliminar Socio", JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION && socioSeleccionado != null) {
                socioService.eliminarSocio(socioSeleccionado);
                JOptionPane.showMessageDialog(null, "Socio eliminado correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "No se ha seleccionado ningún socio");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el socio: " + ex.getMessage());
        } finally {
            vaciarFormulario();
        }
    }

    private void vaciarFormulario(){
        view.TextFieldBusqueda.setText("");
        view.txtCedula.setText("");
        view.txtNombre.setText("");
        view.txtApellido.setText("");
        view.txtEmail.setText("");
        view.txtTelefono.setText("");
        view.txtDireccion.setText("");
        view.lblFotoPerfil.setIcon(null);
    }

    private void modificarSocio() {
        try {
            socioSeleccionado.setCedula(view.txtCedula.getText());
            socioSeleccionado.setNombre(view.txtNombre.getText());
            socioSeleccionado.setApellido(view.txtApellido.getText());
            socioSeleccionado.setEmail(view.txtEmail.getText());
            socioSeleccionado.setNumeroTelefono(view.txtTelefono.getText());
            socioSeleccionado.setDireccion(view.txtDireccion.getText());
            socioService.actualizarSocio(socioSeleccionado);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al modificar el socio: " + ex.getMessage());
        }
    }

    private boolean buscarSocio() {
        try {
            if(view.cmbCriterioBusqueda.getSelectedIndex() == 0){
                socioSeleccionado = socioService.buscarSocioPorId(Integer.parseInt(view.TextFieldBusqueda.getText()));
                if (socioSeleccionado != null) {
                    return true;
                }
            }else if(view.cmbCriterioBusqueda.getSelectedIndex() == 1) {
                socioSeleccionado = socioService.buscarSocioPorCedula(view.TextFieldBusqueda.getText());
                if (socioSeleccionado != null) {
                    return true;
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar el socio: " + ex.getMessage());
        }
        return false;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.ButtonEliminar) {
            eliminarSocio();
            vaciarFormulario();
        } else if (e.getSource() == view.ButtonModificar) {
            modificarSocio();
            vaciarFormulario();
        }else if (e.getSource() == view.ButtonBuscar || e.getSource() == view.TextFieldBusqueda){
            if (buscarSocio()) {
                cargarSocioEnFormulario();
            } else {
                JOptionPane.showMessageDialog(null, "No se ha encontrado ningún socio con el criterio de búsqueda seleccionado");
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            view.TextFieldBusqueda.setText("");
        }
    }

    public static void main(String[] args) {
        new AdministrarSociosController();
    }
}
