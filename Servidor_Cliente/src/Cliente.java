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
            System.out.println("Conectando ao servidor...");
            socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("Conectado ao servidor");

            // Receber o símbolo do jogador
            meuSimbolo = in.readLine().charAt(0);
            simboloOponente = (meuSimbolo == 'X') ? 'O' : 'X';
            System.out.println("Você é o jogador: " + meuSimbolo);

            // Configurar a interface gráfica
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    configurarInterfaceGrafica();
                }
            });

            // Iniciar a thread para receber as atualizações do servidor
            new Thread(new ReceberAtualizacoes()).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void configurarInterfaceGrafica() {
        System.out.println("Configurando a interface gráfica...");
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
        System.out.println("Interface gráfica configurada e visível.");
    }

    private class ReceberAtualizacoes implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    String mensagem = in.readLine();
                    System.out.println("Mensagem recebida do servidor: " + mensagem);
                    if (mensagem.equals("X") || mensagem.equals("O")) {
                        // É a vez desse jogador
                        continuarJogando(mensagem);
                    } else if (mensagem.equals("Você venceu!") || mensagem.equals("Você perdeu!") || mensagem.equals("Empate!")) {
                        // Fim do jogo
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                JOptionPane.showMessageDialog(frame, mensagem);
                            }
                        });
                        break;
                    } else if (mensagem.equals("Continue")) {
                        // Continuar o jogo
                        continue;
                    } else {
                        // Atualizar o tabuleiro
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                atualizarTabuleiro(mensagem);
                            }
                        });
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void continuarJogando(String mensagem) {
            boolean ativar = (mensagem.charAt(0) == meuSimbolo);
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            botoes[i][j].setEnabled(ativar && botoes[i][j].getText().equals("-"));
                        }
                    }
                }
            });
        }

        private void atualizarTabuleiro(String mensagem) {
            String[] linhas = mensagem.split("\n");
            for (int i = 0; i < 3; i++) {
                String[] colunas = linhas[i].split(" ");
                for (int j = 0; j < 3; j++) {
                    botoes[i][j].setText(colunas[j]);
                }
            }
        }
    }

    public static void main(String[] args) {
        new Cliente();
    }
}