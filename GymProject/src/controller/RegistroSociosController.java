package controller;

import model.Socio;
import model.TipoMembresia;
import service.MembresiaService;
import service.SocioService;
import service.TipoMembresiaService;
import view.PaginaPrincipalView;
import view.RegistroSociosView;
import controller.PaginaPrincipalController;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class RegistroSociosController implements ActionListener, ItemListener, MouseListener {
    private RegistroSociosView registroSociosView;
    private SocioService socioService;
    private MembresiaService membresiaService;
    private TipoMembresiaService tipoMembresiaService;
    private Socio socio;
    private List<TipoMembresia> listaTiposMembresia;
    private TipoMembresia tipoMembresia;
    private PaginaPrincipalController paginaPrincipalController;

    public RegistroSociosController(RegistroSociosView view) {
        this.registroSociosView = view;
        this.socioService = new SocioService();
        this.membresiaService = new MembresiaService();
        this.tipoMembresiaService = new TipoMembresiaService();
        this.socio = new Socio();
        this.tipoMembresia = new TipoMembresia();
        registroSociosView.setLocationRelativeTo(null);
        registroSociosView.setVisible(true);
        registroSociosView.cmbTipoMembresia.addItemListener(this);
        registroSociosView.ButtonRegistrar.addActionListener(this);
        registroSociosView.ButtonCancelar.addActionListener(this);
        cargarTiposMembresia();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registroSociosView.ButtonRegistrar) {
            registrarSocio();
        } else if (e.getSource() == registroSociosView.ButtonCancelar) {
            cancelarRegistro();
        }
    }

    private void registrarSocio() {
        try {
            socio = socioService.validarSocio(registroSociosView);
            if(socio != null){
                socioService.registrarSocio(socio);
                JOptionPane.showMessageDialog(null, "Socio registrado correctamente");
                socio = socioService.buscarSocioPorCedula(socio.getCedula());
                membresiaService.registrarMembresia(socio, listaTiposMembresia.get(registroSociosView.cmbTipoMembresia.getSelectedIndex()));
                JOptionPane.showMessageDialog(null, "Membresia registrada correctamente");
                vaciarCampos();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al registrar el socio: " + ex.getMessage());
        }
    }

    private void cancelarRegistro() {
        registroSociosView.dispose();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            tipoMembresia = listaTiposMembresia.get(registroSociosView.cmbTipoMembresia.getSelectedIndex());
            registroSociosView.lblDescripcionMembresia.setText(String.valueOf("<html>"+tipoMembresia.getDescripcion()+"</html>"));
        }
    }

    // Implementación de los métodos de MouseListener...

    private void cargarTiposMembresia() {
        try {
            listaTiposMembresia = tipoMembresiaService.obtenerTiposMembresia();
            for (TipoMembresia tipoMembresia : listaTiposMembresia) {
                registroSociosView.cmbTipoMembresia.addItem(tipoMembresia.getNombre());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar los tipos de membresia: " + e.getMessage());
        }
    }

    private void vaciarCampos() {
        registroSociosView.txtCedula.setText("");
        registroSociosView.txtNombres.setText("");
        registroSociosView.txtApellidos.setText("");
        registroSociosView.txtEmail.setText("");
        registroSociosView.txtTelefono.setText("");
        registroSociosView.txtDireccion.setText("");
        registroSociosView.dcFechaNacimiento.setDate(null);
        registroSociosView.cmbTipoMembresia.setSelectedIndex(0);
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
}