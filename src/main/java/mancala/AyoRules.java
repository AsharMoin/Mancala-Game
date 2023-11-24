package mancala;

import java.io.Serializable;

public class AyoRules extends GameRules implements Serializable{
    private static final long serialVersionUID = 6855804359052193370L;
    private final MancalaDataStructure board = getDataStructure();
    private static final int STORE_TWO = 13;

    public AyoRules() {
        super();
        resetBoard();
    }

    @Override
    public int moveStones(final int startPit, final int playerNum) throws InvalidMoveException {
        final int initialStore = board.getStoreCount(playerNum);
        validateMove(startPit, playerNum);
        board.addStones(9, 2);
        setPlayer(playerNum);
        distributeStones(startPit);

        return board.getStoreCount(playerNum) - initialStore;
    }

    @Override
    public int distributeStones(final int startPit) {
        int numStones = board.removeStones(startPit);
        int distributed = numStones;
        int currentPit;
        board.setIterator(startPit, getPlayer(), true);
        Countable currentSpot;
        while (numStones > 0) {
            currentSpot = board.next();
            currentPit = board.getIterator();

            final boolean isLast = numStones == 1;
            final boolean isLastEmpty = isLast && currentSpot.getStoneCount() == 0;

            currentSpot.addStone();
            numStones--;

            if (isLastEmpty && isValidCapture(currentPit, getPlayer())) {
                captureStones(currentPit); // Adjust to the pit where the last stone landed
                continue;
            }
            
            if (numStones == 0 && currentSpot.getStoneCount() > 1) {
                numStones = currentSpot.removeStones();
                distributed += numStones;
            }
        }
        return distributed;
    }

    @Override
    public int captureStones(final int stoppingPoint) {
        final int oppositePit = STORE_TWO - stoppingPoint;
        final int stonesInOpposite = board.removeStones(oppositePit);
        board.addToStore(getPlayer(), stonesInOpposite);

        return stonesInOpposite;
    }
   
}