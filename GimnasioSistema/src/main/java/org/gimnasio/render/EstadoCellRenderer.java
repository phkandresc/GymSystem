package org.gimnasio.render;

import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import javax.swing.JTable;

public class EstadoCellRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        String estado = (String) table.getModel().getValueAt(row, 5); // Asumiendo que la columna "estado" es la sexta columna (índice 5)
        if ("activo".equals(estado)) {
            component.setBackground(Color.GREEN);
        } else if ("inactivo".equals(estado)) {
            component.setBackground(Color.RED);
        } else if("por caducar".equals(estado)){
            component.setBackground(Color.WHITE);
        }
        return component;
    }
}