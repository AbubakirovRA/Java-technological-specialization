package OOP;

public class CheckWin {
    public boolean checkWin(Board board, Player currentPlayer) {
    int currentPlayerSign = currentPlayer.getSign();

    // Check rows
    for (int i = 0; i < Board.SIZE; i++) {
        if (board.getCell(i, 0) == currentPlayerSign &&
            board.getCell(i, 0) == board.getCell(i, 1) &&
            board.getCell(i, 1) == board.getCell(i, 2)) {
            return true;
        }
    }

    // Check columns
    for (int j = 0; j < Board.SIZE; j++) {
        if (board.getCell(0, j) == currentPlayerSign &&
            board.getCell(0, j) == board.getCell(1, j) &&
            board.getCell(1, j) == board.getCell(2, j)) {
            return true;
        }
    }

    // Check diagonals
    if (board.getCell(0, 0) == currentPlayerSign &&
        board.getCell(0, 0) == board.getCell(1, 1) &&
        board.getCell(1, 1) == board.getCell(2, 2)) {
        return true;
    }
    if (board.getCell(0, 2) == currentPlayerSign &&
        board.getCell(0, 2) == board.getCell(1, 1) &&
        board.getCell(1, 1) == board.getCell(2, 0)) {
        return true;
    }

    return false;
}

}
