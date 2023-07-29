package OOP;

import java.io.Serializable;

public class Board implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final int SIZE = 3;
    private int[][] cells;

    public Board() {
        cells = new int[SIZE][SIZE];
    }

    public void reset() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                cells[i][j] = 0;
            }
        }
    }

    public void makeMove(int row, int col, int player) {
        cells[row][col] = player;
    }

    public int getCell(int row, int col) {
        return cells[row][col];
    }

    public boolean isFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (cells[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean hasWinningCombination() {
        // Check rows
        for (int i = 0; i < SIZE; i++) {
            if (cells[i][0] != 0 && cells[i][0] == cells[i][1] && cells[i][1] == cells[i][2]) {
                return true;
            }
        }

        // Check columns
        for (int j = 0; j < SIZE; j++) {
            if (cells[0][j] != 0 && cells[0][j] == cells[1][j] && cells[1][j] == cells[2][j]) {
                return true;
            }
        }

        // Check diagonals
        if (cells[0][0] != 0 && cells[0][0] == cells[1][1] && cells[1][1] == cells[2][2]) {
            return true;
        }
        if (cells[0][2] != 0 && cells[0][2] == cells[1][1] && cells[1][1] == cells[2][0]) {
            return true;
        }

        return false;
    }

    public void printBoard() {
        System.out.println("Current Board State:");
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(cells[i][j] + " ");
            }
            System.out.println();
        }
    }

    public String serialize() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                sb.append(cells[i][j]);
            }
        }
        return sb.toString();
    }

    public void deserialize(String data) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                cells[i][j] = Character.getNumericValue(data.charAt(i * SIZE + j));
            }
        }
    }

    public boolean checkWin() {
        return false;
    }
}
