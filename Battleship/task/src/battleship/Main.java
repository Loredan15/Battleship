//package battleship;
//
//public class Main {
//
//    public static void main(String[] args) {
//        // Write your code here
//        Board board = new Board(10);
//        Game game = new Game(board);
//        game.placeShips();
//        System.out.println("The game starts!");
//        board.printBoardWithFogOfWar();
//        game.shot();
//
//
//    }
//}


package battleship;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    static final int N = 10; // size of a battlefield
    static final int LC = 65; // used for letter char to convert into meaningful numbers according to ASCII
    static final int NC = 49; // used to number char to convert into meaningful numbers according to ASCII

    public static class Ship {

        public final String name;
        public final int size;
        List<Integer> position;

        Ship(String name, int size) {
            this.name = name;
            this.size = size;
            this.position = new ArrayList<>();
        }
    }

    public static void printGrid(String[][] grid) {
        System.out.print(" ");
        for (int i = 1; i <= N; i++) {
            System.out.print(" " + i);
        }
        System.out.println();
        for (int i = 0; i < N; i++) {
            System.out.print((char) (i + LC));
            for (int j = 0; j < N; j++) {
                System.out.print(" " + grid[i][j]);
            }
            System.out.println();
        }
    }

    // checks input and places ship on the grid once the input is correct
    public static void checkInput(String[][] grid, Ship ship, String cor1, String cor2) {

        int x1, x2, y1, y2;
        String start, end;

        while (true) {

            // determine which point is a start and which is an end of a ship
            if (comparePoints(cor1, cor2)) {
                start = cor1;
                end = cor2;
            } else {
                start = cor2;
                end = cor1;
            }

            // convert input into number coordinates
            y1 = start.charAt(0) - LC;
            x1 = start.charAt(1) - NC;
            y2 = end.charAt(0) - LC;
            x2 = end.charAt(1) - NC;
            // accounts for a case when input contains "10" as 2nd coordinate of a point
            if (start.length() == 3) {
                x1 = 9;
            }
            if (end.length() == 3) {
                x2 = 9;
            }

            // check length of ship
            if ((Math.abs(x1 - x2) != ship.size - 1) && (Math.abs(y1 - y2) != ship.size - 1)) {
                System.out.printf("%nError! Wrong length of the Submarine! Try again:%n%n> ");
                cor1 = scanner.next();
                cor2 = scanner.next();
            }
            // check if it is horizontal or vertical
            else if (x1 != x2 && y1 != y2) {
                System.out.printf("%nError! Wrong ship location! Try again:%n%n> ");
                cor1 = scanner.next();
                cor2 = scanner.next();
            }
            // check if it does not touch or cross other ships
            else if (checkNearbyShips(grid, x1, x2, y1, y2)) {
                System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                cor1 = scanner.next();
                cor2 = scanner.next();
            } else break;
        }
        placeShip(grid, x1, x2, y1, y2, ship);
    }

    // for every cell of a given ship checks, if all nearby cell (or this cell itself) is another ship
    public static boolean checkNearbyShips(String[][] grid, int x1, int x2, int y1, int y2) {
        for (int row = y1; row <= y2; row++) {
            for (int col = x1; col <= x2; col++) {
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        if ((row + i) < 0 || (row + i) > 9 || (col + j) < 0 || (col + j) > 9) {
                            continue;
                        }
                        if (grid[row + i][col + j].equals("O")) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    // places a ship on a grid
    public static void placeShip(String[][] grid, int x1, int x2, int y1, int y2, Ship ship) {
        for (int i = y1; i <= y2; i++) {
            for (int j = x1; j <= x2; j++) {
                grid[i][j] = "O";
                ship.position.add(i * 10 + j);
            }
        }

    }

    /* Checks if point cor1 is starting point (= is smaller than cor2).
    We know in correct input either x or y coordinate must be equal,
    so it is sufficient to check sums of coordinates of 2 point. */
    public static boolean comparePoints(String cor1, String cor2) {
        int y1 = cor1.charAt(0) - LC;
        int x1 = cor1.charAt(1) - NC;
        int y2 = cor2.charAt(0) - LC;
        int x2 = cor2.charAt(1) - NC;
        if (cor1.length() == 3) {
            x1 = 9;
        }
        if (cor2.length() == 3) {
            x2 = 9;
        }
        return x1 + y1 <= x2 + y2;
    }

    public static void placeAllShips(String[][] grid, Ship[] ships) {

        String cor1, cor2;


        for (Ship s : ships) {
            System.out.printf("%nEnter the coordinates of the %s (%d cells):%n%n> ", s.name, s.size);
            cor1 = scanner.next();
            cor2 = scanner.next();
            checkInput(grid, s, cor1, cor2);
            System.out.println();
            printGrid(grid);
        }
    }

    // takes grid1 as grid with ship placed, and grid2 where ships are hidden from the player
    public static void playGame(String[][] grid1, String[][] grid2, Ship[] ships) {

        System.out.printf("%nThe game starts!%n%n");
        printGrid(grid2);
        int x, y;
        String cor;
        System.out.printf("%nTake a shot!%n%n> ");

        while (true) {

            cor = scanner.next();

            // checks if the input is correct
            while (true) {
                y = cor.charAt(0) - LC;
                x = cor.charAt(1) - NC;
                if (cor.length() == 3) {
                    if (cor.charAt(2) - NC == -1) {
                        x = 9;
                    } else {
                        x = 10;
                    }
                }
                if (y < 0 || y > 9 || x < 0 || x > 9) {
                    System.out.printf("%nError! You entered the wrong coordinates! Try again:%n%n> ");
                    cor = scanner.next();
                } else break;
            }

            System.out.println();

            if (grid1[y][x].equals("O") || grid2[y][x].equals("X")) {
                grid1[y][x] = "X";
                grid2[y][x] = "X";
                printGrid(grid2);
                if (isSunk(x, y, ships)) {
                    if (areAllSunk(ships)) {
                        System.out.printf("%nYou sank the last ship. You won. Congratulations!%n");
                        break;
                    } else {
                        System.out.printf("%nYou sank a ship! Specify a new target:%n%n> ");
                    }
                } else System.out.printf("%nYou hit a ship! Try again:%n%n> ");
            } else {
                grid1[y][x] = "M";
                grid2[y][x] = "M";
                printGrid(grid2);
                System.out.printf("%nYou missed. Try again:%n%n> ");
            }
        }
    }

    /*
    Check if given shot sinks a ship.
    Shot removes given cell from list of points given ship occupies (stored in ArrayList position).
    If after this operation list is empty, it means all point have been shot and therefore ship is sunk.
     */
    public static boolean isSunk(int x, int y, Ship[] ships) {
        for (Ship s : ships) {
            if (s.position.contains(y * 10 + x)) {
                s.position.remove((Integer) (y * 10 + x));
                if (s.position.isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }

    // checks if all ships are sunk and therefore game is finished
    public static boolean areAllSunk(Ship[] ships) {
        for (Ship s : ships) {
            if (!s.position.isEmpty()) return false;
        }
        return true;
    }

    public static void main(String[] args) {

        // Create 2 initial grids with only fog ("~")
        String[][] gridWithShips = new String[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                gridWithShips[i][j] = "~";
            }
        }
        String[][] gridWithFog = new String[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                gridWithFog[i][j] = "~";
            }
        }

        Ship[] ships = new Ship[5];
        ships[0] = new Ship("Aircraft Carrier", 5);
        ships[1] = new Ship("Battleship", 4);
        ships[2] = new Ship("Submarine", 3);
        ships[3] = new Ship("Cruiser", 3);
        ships[4] = new Ship("Destroyer", 2);


        printGrid(gridWithShips);
        placeAllShips(gridWithShips, ships);
        playGame(gridWithShips, gridWithFog, ships);
    }
}