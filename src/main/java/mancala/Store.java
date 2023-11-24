package mancala;

import java.io.Serializable;

public class Store implements Countable, Serializable{    
    private static final long serialVersionUID = 7477862967650255197L;
    private Player owner;
    private int stones;

    public Store() {
        this.stones = 0;
    }

     /**
     * Sets the owner of the store to the specified player.
     *
     * @param player The player to set as the owner of the store.
     */
    public void setOwner(final Player player) {
        this.owner = player;
    }

    /**
     * Gets the owner of the store.
     *
     * @return The player who owns the store.
     */
    public Player getOwner() { 
        return this.owner; 
    }

    /**
     * Adds a specified amount of stones to the store.
     *
     * @param amount The number of stones to add to the store.
     */
    @Override
    public void addStones(final int amount) {
        this.stones += amount;
    }

    /**
     * Gets the current count of stones in the store.
     *
     * @return The number of stones in the store.
     */
    @Override
    public int getStoneCount() {
        return this.stones; 
    }

    /**
     * Adds a single stone to the store.
     */
    @Override
    public void addStone() {
        this.stones++;
    }

    /**
     * Removes all stones from the store and returns the count.
     *
     * @return The number of stones removed from the store.
     */
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
