import javax.swing.*;
import java.awt.*;

public class Quatro extends JFrame {
    Quatro() {
        super("Calculadora");

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());

        JTextField textField1 = new JTextField(10);
        topPanel.add(textField1);

        JComboBox<String> comboBox = new JComboBox<>(new String[]{"Soma", "Subtrai", "Multiplica", "Divide"});
        topPanel.add(comboBox);

        JTextField textField2 = new JTextField(10);
        topPanel.add(textField2);

        JLabel label1 = new JLabel("=");
        topPanel.add(label1);

        JLabel label2 = new JLabel("0");
        topPanel.add(label2);

        mainPanel.add(topPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton somaButton = new JButton("Soma");
        bottomPanel.add(somaButton);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String args[]) {
        new Quatro();
    }
}
