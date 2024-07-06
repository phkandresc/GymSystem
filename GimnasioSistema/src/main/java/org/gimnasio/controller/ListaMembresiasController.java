package org.gimnasio.controller;

import org.gimnasio.model.Membresia;
import org.gimnasio.render.EstadoCellRenderer;
import org.gimnasio.service.MembresiaService;
import org.gimnasio.view.ListaMembresiasView;
import org.gimnasio.render.MultiLineTableCellRenderer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

public class ListaMembresiasController extends WindowController implements ActionListener, ItemListener {
    private final ListaMembresiasView view;
    private MembresiaService membresiaService;

    public ListaMembresiasController() {
        this.view = new ListaMembresiasView();

        this.membresiaService = new MembresiaService();
        this.view.jtMembresias.setDefaultRenderer(String.class, new MultiLineTableCellRenderer());
        this.view.cmbCriterioBusqueda.addItemListener(this);
        this.view.btnFiltrar.addActionListener(this);
        cargarListaMembresias();
        this.view.setVisible(true);
    }

    private void cargarListaMembresias() {
        try {
            List<Membresia> listaMembresias = membresiaService.obtenerListaMembresias();
            cargarDatosTabla(listaMembresias);
        } catch (Exception e) {
            System.out.println("Error al cargar los datos: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al cargar los datos: " + e.getMessage());
        }
    }

    private void cargarDatosTabla(List<Membresia> listaMembresias) {
        DefaultTableModel model = (DefaultTableModel) view.jtMembresias.getModel();
        model.setRowCount(0);
        for (Membresia membresia : listaMembresias) {
            model.addRow(new Object[]{
                    membresia.getSocio().getCedula(),
                    membresia.getSocio().getNombre()+" "+membresia.getSocio().getApellido(),
                    membresia.getTipoMembresia().getNombre(),
                    membresia.getFechaInicio().toString(),
                    membresia.getFechaFin().toString(),
                    membresia.getEstado()
            });
        }
        view.lblRegistrosEncontrados.setText("Registros encontrados: " + listaMembresias.size());

        // Aplicar el MultiLineTableCellRenderer a todas las columnas excepto la de estado
        for (int i = 0; i < view.jtMembresias.getColumnCount(); i++) {
            if (i != 5) { // Asumiendo que la columna "estado" es la sexta columna (índice 5)
                view.jtMembresias.getColumnModel().getColumn(i).setCellRenderer(new MultiLineTableCellRenderer());
            }
        }
        // Aplicar el EstadoCellRenderer solo a la columna de estado
        view.jtMembresias.getColumnModel().getColumn(5).setCellRenderer(new EstadoCellRenderer());

    }


    public static void main(String[] args) {
        ListaMembresiasController listaMembresiasController = new ListaMembresiasController();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.btnFiltrar) {
            try {
                String estado = getEstado(view.cmbCriterioBusqueda.getSelectedIndex());
                if(!estado.isEmpty()){
                    List<Membresia> listaMembresias = membresiaService.obtenerMembresiasPorEstado(estado);
                    cargarDatosTabla(listaMembresias);
                }
            } catch (Exception ex) {
                System.out.println("Error al filtrar los datos: " + ex.getMessage());
            }
        }
    }

    public String getEstado(int estado){
        switch (estado){
            case 0:
                return "activo";
            case 1:
                return "por caducar";
            case 2:
                return "inactivo";
            default:
                return "";
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {

    }
}
