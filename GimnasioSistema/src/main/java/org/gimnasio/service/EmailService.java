package org.gimnasio.service;

import org.gimnasio.model.Membresia;
import org.gimnasio.model.Socio;
import org.gimnasio.model.TipoMembresia;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class EmailService {

    private final String username = "kacoraizaca@gmail.com";
    private final String password = "ftbu wjjn ptpx odxx";

    private void sendEmail(String to, String subject, String content) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("tuCorreo@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setContent(content, "text/html");
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private String readHtmlFile(String filePath) {
        String content = "";
        try {
            content = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    public void enviarCorreoNuevoSocio(String to, String nombre, String apellido, String id) {
        String subject = "Bienvenido a nuestro gimnasio";
        String content = readHtmlFile("src/main/resources/templates/CorreoNuevoSocio.html");
        content = content.replace("{nombre}", nombre);
        content = content.replace("{apellido}", apellido);
        content = content.replace("{id}", id);
        sendEmail(to, subject, content);
    }

    public void enviarCorreoRegistroMembresia(Socio socio, Membresia membresia, TipoMembresia tipoMembresia) {
        String subject = "Bienvenido a nuestro gimnasio";
        String content = readHtmlFile("src/main/resources/templates/CorreoRegistroMembresia.html");
        content = content.replace("{nombre}", socio.getNombre());
        content = content.replace("{apellido}", socio.getApellido());
        content = content.replace("{tipo_membresia}", tipoMembresia.getNombre());
        content = content.replace("{duracion}", String.valueOf(tipoMembresia.getDuracion()));
        content = content.replace("{descripcion}", tipoMembresia.getDescripcion());
        content = content.replace("{precio}", String.valueOf(tipoMembresia.getPrecio()));
        content = content.replace("{fecha_inicio}", membresia.getFechaInicio().toString());
        content = content.replace("{fecha_fin}", membresia.getFechaFin().toString());
        sendEmail(socio.getEmail(), subject, content);
    }

    public static void main(String[] args) {
        EmailService emailService = new EmailService();
        emailService.enviarCorreoNuevoSocio("kacoraizaca@gmail.com", "Katherine", "Coraizaca", "1");
    }
}
