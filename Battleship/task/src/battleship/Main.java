package battleship;

import java.util.Scanner;

public class Main {

    private static String[][] board;
    private static Scanner scanner = new Scanner(System.in);
    private static String firstCoordinate;
    private static String secondCoordinate;


    public static void main(String[] args) {
        makeBoard();
        printBoard(board);
        startGame();

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

    public static void makeBoard() {
        board = new String[11][11];

        for (int i = 1; i <= 11; i++) {
            for (int j = 0; j < 11; j++) {
                board[0][0] = " ";
                board[0][j] = String.valueOf(j);
                board[i - 1][j] = "~";
            }
        }
        for (int i = 1; i < 11; i++) {
            board[i][0] = String.valueOf((char) (i + 64));
        }
    }

    public static void startGame() {

        System.out.println("Enter the coordinates of the Aircraft Carrier (5 cells):");
        placeShip(5);

        System.out.println("Enter the coordinates of the Battleship (4 cells):");
        placeShip(4);

        System.out.println("Enter the coordinates of the Submarine (3 cells):");
        placeShip(3);

        System.out.println("Enter the coordinates of the Cruiser (3 cells):");
        placeShip(3);

        System.out.println("Enter the coordinates of the Destroyer (2 cells):");
        placeShip(2);


    }

    public static void placeShip(int size) {

        boolean success = false;

        while (!success) {

            firstCoordinate = scanner.next();
            secondCoordinate = scanner.next();

            int c1x = Character.getNumericValue(firstCoordinate.charAt(0) - 16);
            int c1y = Character.getNumericValue(firstCoordinate.charAt(1));
            int c2x = Character.getNumericValue(secondCoordinate.charAt(0) - 16);
            int c2y = Character.getNumericValue(secondCoordinate.charAt(1));


            if (c1x == c2x) {
                if (checkCoordinate(c1y, c2y, size)) {
                    for (int i = c1y; i <= c2y; i++) {
                        board[c1x][i] = "O";
                    }
                    success = true;
                    printBoard(board);
                }

            } else if (c1y == c2y) {
                if (checkCoordinate(c1x, c2x, size)) {
                    for (int i = c1x; i <= c2x; i++) {
                        board[i][c1y] = "O";
                    }
                    success = true;
                    printBoard(board);
                }
            }
        }


    }

    public static boolean checkCoordinate(int x1, int x2, int size) {
        int xMax = 10;
        if (x1 > xMax || x2 > xMax) {
            System.out.println("Error! Wrong ship location! Try again:");
            return false;
        }
        if (((x2 - x1) +1 < size) || ((x2 - x1) + 1 > size)) {
            System.out.println("Error! Wrong length of the Submarine! Try again:");
            return false;
        }


        return true;
    }
}
