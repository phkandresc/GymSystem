/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

/**
 *
 * @author kacor
 */
public class ListaSociosView extends javax.swing.JFrame {

    /**
     * Creates new form ListaSociosView
     */
    public ListaSociosView() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtSocios = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        cmbCriterioBusqueda = new javax.swing.JComboBox<>();
        TextFieldBusqueda = new javax.swing.JTextField();
        btnFiltrar = new javax.swing.JButton();
        lblRegistrosEncontrados = new javax.swing.JLabel();
        ButtonModificar = new javax.swing.JButton();
        ButtonEliminar = new javax.swing.JButton();

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/barraTitulo.png"))); // NOI18N
        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Lista de Socios");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Poppins Medium", 0, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Lista de Socios");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 770, 40));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/barraTitulo.png"))); // NOI18N
        jLabel2.setText("jLabel1");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 790, 60));

        jScrollPane1.setBorder(new javax.swing.border.MatteBorder(null));

        jtSocios.setFont(new java.awt.Font("Poppins Light", 0, 14)); // NOI18N
        jtSocios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Cedula", "Nombre", "Apellido", "Email", "Telefono", "Direccion", "Fecha de nacimiento"
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
        jtSocios.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jtSocios.setGridColor(new java.awt.Color(153, 153, 153));
        jtSocios.setName(""); // NOI18N
        jtSocios.setShowGrid(false);
        jtSocios.setShowHorizontalLines(true);
        jScrollPane1.setViewportView(jtSocios);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 710, 330));

        jLabel3.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        jLabel3.setText("Filtrar socios por:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, -1, 30));

        cmbCriterioBusqueda.setFont(new java.awt.Font("Poppins Light", 0, 14)); // NOI18N
        cmbCriterioBusqueda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "Cedula", "Apellido", "Mes de registro", "Año de nacimiento" }));
        jPanel1.add(cmbCriterioBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 90, 140, 30));

        TextFieldBusqueda.setFont(new java.awt.Font("Poppins Light", 0, 14)); // NOI18N
        jPanel1.add(TextFieldBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 90, 160, 30));

        btnFiltrar.setBackground(new java.awt.Color(242, 98, 15));
        btnFiltrar.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        btnFiltrar.setForeground(new java.awt.Color(255, 255, 255));
        btnFiltrar.setText("Filtrar");
        btnFiltrar.setBorder(null);
        jPanel1.add(btnFiltrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 90, 120, 30));

        lblRegistrosEncontrados.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        lblRegistrosEncontrados.setText("Registros encontrados: 0");
        jPanel1.add(lblRegistrosEncontrados, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 490, -1, -1));

        ButtonModificar.setBackground(new java.awt.Color(242, 98, 15));
        ButtonModificar.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        ButtonModificar.setForeground(new java.awt.Color(255, 255, 255));
        ButtonModificar.setText("Modificar");
        ButtonModificar.setBorder(null);
        ButtonModificar.setEnabled(false);
        jPanel1.add(ButtonModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 500, 160, 30));

        ButtonEliminar.setBackground(new java.awt.Color(242, 98, 15));
        ButtonEliminar.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        ButtonEliminar.setForeground(new java.awt.Color(255, 255, 255));
        ButtonEliminar.setText("Eliminar");
        ButtonEliminar.setBorder(null);
        ButtonEliminar.setEnabled(false);
        jPanel1.add(ButtonEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 500, 160, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
        );

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
            java.util.logging.Logger.getLogger(ListaSociosView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListaSociosView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListaSociosView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListaSociosView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListaSociosView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton ButtonEliminar;
    public javax.swing.JButton ButtonModificar;
    public javax.swing.JTextField TextFieldBusqueda;
    public javax.swing.JButton btnFiltrar;
    public javax.swing.JComboBox<String> cmbCriterioBusqueda;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    public javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable jtSocios;
    public javax.swing.JLabel lblRegistrosEncontrados;
    // End of variables declaration//GEN-END:variables
}
