import java.util.Scanner;

public class Main {

    private static int N = 8;
    private static int[][] board;
    private static boolean[] rows;
    private static boolean[] d1;
    private static boolean[] d2;
    private static int maxSum;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int T = scanner.nextInt();

        for (int t = 0; t < T; t++) {
            board = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    board[i][j] = scanner.nextInt();
                }
            }

            rows = new boolean[N];
            d1 = new boolean[2 * N];
            d2 = new boolean[2 * N];
            maxSum = 0;

            solve(0, 0);

            System.out.println(maxSum);
        }

        scanner.close();
    }

    private static void solve(int col, int currentSum) {
        if (col == N) {
            maxSum = Math.max(maxSum, currentSum);
            return;
        }

        for (int row = 0; row < N; row++) {
            if (isSafe(row, col)) {
                placeQueen(row, col);
                solve(col + 1, currentSum + board[row][col]);
                removeQueen(row, col);
            }
        }
    }

    private static boolean isSafe(int row, int col) {
        return !rows[row] && !d1[row - col + N - 1] && !d2[row + col];
    }

    private static void placeQueen(int row, int col) {
        rows[row] = true;
        d1[row - col + N - 1] = true;
        d2[row + col] = true;
    }

    private static void removeQueen(int row, int col) {
        rows[row] = false;
        d1[row - col + N - 1] = false;
        d2[row + col] = false;
    }
}
