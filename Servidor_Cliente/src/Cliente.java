import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {

    private static final String SERVER_ADDRESS = "127.0.0.1"; // endereço do servidor
    private static final int SERVER_PORT = 12345; // porta do servidor
    private char meuSimbolo;
    private char simboloOponente;
    private JFrame frame = new JFrame("Jogo da Velha");
    private JButton[][] botoes = new JButton[3][3];
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public Cliente() {
        try {
            // Conectar ao servidor
            socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Receber o símbolo do jogador
            meuSimbolo = in.readLine().charAt(0);
            simboloOponente = (meuSimbolo == 'X') ? 'O' : 'X';

            // Configurar a interface gráfica
            frame.setLayout(new GridLayout(3, 3));
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    botoes[i][j] = new JButton("-");
                    botoes[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                    final int row = i;
                    final int col = j;
                    botoes[i][j].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (botoes[row][col].getText().equals("-")) {
                                out.println(row + "," + col);
                            }
                        }
                    });
                    frame.add(botoes[i][j]);
                }
            }

            frame.setSize(400, 400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

            // Iniciar a thread para receber as atualizações do servidor
            new Thread(new ReceberAtualizacoes()).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class ReceberAtualizacoes implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    String mensagem = in.readLine();
                    if (mensagem.equals("X") || mensagem.equals("O")) {
                        // É a vez desse jogador
                        continuarJogando(mensagem);
                    } else if (mensagem.equals("Você venceu!") || mensagem.equals("Você perdeu!") || mensagem.equals("Empate!")) {
                        // Fim do jogo
                        JOptionPane.showMessageDialog(frame, mensagem);
                        break;
                    } else if (mensagem.equals("Continue")) {
                        // Continuar o jogo
                        continue;
                    } else {
                        // Atualizar o tabuleiro
                        atualizarTabuleiro(mensagem);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void continuarJogando(String mensagem) {
            // Ativar ou desativar botões dependendo se é a vez do jogador
            boolean ativar = (mensagem.charAt(0) == meuSimbolo);
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    botoes[i][j].setEnabled(ativar && botoes[i][j].getText().equals("-"));
                }
            }
        }

        private void atualizarTabuleiro(String mensagem) {
            for (int i = 0; i < 3; i++) {
                String[] linha = mensagem.split(" ");
                for (int j = 0; j < 3; j++) {
                    botoes[i][j].setText(linha[j]);
                }
            }
        }
    }

    public static void main(String[] args) {
        new Cliente();
    }
}