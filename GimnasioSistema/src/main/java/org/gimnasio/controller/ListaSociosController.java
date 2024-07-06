package org.gimnasio.controller;

import org.gimnasio.model.Socio;
import org.gimnasio.service.SocioService;
import org.gimnasio.view.ListaSociosView;
import org.gimnasio.render.MultiLineTableCellRenderer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;

public class ListaSociosController extends WindowController implements ActionListener, ItemListener, MouseListener, WindowFocusListener {
    private final ListaSociosView view;
    private final SocioService socioService;
    private Socio socioSeleccionado;

    public ListaSociosController() {
        this.view = new ListaSociosView();
        this.socioService = new SocioService();
        view.btnFiltrar.addActionListener(this);
        view.cmbCriterioBusqueda.addItemListener(this);
        view.jtSocios.addMouseListener(this);
        view.jtSocios.setDefaultRenderer(String.class, new MultiLineTableCellRenderer());
        view.ButtonEliminar.addActionListener(this);
        view.ButtonModificar.addActionListener(this);
        view.jPanel1.addMouseListener(this);
        view.TextFieldBusqueda.addActionListener(this);
        view.addWindowFocusListener(this);
        view.setVisible(true);
        cargarLista();
    }

    private void cargarLista() {
        try {
            List<Socio> listaSocios = socioService.obtenerTodosSocios();
            cargarDatosTabla(listaSocios);
        } catch (Exception e) {
            System.out.println("Error al cargar los datos: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al cargar los datos: " + e.getMessage());
        }
    }

    public void cargarDatosTabla(List<Socio> listaSocios) {
        DefaultTableModel model = (DefaultTableModel) view.jtSocios.getModel();
        model.setRowCount(0);
        for (Socio socio : listaSocios) {
            model.addRow(new Object[]{
                    socio.getCedula(),
                    socio.getNombre(),
                    socio.getApellido(),
                    socio.getEmail(),
                    socio.getNumeroTelefono(),
                    socio.getDireccion(),
                    socio.getFechaNacimiento().toString()
            });
        }
        view.lblRegistrosEncontrados.setText("Registros encontrados: " + listaSocios.size());
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            view.TextFieldBusqueda.setText("");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == view.ButtonEliminar) {
            eliminarSocio();
        } else if(e.getSource() == view.btnFiltrar) {
            filtrarSocios();
        }else if(e.getSource() == view.TextFieldBusqueda){
            filtrarSocios();
        }else if(e.getSource() == view.ButtonModificar){
            if(socioSeleccionado != null){
                new AdministrarSociosController(socioSeleccionado);
            }else{
                JOptionPane.showMessageDialog(null, "No se ha seleccionado ningún socio");
            }
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == view.jtSocios) {
            int filaSeleccionada = view.jtSocios.getSelectedRow();
            if (filaSeleccionada != -1) {
                try {
                    socioSeleccionado = socioService.buscarSocioPorCedula(view.jtSocios.getValueAt(filaSeleccionada, 0).toString());
                    view.ButtonEliminar.setEnabled(true);
                    view.ButtonModificar.setEnabled(true);
                    JOptionPane.showMessageDialog(null, "Socio seleccionado:\n" + socioSeleccionado.getNombre() + " " + socioSeleccionado.getApellido());
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        }else if (e.getSource() == view.jPanel1){
            view.ButtonEliminar.setEnabled(false);
            view.ButtonModificar.setEnabled(false);
            socioSeleccionado = null;
        }
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

    private void filtrarSocios() {
        try {
            if (view.cmbCriterioBusqueda.getSelectedIndex() == 0) {
                List<Socio> listaSocios = socioService.obtenerSociosPorCriterio("id", view.TextFieldBusqueda.getText());
                cargarDatosTabla(listaSocios);
            } else if (view.cmbCriterioBusqueda.getSelectedIndex() == 1) {
                List<Socio> listaSocios = socioService.obtenerSociosPorCriterio("cedula", view.TextFieldBusqueda.getText());
                cargarDatosTabla(listaSocios);
            } else if (view.cmbCriterioBusqueda.getSelectedIndex() == 2) {
                List<Socio> listaSocios = socioService.obtenerSociosPorCriterio("apellido", view.TextFieldBusqueda.getText());
                cargarDatosTabla(listaSocios);
            } else if (view.cmbCriterioBusqueda.getSelectedIndex() == 3) {
                //obtener socios por mes de registro
                List<Socio> listaSocios = socioService.obtenerSociosPorMesRegistro(obtenerMesDeBusqueda());
                cargarDatosTabla(listaSocios);
            }else if (view.cmbCriterioBusqueda.getSelectedIndex() == 4) {
                //obtener socios por año de nacimiento
                List<Socio> listaSocios = socioService.obtenerSociosPorAñoNacimiento(view.TextFieldBusqueda.getText());
                cargarDatosTabla(listaSocios);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al filtrar los socios: " + ex.getMessage());
        }
    }

    private void eliminarSocio() {
        try {
            int confirmacion = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar el socio y su membresía asociada?", "Eliminar Socio", JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION && socioSeleccionado != null) {
                socioService.eliminarSocio(socioSeleccionado);
                JOptionPane.showMessageDialog(null, "Socio eliminado correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "No se ha seleccionado ningún socio");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el socio: " + ex.getMessage());
        } finally {
            cargarLista();
        }
    }


    private String obtenerMesDeBusqueda() {
        String mes = "";
        switch(view.TextFieldBusqueda.getText().toLowerCase()){
            case "enero":
                mes = "01";
                break;
            case "febrero":
                mes = "02";
                break;
            case "marzo":
                mes = "03";
                break;
            case "abril":
                mes = "04";
                break;
            case "mayo":
                mes = "05";
                break;
            case "junio":
                mes = "06";
                break;
            case "julio":
                mes = "07";
                break;
            case "agosto":
                mes = "08";
                break;
            case "septiembre":
                mes = "09";
                break;
            case "octubre":
                mes = "10";
                break;
            case "noviembre":
                mes = "11";
                break;
            case "diciembre":
                mes = "12";
                break;
        }
        return mes;
    }

    @Override
    public void windowGainedFocus(WindowEvent e) {
        cargarLista();
    }

    public static void main(String[] args) {
        new ListaSociosController();
    }
}
