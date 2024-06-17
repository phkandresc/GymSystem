package service;

import DataAccessObject.SocioDAO;
import model.Socio;
import view.RegistroSociosView;
import view.ListaSociosView;

import javax.swing.*;
import java.sql.Date;
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
        validarSocio(socio);
        socioDAO.actualizarSocio(socio);
    }

    public void eliminarSocio(Socio socio) throws Exception {
        socioDAO.eliminarSocio(socio);
    }

    public Socio buscarSocioPorCedula(String cedula) throws Exception {
        return socioDAO.buscarSocioPorCedula(cedula);
    }

    public Socio buscarSocioPorId(int id) throws Exception {
        return socioDAO.buscarSocioPorId(id);
    }

    public Socio buscarSocioPorApellido(String nombre) throws Exception {
        return socioDAO.buscarSocioPorApellido(nombre);
    }

    public List<Socio> obtenerTodosSocios() throws Exception {
        return socioDAO.obtenerTodosSocios();
    }

    public boolean validarSocio(Socio socio) {
        if (!validarCedula(socio.getCedula())) {
            JOptionPane.showMessageDialog(null, "La cédula debe tener 10 dígitos");
            return false;
        } else if (socio.getNombre() == null || socio.getNombre().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El nombre no puede estar vacío");
            return false;
        } else if (socio.getApellido() == null || socio.getApellido().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El apellido no puede estar vacío");
            return false;
        } else if (!validarEmail(socio.getEmail())) {
            JOptionPane.showMessageDialog(null, "El email no es válido");
            return false;
        } else if (!validarTelefono(socio.getNumeroTelefono())) {
            JOptionPane.showMessageDialog(null, "El teléfono debe tener 10 dígitos");
            return false;
        } else if (!validarDireccion(socio.getDireccion())) {
            JOptionPane.showMessageDialog(null, "La dirección debe tener al menos 10 caracteres");
            return false;
        } else if (!validarFechaNacimiento(socio.getFechaNacimiento())) {
            JOptionPane.showMessageDialog(null, "Ingrese una fecha de nacimiento válida");
            return false;
        }
        return true;
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

    private boolean validarCedula(String cedula) {

        return cedula.length() == 10 && cedula.matches("[0-9]*");
    }

    private boolean validarTelefono(String telefono) {
        return telefono.length() == 10;
    }

    private boolean validarDireccion(String direccion) {
        return direccion.length() >= 10;
    }

    private boolean validarFechaNacimiento(Date fechaNacimiento) {
        return fechaNacimiento != null;
    }

    public boolean validarBusqueda(ListaSociosView view) {
        if(!validarTextField(view.txtBusqueda)) {
            JOptionPane.showMessageDialog(null, "El campo de búsqueda no puede estar vacío");
            return false;
        }else {
            if(view.cmbCriterioBusqueda.getSelectedIndex() == 0) {
                if(!view.txtBusqueda.getText().matches("[0-9]*")) {
                    JOptionPane.showMessageDialog(null, "El ID debe ser un número entero");
                    return false;
                }
            } else if (view.cmbCriterioBusqueda.getSelectedIndex() == 1) {
                if(view.txtBusqueda.getText().length() != 10) {
                    JOptionPane.showMessageDialog(null, "La cédula debe tener 10 dígitos");
                    return false;
                }

            } else if (view.cmbCriterioBusqueda.getSelectedIndex() == 2){
                if(view.txtBusqueda.getText().length() < 3) {
                    JOptionPane.showMessageDialog(null, "El apellido debe tener al menos 3 caracteres");
                    return false;
                }else if(!view.txtBusqueda.getText().matches("[a-zA-Z]*")) {
                    JOptionPane.showMessageDialog(null, "El apellido debe contener solo letras");
                    return false;
                }
            }
        }

        return true;
    }
}
