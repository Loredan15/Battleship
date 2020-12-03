package battleship;

import java.util.Scanner;

public class Game {
    private final Board board;
    Scanner scan = new Scanner(System.in);


    public Game(Board board) {
        this.board = board;
    }

    public void placeShips() {
        board.printBoard();
        for (Ship ship : Ship.values()) {
            while (true) {
                System.out.println("Enter the coordinates of the " + ship.getName() + " (" + ship.getLength() + " cells)");
                Coordinates coordinates = new Coordinates(scan.next(), scan.next());
                if (!coordinates.isValid()) {
                    System.out.println("Error: Input is invalid");
                } else if (coordinates.getLength() != ship.getLength()) {
                    System.out.println("Error: incorrect length for " + ship.getName());
                } else if (board.positionIsTaken(coordinates)) {
                    System.out.println("Error: One or more of the positions is taken!");
                } else if (board.shipIsAdjacent(coordinates)) {
                    System.out.println("Error, too close to another ship");
                } else {
                    takePositions(coordinates);
                    break;
                }
            }
            board.printBoard();
        }
    }

    public void takePositions(Coordinates coordinates) {
        int start;
        int end;

        if (coordinates.isHorizontal()) {
            int row = coordinates.getaX();
            start = coordinates.getaY();
            end = coordinates.getbY();
            for (int j = start; j <= end; j++) {
                board.takeSpace(row, j);
            }
        } else if (coordinates.isVertical()) {
            int col = coordinates.getaY();
            start = coordinates.getaX();
            end = coordinates.getbX();
            for (int i = start; i <= end; i++) {
                board.takeSpace(i, col);
            }
        }
    }

    public void shot() {
        while (true) {
            System.out.println("Take a shot!");
            Coordinates coordinatesShot = new Coordinates(scan.next());
            if (!coordinatesShot.isValid()) {
                System.out.println("Error: Input is invalid");
            } else {
                board.isHit(coordinatesShot);
                board.printBoard();
                break;
            }
        }
    }
}
