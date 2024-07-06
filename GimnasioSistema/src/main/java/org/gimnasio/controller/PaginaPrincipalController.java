package org.gimnasio.controller;

import org.gimnasio.daos.GimnasioDAO;
import org.gimnasio.daos.SocioDAO;
import org.gimnasio.model.Gimnasio;
import org.gimnasio.service.MembresiaService;
import org.gimnasio.view.PaginaPrincipalView;

import java.awt.event.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PaginaPrincipalController implements ActionListener, MouseListener, FocusListener {
    private PaginaPrincipalView paginaPrincipalView = PaginaPrincipalView.getInstance();
    private static PaginaPrincipalController instance;
    private MembresiaService membresiaService;

    private PaginaPrincipalController() {
        paginaPrincipalView.setLocationRelativeTo(null);
        //Gestion de socios
        paginaPrincipalView.btnRegistrarNuevoSocio.addActionListener(this);
        paginaPrincipalView.btnVerSocios.addActionListener(this);
        paginaPrincipalView.btnAdministrarSocios.addActionListener(this);
        //Gestion de membresias
        paginaPrincipalView.btnRegistrarMembresia.addActionListener(this);
        paginaPrincipalView.btnAgregarTipoMembresia.addActionListener(this);
        paginaPrincipalView.btnListaMembresias.addActionListener(this);
        this.membresiaService = new MembresiaService();
        iniciarVerificacionDeMembresias();
    }

    private void iniciarVerificacionDeMembresias() {
        membresiaService.verificarYEnviarAlertas();
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
            new RegistroSociosController();
        } else if (e.getSource() == paginaPrincipalView.btnVerSocios) {
            paginaPrincipalView.dispose();
            new ListaSociosController();
        }else if(e.getSource() == paginaPrincipalView.btnAdministrarSocios){
            paginaPrincipalView.dispose();
            new AdministrarSociosController();
        }else if(e.getSource() == paginaPrincipalView.btnAgregarTipoMembresia){
            paginaPrincipalView.dispose();
            new TiposMembresiaController();
        }else if (e.getSource() == paginaPrincipalView.btnRegistrarMembresia) {
            paginaPrincipalView.dispose();
            new RegistroMembresiaController();
        } else if (e.getSource() == paginaPrincipalView.btnListaMembresias) {
            paginaPrincipalView.dispose();
            new ListaMembresiasController();
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

    public static void main(String[] args) {
        PaginaPrincipalController.getInstance().mostrarPaginaPrincipal();
    }

}
