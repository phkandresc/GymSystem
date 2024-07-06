package org.gimnasio.render;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellRenderer;
import java.awt.Component;
import java.awt.Color;

public class MultiLineAndColorCellRenderer extends JTextArea implements TableCellRenderer {
    public MultiLineAndColorCellRenderer() {
        setLineWrap(true);
        setWrapStyleWord(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected) {
            setForeground(table.getSelectionForeground());
            setBackground(table.getSelectionBackground());
        } else {
            setForeground(table.getForeground());
            if (value != null && !value.toString().trim().isEmpty()) {
                setBackground(Color.YELLOW); // Color de fondo para celdas con contenido
            } else {
                setBackground(Color.WHITE); // Color de fondo para celdas sin contenido
            }
        }
        setText((value == null) ? "" : value.toString());
        adjustRowHeight(table, row, column);
        return this;
    }

    // Ajusta la altura de la fila basada en el contenido de la celda
    private void adjustRowHeight(JTable table, int row, int column) {
        int columnWidth = table.getColumnModel().getColumn(column).getWidth();
        setSize(columnWidth, Short.MAX_VALUE);
        int preferredHeight = getPreferredSize().height;
        if (table.getRowHeight(row) < preferredHeight) {
            table.setRowHeight(row, preferredHeight);
        }
    }
}