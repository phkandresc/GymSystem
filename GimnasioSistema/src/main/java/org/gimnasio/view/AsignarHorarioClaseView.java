package org.gimnasio.view;


import java.util.Calendar;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

/**
 *
 * @author kacor
 */
public class AsignarHorarioClaseView extends javax.swing.JFrame {
    SpinnerDateModel model;

    /**
     * Creates new form AsignarHorarioClaseView
     */
    public AsignarHorarioClaseView() {
        initComponents();


    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        bg = new javax.swing.JPanel();
        btnModificarHorario = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtHorarios = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cmbEspacios = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cmbEspacioClase = new javax.swing.JComboBox<>();
        txtInstructor = new javax.swing.JTextField();
        jspHoraFin = new javax.swing.JSpinner();
        jspHoraInicio = new javax.swing.JSpinner();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cmbClase = new javax.swing.JComboBox<>();
        btnAsignarHorario = new javax.swing.JButton();
        btnEliminarHorario = new javax.swing.JButton();
        cmbDia = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtHorarioClase = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Horarios de Clase");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnModificarHorario.setBackground(new java.awt.Color(242, 98, 15));
        btnModificarHorario.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        btnModificarHorario.setForeground(new java.awt.Color(255, 255, 255));
        btnModificarHorario.setText("Modificar horario");
        btnModificarHorario.setBorder(null);
        btnModificarHorario.setEnabled(false);
        bg.add(btnModificarHorario, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 530, 150, 30));

        jScrollPane1.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N

        jtHorarios.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jtHorarios.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null}
                },
                new String [] {
                        "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"
                }
        ) {
            Class[] types = new Class [] {
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                    false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtHorarios.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jScrollPane1.setViewportView(jtHorarios);

        bg.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 590, 360));

        jLabel10.setFont(new java.awt.Font("Poppins Medium", 0, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Horarios");
        bg.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 910, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/barraTitulo.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        bg.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 930, 60));

        cmbEspacios.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        bg.add(cmbEspacios, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 80, 125, 27));

        jLabel3.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        jLabel3.setText("Ver horario por espacio:");
        bg.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        jLabel4.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        jLabel4.setText("Espacio:");
        bg.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 90, -1, -1));

        jLabel5.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        jLabel5.setText("Dia:");
        bg.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 410, -1, -1));

        jLabel6.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        jLabel6.setText("Clase:");
        bg.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 140, -1, -1));

        jLabel7.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        jLabel7.setText("<html>Hora de fin:</html>");
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bg.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 460, 60, 40));

        cmbEspacioClase.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        bg.add(cmbEspacioClase, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 90, 130, 27));

        txtInstructor.setEditable(false);
        txtInstructor.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        bg.add(txtInstructor, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 190, 150, 27));

        SpinnerDateModel model1 = new SpinnerDateModel();
        model1.setCalendarField(Calendar.MINUTE); // Permite incrementar por minutos
        jspHoraFin.setModel(model1);
        jspHoraFin.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        JSpinner.DateEditor editor1 = new JSpinner.DateEditor(jspHoraFin, "HH:mm");
        jspHoraFin.setEditor(editor1);
        bg.add(jspHoraFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 470, 60, -1));

        SpinnerDateModel model = new SpinnerDateModel();
        model.setCalendarField(Calendar.MINUTE); // Permite incrementar por minutos
        jspHoraInicio.setModel(model);
        jspHoraInicio.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        JSpinner.DateEditor editor = new JSpinner.DateEditor(jspHoraInicio, "HH:mm");
        jspHoraInicio.setEditor(editor);
        bg.add(jspHoraInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 470, 60, -1));

        jLabel8.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        jLabel8.setText("<html>Hora de inicio:</html>");
        jLabel8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bg.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 460, 60, 40));

        jLabel9.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        jLabel9.setText("Entrenador:");
        bg.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 190, -1, -1));

        cmbClase.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        bg.add(cmbClase, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 140, 130, 27));

        btnAsignarHorario.setBackground(new java.awt.Color(242, 98, 15));
        btnAsignarHorario.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        btnAsignarHorario.setForeground(new java.awt.Color(255, 255, 255));
        btnAsignarHorario.setText("Asignar horario");
        btnAsignarHorario.setBorder(null);
        bg.add(btnAsignarHorario, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 530, 150, 30));

        btnEliminarHorario.setBackground(new java.awt.Color(242, 98, 15));
        btnEliminarHorario.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        btnEliminarHorario.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarHorario.setText("Eliminar horario");
        btnEliminarHorario.setBorder(null);
        btnEliminarHorario.setEnabled(false);
        bg.add(btnEliminarHorario, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 530, 150, 30));

        cmbDia.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        cmbDia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo" }));
        bg.add(cmbDia, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 410, 130, 27));

        jtHorarioClase.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jtHorarioClase.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null}
                },
                new String [] {
                        "Dia", "Hora Inicio", "Hora Fin", "Espacio"
                }
        ) {
            Class[] types = new Class [] {
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                    false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtHorarioClase.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jScrollPane2.setViewportView(jtHorarioClase);

        bg.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 240, 240, 150));

        getContentPane().add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 930, 580));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AsignarHorarioClaseView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AsignarHorarioClaseView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AsignarHorarioClaseView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AsignarHorarioClaseView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AsignarHorarioClaseView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JPanel bg;
    public javax.swing.JButton btnAsignarHorario;
    public javax.swing.JButton btnEliminarHorario;
    public javax.swing.JButton btnModificarHorario;
    public javax.swing.JComboBox<String> cmbClase;
    public javax.swing.JComboBox<String> cmbDia;
    public javax.swing.JComboBox<String> cmbEspacioClase;
    public javax.swing.JComboBox<String> cmbEspacios;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JSpinner jspHoraFin;
    public javax.swing.JSpinner jspHoraInicio;
    public javax.swing.JTable jtHorarioClase;
    public javax.swing.JTable jtHorarios;
    public javax.swing.JTextField txtInstructor;
    // End of variables declaration
}