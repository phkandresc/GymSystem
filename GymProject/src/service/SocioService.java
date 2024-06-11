package service;

import DAO.SocioDAO;
import model.Socio;
import view.RegistroSociosView;

import javax.swing.*;
import java.util.List;
import java.util.regex.Pattern;

public class SocioService {
    private SocioDAO socioDAO;

    public SocioService() {
        this.socioDAO = new SocioDAO();
    }

    public void registrarSocio(Socio socio) throws Exception {
        socioDAO.registrarSocio(socio);
    }

    public void actualizarSocio(Socio socio) throws Exception {
        socioDAO.actualizarSocio(socio);
    }

    public void eliminarSocio(String cedula) throws Exception {
        socioDAO.eliminarSocio(cedula);
    }

    public Socio buscarSocio(String cedula) throws Exception {
        return socioDAO.buscarSocio(cedula);
    }

    public List<Socio> obtenerTodosSocios() throws Exception {
        return socioDAO.obtenerTodosSocios();
    }

    public Socio validarSocio(RegistroSociosView view) {
        if (validarTextField(view.txtCedula)) {
            if (view.txtCedula.getText().length() != 10) {
                JOptionPane.showMessageDialog(null, "La cédula debe tener 10 dígitos");
                return null;
            }
        }
        if (validarTextField(view.txtNombres)) {
            if (view.txtNombres.getText().length() < 3) {
                JOptionPane.showMessageDialog(null, "El nombre debe tener al menos 3 caracteres");
                return null;
            }
        }

        if (validarTextField(view.txtApellidos)) {
            if (view.txtApellidos.getText().length() < 3) {
                JOptionPane.showMessageDialog(null, "El apellido debe tener al menos 3 caracteres");
                return null;
            }
        }

        if (validarTextField(view.txtEmail)) {
            if (!validarEmail(view.txtEmail.getText())) {
                JOptionPane.showMessageDialog(null, "El email ingresado no es válido");
                return null;
            }
        }

        if (validarTextField(view.txtTelefono)) {
            if (view.txtTelefono.getText().length() != 10) {
                JOptionPane.showMessageDialog(null, "El número de teléfono debe tener 10 dígitos");
                return null;
            }
        }

        if (validarTextField(view.txtDireccion)) {
            if (view.txtDireccion.getText().length() < 10) {
                JOptionPane.showMessageDialog(null, "La dirección debe tener al menos 10 caracteres");
                return null;
            }
        }
        if (view.dcFechaNacimiento.getDate() == null) {
            JOptionPane.showMessageDialog(null, "La fecha de nacimiento no puede estar vacía");
            return null;
        }

        Socio socio = new Socio();
        socio.setCedula(view.txtCedula.getText());
        socio.setNombre(view.txtNombres.getText());
        socio.setApellido(view.txtApellidos.getText());
        socio.setEmail(view.txtEmail.getText());
        socio.setNumeroTelefono(view.txtTelefono.getText());
        socio.setDireccion(view.txtDireccion.getText());
        socio.setFechaNacimiento(new java.sql.Date(view.dcFechaNacimiento.getDate().getTime()));
        return socio;
    }

    private boolean validarEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        if (email == null) {
            return false;
        }
        return pattern.matcher(email).matches();
    }

    private boolean validarTextField(JTextField textField) {
        return !textField.getText().trim().isEmpty();
    }

}
