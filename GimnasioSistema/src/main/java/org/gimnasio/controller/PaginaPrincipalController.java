package org.gimnasio.controller;

import org.gimnasio.daos.GimnasioDAO;
import org.gimnasio.daos.SocioDAO;
import org.gimnasio.model.Gimnasio;
import org.gimnasio.service.MembresiaService;
import org.gimnasio.view.PaginaPrincipalView;

import java.awt.event.*;

public class PaginaPrincipalController implements ActionListener, MouseListener, FocusListener {
    private PaginaPrincipalView view = PaginaPrincipalView.getInstance();
    private static PaginaPrincipalController instance;
    private MembresiaService membresiaService;

    private PaginaPrincipalController() {
        view.setLocationRelativeTo(null);
        //Socios
        view.btnRegistrarNuevoSocio.addActionListener(this);
        view.btnVerSocios.addActionListener(this);
        view.btnAdministrarSocios.addActionListener(this);
        //Gestion de membresias
        view.btnRegistrarMembresia.addActionListener(this);
        view.btnAgregarTipoMembresia.addActionListener(this);
        view.btnListaMembresias.addActionListener(this);
        //Clases
        view.btnAgregarClase.addActionListener(this);
        view.btnReservarClase.addActionListener(this);
        view.btnProgramarHorarios.addActionListener(this);
        //Equipos
        view.btnMantenerEquipos.addActionListener(this);
        view.btnAdministrarEquipos.addActionListener(this);
        //Entrenador
        view.btnRegistrarEntrenador.addActionListener(this);
        //Espacios
        view.btnAdministrarEspacios.addActionListener(this);
        //ControlAcceso
        view.btnControlAcceso.addActionListener(this);
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
            view.lblNombreGimnasio.setText(gimnasio.getNombre());
            view.lblDireccion.setText("<html>"+ gimnasio.getDireccion()+"</html>");
            view.lblTelefono.setText(gimnasio.getTelefono());
            view.lblCorreoElectronico.setText(gimnasio.getEmail());
            view.lblNumSocios.setText(String.valueOf(SocioDAO.obtenerNumeroSocios()));
            view.lblNumMembresias.setText(String.valueOf(SocioDAO.obtenerNumeroSocios()));
            view.lblNumMaquinas.setText(String.valueOf(gimnasioDAO.numeroEquiposRegistrados()));
            view.lblNumEntrenadores.setText(String.valueOf(gimnasioDAO.numeroEntrenadoresRegistrados()));
            view.lblNumClases.setText(String.valueOf(gimnasioDAO.numeroClasesRegistradas()));
            view.lblNumGanancias.setText(String.valueOf(gimnasioDAO.obtenerIngresosTotales())+"$");

        } catch (Exception e) {
            System.out.println("Error al obtener la informacion del gimnasio");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Socios
        if (e.getSource() == view.btnRegistrarNuevoSocio) {
            view.dispose();
            new RegistroSociosController();
        } else if (e.getSource() == view.btnVerSocios) {
            view.dispose();
            new ListaSociosController();
        }else if(e.getSource() == view.btnAdministrarSocios){
            view.dispose();
            new AdministrarSociosController();
        }
        //Membresias
        if(e.getSource() == view.btnAgregarTipoMembresia){
            view.dispose();
            new TiposMembresiaController();
        }else if (e.getSource() == view.btnRegistrarMembresia) {
            view.dispose();
            new RegistroMembresiaController();
        } else if (e.getSource() == view.btnListaMembresias) {
            view.dispose();
            new ListaMembresiasController();
        }

        //Clases
        if(e.getSource() == view.btnAgregarClase){
            view.dispose();
            new AgregarClaseController();
        }else if(e.getSource() == view.btnReservarClase){
            view.dispose();
            new ReservaClaseController();
        } else if (e.getSource() == view.btnProgramarHorarios) {
            view.dispose();
            new AsignarHorarioClaseController();
        }

        //Equipos
        if(e.getSource() == view.btnMantenerEquipos) {
            view.dispose();
            new MantenimientoEquiposController();
        }else if(e.getSource() == view.btnAdministrarEquipos){
            view.dispose();
            new RegistroEquiposController();
        }

        //Entrenador
        if(e.getSource() == view.btnRegistrarEntrenador){
            view.dispose();
            new RegistroEntrenadorController();
        }

        //Espacios
        if(e.getSource() == view.btnAdministrarEspacios){
            view.dispose();
            new RegistroEspaciosController();
        }

        //Control de acceso
        if(e.getSource() == view.btnControlAcceso){
            view.dispose();
            new ControlAccesoController();
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
        view.setVisible(true);
    }

    public void cerrarPaginaPrincipal() {
        view.setVisible(false);
    }

    public static void main(String[] args) {
        PaginaPrincipalController.getInstance().mostrarPaginaPrincipal();
    }

}
