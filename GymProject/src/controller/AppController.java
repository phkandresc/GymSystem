package controller;

public class AppController {
    private IniciarSesionController iniciarSesionController;
    private PaginaPrincipalController paginaPrincipalController;
    private RegistroSociosController registroSociosController;
    private ListaSociosController listaSociosController;

    public AppController() {
        iniciarSesionController = new IniciarSesionController();
    }

    public void mostrarPaginaPrincipal() {

    }

    public void mostrarRegistroSocios() {
    }

    public void mostrarListaSocios() {

    }

    public static void main(String[] args) {
        AppController appController = new AppController();
    }
}
