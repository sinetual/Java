import java.util.Scanner;

public class Connect4 {
    public static void main(String[] args) {
        char[][] board = createBoard(6, 7);
        char currentPlayer = 'X';
        boolean gameRunning = true;
        Scanner scanner = new Scanner(System.in);

        while (gameRunning) {
            displayBoard(board);
            int column = getPlayerMove(scanner, currentPlayer);

            if (placeDisc(board, column, currentPlayer)) {
                if (isWinningMove(board, currentPlayer)) {
                    displayBoard(board);
                    System.out.println("Player " + currentPlayer + " wins!");
                    gameRunning = false;
                } else if (isBoardFull(board)) {
                    displayBoard(board);
                    System.out.println("The game is a draw!");
                    gameRunning = false;
                } else {
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                }
            }
        }

        scanner.close();
    }

    public static char[][] createBoard(int rows, int cols) {
        char[][] board = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = '-';
            }
        }
        return board;
    }

    public static void displayBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int getPlayerMove(Scanner scanner, char player) {
        int column;
        while (true) {
            System.out.println("Player " + player + ", enter a column (0-6): ");
            column = scanner.nextInt();
            if (column >= 0 && column <= 6) {
                return column;
            } else {
                System.out.println("Invalid column. Please try again.");
            }
        }
    }

    public static boolean placeDisc(char[][] board, int col, char player) {
        for (int i = board.length - 1; i >= 0; i--) {
            if (board[i][col] == '-') {
                board[i][col] = player;
                return true;
            }
        }
        System.out.println("Column is full. Try another column.");
        return false;
    }

    public static boolean isWinningMove(char[][] board, char player) {
        int rows = board.length;
        int cols = board[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j <= cols - 4; j++) {
                if (board[i][j] == player && board[i][j + 1] == player &&
                        board[i][j + 2] == player && board[i][j + 3] == player) {
                    return true;
                }
            }
        }

        for (int i = 0; i <= rows - 4; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == player && board[i + 1][j] == player &&
                        board[i + 2][j] == player && board[i + 3][j] == player) {
                    return true;
                }
            }
        }

        for (int i = 3; i < rows; i++) {
            for (int j = 0; j <= cols - 4; j++) {
                if (board[i][j] == player && board[i - 1][j + 1] == player &&
                        board[i - 2][j + 2] == player && board[i - 3][j + 3] == player) {
                    return true;
                }
            }
        }

        for (int i = 0; i <= rows - 4; i++) {
            for (int j = 0; j <= cols - 4; j++) {
                if (board[i][j] == player && board[i + 1][j + 1] == player &&
                        board[i + 2][j + 2] == player && board[i + 3][j + 3] == player) {
                    return true;
                }
            }
        }

        return false;
    }
// Self explanatory
    public static boolean isBoardFull(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }
}
