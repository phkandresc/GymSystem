package controller;
import view.IniciarSesionView;
import model.Admin;

public class IniciarSesionController{
    private IniciarSesionView iniciarSesionView;
    private Admin admin;

    public IniciarSesionController(IniciarSesionView iniciarSesionView, Admin admin){
        this.iniciarSesionView = iniciarSesionView;
        this.admin = admin;
    }

    public void iniciarSesion(){
        String usuario = iniciarSesionView.getUsuario();
        String contrasena = iniciarSesionView.getContrasena();

        if(usuario.equals(admin.getUsuario()) && contrasena.equals(admin.getContrasena())){
            iniciarSesionView.mostrarMensaje("Inicio de sesion exitoso");
        }else{
            iniciarSesionView.mostrarMensaje("Usuario o contrasena incorrectos");
        }
    }

}
