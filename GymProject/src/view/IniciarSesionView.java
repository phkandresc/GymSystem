package view;

import javax.swing.*;

public class IniciarSesionView extends JFrame {

    public javax.swing.JPanel background;
    public javax.swing.JPanel btnIniciarSesion;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel4;
    public javax.swing.JLabel jLabel5;
    public javax.swing.JLabel jLabel6;
    public javax.swing.JLabel jLabel7;
    public javax.swing.JLabel jLabel8;
    public javax.swing.JSeparator jSeparator1;
    public javax.swing.JSeparator jSeparator2;
    public javax.swing.JTextField txtContrasena;
    public javax.swing.JTextField txtUsuario;
    public javax.swing.JPanel panelIniciarSesion;

    public IniciarSesionView() {
        background = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        panelIniciarSesion = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtContrasena = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        btnIniciarSesion = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        background.setBackground(new java.awt.Color(255, 255, 255));
        background.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/background.png"))); // NOI18N
        jLabel1.setPreferredSize(new java.awt.Dimension(775, 680));
        background.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 680));

        panelIniciarSesion.setBackground(new java.awt.Color(255, 255, 255));
        panelIniciarSesion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("jLabel2");
        panelIniciarSesion.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 90, -1, -1));

        jLabel4.setFont(new java.awt.Font("Poppins Medium", 0, 26)); // NOI18N
        jLabel4.setText("Iniciar Sesion");
        panelIniciarSesion.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 180, 40));

        jLabel3.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        jLabel3.setText("Contrasena");
        panelIniciarSesion.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, -1, -1));

        txtContrasena.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        txtContrasena.setText("Ingrese su contrasena");
        txtContrasena.setBorder(null);
        txtContrasena.setPreferredSize(new java.awt.Dimension(127, 25));

        panelIniciarSesion.add(txtContrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 300, -1));
        panelIniciarSesion.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 360, 10));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/iconoContrasena.png"))); // NOI18N
        panelIniciarSesion.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, -1, -1));

        jLabel6.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        jLabel6.setText("Usuario");
        panelIniciarSesion.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/iconoMail.png"))); // NOI18N
        panelIniciarSesion.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, -1, -1));

        txtUsuario.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        txtUsuario.setText("Ingrese su usuario");
        txtUsuario.setBorder(null);
        txtUsuario.setPreferredSize(new java.awt.Dimension(127, 25));

        panelIniciarSesion.add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 300, -1));
        panelIniciarSesion.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 360, 10));

        btnIniciarSesion.setBackground(new java.awt.Color(242, 68, 5));
        btnIniciarSesion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Poppins Medium", 0, 15)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Iniciar Sesion");
        btnIniciarSesion.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, -1, 20));

        panelIniciarSesion.add(btnIniciarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 490, 360, 40));

        background.add(panelIniciarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 60, 380, 560));

        getContentPane().add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 680));

        pack();
        this.setLocationRelativeTo(null);
    }

    private void initComponents() {

        background = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        panelIniciarSesion = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtContrasena = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        btnIniciarSesion = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        background.setBackground(new java.awt.Color(255, 255, 255));
        background.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/background.png"))); // NOI18N
        jLabel1.setPreferredSize(new java.awt.Dimension(775, 680));
        background.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 680));

        panelIniciarSesion.setBackground(new java.awt.Color(255, 255, 255));
        panelIniciarSesion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("jLabel2");
        panelIniciarSesion.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 90, -1, -1));

        jLabel4.setFont(new java.awt.Font("Poppins Medium", 0, 26)); // NOI18N
        jLabel4.setText("Iniciar Sesion");
        panelIniciarSesion.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 180, 40));

        jLabel3.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        jLabel3.setText("Contrasena");
        panelIniciarSesion.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, -1, -1));

        txtContrasena.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        txtContrasena.setText("Ingrese su contrasena");
        txtContrasena.setBorder(null);
        txtContrasena.setPreferredSize(new java.awt.Dimension(127, 25));

        panelIniciarSesion.add(txtContrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 300, -1));
        panelIniciarSesion.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 360, 10));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/iconoContrasena.png"))); // NOI18N
        panelIniciarSesion.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, -1, -1));

        jLabel6.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        jLabel6.setText("Usuario");
        panelIniciarSesion.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/iconoMail.png"))); // NOI18N
        panelIniciarSesion.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, -1, -1));

        txtUsuario.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        txtUsuario.setText("Ingrese su usuario");
        txtUsuario.setBorder(null);
        txtUsuario.setPreferredSize(new java.awt.Dimension(127, 25));

        panelIniciarSesion.add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 300, -1));
        panelIniciarSesion.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 360, 10));

        btnIniciarSesion.setBackground(new java.awt.Color(242, 68, 5));
        btnIniciarSesion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Poppins Medium", 0, 15)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Iniciar Sesion");
        btnIniciarSesion.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, -1, 20));

        panelIniciarSesion.add(btnIniciarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 490, 360, 40));

        background.add(panelIniciarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 60, 380, 560));

        getContentPane().add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 680));

        pack();
    }



}
