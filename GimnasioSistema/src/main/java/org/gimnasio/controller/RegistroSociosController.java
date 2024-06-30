package org.gimnasio.controller;

import org.gimnasio.model.Socio;
import org.gimnasio.service.SocioService;
import org.gimnasio.view.RegistroSociosView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RegistroSociosController implements ActionListener, MouseListener{
    private RegistroSociosView view;
    private SocioService socioService;
    private PaginaPrincipalController paginaPrincipalController;

    public RegistroSociosController() {
        this.view = new RegistroSociosView();
        this.paginaPrincipalController = PaginaPrincipalController.getInstance();
        this.socioService = new SocioService();
        view.setLocationRelativeTo(null);
        view.setVisible(true);
        view.ButtonRegistrar.addActionListener(this);
        view.ButtonCancelar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.ButtonRegistrar) {
            JTextField[] textFields = {view.txtCedula, view.txtNombres, view.txtApellidos,
                    view.txtTelefono, view.txtDireccion, view.txtEmail};
            if (Validator.validarTextFields(textFields)) {
                registrarSocio();
            }
        } else if (e.getSource() == view.ButtonCancelar) {
            cancelarRegistro();
        }
    }

    private void registrarSocio() {
        try {
                Socio socioNuevo = new Socio.SocioBuilder()
                        .setCedula(view.txtCedula.getText())
                        .setNombre(view.txtNombres.getText())
                        .setApellido(view.txtApellidos.getText())
                        .setEmail(view.txtEmail.getText())
                        .setNumeroTelefono(view.txtTelefono.getText())
                        .setDireccion(view.txtDireccion.getText())
                        .setFechaNacimiento(new java.sql.Date(view.dcFechaNacimiento.getDate().getTime()))
                        .build();
                if(Validator.validarSocio(socioNuevo)){
                    if(socioService.registrarSocio(socioNuevo)) {
                        JOptionPane.showMessageDialog(null, "Socio registrado correctamente");
                        vaciarCampos();
                    }
                }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al registrar el socio: " + ex.getMessage());
        }
    }

    private void cancelarRegistro() {
        view.dispose();
        paginaPrincipalController.mostrarPaginaPrincipal();
    }


    private void vaciarCampos() {
        view.txtCedula.setText("");
        view.txtNombres.setText("");
        view.txtApellidos.setText("");
        view.txtEmail.setText("");
        view.txtTelefono.setText("");
        view.txtDireccion.setText("");
        view.dcFechaNacimiento.setDate(null);
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

    public static void main(String[] args) {
        new RegistroSociosController();
    }
}