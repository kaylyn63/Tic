
import java.util.Scanner;


public class Game {
    private char[][] board = {{'_', '_', '_'}, {'_', '_', '_'}, {' ', ' ', ' '}};

    GameCheck checks = new GameCheck();
    private char player;
    private char computer;
    private boolean gameDone = false;

    public void starGame() {
        Scanner input = new Scanner(System.in);
        GetUserInput(input);
        while (!gameDone) {
            printBoard();

            System.out.println("Enter Row and Column(0 - 2): ");
            if (!gameOver('O', board)) {
                int row = input.nextInt();
                int col = input.nextInt();
                makeMove(row, col);
                computerTurn();
            }

        }
        printBoard();
        System.out.println("Game Over");

    }

    private void GetUserInput(Scanner input){
        System.out.println("What would you like to play as? (X/ 0)");
        player = input.next().trim().charAt(0);

        if (player == 'X'){
            computer = 'O';
        } else {
            computer = 'X';
        }

    }



    public void printBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j]);
                if (j < 2) {
                    System.out.print('|');
                }

            }
            System.out.println();
        }
    }

    public void makeMove(int row, int col) {
        if (checks.isValid(row, col, board)) {
            board[row][col] = player;
        } else {
            System.out.println("Not a Valid Space");
        }

    }

    public void computerTurn() {
        TicTac compTicTac = getComputerTurn();
        board[compTicTac.getRow()][compTicTac.getCol()] = computer;


    }

    public TicTac getComputerTurn() {
        while (true) {
            int r = (int) (Math.random() * 3);
            int c = (int) (Math.random() * 3);
            if (checks.isValid(r, c, board)) {
                return new TicTac(r, c);

            }

        }
    }

    public boolean winCheck(char letter, char[][] board) {
        if (checks.rowVictory(letter, board) || checks.comVictory(letter, board) || checks.diagonalVictory(letter, board)) {
            return true;
        }
        return false;
    }

    public boolean boardFull() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '_' || board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean gameOver(char letter, char[][] board) {
        if (winCheck(letter, board)) {
            gameDone = true;
            return true;
        }
        if (boardFull()) {
            gameDone = true;
            return true;
        }
        return false;
    }
}










