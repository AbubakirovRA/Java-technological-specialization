package task2;

import java.io.*;
import java.util.Scanner;

public class TicTakGame {
    private int[][] board;
    private final static int SIZE = 3;
    private int currentPlayer;

    public TicTakGame() {
        board = new int[SIZE][SIZE];
        currentPlayer = 1;
    }

    private static int readCoordinate(String coordinateName) {
        try (Scanner scanner = new Scanner(System.in)) {
            int coordinate = -1;
            while (coordinate < 0 || coordinate >= SIZE) {
                System.out.print("Enter " + coordinateName + " (0, 1, or 2): ");
                if (scanner.hasNextInt()) {
                    coordinate = scanner.nextInt();
                } else {
                    scanner.next(); // clear invalid input
                }
            }
            return coordinate;
        }
    }

    public void makeMove(int row, int col) {
        if (row >= 0 && row < SIZE && col >= 0 && col < SIZE && board[row][col] == 0) {
            board[row][col] = currentPlayer;
            currentPlayer = 3 - currentPlayer; // Switch to the other player (1 -> 2, 2 -> 1)
        } else {
            System.err.println("Invalid move!");
        }
    }

    public boolean checkWin() {
        // Check rows
        for (int i = 0; i < SIZE; i++) {
            if (board[i][0] != 0 && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return true;
            }
        }

        // Check columns
        for (int j = 0; j < SIZE; j++) {
            if (board[0][j] != 0 && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
                return true;
            }
        }

        // Check diagonals
        if (board[0][0] != 0 && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return true;
        }
        if (board[0][2] != 0 && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return true;
        }

        return false;
    }

    public void printBoard() {
        System.out.println("Current Board State:");
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void saveGame(String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(board);
            oos.writeInt(currentPlayer);
            System.out.println("Game state has been saved successfully.");
        } catch (IOException e) {
            System.err.println("Error while saving the game state: " + e.getMessage());
        }
    }

    public static TicTakGame loadGame(String filePath) {
        TicTakGame game = new TicTakGame();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            int[][] loadedBoard = (int[][]) ois.readObject();
            if (loadedBoard.length == SIZE && loadedBoard[0].length == SIZE) {
                game.board = loadedBoard;
                game.currentPlayer = ois.readInt();
                System.out.println("Game state has been loaded successfully.");
            } else {
                System.err.println("Invalid file format. Starting a new game.");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error while loading the game state: " + e.getMessage());
        }
        return game;
    }

    public static void main(String[] args) {
        TicTakGame game = loadGame("game_state.txt");

        try (Scanner scanner = new Scanner(System.in)) {
            while (!game.checkWin()) {
                game.printBoard();

                int row, col;
                do {
                    // Получаем ход игрока 1 (крестик)
                    System.out.println("Player 1 (X) turn:");
                    String input = scanner.next();
                    if (input.equalsIgnoreCase("exit")) {
                        game.saveGame("game_state.txt");
                        System.out.println("Game saved. Exiting...");
                        return;
                    }
                    row = Integer.parseInt(input);
                    col = readCoordinate("column");
                } while (game.board[row][col] != 0);
                game.makeMove(row, col);

                if (game.checkWin()) {
                    break; // Выход из цикла, если кто-то победил
                }

                game.printBoard();

                do {
                    // Получаем ход игрока 2 (нолик)
                    System.out.println("Player 2 (O) turn:");
                    String input = scanner.next();
                    if (input.equalsIgnoreCase("exit")) {
                        game.saveGame("game_state.txt");
                        System.out.println("Game saved. Exiting...");
                        return;
                    }
                    row = Integer.parseInt(input);
                    col = readCoordinate("column");
                } while (game.board[row][col] != 0);
                game.makeMove(row, col);
            }
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        game.printBoard();

        // Если игрок 1 победил:
        if (game.checkWin()) {
            System.out.println("Player 1 (X) wins!");
        }
        // Если игрок 2 победил:
        else {
            System.out.println("Player 2 (O) wins!");
        }

        game.saveGame("game_state.txt");
    }
}