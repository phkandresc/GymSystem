import DAO.*;
import controller.*;
import view.*;
import DAO.*;

public class Main {
    public static void main(String[] args) {
        IniciarSesionController iniciarSesionController = new IniciarSesionController(new IniciarSesionView(), new AdministradorDAO());

    }
}
