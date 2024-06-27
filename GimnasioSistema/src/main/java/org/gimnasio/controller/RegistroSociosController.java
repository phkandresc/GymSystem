package org.gimnasio.controller;

import org.gimnasio.model.Socio;
import org.gimnasio.service.EmailService;
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
    private Socio socio;
    private PaginaPrincipalController paginaPrincipalController;

    public RegistroSociosController() {
        this.view = new RegistroSociosView();
        this.paginaPrincipalController = PaginaPrincipalController.getInstance();
        this.socioService = new SocioService();
        this.socio = new Socio();
        view.setLocationRelativeTo(null);
        view.setVisible(true);
        view.ButtonRegistrar.addActionListener(this);
        view.ButtonCancelar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.ButtonRegistrar) {
            JTextField[] textFields = {view.txtCedula, view.txtNombres, view.txtApellidos, view.txtTelefono, view.txtDireccion, view.txtEmail};
            if (validarTextFields(textFields)) {
                registrarSocio();
            }
        } else if (e.getSource() == view.ButtonCancelar) {
            cancelarRegistro();
        }
    }

    private void registrarSocio() {
        try {
            if(validarTextFields(new JTextField[]{view.txtCedula, view.txtNombres, view.txtApellidos, view.txtTelefono, view.txtDireccion, view.txtEmail})){
                socio = new Socio();
                socio.setCedula(view.txtCedula.getText());
                socio.setNombre(view.txtNombres.getText());
                socio.setApellido(view.txtApellidos.getText());
                socio.setEmail(view.txtEmail.getText());
                socio.setNumeroTelefono(view.txtTelefono.getText());
                socio.setDireccion(view.txtDireccion.getText());
                socio.setFechaNacimiento(new java.sql.Date(view.dcFechaNacimiento.getDate().getTime()));
                if(socioService.validarSocio(socio)){
                    socioService.registrarSocio(socio);
                    socio = socioService.buscarSocioPorCedula(socio.getCedula());
                    JOptionPane.showMessageDialog(null, "Socio registrado correctamente");
                    EmailService emailService = new EmailService();
                    emailService.enviarCorreoNuevoSocio(socio.getEmail(), socio.getNombre(), socio.getApellido(), String.valueOf(socio.getId()));
                    vaciarCampos();
                } else {
                    JOptionPane.showMessageDialog(null, "Ingrese correctamente los datos del socio");
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


    public boolean validarTextFields(JTextField[] textFields) {
        for (JTextField textField : textFields) {
            if (textField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, llene todos los campos correctamente");
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        new RegistroSociosController();
    }
}