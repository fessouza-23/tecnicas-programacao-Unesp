import javax.swing.*;
import java.awt.*;

public class Dois extends JFrame {
    Dois() {
        super("Calculadora");

        setLayout(new GridLayout(1, 5));

        JPanel textFielPanelLeft = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JTextField textField1 = new JTextField(10);
        textFielPanelLeft.add(textField1);
        add(textFielPanelLeft);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 1));
        JButton somaButton = new JButton("Soma");
        JButton subtraiButton = new JButton("Subtrai");
        JButton multiplicaButton = new JButton("Multiplica");
        JButton divideButton = new JButton("Divide");

        buttonPanel.add(somaButton);
        buttonPanel.add(subtraiButton);
        buttonPanel.add(multiplicaButton);
        buttonPanel.add(divideButton);

        add(buttonPanel);

        JPanel textFielPanelRight = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JTextField textField2 = new JTextField(10);
        textFielPanelRight.add(textField2);
        add(textFielPanelRight);

        JLabel label1 = new JLabel("=");
        add(label1);

        JLabel label2 = new JLabel("0");
        add(label2);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String args[]) {
        new Dois();
    }
}
