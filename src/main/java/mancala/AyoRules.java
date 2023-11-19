package mancala;

import java.io.Serializable;

//currently, it is also hitting its own store, which it shouldnt do
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
        // System.out.println("in pit 7: " + board.getNumStones(7));
        setPlayer(playerNum);
        distributeStones(startPit);

        return board.getStoreCount(playerNum) - initialStore;
    }

    @Override
    public int distributeStones(final int startPit) {
        // System.out.println(startPit);
        int numStones = board.removeStones(startPit);
        int distributed = numStones;
        int currentPit;
        board.setIterator(startPit, getPlayer(), true);
        Countable currentSpot;
        // System.out.println("CP: " + currentPit);
        // System.out.println("num stones: " + numStones);
        while (numStones > 0) {
            System.out.println("stones in number 9: " + board.getNumStones(9));
            currentSpot = board.next();
            currentPit = board.getIterator();
            // currentPit = (currentPit + 1) % 14;
            // if (currentPit == 0 ) currentPit += 1;

            System.out.println("CP: " + currentPit);
            
            // if (currentPit == startPit) {
            //     // System.out.println("hit start pit : " + currentPit);
            //     currentPit = (currentPit + 1) % 13;
            //     if (currentPit == 0) {
            //         currentPit += 1;
            //     }
            // }

            final boolean isLast = numStones == 1;
            final boolean isLastEmpty = isLast && currentSpot.getStoneCount() == 0;

            currentSpot.addStone();
            numStones--;

            System.out.println("num stones: " + numStones);
            if (isLastEmpty && isValidCapture(currentPit, getPlayer())) {
                captureStones(currentPit); // Adjust to the pit where the last stone landed
                continue;
            }
            
            if (numStones == 0 && currentSpot.getStoneCount() > 1) {
                // System.out.println("removing from pit: " + currentPit);
                // System.out.println("Stones in pit 7:  " + currentSpot.getStoneCount());
                numStones = currentSpot.removeStones();
                distributed += numStones;
            }
            // System.out.println(currentSpot.getStoneCount());
        }
        System.out.println(board.getNumStones(9));
        return distributed;
    }

    @Override
    public int captureStones(final int stoppingPoint) {
        // System.out.println("stopping point: " + stoppingPoint);
        final int oppositePit = STORE_TWO - stoppingPoint;
        final int stonesInOpposite = board.removeStones(oppositePit);
        board.addToStore(getPlayer(), stonesInOpposite);
        System.out.println("stones in opposite: " + stonesInOpposite);
        System.out.println("opposite pit: " + oppositePit);
        // System.out.println("add to store: " + stonesInOpposite);

        return stonesInOpposite;
    }
   
}