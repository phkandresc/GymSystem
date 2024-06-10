import DAO.AdministradorDAO;
import controller.IniciarSesionController;
import view.IniciarSesionView;
import DAO.*;

public class Main {
    public static void main(String[] args) {
        IniciarSesionController iniciarSesionController = new IniciarSesionController(new IniciarSesionView(), new AdministradorDAO());
    }   
}
