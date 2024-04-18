import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tres extends JFrame {
    private JTextField textField1;
    private JTextField textField2;
    private JComboBox<String> comboBox;
    private JLabel resultLabel;

    Tres() {
        super("Calculadora");

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout());

        textField1 = new JTextField(10);
        topPanel.add(textField1);

        comboBox = new JComboBox<>(new String[]{"Soma", "Subtrai", "Multiplica", "Divide"});
        topPanel.add(comboBox);

        textField2 = new JTextField(10);
        topPanel.add(textField2);

        JLabel label1 = new JLabel("=");
        topPanel.add(label1);

        resultLabel = new JLabel("0");
        topPanel.add(resultLabel);

        mainPanel.add(topPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout());
        JButton calculateButton = new JButton("Calcular");
        bottomPanel.add(calculateButton);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateResult();
            }
        });

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void calculateResult() {
        try {
            double num1 = Double.parseDouble(textField1.getText());
            double num2 = Double.parseDouble(textField2.getText());
            double result = 0;
            String operation = comboBox.getSelectedItem().toString();

            switch (operation) {
                case "Soma":
                    result = num1 + num2;
                    break;
                case "Subtrai":
                    result = num1 - num2;
                    break;
                case "Multiplica":
                    result = num1 * num2;
                    break;
                case "Divide":
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        JOptionPane.showMessageDialog(this, "Não é possível dividir por zero.", "Erro de divisão", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    break;
            }
            resultLabel.setText(String.valueOf(result));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, insira números válidos.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String args[]) {
        new Tres();
    }
}
