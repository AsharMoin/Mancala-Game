import java.util.*;

public class MancalaGame {
    
    private static final int PITS_PER_PLAYER = 6;
    private static final int INITIAL_STONES_PER_PIT = 4;
    private int[] board = new int[2 * PITS_PER_PLAYER + 2]; // including two stores
    private boolean playerOneTurn = true;
    
    public MancalaGame() {
        for (int i = 0; i < PITS_PER_PLAYER; i++) {
            board[i] = INITIAL_STONES_PER_PIT;
            board[i + PITS_PER_PLAYER + 1] = INITIAL_STONES_PER_PIT; // skip the player one's store
        }
    }
    
    public void play() {
        Scanner scanner = new Scanner(System.in);
        while (!isGameOver()) {
            printBoard();
            System.out.println(playerOneTurn ? "Player 1's turn" : "Player 2's turn");
            System.out.print("Choose a pit (1-6): ");
            int pit = scanner.nextInt();
            if (isValidMove(pit - 1)) {
                makeMove(pit - 1);
                playerOneTurn = !playerOneTurn;
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
        scanner.close();
        declareWinner();
    }
    
    private boolean isValidMove(int pit) {
        if (pit < 0 || pit >= PITS_PER_PLAYER) return false;
        if (playerOneTurn) return board[pit] > 0;
        return board[pit + PITS_PER_PLAYER + 1] > 0;
    }
    
    private void makeMove(int pit) {
        if (playerOneTurn) {
            distributeStones(pit, 0);
        } else {
            distributeStones(pit + PITS_PER_PLAYER + 1, PITS_PER_PLAYER + 1);
        }
    }

    private void distributeStones(int pit, int storeIndex) {
        int stones = board[pit];
        board[pit] = 0;
        while (stones > 0) {
            pit++;
            if (pit == storeIndex) pit++; // skip opponent's store
            pit %= board.length;
            board[pit]++;
            stones--;
        }
    }
    
    private boolean isGameOver() {
        return sumStones(0, PITS_PER_PLAYER) == 0 || sumStones(PITS_PER_PLAYER + 1, 2 * PITS_PER_PLAYER + 1) == 0;
    }
    
    private int sumStones(int from, int to) {
        int sum = 0;
        for (int i = from; i < to; i++) sum += board[i];
        return sum;
    }
    
    private void declareWinner() {
        int playerOneScore = board[PITS_PER_PLAYER];
        int playerTwoScore = board[2 * PITS_PER_PLAYER + 1];
        printBoard();
        System.out.println("Player 1 score: " + playerOneScore);
        System.out.println("Player 2 score: " + playerTwoScore);
        if (playerOneScore > playerTwoScore) {
            System.out.println("Player 1 wins!");
        } else if (playerOneScore < playerTwoScore) {
            System.out.println("Player 2 wins!");
        } else {
            System.out.println("It's a draw!");
        }
    }
    
    private void printBoard() {
        System.out.println("  Player 2");
        System.out.println("6 5 4 3 2 1");
        for (int i = 2 * PITS_PER_PLAYER; i > PITS_PER_PLAYER; i--) {
            System.out.print(board[i] + " ");
        }
        System.out.println("\n" + board[2 * PITS_PER_PLAYER + 1] + "           " + board[PITS_PER_PLAYER]);
        for (int i = 0; i < PITS_PER_PLAYER; i++) {
            System.out.print(board[i] + " ");
        }
        System.out.println("\n1 2 3 4 5 6");
        System.out.println("  Player 1");
    }

    public static void main(String[] args) {
        MancalaGame game = new MancalaGame();
        game.play();
    }
}
