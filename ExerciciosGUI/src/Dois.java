import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dois extends JFrame {
    private JTextField textField1;
    private JTextField textField2;
    private JLabel resultLabel;

    Dois() {
        super("Calculadora");

        setLayout(new GridLayout(1, 5));

        JPanel textFielPanelLeft = new JPanel(new FlowLayout(FlowLayout.CENTER));
        textField1 = new JTextField(10);
        textFielPanelLeft.add(textField1);
        add(textFielPanelLeft);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 1));
        JButton somaButton = new JButton("Soma");
        JButton subtraiButton = new JButton("Subtrai");
        JButton multiplicaButton = new JButton("Multiplica");
        JButton divideButton = new JButton("Divide");

        // Adiciona os listeners para os botões de operação
        somaButton.addActionListener(new OperationListener('+'));
        subtraiButton.addActionListener(new OperationListener('-'));
        multiplicaButton.addActionListener(new OperationListener('*'));
        divideButton.addActionListener(new OperationListener('/'));

        buttonPanel.add(somaButton);
        buttonPanel.add(subtraiButton);
        buttonPanel.add(multiplicaButton);
        buttonPanel.add(divideButton);

        add(buttonPanel);

        JPanel textFielPanelRight = new JPanel(new FlowLayout(FlowLayout.CENTER));
        textField2 = new JTextField(10);
        textFielPanelRight.add(textField2);
        add(textFielPanelRight);

        JLabel label1 = new JLabel("=");
        add(label1);

        resultLabel = new JLabel("0");
        add(resultLabel);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Classe interna para lidar com as operações
    private class OperationListener implements ActionListener {
        private char operation;

        public OperationListener(char operation) {
            this.operation = operation;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Obtém os valores dos campos de texto
            double num1 = Double.parseDouble(textField1.getText());
            double num2 = Double.parseDouble(textField2.getText());
            double result = 0;

            // Realiza a operação de acordo com o botão pressionado
            switch (operation) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    // Trata a divisão por zero
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        JOptionPane.showMessageDialog(Dois.this, "Não é possível dividir por zero", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
            }

            // Exibe o resultado no label
            resultLabel.setText(Double.toString(result));
        }
    }

    public static void main(String args[]) {
        new Dois();
    }
}
