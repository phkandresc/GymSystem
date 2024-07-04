package org.gimnasio.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ProfilePictureForm extends JFrame {

    private JLabel pictureLabel;
    private JButton selectButton;

    public ProfilePictureForm() {
        setTitle("Registro de Usuario");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        pictureLabel = new JLabel();
        pictureLabel.setHorizontalAlignment(JLabel.CENTER);
        pictureLabel.setPreferredSize(new Dimension(200, 200));
        add(pictureLabel, BorderLayout.CENTER);

        selectButton = new JButton("Seleccionar Foto de Perfil");
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectProfilePicture();
            }
        });
        add(selectButton, BorderLayout.SOUTH);
    }

    private void selectProfilePicture() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            ImageIcon profileIcon = new ImageIcon(selectedFile.getAbsolutePath());
            // Escalar la imagen para que se ajuste al JLabel
            Image image = profileIcon.getImage().getScaledInstance(pictureLabel.getWidth(), pictureLabel.getHeight(), Image.SCALE_SMOOTH);
            pictureLabel.setIcon(new ImageIcon(image));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ProfilePictureForm().setVisible(true);
            }
        });
    }
}