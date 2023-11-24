package mancala;

import java.io.Serializable;

public class Player implements Serializable{
    private static final long serialVersionUID = -2277888924366162121L;
    private UserProfile user;
    private Store playerStore; 
    private static int playerCount = 0;
    private final int playerNum;

    public Player() {
        this.playerNum = playerCount++;
        this.user = new UserProfile(); 
        this.playerStore = new Store();
    }

    /**
     * Constructs a new {@code Player} with the specified name, an associated user profile, and a store.
     *
     * @param name The name to set for the player.
     */
    public Player(final String name) {
        this.playerNum = playerCount++;
        this.user = new UserProfile(); 
        this.user.setName(name); 
        this.playerStore = new Store(); 
    }

    /**
     * Gets the unique player number.
     *
     * @return The player number.
     */
    public int getPlayerNum() {
        return this.playerNum;
    }

    /**
     * Gets the name of the player.
     *
     * @return The name of the player.
     */
    public String getName() { 
        return user.getName(); 
    }

    /**
     * Gets the store associated with the player.
     *
     * @return The player's store.
     */
    public Store getStore() { 
        return this.playerStore; 
    }

    /**
     * Gets the stone count in the player's store.
     *
     * @return The stone count in the player's store.
     */
    public int getStoreCount() { 
        return this.playerStore.getStoneCount(); 
    }

    /**
     * Gets the user profile associated with the player.
     *
     * @return The user profile of the player.
     */
    public UserProfile getUserProfile() {
        return this.user;
    }

    /**
     * Sets the user profile for the player.
     *
     * @param userProfile The user profile to set.
     */
    public void setUserProfile(final UserProfile userProfile) {
        this.user = userProfile;
    }

    /**
     * Sets the store for the player.
     *
     * @param store The store to set.
     */
    public void setStore(final Store store) {
        this.playerStore = store;
    }

    /**
     * Sets the name for the player if it is not null and has a length greater than 0.
     *
     * @param name The name to set for the player.
     */
    public void setName(final String name) {
        if(name != null && name.length() > 0) {
            user.setName(name);
        }
    }

    @Override
    public String toString() {
        return "Player " + this.getName() + " with " + getStoreCount() + " stones in their store.";
    }

}
