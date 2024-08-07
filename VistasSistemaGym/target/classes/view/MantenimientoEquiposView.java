/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

/**
 *
 * @author ASUS
 */
public class MantenimientoEquiposView extends javax.swing.JFrame {

    /**
     * Creates new form MantenimientoEquiposView
     */
    public MantenimientoEquiposView() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        dcFechaProcedimiento = new com.toedter.calendar.JDateChooser();
        cmbEstado = new javax.swing.JComboBox<>();
        txtEncargado = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cmbEspacio = new javax.swing.JComboBox<>();
        btnCrear = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtEquipos = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtEquipoSeleccionado = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cmbProcedimiento = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtMantenimientos = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jtReparaciones = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Mantenimiento y reparacion de equipos");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dcFechaProcedimiento.setDateFormatString("yyyy-MM-dd");
        dcFechaProcedimiento.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jPanel1.add(dcFechaProcedimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 400, 180, 27));

        cmbEstado.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        cmbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pendiente", "Completado" }));
        jPanel1.add(cmbEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 350, 180, 27));

        txtEncargado.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jPanel1.add(txtEncargado, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 190, 180, 27));

        jLabel2.setFont(new java.awt.Font("Poppins Light", 1, 14)); // NOI18N
        jLabel2.setText("Procedimiento:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 90, 120, 30));

        cmbEspacio.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jPanel1.add(cmbEspacio, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, 220, 27));

        btnCrear.setBackground(new java.awt.Color(242, 68, 5));
        btnCrear.setFont(new java.awt.Font("Poppins Medium", 0, 15)); // NOI18N
        btnCrear.setForeground(new java.awt.Color(255, 255, 255));
        btnCrear.setText("Mantener/Reparar");
        btnCrear.setBorder(null);
        jPanel1.add(btnCrear, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 490, 350, 27));

        jLabel10.setFont(new java.awt.Font("Poppins Medium", 0, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Mantenimiento y reparacion de equipos");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1210, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/barraTitulo.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1230, 60));

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setBorder(null);

        jtEquipos.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jtEquipos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nombre", "Descripcion", "Estado", "Espacio"
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
        jScrollPane2.setViewportView(jtEquipos);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 350, 400));

        jLabel3.setFont(new java.awt.Font("Poppins Light", 1, 14)); // NOI18N
        jLabel3.setText("Historial de reparaciones:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 100, 200, -1));

        jLabel4.setFont(new java.awt.Font("Poppins Light", 1, 14)); // NOI18N
        jLabel4.setText("<html>Fecha de procedimiento:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 390, 150, 40));

        jLabel5.setFont(new java.awt.Font("Poppins Light", 1, 14)); // NOI18N
        jLabel5.setText("Equipo seleccionado:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 140, 160, 20));

        txtEquipoSeleccionado.setEditable(false);
        txtEquipoSeleccionado.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jPanel1.add(txtEquipoSeleccionado, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 140, 180, -1));

        jLabel6.setFont(new java.awt.Font("Poppins Light", 1, 14)); // NOI18N
        jLabel6.setText("Descripcion:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 240, 160, 20));

        cmbProcedimiento.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        cmbProcedimiento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mantenimiento", "Reparacion" }));
        jPanel1.add(cmbProcedimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 90, 180, 27));

        jLabel7.setFont(new java.awt.Font("Poppins Light", 1, 14)); // NOI18N
        jLabel7.setText("Estado:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 350, 110, 20));

        jLabel8.setFont(new java.awt.Font("Poppins Light", 1, 14)); // NOI18N
        jLabel8.setText("Encargado:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 190, 160, 20));

        txtDescripcion.setColumns(20);
        txtDescripcion.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setRows(5);
        jScrollPane1.setViewportView(txtDescripcion);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 240, 180, 90));

        jtMantenimientos.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jtMantenimientos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Equipo", "Descripcion", "Fecha", "Encargado", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jtMantenimientos);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 360, 410, 150));

        jLabel9.setFont(new java.awt.Font("Poppins Light", 1, 14)); // NOI18N
        jLabel9.setText("Lista de equipos:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 135, -1));

        jLabel11.setFont(new java.awt.Font("Poppins Light", 1, 14)); // NOI18N
        jLabel11.setText("Historial de mantenimientos:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 320, 220, -1));

        jtReparaciones.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jtReparaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Equipo", "Descripcion", "Fecha", "Encargado", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jtReparaciones);

        jPanel1.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 140, 410, 150));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1230, 550));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(MantenimientoEquiposView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MantenimientoEquiposView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MantenimientoEquiposView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MantenimientoEquiposView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MantenimientoEquiposView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnCrear;
    public javax.swing.JComboBox<String> cmbEspacio;
    public javax.swing.JComboBox<String> cmbEstado;
    public javax.swing.JComboBox<String> cmbProcedimiento;
    public com.toedter.calendar.JDateChooser dcFechaProcedimiento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    public javax.swing.JTable jtEquipos;
    public javax.swing.JTable jtMantenimientos;
    public javax.swing.JTable jtReparaciones;
    public javax.swing.JTextArea txtDescripcion;
    public javax.swing.JTextField txtEncargado;
    public javax.swing.JTextField txtEquipoSeleccionado;
    // End of variables declaration//GEN-END:variables
}
