package org.gimnasio.controller;

import org.gimnasio.model.*;
import org.gimnasio.service.EmailService;
import org.gimnasio.service.MembresiaService;
import org.gimnasio.service.SocioService;
import org.gimnasio.view.RegistroMembresiaView;

import javax.swing.*;
import java.awt.event.*;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class RegistroMembresiaController extends WindowController implements ActionListener, ItemListener, KeyListener {
    private final RegistroMembresiaView view;
    private final MembresiaService serviceMembresia;
    private final SocioService serviceSocio;
    private Socio socioSeleccionado;
    private TipoMembresia tipoMembresiaSeleccionado;
    private List<TipoMembresia> listaTiposMembresia;

    private static final String ERROR_BUSCAR_SOCIO = "Error al buscar el socio: ";
    private static final String ERROR_REGISTRAR_MEMBRESIA = "Error al registrar la membresía: ";
    private static final String MEMBRESIA_REGISTRADA_EXITO = "Membresía registrada con éxito";
    private static final String DEBE_SELECCIONAR_SOCIO = "Debe seleccionar un socio";



    public RegistroMembresiaController() {
        this.view = new RegistroMembresiaView();
        this.serviceMembresia = new MembresiaService();
        this.serviceSocio = new SocioService();
        PaginaPrincipalController paginaPrincipalController = PaginaPrincipalController.getInstance();
        this.tipoMembresiaSeleccionado = new TipoMembresia();
        view.btnBuscar.addActionListener(this);
        view.cmbTipoMembresia.addItemListener(this);
        view.btnCobrar.addActionListener(this);
        view.setVisible(true);
        cargarTiposMembresia();
    }

    private void cargarTiposMembresia(){
        try{
            listaTiposMembresia = serviceMembresia.obtenerTiposMembresia();
            for (TipoMembresia tipoMembresia : listaTiposMembresia) {
                view.cmbTipoMembresia.addItem(tipoMembresia.getNombre());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.btnBuscar) {
            buscarSocio();

        } else if (e.getSource() == view.btnCobrar) {
            if(socioSeleccionado == null){
                JOptionPane.showMessageDialog(null, "Debe seleccionar un socio");
                return;
            }
            try {
                if(serviceMembresia.registrarMembresia(socioSeleccionado, tipoMembresiaSeleccionado)){
                    vaciarFormulario();
                }
            } catch (SQLException ex) {
                vaciarFormulario();
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
            } else if(view.cmbCriterioBusqueda.getSelectedIndex() == 2){
                vaciarFormulario();
                socioSeleccionado = serviceSocio.buscarSocioPorApellido(textoBusqueda);
                cargarSocioEnFormulario(socioSeleccionado);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ERROR_BUSCAR_SOCIO + ex.getMessage());
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED){
            int index = view.cmbTipoMembresia.getSelectedIndex();
            tipoMembresiaSeleccionado = listaTiposMembresia.get(index);
            view.lblPrecioTotal.setText("$"+String.valueOf(tipoMembresiaSeleccionado.getPrecio()));
            view.lblNombreMembresia.setText(tipoMembresiaSeleccionado.getNombre());
            view.lblDescripcion.setText("<html>"+tipoMembresiaSeleccionado.getDescripcion()+"</html>");
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void vaciarFormulario(){
        view.txtNombre.setText("");
        view.txtApellido.setText("");
        view.txtCedula.setText("");
        view.txtDireccion.setText("");
        view.txtEmail.setText("");
        view.txtTelefono.setText("");
    }

    public void cargarSocioEnFormulario(Socio socio){
        if (socio == null){
            JOptionPane.showMessageDialog(null, "No se ha encontrado ningún socio con ese criterio de búsqueda");
            return;
        } else {
            view.txtNombre.setText(socio.getNombre());
            view.txtApellido.setText(socio.getApellido());
            view.txtCedula.setText(socio.getCedula());
            view.txtDireccion.setText(socio.getDireccion());
            view.txtEmail.setText(socio.getEmail());
            view.txtTelefono.setText(socio.getNumeroTelefono());
        }
    }

}
