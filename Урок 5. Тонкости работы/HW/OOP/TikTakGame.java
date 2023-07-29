package OOP;
import java.io.*;
import java.util.Scanner;

public class TicTakGame {
    private Board board;
    private Player currentPlayer;

    public TicTakGame() {
        board = new Board();
        currentPlayer = new Player(1); // Start with player 1 (X)
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        TicTakGame savedGame = loadGame("game_state.txt");
        if (savedGame != null) {
            this.board = savedGame.board;
            this.currentPlayer = savedGame.currentPlayer;
            System.out.println("Game state has been loaded successfully.");
        }

        boolean playAgain = true;
        while (playAgain) {
            while (!board.hasWinningCombination() && !board.isFull()) {
                board.printBoard();

                int[] move;
                do {
                    // Получаем ход текущего игрока
                    System.out.println("Player " + currentPlayer.getSign() + " turn:");
                    move = getPlayerMove(scanner);
                    if (move[0] == -1) {
                        saveGame("game_state.txt");
                        System.out.println("Game saved. Exiting...");
                        scanner.close();
                        return;
                    } else if (move[0] == -2 || board.getCell(move[0], move[1]) != 0) {
                        System.out.println("Invalid move! Please try again.");
                    }
                } while (move[0] == -2 || board.getCell(move[0], move[1]) != 0);

                board.makeMove(move[0], move[1], currentPlayer.getSign());
                currentPlayer = currentPlayer.getOpponent(); // Switch to the other player
            }

            board.printBoard();

            if (board.hasWinningCombination()) {
                System.out.println("Player " + currentPlayer.getOpponent().getSign() + " wins!");
            } else {
                System.out.println("It's a draw! No one wins.");
            }

            saveGame("game_state.txt");

            System.out.println("Do you want to play again? (yes/no): ");
            String playAgainInput = scanner.next();
            if (!playAgainInput.equalsIgnoreCase("yes")) {
                playAgain = false;
            } else {
                board.reset(); // Начинаем новую игру
                currentPlayer = new Player(1); // Сбрасываем текущего игрока до игрока 1 (X)
            }
        }

        scanner.close();
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

    private static int readCoordinate(Scanner scanner, String coordinateName) {
        int coordinate = -1;
        while (coordinate < 0 || coordinate >= Board.SIZE) {
            System.out.print("Enter " + coordinateName + " (0, 1, or 2): ");
            if (scanner.hasNextInt()) {
                coordinate = scanner.nextInt();
            } else {
                scanner.next(); // clear invalid input
            }
        }
        return coordinate;
    }

    public void saveGame(String filePath) {
        try (PrintWriter writer = new PrintWriter(filePath)) {
            writer.println(board.serialize());
            writer.println(currentPlayer.getSign());
            System.out.println("Game state has been saved successfully.");
        } catch (IOException e) {
            System.err.println("Error while saving the game state: " + e.getMessage());
        }
    }

    public static TicTakGame loadGame(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            TicTakGame game = new TicTakGame();
            game.board.deserialize(reader.readLine());
            game.currentPlayer = new Player(Integer.parseInt(reader.readLine()));
            return game;
        } catch (IOException | NumberFormatException e) {
            return null;
        }
    }

    public static void main(String[] args) {
        TicTakGame game = new TicTakGame();
        game.startGame();
    }
}
