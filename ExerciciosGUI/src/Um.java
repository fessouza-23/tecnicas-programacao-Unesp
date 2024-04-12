import javax.swing.*;
import java.awt.*;

public class Um extends JFrame {
    Um() {
        super("Editor de Texto");

        setLayout(new GridLayout(1, 2));

        JPanel left = new JPanel(new GridLayout(4, 1));
        JPanel right = new JPanel(new BorderLayout());

        left.add(new JButton("Abrir"));
        left.add(new JButton("Salvar"));
        left.add(new JButton("Salvar Como"));
        left.add(new JButton("Fechar"));

        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        right.add(scrollPane, BorderLayout.CENTER);

        add(left);
        add(right);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String args[]) {
        new Um();
    }
}
