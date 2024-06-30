package org.gimnasio.controller;

import org.gimnasio.model.Socio;

import javax.swing.*;
import java.sql.Date;
import java.util.regex.Pattern;

public class Validator {
    public static boolean validarTextFields(JTextField[] textFields) {
        for (JTextField textField : textFields) {
            if (textField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor llene todos los campos");
                return false;
            }
        }
        return true;
    }

    public static boolean validarSocio(Socio socio){
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
        }else if(!validarTelefono(socio.getNumeroTelefono())){
            JOptionPane.showMessageDialog(null, "El número de teléfono debe tener 10 dígitos");
            return false;
        }else if(!validarFechaNacimiento(socio.getFechaNacimiento())) {
            JOptionPane.showMessageDialog(null, "La fecha de nacimiento no puede estar vacía");
            return false;
        }
        return true;
    }

    private static boolean validarCedula(String cedula) {

        return cedula.length() == 10 && cedula.matches("[0-9]*");
    }

    private static boolean validarTelefono(String telefono) {
        return telefono.length() == 10;
    }

    private boolean validarDireccion(String direccion) {
        return direccion.length() >= 5;
    }

    private static boolean validarFechaNacimiento(Date fechaNacimiento) {

        return fechaNacimiento != null;
    }

    private static boolean validarEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        if (email == null) {
            return false;
        }
        return pattern.matcher(email).matches();
    }
}
