package mancala;

import java.io.Serializable;

public class Pit implements Countable, Serializable{
    private static final long serialVersionUID = -3807213840442923128L;
    private int stones;

    public Pit() {
        this.stones = 0;
    }

    /**
     * Gets the current count of stones in the pit.
     *
     * @return The number of stones in the pit.
     */
    @Override
    public int getStoneCount() { 
        return this.stones; 
    }

    /**
     * Removes all stones from the pit and returns the count.
     *
     * @return The number of stones removed from the pit.
     */
    @Override
    public int removeStones() {
        final int removed = this.stones;
        this.stones = 0; 
        return removed;
    }

    /**
     * Adds a single stone to the pit.
     */
    @Override
    public void addStone() {
        this.stones++;
    }

    /**
     * Adds a specified number of stones to the pit.
     *
     * @param numToAdd The number of stones to add to the pit.
     */
    @Override
    public void addStones(final int numToAdd) {
        this.stones += numToAdd;
    }
    
    @Override
    public String toString() { 
        return "Pit: " + this.stones + " stones";
    }
}
