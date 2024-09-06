import java.util.Scanner;

public class TicTacToe {
    
    // 3x3 board for the game
    private static char[][] board = {
        {' ', ' ', ' '},
        {' ', ' ', ' '},
        {' ', ' ', ' '}
    };
    
    // Current player ('X' or 'O')
    private static char currentPlayer = 'X';
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        boolean gameWon = false;
        boolean isDraw = false;
        
        // Main game loop
        while (!gameWon && !isDraw) {
            printBoard();
            playerMove(scanner);
            gameWon = checkWinner();
            isDraw = checkDraw();
            if (!gameWon && !isDraw) {
                // Switch player for next turn
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }
        
        printBoard();
        
        if (gameWon) {
            System.out.println("Player " + currentPlayer + " wins!");
        } else {
            System.out.println("It's a draw!");
        }
        
        scanner.close();
    }

    // Method to print the current state of the board
    private static void printBoard() {
        System.out.println("  0   1   2");
        System.out.println("0 " + board[0][0] + " | " + board[0][1] + " | " + board[0][2]);
        System.out.println("  ---------");
        System.out.println("1 " + board[1][0] + " | " + board[1][1] + " | " + board[1][2]);
        System.out.println("  ---------");
        System.out.println("2 " + board[2][0] + " | " + board[2][1] + " | " + board[2][2]);
    }

    // Method to handle a player's move
    private static void playerMove(Scanner scanner) {
        boolean validMove = false;
        while (!validMove) {
            System.out.println("Player " + currentPlayer + "'s turn");
            System.out.print("Enter row (0, 1, or 2): ");
            int row = scanner.nextInt();
            System.out.print("Enter column (0, 1, or 2): ");
            int col = scanner.nextInt();
            
            if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ') {
                board[row][col] = currentPlayer;
                validMove = true;
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
    }

    // Method to check if there is a winner
    private static boolean checkWinner() {
        // Check rows, columns, and diagonals for a win
        return (checkRowCol(board[0][0], board[0][1], board[0][2]) ||
                checkRowCol(board[1][0], board[1][1], board[1][2]) ||
                checkRowCol(board[2][0], board[2][1], board[2][2]) ||
                checkRowCol(board[0][0], board[1][0], board[2][0]) ||
                checkRowCol(board[0][1], board[1][1], board[2][1]) ||
                checkRowCol(board[0][2], board[1][2], board[2][2]) ||
                checkRowCol(board[0][0], board[1][1], board[2][2]) ||
                checkRowCol(board[0][2], board[1][1], board[2][0]));
    }

    // Helper method to check if all three positions are the same (and not empty)
    private static boolean checkRowCol(char c1, char c2, char c3) {
        return (c1 != ' ' && c1 == c2 && c2 == c3);
    }

    // Method to check if the game is a draw (i.e., the board is full)
    private static boolean checkDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false; // There's still an empty spot, so no draw yet
                }
            }
        }
        return true; // No empty spots, so it's a draw
    }
}
