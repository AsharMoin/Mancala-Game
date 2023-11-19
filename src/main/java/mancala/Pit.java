package mancala;

import java.io.Serializable;

public class Pit implements Countable, Serializable{
    private static final long serialVersionUID = -3807213840442923128L;
    private int stones;

    public Pit() {
        this.stones = 0;
    }

    @Override
    public int getStoneCount() { 
        return this.stones; 
    }

    @Override
    public int removeStones() {
        final int removed = this.stones;
        this.stones = 0; 
        return removed;
    }

    @Override
    public void addStone() {
        this.stones++;
    }

    @Override
    public void addStones(final int numToAdd) {
        this.stones += numToAdd;
    }
    
    @Override
    public String toString() { 
        return "Pit: " + this.stones + " stones";
    }
}
