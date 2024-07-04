package org.gimnasio.controller;

import org.gimnasio.model.Socio;
import org.gimnasio.service.SocioService;
import org.gimnasio.view.RegistroSociosView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

public class RegistroSociosController extends WindowController implements ActionListener, MouseListener{
    private RegistroSociosView view;
    private SocioService socioService;
    private PaginaPrincipalController paginaPrincipalController;
    private final String RUTA_IMAGENES = "src/main/resources/images/fotosdeperfil";
    private final String EXTENSION_IMAGEN = ".jpg";
    private File fotoSeleccionada;
    private String nombreFoto;

    public RegistroSociosController() {
        this.view = new RegistroSociosView();
        this.paginaPrincipalController = PaginaPrincipalController.getInstance();
        this.socioService = new SocioService();
        view.setLocationRelativeTo(null);
        view.setVisible(true);
        view.ButtonRegistrar.addActionListener(this);
        view.btnCargarFoto.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.ButtonRegistrar) {
            JTextField[] textFields = {view.txtCedula, view.txtNombres, view.txtApellidos,
                    view.txtTelefono, view.txtDireccion, view.txtEmail};
            if (Validator.validarTextFields(textFields)) {
                if(validarFoto()){
                    registrarSocio();
                }
            }
        }else if(e.getSource() == view.btnCargarFoto){
            cargarFoto();
        }
    }

    private void cargarFoto() {
        JFileChooser fileChooser = new JFileChooser();
        try {
            fileChooser.showOpenDialog(null);
            fotoSeleccionada = fileChooser.getSelectedFile();
            ImageIcon fotoPerfil = new ImageIcon(fotoSeleccionada.getAbsolutePath());
            Image imagen = fotoPerfil.getImage().getScaledInstance(view.lblFotoPerfil.getWidth(), view.lblFotoPerfil.getHeight(), Image.SCALE_SMOOTH);
            view.lblFotoPerfil.setIcon(new ImageIcon(imagen));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar la foto: " + e.getMessage());
        }
    }

    public boolean validarFoto() {
        if (fotoSeleccionada != null) {
            nombreFoto = view.txtCedula.getText() + EXTENSION_IMAGEN;
            File fotoDestino = new File(RUTA_IMAGENES + "/" + nombreFoto);
            if (fotoDestino.exists()) {
                int respuesta = JOptionPane.showConfirmDialog(null, "Ya existe una foto con ese nombre, ¿Desea reemplazarla?");
                if (respuesta == JOptionPane.YES_OPTION) {
                    fotoSeleccionada.renameTo(fotoDestino);
                }
            } else {
                fotoSeleccionada.renameTo(fotoDestino);
            }
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una foto de perfil");
            return false;
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
                        .setFotoPerfil(RUTA_IMAGENES+"/"+view.txtCedula.getText() + EXTENSION_IMAGEN)
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
        view.lblFotoPerfil.setIcon(null);
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