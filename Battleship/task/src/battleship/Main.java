package battleship;

public class Main {

    public static void main(String[] args) {
        // Write your code here
        Board board = new Board(10);
        Game game = new Game(board);
        game.placeShips();
        System.out.println("The game starts!");
        board.printBoardWithFogOfWar();
        game.shot();
    }
}