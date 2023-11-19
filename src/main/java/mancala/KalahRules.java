package mancala;

import java.io.Serializable;

public class KalahRules extends GameRules implements Serializable {
    private static final long serialVersionUID = 6495974495258271469L;
    private static final int STORE_ONE = 6;
    private static final int STORE_TWO = 13;
    private final MancalaDataStructure board = getDataStructure();

    public KalahRules() {
        super();
        resetBoard();
    }

    @Override
    public int moveStones(final int startPit, final int playerNum) throws InvalidMoveException {
        final int initialScore = board.getStoreCount(playerNum);
        validateMove(startPit, playerNum);
        setPlayer(playerNum);
        distributeStones(startPit);
        return board.getStoreCount(playerNum) - initialScore;
    }

    @Override
    public int distributeStones(final int startPit) {
        int numStones = board.removeStones(startPit);
        int currentPit = startPit;
        final int distribute = numStones;
        board.setIterator(startPit, getPlayer(), false); 
        Countable currentSpot;
        System.out.println(startPit);
        while(numStones > 0) {
            currentSpot = board.next(); // Get the next spot
            currentSpot.addStone(); // Add a stone to the current spot
            numStones--;

            final boolean isLast = numStones == 0;
            final boolean isLastEmpty = isLast && currentSpot.getStoneCount() == 1;

            if (isLast && ((getPlayer() == 1 && currentPit == STORE_ONE) || 
                            (getPlayer() == 2 && currentPit == STORE_TWO))) {
                setRepeat(1); // Player gets another turn
            }
            currentPit = (currentPit + 1) % STORE_TWO;
            // currentPit = (currentPit + 1) % 14; // Update the current pit for the next iteration

            System.out.println("CurrentPit: " + currentPit);
            // System.out.println("Num stones: " + numStones);
            // System.out.println("cS:"+currentSpot.getStoneCount());
            System.out.println(currentSpot.getStoneCount());
            System.out.println(isLast);

            if (isLastEmpty && isValidCapture(currentPit, getPlayer())) {
                System.out.println("inside if");
                System.out.println(currentPit);
                captureStones(currentPit); // Adjust to the pit where the last stone landed
                continue;
            }
        }
        return distribute;
    }

    @Override
    public int captureStones(final int stoppingPoint) {
        System.out.println("Stopping point: " + stoppingPoint);
        final int oppositePit = STORE_TWO - stoppingPoint;
        final int stonesInOpposite = board.removeStones(oppositePit);
        final int stonesInStop = board.removeStones(stoppingPoint);
        final int total = stonesInOpposite + stonesInStop;
        board.addToStore(getPlayer(), total);
        System.out.println("stones in opposite: " + stonesInOpposite);
        System.out.println("opposite pit: " + oppositePit);
        System.out.println("add to store: " + stonesInOpposite);

        return total;
    }
}