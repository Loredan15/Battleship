package battleship;

public class Board {
    private final char[][] board;
    private final char[][] boardWithFog;

    public Board(int size) {
        this.board = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = '~';
            }
        }
        boardWithFog = new char[size][size];
        for (int k = 0; k < size; k++) {
            for (int l = 0; l < size; l++) {
                boardWithFog[k][l] = '~';
            }
        }
    }

    public void takeSpace(int col, int row) {
        board[col][row] = 'O';
    }

    //checks if any of the required positions are already taken by a different ship
    public boolean positionIsTaken(Coordinates coordinates) {
        int start;
        int end;
        int col;
        int row;
        if (coordinates.isHorizontal()) {
            col = coordinates.getaX();
            start = coordinates.getaY();
            end = coordinates.getbY();
            for (int i = start; i < end; i++) {
                if (board[col][i] != '~') {
                    return true;
                }
            }
        } else if (coordinates.isVertical()) {
            row = coordinates.getaY();
            start = coordinates.getaX();
            end = coordinates.getbX();
            for (int i = start; i < end; i++) {
                if (board[i][row] != '~') {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean shipIsAdjacent(Coordinates coordinates) {
        int start;
        int end;
        int col;
        int row;
        if (coordinates.isHorizontal()) {
            col = coordinates.getaX();
            start = coordinates.getaY();
            end = coordinates.getbY();
            for (int i = start; i <= end; i++) {
                if (col == 9) {
                    if (board[col - 1][i] != '~') {
                        return true;
                    }
                } else if (col == 0) {
                    if (board[col + 1][i] != '~') {
                        return true;
                    }
                } else {
                    if (board[col - 1][i] != '~' || board[col + 1][i] != '~') {
                        return true;
                    }
                }
                if (i == start && start != 0) {
                    if (board[col][i - 1] != '~') {
                        return true;
                    }
                }
                if (i == end && end != 9) {
                    if (board[col][i + 1] != '~') {
                        return true;
                    }
                }
            }
        }
        if (coordinates.isVertical()) {
            row = coordinates.getaY();
            start = coordinates.getaX();
            end = coordinates.getbX();
            for (int i = start; i <= end; i++) {
                if (row == 9) {
                    if (board[i][row - 1] != '~') {
                        return true;
                    }
                } else if (row == 0) {
                    if (board[i][row + 1] != '~') {
                        return true;
                    }
                } else {
                    if (board[i][row - 1] != '~' || board[i][row + 1] != '~') {
                        return true;
                    }
                }
                if (i == start && start != 0) {
                    if (board[i - 1][row] != '~') {
                        return true;
                    }
                }
                if (i == end && end != 9) {
                    if (board[i + 1][row] != '~') {
                        return true;
                    }
                }

            }
        }
        return false;
    }

    //prints the board with the row and column names
    public void printBoard() {
        char row = 'A';
        int col = 1;
        for (int i = 0; i < 11; i++) {
            if (i == 0) {
                System.out.print("" + " ");
            } else {
                System.out.print(row + " ");
                row++;
            }
            for (int j = 0; j < 11; j++) {
                if (i == 0) {
                    if (j == 0) {
                        System.out.print("" + " ");
                    } else {
                        System.out.print(col + " ");
                        col++;
                    }
                } else if (j < 10) {
                    System.out.print(board[i - 1][j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public void printBoardWithFogOfWar() {
        char row = 'A';
        int col = 1;
        for (int i = 0; i < 11; i++) {
            if (i == 0) {
                System.out.print("" + " ");
            } else {
                System.out.print(row + " ");
                row++;
            }
            for (int j = 0; j < 11; j++) {
                if (i == 0) {
                    if (j == 0) {
                        System.out.print("" + " ");
                    } else {
                        System.out.print(col + " ");
                        col++;
                    }
                } else if (j < 10) {
                    System.out.print(boardWithFog[i - 1][j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public void isHit(Coordinates coordinates){
        int x = coordinates.getaX();
        int y = coordinates.getaY();
        if (board[x][y] == '~') {
            board[x][y] = 'M';
            boardWithFog[x][y] = 'M';
            printBoardWithFogOfWar();
            System.out.println("You missed!");
        }
        if (board[x][y] == 'O') {
            board[x][y] = 'X';
            boardWithFog[x][y] = 'X';
            printBoardWithFogOfWar();
            System.out.println("You hit a ship!");
        }

    }
}