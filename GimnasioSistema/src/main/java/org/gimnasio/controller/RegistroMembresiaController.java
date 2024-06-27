package org.gimnasio.controller;

import org.gimnasio.model.*;
import org.gimnasio.service.EmailService;
import org.gimnasio.service.MembresiaService;
import org.gimnasio.service.SocioService;
import org.gimnasio.service.TipoMembresiaService;
import org.gimnasio.view.RegistroMembresiaView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.List;

public class RegistroMembresiaController extends WindowController implements ActionListener, ItemListener, KeyListener {
    private RegistroMembresiaView view;
    private MembresiaService serviceMembresia;
    private SocioService serviceSocio;
    private TipoMembresiaService serviceTipoMembresia;
    private PaginaPrincipalController paginaPrincipalController;
    private Socio socioSeleccionado;
    private TipoMembresia tipoMembresiaSeleccionado;
    private List<TipoMembresia> listaTiposMembresia;

    public RegistroMembresiaController() {
        this.view = new RegistroMembresiaView();
        this.serviceMembresia = new MembresiaService();
        this.serviceSocio = new SocioService();
        this.serviceTipoMembresia = new TipoMembresiaService();
        this.paginaPrincipalController = PaginaPrincipalController.getInstance();
        this.tipoMembresiaSeleccionado = new TipoMembresia();
        this.socioSeleccionado = new Socio();
        view.btnBuscar.addActionListener(this);
        view.cmbTipoMembresia.addItemListener(this);
        view.btnCobrar.addActionListener(this);
        view.setVisible(true);
        cargarTiposMembresia();
    }

    public void cargarTiposMembresia(){
        try{
            listaTiposMembresia = serviceTipoMembresia.obtenerTiposMembresia();
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
                JOptionPane.showMessageDialog(null, "Error al buscar el socio: " + ex.getMessage());
            }
        } else if (e.getSource() == view.btnCobrar) {
            try {
                if (socioSeleccionado.getId() == 0) {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un socio");
                    return;
                }
                Membresia membresia = serviceMembresia.registrarMembresia(socioSeleccionado, tipoMembresiaSeleccionado);
                Pago pago = new Pago.Builder()
                        .socio(socioSeleccionado)
                        .membresia(membresia)
                        .monto(tipoMembresiaSeleccionado.getPrecio())
                        .fechaPago(new Date(System.currentTimeMillis()))
                        .metodoPago("Efectivo")
                        .tipoPago("membresia")
                        .build();
                Factura factura = new Factura.Builder()
                        .pago(pago)
                        .numeroFactura("F" + pago.getId())
                        .fechaEmision(new Date(System.currentTimeMillis()))
                        .detalle("Pago de membresía")
                        .subtotal(pago.getMonto())
                        .iva(15)
                        .total(pago.getMonto())
                        .build();
                EmailService emailService = new EmailService();
                emailService.enviarCorreoRegistroMembresia(socioSeleccionado, membresia, tipoMembresiaSeleccionado);
                JOptionPane.showMessageDialog(null, "Membresía registrada con éxito");
                //paginaPrincipalController.cargarMembresias();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al registrar la membresía: " + ex.getMessage());
            }
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

    public static void main(String[] args) {
        new RegistroMembresiaController();
    }
}
