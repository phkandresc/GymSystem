package controller;

import DAO.SocioDAO;
import DAO.TipoMembresiaDAO;
import model.Socio;
import model.TipoMembresia;
import view.RegistroSociosView;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class RegistroSociosController implements ActionListener, MouseListener, FocusListener, ItemListener {
    private RegistroSociosView registroSociosView;
    private SocioDAO socioDAO;
    private TipoMembresiaDAO tipoMembresiaDAO;
    private TipoMembresia tipoMembresia;
    private Socio socio;
    private List<TipoMembresia> listaTiposMembresia;

    public RegistroSociosController(RegistroSociosView registroSociosView) {
        this.registroSociosView = registroSociosView;
        registroSociosView.setVisible(true);
        registroSociosView.cmbTipoMembresia.addItemListener(this);
        cargarTiposMembresia();
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

    @Override
    public void actionPerformed(ActionEvent e) {
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
            registroSociosView.lblDescripcionMembresia.setText(String.valueOf(tipoMembresia.getDescripcion()));
        }
    }
}
