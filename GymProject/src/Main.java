import controller.*;
import view.*;
import DataAccessObject.AdministradorDAO;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ListaSociosController listaSociosController = new ListaSociosController(new ListaSociosView());
    }
}
