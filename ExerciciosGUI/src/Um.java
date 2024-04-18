import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

public class Um extends JFrame {
    private JTextArea textArea;

    public Um() {
        super("Editor de Texto");
        initializeComponents();
    }

    private void initializeComponents() {
        setLayout(new GridLayout(1, 2));

        JPanel leftPanel = new JPanel(new GridLayout(4, 1));
        JPanel rightPanel = new JPanel(new BorderLayout());

        JButton openButton = new JButton("Abrir");
        JButton saveButton = new JButton("Salvar");
        JButton saveAsButton = new JButton("Salvar Como");
        JButton closeButton = new JButton("Fechar");

        // Importante criar o textArea antes de passá-lo aos listeners para dar certo
        textArea = new JTextArea();
        openButton.addActionListener(new OpenAction());
        saveButton.addActionListener(new SaveAction());
        saveAsButton.addActionListener(new SaveAsAction());
        closeButton.addActionListener(new CloseAction());

        leftPanel.add(openButton);
        leftPanel.add(saveButton);
        leftPanel.add(saveAsButton);
        leftPanel.add(closeButton);

        JScrollPane scrollPane = new JScrollPane(textArea);
        rightPanel.add(scrollPane, BorderLayout.CENTER);

        add(leftPanel);
        add(rightPanel);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private class OpenAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(Um.this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                try (Scanner scanner = new Scanner(new FileInputStream(selectedFile))) {
                    textArea.setText(""); // Limpa a área de texto antes de anexar
                    while (scanner.hasNextLine()) {
                        textArea.append(scanner.nextLine() + "\n");
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(Um.this, "Não foi possível ler o arquivo: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private class SaveAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            saveToFile(false); // Chama função de salvar passando 'false' para não forçar 'Salvar Como'
        }
    }

    private class SaveAsAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            saveToFile(true); // Sempre mostra a janela de escolha de arquivo
        }
    }

    private void saveToFile(boolean forceDialog) {
        JFileChooser fileChooser = new JFileChooser();
        if (forceDialog || fileChooser.getSelectedFile() == null) {
            int userSelection = fileChooser.showSaveDialog(Um.this);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                try (FileOutputStream out = new FileOutputStream(fileToSave)) {
                    out.write(textArea.getText().getBytes());
                    JOptionPane.showMessageDialog(Um.this, "Arquivo salvo com sucesso!", "Arquivo Salvo", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(Um.this, "Erro ao salvar o arquivo: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private class CloseAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    public static void main(String args[]) {
        new Um();
    }
}
