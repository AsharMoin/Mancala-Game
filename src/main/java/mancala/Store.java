package mancala;

import java.io.Serializable;

public class Store implements Countable, Serializable{    
    private static final long serialVersionUID = 7477862967650255197L;
    private Player owner;
    private int stones;

    public Store() {
        this.stones = 0;
    }

    public void setOwner(final Player player) {
        this.owner = player;
    }

    public Player getOwner() { 
        return this.owner; 
    }

    @Override
    public void addStones(final int amount) {
        this.stones += amount;
    }

    @Override
    public int getStoneCount() {
        return this.stones; 
    }

    @Override
    public void addStone() {
        this.stones++;
    }

    @Override
    public int removeStones() {
        final int emptiedStones = this.stones;
        this.stones = 0; 
        return emptiedStones;
    }

    @Override
    public String toString() { 
        return "Store of " + this.owner + ": " + this.stones + " stones";
    }
}
