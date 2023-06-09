package com.calc;

import java.awt.*;
import java.awt.event.*;

public class CalculatorGUI extends Frame implements ActionListener {
	private TextField displayField;
    private Button[] numberButtons;
    private Button[] operatorButtons;
    private Button clearButton;
    private Button equalsButton;
    private double num1, num2, result;
    private String operator;
    private boolean operatorClicked;

    public CalculatorGUI() {
        setTitle("Calculator");
        setLayout(new BorderLayout());

        displayField = new TextField();
        displayField.setEditable(false);
        add(displayField, BorderLayout.NORTH);

        Panel buttonPanel = new Panel();
        buttonPanel.setLayout(new GridLayout(4, 4));

        numberButtons = new Button[10];
        for (int i = 0; i < numberButtons.length; i++) {
            numberButtons[i] = new Button(Integer.toString(i));
            numberButtons[i].addActionListener(this);
            buttonPanel.add(numberButtons[i]);
        }

        operatorButtons = new Button[4];
        operatorButtons[0] = new Button("+");
        operatorButtons[1] = new Button("-");
        operatorButtons[2] = new Button("*");
        operatorButtons[3] = new Button("/");
        for (int i = 0; i < operatorButtons.length; i++) {
            operatorButtons[i].addActionListener(this);
            buttonPanel.add(operatorButtons[i]);
        }

        clearButton = new Button("C");
        clearButton.addActionListener(this);
        buttonPanel.add(clearButton);

        equalsButton = new Button("=");
        equalsButton.addActionListener(this);
        buttonPanel.add(equalsButton);

        add(buttonPanel, BorderLayout.CENTER);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        setSize(300, 300);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String buttonLabel = e.getActionCommand();

        if (buttonLabel.equals("C")) {
            displayField.setText("");
            operatorClicked = false;
        } else if (buttonLabel.equals("=")) {
            num2 = Double.parseDouble(displayField.getText());

            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    result = num1 / num2;
                    break;
            }

            displayField.setText(Double.toString(result));
            operatorClicked = false;
        } else if (buttonLabel.equals("+") || buttonLabel.equals("-") || buttonLabel.equals("*")
                || buttonLabel.equals("/")) {
            operator = buttonLabel;
            num1 = Double.parseDouble(displayField.getText());
            operatorClicked = true;
        } else {
            if (operatorClicked) {
                displayField.setText("");
                operatorClicked = false;
            }

            displayField.setText(displayField.getText() + buttonLabel);
        }
    }

    public static void main(String[] args) {
        CalculatorGUI calculator = new CalculatorGUI();
    }
}
