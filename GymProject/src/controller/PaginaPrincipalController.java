package controller;

import DataAccessObject.GimnasioDAO;
import DataAccessObject.SocioDAO;
import model.Gimnasio;
import view.PaginaPrincipalView;

import java.awt.event.*;

public class PaginaPrincipalController implements ActionListener, MouseListener, FocusListener {
    private PaginaPrincipalView paginaPrincipalView = PaginaPrincipalView.getInstance();
    private static PaginaPrincipalController instance;

    private PaginaPrincipalController() {
        paginaPrincipalView.setLocationRelativeTo(null);
        paginaPrincipalView.btnRegistrarNuevoSocio.addActionListener(this);
        paginaPrincipalView.btnVerSocios.addActionListener(this);
        paginaPrincipalView.btnRegistrarMembresia.addActionListener(this);
    }

    public static PaginaPrincipalController getInstance() {
        if (instance == null) {
            instance = new PaginaPrincipalController();
        }
        return instance;
    }

    public void setInformacionGimnasio() {
        try {
            GimnasioDAO gimnasioDAO = new GimnasioDAO();
            Gimnasio gimnasio = gimnasioDAO.obtenerGimnasioPorId(1);
            paginaPrincipalView.lblNombreGimnasio.setText(gimnasio.getNombre());
            paginaPrincipalView.lblDireccion.setText("<html>"+ gimnasio.getDireccion()+"</html>");
            paginaPrincipalView.lblTelefono.setText(gimnasio.getTelefono());
            paginaPrincipalView.lblCorreoElectronico.setText(gimnasio.getEmail());
            paginaPrincipalView.lblNumSocios.setText(String.valueOf(SocioDAO.obtenerNumeroSocios()));
            paginaPrincipalView.lblNumMembresias.setText(String.valueOf(SocioDAO.obtenerNumeroSocios()));

        } catch (Exception e) {
            System.out.println("Error al obtener la informacion del gimnasio");
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == paginaPrincipalView.btnRegistrarNuevoSocio) {
            paginaPrincipalView.dispose();
            RegistroSociosController registroSociosController = new RegistroSociosController();
        } else if (e.getSource() == paginaPrincipalView.btnVerSocios) {
            paginaPrincipalView.dispose();
            ListaSociosController listaSociosController = new ListaSociosController();
        } else if (e.getSource() == paginaPrincipalView.btnRegistrarMembresia) {
            paginaPrincipalView.dispose();
            RegistroMembresiaController registroMembresiaController = new RegistroMembresiaController();
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

    public void mostrarPaginaPrincipal() {
        setInformacionGimnasio();
        paginaPrincipalView.setVisible(true);
    }

    public void cerrarPaginaPrincipal() {
        paginaPrincipalView.setVisible(false);
    }
}
