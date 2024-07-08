package org.gimnasio.controller;

import org.gimnasio.model.Clase;
import org.gimnasio.model.Socio;
import org.gimnasio.service.ClasesService;
import org.gimnasio.service.SocioService;
import org.gimnasio.view.ReservaClaseView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

public class ReservaClaseController extends WindowController implements ActionListener, ItemListener {
    private ReservaClaseView view;
    private Socio socioSeleccionado;
    private List<Clase> listaClases;
    private Clase claseSeleccionada;
    private ClasesService clasesService;
    private SocioService socioService;

    public ReservaClaseController() {
        this.view = new ReservaClaseView();
        this.clasesService = new ClasesService();
        this.socioService = new SocioService();
        view.btnBuscar.addActionListener(this);
        view.btnReservar.addActionListener(this);
        view.cmbClases.addItemListener(this);
        view.setVisible(true);
        cargarClases();
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.btnBuscar) {
            buscarSocio();
        } else if (e.getSource() == view.btnReservar) {
            if (socioSeleccionado == null) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un socio");
                return;
            }
            try {
                if(clasesService.realizarReservaEnClase(socioSeleccionado, claseSeleccionada)){
                    JOptionPane.showMessageDialog(null, "Clase reservada correctamente");
                    vaciarFormulario();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al reservar la clase");
                    vaciarFormulario();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al reservar la clase" + ex.getMessage());
                vaciarFormulario();
            }
        }

    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == view.cmbClases) {
            int index = view.cmbClases.getSelectedIndex();
            if (index >= 0) {
                claseSeleccionada = listaClases.get(index);
                view.lblNombreClase.setText(claseSeleccionada.getNombre());
                view.lblDescripcion.setText("<html>"+claseSeleccionada.getDescripcion()+"</html>");
                view.lblPrecioTotal.setText("$"+String.valueOf(claseSeleccionada.getCosto()));
            }
        }
    }

    private void cargarClases(){
        view.cmbClases.removeAllItems();
        listaClases = clasesService.obtenerListaDeClases();
        for (Clase clase : listaClases) {
            view.cmbClases.addItem(clase.getNombre());
        }
    }

    private void buscarSocio() {
        String textoBusqueda = view.txtBusqueda.getText();
        try {
            if(view.cmbCriterioBusqueda.getSelectedIndex() == 0) {
                vaciarFormulario();
                socioSeleccionado = socioService.buscarSocioPorId(Integer.parseInt(textoBusqueda));
                cargarSocioEnFormulario(socioSeleccionado);
            } else if(view.cmbCriterioBusqueda.getSelectedIndex() == 1) {
                vaciarFormulario();
                socioSeleccionado = socioService.buscarSocioPorCedula(textoBusqueda);
                cargarSocioEnFormulario(socioSeleccionado);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar el socio" + ex.getMessage());
        }
    }


    private void vaciarFormulario(){
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
