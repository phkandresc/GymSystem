package org.gimnasio.view;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class MultiLineTableCellRenderer extends JTextArea implements TableCellRenderer {

    public MultiLineTableCellRenderer() {
        setLineWrap(true);
        setWrapStyleWord(true);
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected) {
            setForeground(table.getSelectionForeground());
            setBackground(table.getSelectionBackground());
        } else {
            setForeground(table.getForeground());
            setBackground(table.getBackground());
        }
        setFont(table.getFont());
        if (hasFocus) {
            setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
        } else {
            setBorder(null);
        }
        setText(value == null ? "" : value.toString());
        setSize(table.getColumnModel().getColumn(column).getWidth(), getPreferredSize().height);
        int height = Math.max(getPreferredSize().height, table.getRowHeight(row));
        if (table.getRowHeight(row) != height) {
            table.setRowHeight(row, height);
        }
        return this;
    }
}



