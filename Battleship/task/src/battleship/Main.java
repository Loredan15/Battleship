package battleship;

import java.util.Scanner;

public class Main {

    private static String[][] board;
    private static Scanner scanner = new Scanner(System.in);
    private static String firstCoordinate;
    private static String secondCoordinate;
    private static final int BOARD_SIZE = 10;


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

            int c1x = Character.getNumericValue(firstCoordinate.charAt(0) - 17);
            int c2x = Character.getNumericValue(secondCoordinate.charAt(0) - 17);

            int c1y = Integer.parseInt(firstCoordinate.substring(1));
            int c2y = Integer.parseInt(secondCoordinate.substring(1));

            if (c1y > c2y) {
                int temp = c1y;
                c1y = c2y;
                c2y = temp;
            }

            if (c1x == c2x) {
                if (checkCoordinate(c1y, c2y, size)) {
                    for (int i = c1y; i <= c2y; i++) {
                        if (!anotherShipLocation(c1x + 1, i, board, 1)) {
                            System.out.println("Error! You placed it too close to another one. Try again:");
                        } else {
                            board[c1x + 1][i] = "O";
                        }
                    }
                    success = true;
                    printBoard(board);
                }
            } else if (c1y == c2y) {
                if (checkCoordinate(c1x, c2x, size)) {
                    for (int i = c1x; i <= c2x; i++) {
                        if (!anotherShipLocation(i, c1y, board, 2)) {
                            System.out.println("Error! You placed it too close to another one. Try again:");
                        } else {
                            board[i + 1][c1y] = "O";
                        }
                    }
                    success = true;
                    printBoard(board);
                }
            }
        }
    }

    public static boolean checkCoordinate(int x1, int x2, int size) {
        int xMax = 10;
        boolean x1MoreX2 = x1 > x2;
        if (x1 > xMax || x2 > xMax) {
            System.out.println("Error! Wrong ship location! Try again:");
            return false;
        }
        if (x1MoreX2) {
            int temp = x1;
            x1 = x2;
            x2 = temp;
        }
        if (((x2 - x1) + 1 < 1) || ((x2 - x1) + 1 > BOARD_SIZE) || (x2 - x1 + 1 != size)) {
            System.out.println("Error! Wrong length of the Submarine! Try again:");
            return false;
        }

        return true;
    }

    public static boolean anotherShipLocation(int x, int y, String[][] board, int isHorizontal) {
        //проверяем саму ячейку, куда хотим поставить корабль
//        if (board[x][y].equals("O")) return false;

        //проверяем клетки вокруг

        switch (isHorizontal) {
            case 1: {
                if (x == 10 && y == 10) {
                    if (board[x - 1][y - 1].equals("O")) return false;
                    if (board[x][y - 1].equals("O")) return false;
                } else if (x == 0 && y == 0) {
                    if (board[x + 1][y + 1].equals("O")) return false;
                    if (board[x][y + 1].equals("O")) return false;
                    if (board[x + 1][y].equals("O")) return false;
                } else if (x == 0) {
                    if (board[x][y + 1].equals("O")) return false;
                } else if (y == 0) {
                    if (board[x + 1][y].equals("O")) return false;
                } else if (x == 10) {
                    if (board[x][y - 1].equals("O")) return false;
                    else {
                        if (board[x][y + 1].equals("O")) return false;
                        if (board[x + 1][y].equals("O")) return false;
                        if (board[x + 1][y + 1].equals("O")) return false;
                        if (board[x][y - 1].equals("O")) return false;
                        if (board[x - 1][y - 1].equals("O")) return false;
                        if (board[x + 1][y - 1].equals("O")) return false;
                        if (board[x - 1][y + 1].equals("O")) return false;
                    }
                }
                break;
            }
            case 2: {
                if (x == 10 && y == 10) {
                    if (board[x - 1][y - 1].equals("O")) return false;
                    if (board[x - 1][y].equals("O")) return false;
                } else if (x == 0 && y == 0) {
                    if (board[x + 1][y + 1].equals("O")) return false;
                    if (board[x][y + 1].equals("O")) return false;
                    if (board[x + 1][y].equals("O")) return false;
                } else if (x == 0) {
                    if (board[x][y + 1].equals("O")) return false;
                } else if (y == 0) {
                    if (board[x + 1][y].equals("O")) return false;
                } else if (y == 10) {
                    if (board[x - 1][y].equals("O")) return false;
                } else {
                    if (board[x][y + 1].equals("O")) return false;
                    if (board[x + 1][y].equals("O")) return false;
                    if (board[x + 1][y + 1].equals("O")) return false;
                    if (board[x - 1][y].equals("O")) return false;
//                    if (board[x - 1][y - 1].equals("O")) return false;
//                    if (board[x + 1][y - 1].equals("O")) return false;
//                    if (board[x - 1][y + 1].equals("O")) return false;
                }
                break;
            }
        }
        return true;
    }
}
