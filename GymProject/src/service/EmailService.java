package service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
            message.setText(content);

            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void enviarCorreoNuevoSocio(String to, String nombre, String apellido, String id) {
        String subject = "Bienvenido a nuestro gimnasio";
        String content = "Hola " + nombre + " " + apellido + ",\n\n" +
                "Bienvenido a nuestro gimnasio. Tu número de socio es: " + id + "\n\n" +
                "Saludos,\n" +
                "Gimnasio";
        sendEmail(to, subject, content);
    }
}
