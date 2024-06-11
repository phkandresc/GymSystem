package controller;

import model.Socio;
import service.SocioService;

import view.ListaSociosView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ListaSociosController {
    private ListaSociosView view;
    private SocioService service;

    public ListaSociosController(ListaSociosView view) {
        this.view = view;
        this.service = new SocioService();
        view.setLocationRelativeTo(null);
        view.setVisible(true);
        cargarLista();
    }

    private void cargarLista() {
        try {
            List<Socio> listaSocios = service.obtenerTodosSocios();
            cargarDatosTabla(listaSocios);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar los datos: " + e.getMessage());
        }
    }

    public void cargarDatosTabla(List<Socio> listaSocios) {
        DefaultTableModel model = (DefaultTableModel) view.jtSocios.getModel();
        model.setRowCount(0); // Limpiar la tabla antes de añadir los datos
        for (Socio socio : listaSocios) {
            model.addRow(new Object[] {
                    socio.getCedula(),
                    socio.getNombre(),
                    socio.getApellido(),
                    socio.getEmail(),
                    socio.getNumeroTelefono(),
                    socio.getDireccion(),
                    socio.getFechaNacimiento()
            });
        }
    }

}
