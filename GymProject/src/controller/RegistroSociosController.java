package controller;

import DAO.MembresiaDAO;
import DAO.SocioDAO;
import DAO.TipoMembresiaDAO;
import model.Membresia;
import model.Socio;
import model.TipoMembresia;
import view.RegistroSociosView;

import javax.swing.*;
import java.awt.event.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;

public class RegistroSociosController implements ActionListener, MouseListener, FocusListener, ItemListener {
    private RegistroSociosView registroSociosView;
    private SocioDAO socioDAO;
    private TipoMembresiaDAO tipoMembresiaDAO;
    private TipoMembresia tipoMembresia;
    private Socio socio;
    private List<TipoMembresia> listaTiposMembresia;
    private Membresia membresia;
    private MembresiaDAO membresiaDAO;

    public RegistroSociosController(RegistroSociosView registroSociosView) {
        this.registroSociosView = registroSociosView;
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
        } else if(e.getSource() == registroSociosView.ButtonCancelar){
            borrarRegistrosPrueba();

        }
    }

    @Override
    public void focusGained(FocusEvent e) {

    }

    @Override
    public void focusLost(FocusEvent e) {

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

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            tipoMembresia = listaTiposMembresia.get(registroSociosView.cmbTipoMembresia.getSelectedIndex());
            registroSociosView.lblDescripcionMembresia.setText(String.valueOf("<html>"+tipoMembresia.getDescripcion()+"</html>"));
        }
    }

    public void cargarTiposMembresia(){
        try {
            tipoMembresiaDAO = new TipoMembresiaDAO();
            listaTiposMembresia = tipoMembresiaDAO.obtenerTiposMembresia();
            for (TipoMembresia tipoMembresia : listaTiposMembresia) {
                registroSociosView.cmbTipoMembresia.addItem(tipoMembresia.getNombre());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar los tipos de membresia");
        }
    }

    public void registrarSocio() {
        try {
            socio = new Socio();
            socio.setCedula("0105627806");
            socio.setNombre("Kevin");
            socio.setApellido("Coraizaca");
            socio.setEmail("kacoraizaca@gmail.com");
            socio.setNumeroTelefono("0987654321");
            socio.setDireccion("Cuenca");
            socio.setFechaNacimiento(Date.valueOf("2003-07-03"));
            SocioDAO socioDAO = new SocioDAO();
            socioDAO.registrarSocio(socio);
            socio = socioDAO.seleccionarSocio("0105627806");

            MembresiaDAO membresiaDAO = new MembresiaDAO();
            membresia = new Membresia(socio.getId(), listaTiposMembresia.getFirst().getId(), 1,  Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime())), Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime())));
            membresiaDAO.registrarMembresia(membresia);
            JOptionPane.showMessageDialog(null, "Socio registrado correctamente");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error al registrar el socio");
        }
    }

    public void borrarRegistrosPrueba(){
        try {
            membresiaDAO = new MembresiaDAO();
            membresiaDAO.eliminarTodasMembresias();
            socioDAO = new SocioDAO();
            socioDAO.eliminarSocio("0105627806");
            JOptionPane.showMessageDialog(null, "Registros de prueba eliminados correctamente");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error al eliminar los registros de prueba");
        }
    }
    
    public boolean validarSocio() {

        validarTextField(registroSociosView.txtCedula, "El campo Cédula no puede estar vacío");
        validarTextField(registroSociosView.txtNombres, "El campo Nombre no puede estar vacío");
        validarTextField(registroSociosView.txtApellidos, "El campo Apellido no puede estar vacío");
        if(validarTextField(registroSociosView.txtEmail, "El campo Email no puede estar vacío")){
            if (!validarEmail(registroSociosView.txtEmail.getText())) {
                JOptionPane.showMessageDialog(null, "El email ingresado no es válido");
                return false;
            }
        }
        validarTextField(registroSociosView.txtTelefono, "El campo Número de Teléfono no puede estar vacío");
        validarTextField(registroSociosView.txtDireccion, "El campo Dirección no puede estar vacío");
        if (registroSociosView.dcFechaNacimiento.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una Fecha de Nacimiento");
            return false;
        }


        return true;
    }

    public boolean validarEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        if (email == null) {
            return false;
        }
        return pattern.matcher(email).matches();
    }

    public boolean validarTextField(JTextField textField, String mensaje) {
        if (textField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, mensaje);
            return false;
        }
        return true;
    }
}
