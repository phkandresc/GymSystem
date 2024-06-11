import DAO.*;
import controller.*;
import view.*;
import DAO.*;

public class Main {
    public static void main(String[] args) {
        ListaSociosController listaSociosController = new ListaSociosController(new ListaSociosView());
        //RegistroSociosController registroSociosController = new RegistroSociosController(new RegistroSociosView());
    }
}
