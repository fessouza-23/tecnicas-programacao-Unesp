import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    private static final int PORTA = 12345;
    private static final int TAMANHO_TABULEIRO = 3;
    private static char[][] tabuleiro = new char[TAMANHO_TABULEIRO][TAMANHO_TABULEIRO];
    private static Socket jogadorX;
    private static Socket jogadorO;
    private static PrintWriter outX;
    private static PrintWriter outO;
    private static BufferedReader inX;
    private static BufferedReader inO;
    private static char jogadorAtual = 'X';

    public static void main(String[] args) {

        // Antes das conexões dos jogadores já inicializa o tabuleiro
        inicializarTabuleiro();

        try {
            // Cria o socket do servidor na porta identificada pela constante PORTA
            ServerSocket serverSocket = new ServerSocket(PORTA);
            System.out.println("Servidor iniciado. Aguardando conex�o...");

            // Aguarda a conexão do 1o jogador, que será o jogador X
            // E cria seus streams de entrada e saída
            jogadorX = serverSocket.accept();
            System.out.println("Jogador X conectado.");
            outX = new PrintWriter(jogadorX.getOutputStream(), true);
            inX = new BufferedReader(new InputStreamReader(jogadorX.getInputStream()));

            // Aguarda a conexão do 1o jogador, que será o jogador O
            // E cria seus streams de entrada e saída
            jogadorO = serverSocket.accept();
            System.out.println("Jogador O conectado.");
            outO = new PrintWriter(jogadorO.getOutputStream(), true);
            inO = new BufferedReader(new InputStreamReader(jogadorO.getInputStream()));

            // Envia as mensagens para os respectivos jogadores.
            outX.println("X");
            outO.println("O");

            // Método que implementa toda a lógica do jogo
            jogar();

            jogadorX.close();
            jogadorO.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Inicializa o tabuleiro com -, indicando que ainda nenhum jogador escolheu a posição
    private static void inicializarTabuleiro() {
        for (int i = 0; i < TAMANHO_TABULEIRO; i++) {
            for (int j = 0; j < TAMANHO_TABULEIRO; j++) {
                tabuleiro[i][j] = '-';
            }
        }
    }

    private static void jogar() throws IOException {
        while (true) {
            // Mostra no terminal quem deve jogar (X ou O)
            // Essa variável inicia com X
            outX.println(jogadorAtual);
            outO.println(jogadorAtual);
            // Mostra o tabuleiro para os jogadores
            enviarTabuleiro();

            // Lê a jogada do jogadorAtual (X ou O)
            String linha = null;
            if (jogadorAtual == 'X') {
                linha = inX.readLine();
            } else if (jogadorAtual == 'O') {
                linha = inO.readLine();
            }

            // Se o servidor recebeu algun dado (linha), anota a jogada no tabuleiro
            // Depois, verifica se o jogo terminou (vitoria de um dos jogadores ou empate)
            if (linha != null) {
                String[] partes = linha.split(",");
                int row = Integer.parseInt(partes[0]);
                int col = Integer.parseInt(partes[1]);

                if (tabuleiro[row][col] == '-' && jogadorAtual == 'X') {
                    tabuleiro[row][col] = 'X';
                    jogadorAtual = 'O';
                } else if (tabuleiro[row][col] == '-' && jogadorAtual == 'O') {
                    tabuleiro[row][col] = 'O';
                    jogadorAtual = 'X';
                }

                if (verificarVitoria('X')) {
                    outX.println("Voc� venceu!");
                    outO.println("Voc� perdeu!");
                    enviarTabuleiro();
                    break;
                } else if (verificarVitoria('O')) {
                    outO.println("Voc� venceu!");
                    outX.println("Voc� perdeu!");
                    enviarTabuleiro();
                    break;
                } else if (verificarEmpate()) {
                    outX.println("Empate!");
                    outO.println("Empate!");
                    enviarTabuleiro();
                    break;
                }
                else
                {
                    outX.println("Continue");
                    outO.println("Continue");
                }
            }
        }
    }

    // Mostra o tabuleiro para os jogadores
    private static void enviarTabuleiro() {
        for (int i = 0; i < TAMANHO_TABULEIRO; i++) {
            String sb = "";
            for (int j = 0; j < TAMANHO_TABULEIRO; j++) {
                sb += tabuleiro[i][j] + " ";
            }
            outX.println(sb);
            outO.println(sb);
        }
    }

    // Verifica se ocorreu Vitoria de algum jogador.
    private static boolean verificarVitoria(char jogador) {
        // Verifica linhas
        for (int i = 0; i < TAMANHO_TABULEIRO; i++) {
            if (tabuleiro[i][0] == jogador && tabuleiro[i][1] == jogador && tabuleiro[i][2] == jogador) {
                return true;
            }
        }

        // Verifica colunas
        for (int j = 0; j < TAMANHO_TABULEIRO; j++) {
            if (tabuleiro[0][j] == jogador && tabuleiro[1][j] == jogador && tabuleiro[2][j] == jogador) {
                return true;
            }
        }

        // Verifica diagonais
        if ((tabuleiro[0][0] == jogador && tabuleiro[1][1] == jogador && tabuleiro[2][2] == jogador) ||
                (tabuleiro[0][2] == jogador && tabuleiro[1][1] == jogador && tabuleiro[2][0] == jogador)) {
            return true;
        }

        return false;
    }

    // Verifica se ocorreu empate
    private static boolean verificarEmpate() {
        for (int i = 0; i < TAMANHO_TABULEIRO; i++) {
            for (int j = 0; j < TAMANHO_TABULEIRO; j++) {
                if (tabuleiro[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }
}