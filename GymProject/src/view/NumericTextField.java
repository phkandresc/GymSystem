package view;
import javax.swing.*;
import javax.swing.text.*;

public class NumericTextField extends JTextField {

    public NumericTextField() {
        super();
        ((AbstractDocument) getDocument()).setDocumentFilter(new NumericDocumentFilter());
    }

    class NumericDocumentFilter extends DocumentFilter {
        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            if (string.matches("\\d*(\\.\\d*)?")) {
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            if (text.matches("\\d*(\\.\\d*)?")) {
                super.replace(fb, offset, length, text, attrs);
            }
        }
    }
}
