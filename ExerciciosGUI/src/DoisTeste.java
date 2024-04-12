import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

class Calculator extends JFrame {
    private JTextField inputLeft = new JTextField(10);
    private JButton sumButton = new JButton("Soma");
    private JButton multiplicationButton = new JButton("Subtrai");
    private JButton subtractionButton = new JButton("Multiplica");
    private JButton divisionButton = new JButton("Divide");
    private JTextField inputRight = new JTextField(10);
    private JLabel resultLabel = new JLabel("= 0", JLabel.RIGHT);

    public Calculator() {
        super("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel leftPanel = new JPanel();
        JPanel middlePanel = new JPanel();
        JPanel rightPanel = new JPanel();

        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.X_AXIS));
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.X_AXIS));

        Dimension buttonSize = new Dimension(100, 30);
        sumButton.setPreferredSize(buttonSize);
        multiplicationButton.setPreferredSize(buttonSize);
        subtractionButton.setPreferredSize(buttonSize);
        divisionButton.setPreferredSize(buttonSize);

        sumButton.setMaximumSize(buttonSize);
        multiplicationButton.setMaximumSize(buttonSize);
        subtractionButton.setMaximumSize(buttonSize);
        divisionButton.setMaximumSize(buttonSize);

        leftPanel.setBorder(new EmptyBorder(0, 10, 0, 10));
        leftPanel.add(inputLeft);

        middlePanel.add(sumButton);
        middlePanel.add(multiplicationButton);
        middlePanel.add(subtractionButton);
        middlePanel.add(divisionButton);

        rightPanel.setBorder(new EmptyBorder(0, 10, 0, 10));
        rightPanel.add(inputRight);
        rightPanel.add(Box.createHorizontalStrut(10));
        rightPanel.add(resultLabel, BorderLayout.EAST);

        sumButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        multiplicationButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtractionButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        divisionButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        inputLeft.setMaximumSize(new Dimension(Integer.MAX_VALUE, inputLeft.getPreferredSize().height));
        inputRight.setMaximumSize(new Dimension(Integer.MAX_VALUE, inputRight.getPreferredSize().height));

        add(leftPanel, BorderLayout.WEST);
        add(middlePanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);

        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
