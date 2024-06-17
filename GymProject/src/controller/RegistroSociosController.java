package controller;

import model.Socio;
import model.TipoMembresia;
import service.EmailService;
import service.MembresiaService;
import service.SocioService;
import service.TipoMembresiaService;
import view.RegistroSociosView;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class RegistroSociosController extends WindowController implements ActionListener, ItemListener, MouseListener{
    private RegistroSociosView view;
    private SocioService socioService;
    private MembresiaService membresiaService;
    private TipoMembresiaService tipoMembresiaService;
    private Socio socio;
    private List<TipoMembresia> listaTiposMembresia;
    private TipoMembresia tipoMembresia;
    private PaginaPrincipalController paginaPrincipalController;

    public RegistroSociosController() {
        this.view = new RegistroSociosView();
        this.paginaPrincipalController = PaginaPrincipalController.getInstance();
        this.socioService = new SocioService();
        this.membresiaService = new MembresiaService();
        this.tipoMembresiaService = new TipoMembresiaService();
        this.socio = new Socio();
        this.tipoMembresia = new TipoMembresia();
        view.setLocationRelativeTo(null);
        view.setVisible(true);
        view.cmbTipoMembresia.addItemListener(this);
        view.ButtonRegistrar.addActionListener(this);
        view.ButtonCancelar.addActionListener(this);
        cargarTiposMembresia();
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
                    JOptionPane.showMessageDialog(null, "Socio registrado correctamente");
                    socio = socioService.buscarSocioPorCedula(socio.getCedula());
                    membresiaService.registrarMembresia(socio, listaTiposMembresia.get(view.cmbTipoMembresia.getSelectedIndex()));
                    JOptionPane.showMessageDialog(null, "Membresia registrada correctamente");
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

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            tipoMembresia = listaTiposMembresia.get(view.cmbTipoMembresia.getSelectedIndex());
            view.lblDescripcionMembresia.setText(String.valueOf("<html>"+tipoMembresia.getDescripcion()+"</html>"));
        }
    }

    // Implementación de los métodos de MouseListener...

    private void cargarTiposMembresia() {
        try {
            listaTiposMembresia = tipoMembresiaService.obtenerTiposMembresia();
            for (TipoMembresia tipoMembresia : listaTiposMembresia) {
                view.cmbTipoMembresia.addItem(tipoMembresia.getNombre());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar los tipos de membresia: " + e.getMessage());
        }
    }

    private void vaciarCampos() {
        view.txtCedula.setText("");
        view.txtNombres.setText("");
        view.txtApellidos.setText("");
        view.txtEmail.setText("");
        view.txtTelefono.setText("");
        view.txtDireccion.setText("");
        view.dcFechaNacimiento.setDate(null);
        view.cmbTipoMembresia.setSelectedIndex(0);
        // Agrega aquí todos los demás campos de texto que quieras vaciar
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