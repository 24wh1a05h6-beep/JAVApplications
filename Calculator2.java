package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator2 extends JFrame implements ActionListener {
    private JTextField display;
    private String currentInput = "";
    private double firstOperand = 0;
    private String operator = "";

    public Calculator2() {
        setTitle("Simple Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 4, 5, 5));
        String[] buttons = {
            "7", "8", "9", "+",
            "4", "5", "6", "-",
            "1", "2", "3", "*",
            "0", "%", "=", "C"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.matches("\\d")) {
            currentInput += command;
            display.setText(currentInput);
        } else if (command.matches("[+\\-*/%]")) {
            try {
                firstOperand = Double.parseDouble(currentInput);
                operator = command;
                currentInput = "";
                display.setText("");
            } catch (NumberFormatException ex) {
                display.setText("Error");
            }
        } else if (command.equals("=")) {
            try {
                double secondOperand = Double.parseDouble(currentInput);
                double result = 0;

                switch (operator) {
                    case "+": result = firstOperand + secondOperand; break;
                    case "-": result = firstOperand - secondOperand; break;
                    case "*": result = firstOperand * secondOperand; break;
                    case "/":
                    case "%":
                        if (secondOperand == 0) {
                            display.setText("Divide by Zero");
                            return;
                        }
                        result = operator.equals("/") ? firstOperand / secondOperand : firstOperand % secondOperand;
                        break;
                }

                display.setText(String.valueOf(result));
                currentInput = String.valueOf(result);
            } catch (NumberFormatException ex) {
                display.setText("Error");
            }
        } else if (command.equals("C")) {
            currentInput = "";
            firstOperand = 0;
            operator = "";
            display.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Calculator2());
    }
}