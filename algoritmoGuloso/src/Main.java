import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int[][] matriz = gerarMatriz(15, 15);
        imprimirMatriz(matriz);

        SolucaoGulosa resultadoGulosa = caminhoDeCustoMinimoGuloso(matriz);
        System.out.println("Custo mínimo (solução gulosa): " + resultadoGulosa.custo);
        System.out.println("Caminho (solução gulosa): " + resultadoGulosa.caminho);

        SolucaoBacktracking resultadoBacktracking = new SolucaoBacktracking();
        caminhoDeCustoMinimoBacktracking(matriz, 0, 0, 0, new StringBuilder(), resultadoBacktracking);
        System.out.println("Custo mínimo (backtracking): " + resultadoBacktracking.custoMinimo);
        System.out.println("Caminho (backtracking): " + resultadoBacktracking.caminhoMinimo);
    }

    public static int[][] gerarMatriz(int linhas, int colunas) {
        Random random = new Random();
        int[][] matriz = new int[linhas][colunas];
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                matriz[i][j] = random.nextInt(10) + 1; // Gera números aleatórios de 1 a 10
            }
        }
        return matriz;
    }

    public static void imprimirMatriz(int[][] matriz) {
        for (int[] linha : matriz) {
            for (int elemento : linha) {
                System.out.print(elemento + "\t");
            }
            System.out.println();
        }
    }

    public static SolucaoGulosa caminhoDeCustoMinimoGuloso(int[][] matriz) {
        if (matriz == null || matriz.length == 0 || matriz[0].length == 0) {
            return new SolucaoGulosa(0, "");
        }

        int m = matriz.length;
        int n = matriz[0].length;
        int i = 0, j = 0;
        int custoTotal = matriz[0][0];
        StringBuilder caminho = new StringBuilder();
        caminho.append("(").append(i).append(", ").append(j).append(")");

        while (i < m - 1 || j < n - 1) {
            if (i == m - 1) {
                j++;
            }
            else if (j == n - 1) {
                i++;
            }
            else {
                if (matriz[i][j + 1] < matriz[i + 1][j]) {
                    j++;
                } else {
                    i++;
                }
            }
            custoTotal += matriz[i][j];
            caminho.append(" -> (").append(i).append(", ").append(j).append(")");
        }

        return new SolucaoGulosa(custoTotal, caminho.toString());
    }

    public static void caminhoDeCustoMinimoBacktracking(int[][] matriz, int i, int j, int custoAtual, StringBuilder caminhoAtual, SolucaoBacktracking resultado) {
        int m = matriz.length;
        int n = matriz[0].length;

        if (i == m - 1 && j == n - 1) {
            custoAtual += matriz[i][j];
            caminhoAtual.append("(").append(i).append(", ").append(j).append(")");
            if (custoAtual < resultado.custoMinimo) {
                resultado.custoMinimo = custoAtual;
                resultado.caminhoMinimo = caminhoAtual.toString();
            }
            return;
        }

        if (i >= m || j >= n) {
            return;
        }

        custoAtual += matriz[i][j];
        caminhoAtual.append("(").append(i).append(", ").append(j).append(") -> ");

        caminhoDeCustoMinimoBacktracking(matriz, i, j + 1, custoAtual, new StringBuilder(caminhoAtual), resultado);

        caminhoDeCustoMinimoBacktracking(matriz, i + 1, j, custoAtual, new StringBuilder(caminhoAtual), resultado);
    }

    static class SolucaoGulosa {
        int custo;
        String caminho;

        SolucaoGulosa(int custo, String caminho) {
            this.custo = custo;
            this.caminho = caminho;
        }
    }

    static class SolucaoBacktracking {
        int custoMinimo = Integer.MAX_VALUE;
        String caminhoMinimo = "";
    }
}