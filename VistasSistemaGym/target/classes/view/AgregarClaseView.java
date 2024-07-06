/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

/**
 *
 * @author kacor
 */
public class AgregarClaseView extends javax.swing.JFrame {

    /**
     * Creates new form AgregarClaseView
     */
    public AgregarClaseView() {
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

        jScrollBar1 = new javax.swing.JScrollBar();
        bg = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtClases = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cmbEspacio = new javax.swing.JComboBox<>();
        cmbInstructor = new javax.swing.JComboBox<>();
        jsCupos = new javax.swing.JSpinner();
        jsCosto = new javax.swing.JSpinner();
        txtNombre = new javax.swing.JTextField();
        txtDescripcion = new javax.swing.JTextField();
        cmbTipoClase = new javax.swing.JComboBox<>();
        btnCrear = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Agregar una clase");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Poppins Medium", 0, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Clases");
        bg.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 10, 890, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/barraTitulo.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        bg.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 60));

        jtClases.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jtClases.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Tipo de Clase", "Nombre", "Descripcion", "Costo", "Cupos", "Instructor", "Espacio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
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
        jScrollPane1.setViewportView(jtClases);

        bg.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 80, 510, 320));

        jLabel2.setFont(new java.awt.Font("Poppins SemiBold", 0, 14)); // NOI18N
        jLabel2.setText("Tipo de clase:");
        bg.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 150, -1));

        jLabel3.setFont(new java.awt.Font("Poppins SemiBold", 0, 14)); // NOI18N
        jLabel3.setText("Nombre:");
        bg.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 150, -1));

        jLabel4.setFont(new java.awt.Font("Poppins SemiBold", 0, 14)); // NOI18N
        jLabel4.setText("Descripcion:");
        bg.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 160, 200, -1));

        jLabel5.setFont(new java.awt.Font("Poppins SemiBold", 0, 14)); // NOI18N
        jLabel5.setText("Costo:");
        bg.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 150, -1));

        jLabel6.setFont(new java.awt.Font("Poppins SemiBold", 0, 14)); // NOI18N
        jLabel6.setText("Cupos:");
        bg.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 240, 200, 20));

        jLabel7.setFont(new java.awt.Font("Poppins SemiBold", 0, 14)); // NOI18N
        jLabel7.setText("Instructor:");
        bg.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 80, 150, -1));

        jLabel8.setFont(new java.awt.Font("Poppins SemiBold", 0, 14)); // NOI18N
        jLabel8.setText("Espacio:");
        bg.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 150, -1));

        cmbEspacio.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        bg.add(cmbEspacio, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, 320, 27));

        cmbInstructor.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        bg.add(cmbInstructor, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 110, 150, 27));

        jsCupos.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jsCupos.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        bg.add(jsCupos, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 270, 150, 27));

        jsCosto.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jsCosto.setModel(new javax.swing.SpinnerNumberModel(0.0d, 0.0d, null, 0.5d));
        bg.add(jsCosto, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 150, 27));

        txtNombre.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        bg.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 150, 27));

        txtDescripcion.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        bg.add(txtDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 190, 150, 27));

        cmbTipoClase.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        bg.add(cmbTipoClase, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 150, 27));

        btnCrear.setBackground(new java.awt.Color(242, 98, 15));
        btnCrear.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        btnCrear.setForeground(new java.awt.Color(255, 255, 255));
        btnCrear.setText("Crear");
        btnCrear.setBorder(null);
        bg.add(btnCrear, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 430, 150, 30));

        btnModificar.setBackground(new java.awt.Color(242, 98, 15));
        btnModificar.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        btnModificar.setForeground(new java.awt.Color(255, 255, 255));
        btnModificar.setText("Modificar");
        btnModificar.setBorder(null);
        bg.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 430, 150, 30));

        btnEliminar.setBackground(new java.awt.Color(242, 98, 15));
        btnEliminar.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("Eliminar");
        btnEliminar.setBorder(null);
        bg.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 430, 150, 30));

        getContentPane().add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 490));

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
            java.util.logging.Logger.getLogger(AgregarClaseView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AgregarClaseView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AgregarClaseView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AgregarClaseView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AgregarClaseView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    public javax.swing.JButton btnCrear;
    public javax.swing.JButton btnEliminar;
    public javax.swing.JButton btnModificar;
    public javax.swing.JComboBox<String> cmbEspacio;
    public javax.swing.JComboBox<String> cmbInstructor;
    public javax.swing.JComboBox<String> cmbTipoClase;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JSpinner jsCosto;
    public javax.swing.JSpinner jsCupos;
    public javax.swing.JTable jtClases;
    public javax.swing.JTextField txtDescripcion;
    public javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}