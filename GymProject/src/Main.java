import controller.IniciarSesionController;
import view.IniciarSesionView;
import dao.AdminDAO;

public class Main {
    public static void main(String[] args) {
        IniciarSesionController iniciarSesionController = new IniciarSesionController(new IniciarSesionView(), new AdminDAO());
    }
}
