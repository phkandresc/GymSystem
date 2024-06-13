import controller.*;
import view.*;
import DataAccessObject.AdministradorDAO;

public class Main {
    public static void main(String[] args) {
        IniciarSesionView iniciarSesionView = new IniciarSesionView();
        iniciarSesionView.initComponents();
        iniciarSesionView.setLocationRelativeTo(null);
        iniciarSesionView.setVisible(true);
    }
}
