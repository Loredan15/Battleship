package battleship;

public class Main {

    private static String[][] board;

    public static void main(String[] args) {
        makeBoard();
        printBoard(board);
    }

    private static void printBoard(String[][] board) {
        for (String[] str : board) {
            for (String str2 : str) {
                System.out.print(str2 + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void makeBoard(){
        board = new String[11][11];

        for (int i = 1; i <= 11; i++) {
            for (int j = 0; j < 11; j++) {
                board[0][0] = " ";
                board[0][j] = String.valueOf(j);
                board[i - 1][j] = "~";
            }
        }
        for (int i = 1; i < 11; i++) {
            board[i][0] = String.valueOf((char) (i+64));
        }
    }
}
