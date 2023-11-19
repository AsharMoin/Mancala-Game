package mancala;

import java.io.Serializable;

/**
 * Abstract class representing the rules of a Mancala game.
 * KalahRules and AyoRules will subclass this class.
 */
public abstract class GameRules implements Serializable{
    private static final long serialVersionUID = 4909869731361291755L;
    private final MancalaDataStructure gameBoard;
    private int currentPlayer = 1; // Player number (1 or 2)
    private int repeatTurn = -1;
    private int mode = 0; // 1 for ayo, 2 for kalah
    private static final int FIRST_PIT = 0;
    private static final int LAST_PIT = 12;

    /**
     * Constructor to initialize the game board.
     */
    public GameRules() {
        gameBoard = new MancalaDataStructure();
    }
    /**
     * Get the number of stones in a pit.
     *
     * @param pitNum The number of the pit.
     * @return The number of stones in the pit.
     */
    public int getNumStones(final int pitNum) {
        return gameBoard.getNumStones(pitNum);
    }

    /**
     * Get the game data structure.
     *
     * @return The MancalaDataStructure.
     */
    MancalaDataStructure getDataStructure() {
        return gameBoard;
    }

    /**
     * Check if a side (player's pits) is empty.
     *
     * @param pitNum The number of a pit in the side.
     * @return True if the side is empty, false otherwise.
     */
    boolean isSideEmpty(final int pitNum) {
        boolean isEmpty = true;
        final int start = (pitNum < 6) ? 1 : 7;
        final int end = (pitNum < 6) ? 7 : 13;

        for(int i = start; i < end; i++) {
            if(gameBoard.getNumStones(i) > 0) {
                isEmpty = false;
                break;
            }
        }
        
        return isEmpty; 
        // This method can be implemented in the abstract class.
    }

    /**
     * Set the current player.
     *
     * @param playerNum The player number (1 or 2).
     */
    public void setPlayer(final int playerNum) {
        currentPlayer = playerNum;
    }
    public int getPlayer(){
        return currentPlayer;
    }

    /**
     * Perform a move and return the number of stones added to the player's store.
     *
     * @param startPit  The starting pit for the move.
     * @param playerNum The player making the move.
     * @return The number of stones added to the player's store.
     * @throws InvalidMoveException If the move is invalid.
     */
    public abstract int moveStones(int startPit, int playerNum) throws InvalidMoveException;

    /**
     * Distribute stones from a pit and return the number distributed.
     *
     * @param startPit The starting pit for distribution.
     * @return The number of stones distributed.
     */
    abstract int distributeStones(int startPit);

    /**
     * Capture stones from the opponent's pit and return the number captured.
     *
     * @param stoppingPoint The stopping point for capturing stones.
     * @return The number of stones captured.
     */
    abstract int captureStones(int stoppingPoint);

    /**
     * Register two players and set their stores on the board.
     *
     * @param one The first player.
     * @param two The second player.
     */
    public void registerPlayers(final Player one, final Player two) {
        // this method can be implemented in the abstract class.
        final Store store1 = new Store();
        final Store store2 = new Store();

        //create a two way relationship between the player and the store
        store1.setOwner(one);
        one.setStore(store1);
        store2.setOwner(two);
        two.setStore(store2);

        gameBoard.setStore(store1, 1);
        gameBoard.setStore(store2, 2);
        /* make a new store in this method, set the owner
         then use the setStore(store,playerNum) method of the data structure*/
    }

    /**
     * Reset the game board by setting up pits and emptying stores.
     */
    public void resetBoard() {
        setPlayer(1);
        gameBoard.emptyPits();
        gameBoard.setUpPits();
        gameBoard.emptyStores();        
    }

    public void setRepeat(final int set) {
        this.repeatTurn = set;
    }

    public int getRepeat() {
        return repeatTurn;
    }

    public boolean isValidCapture(final int pit, final int player) {
        return (player == 1 && pit >= FIRST_PIT && pit <= 6) || (player == 2 && pit >= 7 && pit <= 12);
    }
    
    public void validateMove(final int startPit, final int playerNum) throws InvalidMoveException {
        if (startPit < FIRST_PIT || startPit > LAST_PIT) {
            throw new InvalidMoveException();
        }
        if ((playerNum == 1 && startPit > 6 || playerNum == 2 && startPit < 7)) {
            throw new InvalidMoveException();
        }
        if (getNumStones(startPit) == 0) {
            throw new InvalidMoveException();
        }
    }

    public void setMode(final GameRules ruleSet) {
        if (ruleSet.getClass() == KalahRules.class) {
            this.mode = 2;
        } else {
            this.mode = 1;
        }
    }
    public int gameMode() {
        return mode;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        
        builder.append("Current Player: ").append(currentPlayer).append("\n");
        builder.append("Repeat Turn: ").append(repeatTurn).append("\n");
        builder.append("Game Mode: ").append(mode == 1 ? "Ayo" : "Kalah").append("\n");

        return builder.toString();
    }
}