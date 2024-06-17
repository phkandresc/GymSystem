package controller;

import model.Socio;
import model.TipoMembresia;
import service.SocioService;
import service.TipoMembresiaService;
import view.RegistroMembresiaView;
import service.MembresiaService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
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
    private DecimalFormat df;

    public RegistroMembresiaController() {
        df = new DecimalFormat("#.##");
        this.view = new RegistroMembresiaView();
        this.serviceMembresia = new MembresiaService();
        this.serviceSocio = new SocioService();
        this.serviceTipoMembresia = new TipoMembresiaService();
        this.paginaPrincipalController = PaginaPrincipalController.getInstance();
        this.tipoMembresiaSeleccionado = new TipoMembresia();
        this.socioSeleccionado = new Socio();
        view.btnBuscar.addActionListener(this);
        view.cmbTipoMembresia.addItemListener(this);
        view.txtEfectivo.addKeyListener(this);
        view.btnCobrar.addActionListener(this);
        view.setVisible(true);
        cargarTiposMembresia();
    }



    public void vaciarFormulario() {
        view.txtCedula.setText("");
        view.txtNombre.setText("");
        view.txtApellido.setText("");
        view.txtEmail.setText("");
        view.txtTelefono.setText("");
        view.txtDireccion.setText("");
    }

    public void cargarSocioEnFormulario(Socio socio) {
        if (socio == null) {
            JOptionPane.showMessageDialog(null, "No se encontró el socio");
        } else {
            JOptionPane.showMessageDialog(null, "Socio encontrado");
            view.txtCedula.setText(socio.getCedula());
            view.txtNombre.setText(socio.getNombre());
            view.txtApellido.setText(socio.getApellido());
            view.txtEmail.setText(socio.getEmail());
            view.txtTelefono.setText(socio.getNumeroTelefono());
            view.txtDireccion.setText(socio.getDireccion());
        }
    }

    private void cargarTiposMembresia() {
        try {
            listaTiposMembresia = serviceTipoMembresia.obtenerTiposMembresia();
            for (TipoMembresia tipoMembresia : listaTiposMembresia) {
                view.cmbTipoMembresia.addItem(tipoMembresia.getNombre());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar los tipos de membresia: " + e.getMessage());
        }
    }

    private void cargarMembresiaEnTabla(TipoMembresia tipoMembresiaSeleccionado) {
        DefaultTableModel model = (DefaultTableModel) view.jtMembresia.getModel();
        model.setRowCount(0);
        model.addRow(new Object[]{
                tipoMembresiaSeleccionado.getNombre(),
                tipoMembresiaSeleccionado.getDescripcion(),
                tipoMembresiaSeleccionado.getPrecio()
        });
    }

    private void cargarMembresiaEnFormulario(){
        view.txtSubtotal.setText(String.valueOf(df.format(tipoMembresiaSeleccionado.getPrecio()-tipoMembresiaSeleccionado.getPrecio()*0.12)));
        view.txtIVA.setText(String.valueOf(df.format(tipoMembresiaSeleccionado.getPrecio()*0.12)));
        view.txtTotalPagar.setText(String.valueOf(df.format(tipoMembresiaSeleccionado.getPrecio())));
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.btnBuscar) {
            try {
                if(view.cmbCriterioBusqueda.getSelectedIndex() == 0) {
                    System.out.println("ID");
                    vaciarFormulario();
                    socioSeleccionado = serviceSocio.buscarSocioPorId(Integer.parseInt(view.txtBusqueda.getText()));
                    cargarSocioEnFormulario(socioSeleccionado);
                } else if(view.cmbCriterioBusqueda.getSelectedIndex() == 1) {
                    System.out.println("Cedula");
                    vaciarFormulario();
                    socioSeleccionado = serviceSocio.buscarSocioPorCedula(view.txtBusqueda.getText());
                    cargarSocioEnFormulario(socioSeleccionado);
                } else if(view.cmbCriterioBusqueda.getSelectedIndex() == 2){
                    System.out.println("Apellido");
                    vaciarFormulario();
                    socioSeleccionado = serviceSocio.buscarSocioPorApellido(view.txtBusqueda.getText());
                    cargarSocioEnFormulario(socioSeleccionado);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al buscar el socio: " + ex.getMessage());
            }
        } else if (e.getSource() == view.btnCobrar) {
            cobrarMembresia();
        }
    }

    private void cobrarMembresia(){
        try {
            if (socioSeleccionado == null) {
                JOptionPane.showMessageDialog(null, "Debe buscar un socio");
            } else{
                if(view.txtEfectivo.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Debe ingresar el efectivo");
                    return;
                }else if(Double.parseDouble(view.txtEfectivo.getText()) < Double.parseDouble(view.txtTotalPagar.getText())){
                    JOptionPane.showMessageDialog(null, "El efectivo ingresado es menor al total a pagar");
                    return;
                }else if(Double.parseDouble(view.txtEfectivo.getText()) == Double.parseDouble(view.txtTotalPagar.getText())) {
                    serviceMembresia.registrarMembresia(socioSeleccionado, listaTiposMembresia.get(view.cmbTipoMembresia.getSelectedIndex()));
                    JOptionPane.showMessageDialog(null, "Membresia registrada correctamente");
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al registrar la membresia: " + ex.getMessage());
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            tipoMembresiaSeleccionado = listaTiposMembresia.get(view.cmbTipoMembresia.getSelectedIndex());
            view.lblDescripcionMembresia.setText("<html>"+tipoMembresiaSeleccionado.getDescripcion()+"</html>");
            cargarMembresiaEnTabla(tipoMembresiaSeleccionado);
            cargarMembresiaEnFormulario();
        }
    }

    private void imprimirTiposMembresia() {
        for (TipoMembresia tipoMembresia : listaTiposMembresia) {
            System.out.println(tipoMembresia.getNombre()+" "+tipoMembresia.getDescripcion()+" "+tipoMembresia.getPrecio());
        }
        System.out.println(listaTiposMembresia.size());
    }

    public static void main(String[] args) {
        new RegistroMembresiaController();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getSource() == view.txtEfectivo){
            if(view.txtEfectivo.getText().isEmpty()){
                view.txtCambio.setText("");
                return;
            }else{
                double efectivo = Double.parseDouble(view.txtEfectivo.getText());
                double totalPagar = Double.parseDouble(view.txtTotalPagar.getText());
                double cambio = efectivo - totalPagar;

                String cambioFormateado = df.format(cambio);

                view.txtCambio.setText(cambioFormateado);
            }

        }
    }
}
