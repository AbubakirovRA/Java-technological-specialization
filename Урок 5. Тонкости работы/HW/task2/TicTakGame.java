package task2;

import java.io.*;
import java.util.Scanner;

public class TicTakGame {
    private int[][] board;
    private final static int SIZE = 3;
    private int currentPlayer;
    private int movesCount;

    public TicTakGame() {
        board = new int[SIZE][SIZE];
        currentPlayer = 1;
        movesCount = 0;
    }

    private static int readCoordinate(Scanner scanner, String coordinateName) {
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

    private static int[] getPlayerMove(Scanner scanner) {
        int[] move = new int[2];
        String input = scanner.next();
        if (input.equalsIgnoreCase("exit")) {
            move[0] = -1; // Special value to indicate exit
            return move;
        }
        try {
            move[0] = Integer.parseInt(input);
            move[1] = readCoordinate(scanner, "column");
        } catch (NumberFormatException e) {
            move[0] = -2; // Special value to indicate invalid input
        }
        return move;
    }

    public void makeMove(int row, int col) {
        if (row >= 0 && row < SIZE && col >= 0 && col < SIZE && board[row][col] == 0) {
            board[row][col] = currentPlayer;
            movesCount++;

            if (movesCount == SIZE * SIZE || checkWin()) {
                return; // Игра завершается, если ничья или кто-то победил
            }

            currentPlayer = 3 - currentPlayer; // Switch to the other player (1 -> 2, 2 -> 1)
        } else {
            System.err.println("Invalid move!");
        }
    }

    public boolean checkWin() {
        int currentPlayerSign = currentPlayer == 1 ? 1 : 2;

        // Check rows
        for (int i = 0; i < SIZE; i++) {
            if (board[i][0] == currentPlayerSign && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return true;
            }
        }

        // Check columns
        for (int j = 0; j < SIZE; j++) {
            if (board[0][j] == currentPlayerSign && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
                return true;
            }
        }

        // Check diagonals
        if (board[0][0] == currentPlayerSign && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return true;
        }
        if (board[0][2] == currentPlayerSign && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return true;
        }

        return false;
    }

    public boolean isDraw() {
        return movesCount == SIZE * SIZE && !checkWin();
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
            oos.writeInt(movesCount);
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
                game.movesCount = ois.readInt();
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
        Scanner scanner = new Scanner(System.in);
        TicTakGame game = loadGame("game_state.txt");
        if (game == null) {
            game = new TicTakGame();
        }

        boolean playAgain = true;
        while (playAgain) {
            while (!game.checkWin() && !game.isDraw()) {
                game.printBoard();

                int[] move;
                do {
                    // Получаем ход игрока 1 (крестик)
                    System.out.println("Player 1 (X) turn:");
                    move = getPlayerMove(scanner);
                    if (move[0] == -1) {
                        game.saveGame("game_state.txt");
                        System.out.println("Game saved. Exiting...");
                        scanner.close();
                        return;
                    } else if (move[0] == -2 || game.board[move[0]][move[1]] != 0) {
                        System.out.println("Invalid move! Please try again.");
                    }
                } while (move[0] == -2 || game.board[move[0]][move[1]] != 0);
                game.makeMove(move[0], move[1]);

                if (game.checkWin() || game.isDraw()) {
                    break; // Выход из цикла, если кто-то победил или ничья
                }

                game.printBoard();

                do {
                    // Получаем ход игрока 2 (нолик)
                    System.out.println("Player 2 (O) turn:");
                    move = getPlayerMove(scanner);
                    if (move[0] == -1) {
                        game.saveGame("game_state.txt");
                        System.out.println("Game saved. Exiting...");
                        scanner.close();
                        return;
                    } else if (move[0] == -2 || game.board[move[0]][move[1]] != 0) {
                        System.out.println("Invalid move! Please try again.");
                    }
                } while (move[0] == -2 || game.board[move[0]][move[1]] != 0);

                game.makeMove(move[0], move[1]);
            }

            game.printBoard();

            // Если игрок 1 победил:
            if (game.checkWin() && game.currentPlayer == 1) {
                System.out.println("Player 1 (X) wins!");
            }
            // Если игрок 2 победил:
            else if (game.checkWin() && game.currentPlayer == 2) {
                System.out.println("Player 2 (O) wins!");
            }
            // Если ничья:
            else if (game.isDraw()) {
                System.out.println("It's a draw! No one wins.");
            }

            game.saveGame("game_state.txt");

            System.out.println("Do you want to play again? (y/n): ");
            String playAgainInput = scanner.next();
            if (!playAgainInput.equalsIgnoreCase("y")) {
                playAgain = false;
            } else {
                game = new TicTakGame(); // Начинаем новую игру
            }
        }

        scanner.close();
    }
}

