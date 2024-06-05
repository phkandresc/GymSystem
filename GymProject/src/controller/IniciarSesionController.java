package controller;

import dao.AdminDAO;
import view.IniciarSesionView;
import model.Admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class IniciarSesionController implements ActionListener, MouseListener {
    private IniciarSesionView iniciarSesionView;
    private AdminDAO adminDAO;

    public IniciarSesionController(IniciarSesionView iniciarSesionView, AdminDAO adminDAO){
        this.iniciarSesionView = iniciarSesionView;
        this.adminDAO = adminDAO;
        iniciarSesionView.btnIniciarSesion.addMouseListener(this);
        iniciarSesionView.setVisible(true);
    }

    public void iniciarSesion(String usuario, String contrasena) {
        Admin admin = adminDAO.buscarPorUsuario(usuario);
        if (admin != null && admin.getContrasena().equals(contrasena)) {
            // Inicio de sesión exitoso
            // Aquí puedes cambiar a la vista principal de tu aplicación
        } else {
            // Inicio de sesión fallido
            // Aquí puedes mostrar un mensaje de error en tu vista
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == iniciarSesionView.btnIniciarSesion) {
            System.out.println("Iniciar sesión");
            String usuario = iniciarSesionView.txtContrasena.getText();
            String contrasena = new String(iniciarSesionView.txtUsuario.getText());
            iniciarSesion(usuario, contrasena);
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
}
