package mancala;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

//gamerules used in mancalagame
public class MancalaGame implements Serializable{

    private static final long serialVersionUID = 3709230983028419358L;
    private static final Player TIE_GAME = new Player("Tie");
    
    private Player playerOne;
    private Player playerTwo;
    private Player currentPlayer;
    private GameRules ruleSet;

    public void setPlayers(final Player onePlayer, final Player twoPlayer) {
        this.playerOne = onePlayer;
        this.playerTwo = twoPlayer;
        ruleSet.registerPlayers(playerOne, playerTwo);
        this.currentPlayer = playerOne;
    }

    public List<Player> getPlayers() { 
        return new ArrayList<>(Arrays.asList(playerOne, playerTwo)); 
    }

    public Player getCurrentPlayer() { 
        return this.currentPlayer; 
    }

    public void setCurrentPlayer(final Player player) {
        final int repeatTurn = ruleSet.getRepeat();
        System.out.println("repeat: "+ repeatTurn);
        if (repeatTurn == -1) {this.currentPlayer = player;}
        ruleSet.setRepeat(-1);        
    }

    public void setBoard(final GameRules rules) {
        this.ruleSet = rules;
    }

    public GameRules getBoard() { 
        return this.ruleSet;
    }

    public int getNumStones(final int pitNum){ 
        return ruleSet.getNumStones(pitNum); 
    }

    public int move(final int startPit) throws InvalidMoveException{ 
        return ruleSet.moveStones(startPit, this.currentPlayer.getPlayerNum()); 
    }

    public int getStoreCount(final Player player) { 
        System.out.println("playerstore: " + player.getStoreCount());
        return player.getStoreCount(); 
    }

    public Player getWinner() throws GameNotOverException {
        final int playerOneStones = playerOne.getStoreCount();
        final int playerTwoStones = playerTwo.getStoreCount();
        Player winner;

        if (playerOneStones > playerTwoStones) {
            winner =  playerOne;
        } else if (playerOneStones < playerTwoStones) {
            winner =  playerTwo;
        } else if (playerOneStones == playerTwoStones) {
            winner =  TIE_GAME;
        } else {
            throw new GameNotOverException();
        } 
        return winner;
    }

    public boolean isGameOver() {
        boolean isEmpty = false;
        if (ruleSet.isSideEmpty(1) || ruleSet.isSideEmpty(7)) {
                isEmpty = true;
        }  
        return isEmpty;
    }

    public void startNewGame() { 
        ruleSet.resetBoard(); 
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();

        // Header
        builder.append("Mancala Game Status\n");
        builder.append("--------------------\n");

        // Players
        builder.append("Player One: ").append(playerOne.getName()).append("\n");
        builder.append("Player Two: ").append(playerTwo.getName()).append("\n\n");

        // Current player's turn
        builder.append("Current Turn: ").append(currentPlayer.getName()).append("\n\n");

        // Board status
        builder.append("Board Layout:\n");
        builder.append("--------------------\n");

        // Player Two's pits (7-12) in reverse since they are across the board from player one
        for (int i = 12; i >= 7; i--) {
            builder.append("Pit ").append(String.valueOf(i).length() == 2 ? i : "0" + i)
                       .append(": ").append(ruleSet.getNumStones(i)).append(" | ");
        }
        builder.append("\n");

        builder.append("Player Two Store: ").append(playerTwo.getStoreCount());
        final int spaceCount = 8 * 4;
        builder.append(" ".repeat(spaceCount));
        builder.append("Player One Store: ").append(playerOne.getStoreCount()).append("\n");

        // Player One's pits (1-6)
        for (int i = 0; i <= 5; i++) {
            builder.append("Pit 0").append(i + 1).append(": ").append(ruleSet.getNumStones(i + 1)).append(" | ");
        }
        builder.append("\n");

        return builder.toString(); 
    }
}
