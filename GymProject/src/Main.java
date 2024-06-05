import model.*;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        // Crear un objeto DatabaseManager
        DatabaseManager dbManager = new DatabaseManager();

        // Crear un objeto GestorDeSocios
        GestorDeSocios gestorDeSocios = new GestorDeSocios();

        Socio nuevoSocio =new Socio.SocioBuild()
                .nombre("Andres")
                .apellido("Coraizaca")
                .cedula("1720034212")
                .direccion("Calle 1")
                .email("a@a.com")
                .numeroTelefono("0987654321")
                .fechaNacimiento(new Date())
                .build();

        // Agregar el socio a la base de datos
        gestorDeSocios.agregarSocio(nuevoSocio);

        Membresia nuevaMembresia = new Membresia.MembresiaBuilder()
                .setTipo("Mensual")
                .setFechaInicio("2021-01-01")
                .setFechaFin("2021-01-31")
                .setCosto(20.0)
                .setEstado("Activa")
                .build();
        gestorDeSocios.agregarMembresia(nuevaMembresia);


        // Imprimir un mensaje para confirmar que el socio se agregó correctamente
        System.out.println("Socio agregado correctamente");
    }
}
