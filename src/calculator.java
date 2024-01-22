import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class calculator implements ActionListener {

    JFrame jf;
    JLabel displLabel;
    JButton oneButton, twoButton, threeButton, fourButton, fiveButton, sixButton, sevenButton, eightButton, nineButton;
    JButton plusButton, minButton, divButton, mulButton, zeroButton, equalButton, acButton, delButton, doubleZeButton, dotButton,exitButton;

    boolean isOperatorClicked = false;
    String oldValue;
    String newValue;

    Double num1, num2, result;
    char opr;

    public calculator() {
        jf = new JFrame("Calculator");
        jf.setLayout(null);
        jf.setSize(450, 800);

        displLabel = new JLabel("");
        displLabel.setBounds(25, 40, 390, 40);
        displLabel.setBackground(Color.white);
        displLabel.setOpaque(true);
        displLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        jf.getContentPane().setBackground(Color.black);
        jf.add(displLabel);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        acButton = createStyledButton("AC", 30, 133, 83, 83, 25);
        delButton = createStyledButton("DL", 130, 133, 83, 83, 25);
        
        exitButton = createStyledButton("Exit", 230, 133, 83, 83, 25);
        divButton = createStyledButton("/", 330, 133, 83, 83, 25);

        sevenButton = createStyledButton("7", 30, 233, 83, 83, 40);
        eightButton = createStyledButton("8", 130, 233, 83, 83, 40);
        nineButton = createStyledButton("9", 230, 233, 83, 83, 40);
        mulButton = createStyledButton("x", 330, 233, 83, 83, 40);

        fourButton = createStyledButton("4", 30, 333, 83, 83, 40);
        fiveButton = createStyledButton("5", 130, 333, 83, 83, 40);
        sixButton = createStyledButton("6", 230, 333, 83, 83, 40);
        minButton = createStyledButton("-", 330, 333, 83, 83, 40);

        oneButton = createStyledButton("1", 30, 433, 83, 83, 40);
        twoButton = createStyledButton("2", 130, 433, 83, 83, 40);
        threeButton = createStyledButton("3", 230, 433, 83, 83, 40);
        plusButton = createStyledButton("+", 330, 433, 83, 83, 40);

        zeroButton = createStyledButton("0", 30, 533, 83, 83, 40);
        doubleZeButton = createStyledButton("00", 130, 533, 83, 83, 40);
        dotButton = createStyledButton(".", 230, 533, 83, 83, 40);
        equalButton = createStyledButton("=", 330, 533, 83, 83, 40);
    }
//refactor Button using createStyledButton
    private JButton createStyledButton(String label, int x, int y, int width, int height, int fontSize) {
        JButton button = new JButton(label);
        button.setFont(new Font("Arial", Font.PLAIN, fontSize));
        button.addActionListener(this);
        button.setBounds(x, y, width, height);
        button.setBackground(Color.WHITE);
        button.setForeground(Color.BLACK);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setFocusPainted(false);

        jf.add(button);
        return button;
    }

    public static void main(String[] args) {
        new calculator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == oneButton || e.getSource() == twoButton || e.getSource() == threeButton ||
            e.getSource() == fourButton || e.getSource() == fiveButton || e.getSource() == sixButton ||
            e.getSource() == sevenButton || e.getSource() == eightButton || e.getSource() == nineButton ||
            e.getSource() == zeroButton || e.getSource() == doubleZeButton || e.getSource() == dotButton) {

            handleNumberButtonClick(e);
        } else if (e.getSource() == acButton) {
            handleACButtonClick();
        } else if (e.getSource() == delButton) {
            handleDeleteButtonClick();
        } else if (e.getSource() == exitButton) {
            System.exit(0);
        }
         else if (e.getSource() == plusButton || e.getSource() == minButton ||
                   e.getSource() == mulButton || e.getSource() == divButton ) {

            handleOperatorButtonClick(e);
        } else if (e.getSource() == equalButton) {
            handleEqualButtonClick();
        }
    }

    private void handleNumberButtonClick(ActionEvent e) {
        JButton sourceButton = (JButton) e.getSource();
        if (isOperatorClicked) {
            displLabel.setText(sourceButton.getText());
            isOperatorClicked = false;
        } else {
            displLabel.setText(displLabel.getText() + sourceButton.getText());
        }
    }

    private void handleACButtonClick() {
        displLabel.setText("");
        num1 = null;
        num2 = null;
        isOperatorClicked = false;
    }

    private void handleDeleteButtonClick() {
        String currentText = displLabel.getText();
        if (!currentText.isEmpty()) {
            displLabel.setText(currentText.substring(0, currentText.length() - 1));
        }
    }

    private void handleOperatorButtonClick(ActionEvent e) {
        isOperatorClicked = true;
        oldValue = displLabel.getText();
        opr = e.getActionCommand().charAt(0);
    }

    private void handleEqualButtonClick() {
        newValue = displLabel.getText();

        if (num1 == null) {
            num1 = Double.parseDouble(oldValue);
        } else {
            num2 = Double.parseDouble(newValue);
            switch (opr) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case 'x':
                    result = num1 * num2;
                    break;
                case '/':
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        // Handle division by zero error if needed
                        result = 0.0;
                    }
                    break;
               
            }
            displLabel.setText(String.valueOf(result));
            num1 = result;
        }
        isOperatorClicked = false;
    }
}
