/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package includeClass;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;

/**
 *
 * @author Admin
 */
public class NumberComma {
    public static void numberFormat(JTextField txtField, boolean decimalNumber) {
        if (!decimalNumber) {
            NumberInput.intValidator(txtField);
        } else {
            NumberInput.doubleValidator(txtField);
        }

        if (txtField.getText().isEmpty()) {
            return;
        }
        String inputNum = txtField.getText().replace(",", "");
        String commafiedNum = "";
        Character firstChar = inputNum.charAt(0);

        if (firstChar == '+' || firstChar == '-') {
            commafiedNum = commafiedNum + Character.toString(firstChar);
            inputNum = inputNum.replaceAll("[-\\+]", "");
        }

        String[] splittedNum = inputNum.split("\\.");
        String decimalNum = "";
        if (splittedNum.length == 2) {
            inputNum = splittedNum[0];
            decimalNum = "." + splittedNum[1];
        }

        int numLength = inputNum.length();
        for (int i = 0; i < numLength; i++) {
            if ((numLength - i) % 3 == 0 && i != 0) {
                commafiedNum += ",";
            }
            commafiedNum += inputNum.charAt(i);
        }
        if (!Character.isDigit(commafiedNum.charAt(0))) {
            commafiedNum = commafiedNum.substring(1);
        }
        txtField.setText(commafiedNum + decimalNum);
    }

}

class NumberInput {

    public static void intValidator(JTextField txtField) {
        txtField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_ENTER))) {
                    e.consume();
                }
            }
        });
    }

    public static void doubleValidator(JTextField txtField) {
        txtField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (c == KeyEvent.VK_PERIOD && txtField.getText().contains(".") == true) {
                    e.consume();
                }
                if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_PERIOD) || (c == KeyEvent.VK_ENTER))) {
                    e.consume();
                }
            }
        });
    }
}
