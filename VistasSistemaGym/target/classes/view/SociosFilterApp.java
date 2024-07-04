/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

public class SociosFilterApp extends JFrame {

    private final JTextField tfNombre;

    private JTextField tfApellido, tfCedula, tfEmail, tfTelefono, tfDireccion, tfFechaNacimiento;
    private JButton btnBuscar;
    private JTable table;
    private DefaultTableModel tableModel;

    public SociosFilterApp() {
        setTitle("Buscar Socios");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelFiltros = new JPanel(new GridLayout(8, 2, 5, 5));
        
        panelFiltros.add(new JLabel("Nombre:"));
        tfNombre = new JTextField();
        panelFiltros.add(tfNombre);
        
        panelFiltros.add(new JLabel("Apellido:"));
        tfApellido = new JTextField();
        panelFiltros.add(tfApellido);
        
        panelFiltros.add(new JLabel("Cédula:"));
        tfCedula = new JTextField();
        panelFiltros.add(tfCedula);
        
        panelFiltros.add(new JLabel("Email:"));
        tfEmail = new JTextField();
        panelFiltros.add(tfEmail);
        
        panelFiltros.add(new JLabel("Teléfono:"));
        tfTelefono = new JTextField();
        panelFiltros.add(tfTelefono);
        
        panelFiltros.add(new JLabel("Dirección:"));
        tfDireccion = new JTextField();
        panelFiltros.add(tfDireccion);
        
        panelFiltros.add(new JLabel("Fecha de Nacimiento:"));
        tfFechaNacimiento = new JTextField();
        panelFiltros.add(tfFechaNacimiento);

        btnBuscar = new JButton("Buscar");
        panelFiltros.add(btnBuscar);

        add(panelFiltros, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new String[]{"ID", "Cédula", "Nombre", "Apellido", "Email", "Teléfono", "Dirección", "Fecha de Nacimiento", "Fecha de Creación", "Fecha de Actualización"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SociosFilterApp().setVisible(true);
        });
    }
}
