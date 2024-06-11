package controller;

import DAO.GimnasioDAO;
import model.Gimnasio;
import view.PaginaPrincipalView;
import view.RegistroSociosView;

import java.awt.event.*;

public class PaginaPrincipalController implements ActionListener, MouseListener, FocusListener {
    private PaginaPrincipalView paginaPrincipalView;
    private GimnasioDAO gimnasioDAO;
    private Gimnasio gimnasio;

    public PaginaPrincipalController(PaginaPrincipalView paginaPrincipalView) {
        this.paginaPrincipalView = paginaPrincipalView;
        paginaPrincipalView.btnRegistrarNuevoSocio.addActionListener(this);
        paginaPrincipalView.setLocationRelativeTo(null);
        paginaPrincipalView.setVisible(true);
        setInformacionGimnasio();
    }

    public void setInformacionGimnasio() {

        try {
            gimnasioDAO = new GimnasioDAO();
            gimnasio = gimnasioDAO.obtenerGimnasioPorId(1);
            paginaPrincipalView.lblNombreGimnasio.setText(gimnasio.getNombre());
            paginaPrincipalView.lblDireccion.setText("<html>"+gimnasio.getDireccion()+"</html>");
            paginaPrincipalView.lblTelefono.setText(gimnasio.getTelefono());
            paginaPrincipalView.lblCorreoElectronico.setText(gimnasio.getEmail());
        } catch (Exception e) {
            System.out.println("Error al obtener la informacion del gimnasio");
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == paginaPrincipalView.btnRegistrarNuevoSocio) {
            paginaPrincipalView.dispose();
            new RegistroSociosController(new RegistroSociosView());
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
}
