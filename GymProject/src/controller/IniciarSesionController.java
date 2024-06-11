package controller;

import DataAccessObject.AdministradorDAO;
import model.Administrador;
import view.IniciarSesionView;
import view.PaginaPrincipalView;

import javax.swing.*;
import java.awt.event.*;

public class IniciarSesionController implements ActionListener, MouseListener, FocusListener{
    private IniciarSesionView iniciarSesionView;
    private AdministradorDAO administradorDAO;

    public IniciarSesionController(IniciarSesionView iniciarSesionView, AdministradorDAO administradorDAO){
        this.iniciarSesionView = iniciarSesionView;
        this.administradorDAO = administradorDAO;
        iniciarSesionView.btnIniciarSesion.addMouseListener(this);
        iniciarSesionView.txtUsuario.addFocusListener(this);
        iniciarSesionView.txtContrasena.addFocusListener(this);
        iniciarSesionView.txtUsuario.addActionListener(this);
        iniciarSesionView.txtContrasena.addActionListener(this);
        iniciarSesionView.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == iniciarSesionView.txtUsuario || e.getSource() == iniciarSesionView.txtContrasena){
            iniciarSesion();
        }
    }

    private void iniciarSesion() {
        Administrador administrador = new Administrador();
        administrador.setUsuario(iniciarSesionView.txtUsuario.getText());
        administrador.setContrasena(iniciarSesionView.txtContrasena.getText());

        try {
            if (administradorDAO.iniciarSesion(administrador)) {
                JOptionPane.showMessageDialog(null, "Sesion iniciada");
                System.out.println("Sesion iniciada");
                iniciarSesionView.dispose();
                PaginaPrincipalController paginaPricipal = new PaginaPrincipalController(new PaginaPrincipalView());
            } else {
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
                System.out.println("Usuario o contraseña incorrectos");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al iniciar sesion");
            System.out.println("Error al iniciar sesion");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == iniciarSesionView.btnIniciarSesion){
            iniciarSesion();
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

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource() == iniciarSesionView.txtUsuario) {
            iniciarSesionView.txtUsuario.setText("");
        } else if (e.getSource() == iniciarSesionView.txtContrasena) {
            iniciarSesionView.txtContrasena.setText("");
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource() == iniciarSesionView.txtUsuario && iniciarSesionView.txtUsuario.getText().equals("")) {
            iniciarSesionView.txtUsuario.setText("Usuario");
        } else if (e.getSource() == iniciarSesionView.txtContrasena && iniciarSesionView.txtContrasena.getText().equals("")) {
            iniciarSesionView.txtContrasena.setText("Contraseña");
        }
    }
}
