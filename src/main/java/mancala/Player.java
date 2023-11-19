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

    public Player(final String name) {
        this.playerNum = playerCount++;
        this.user = new UserProfile(); 
        this.user.setName(name); 
        this.playerStore = new Store(); 
    }

    public int getPlayerNum() {
        return this.playerNum;
    }

    public String getName() { 
        return user.getName(); 
    }

    public Store getStore() { 
        return this.playerStore; 
    }

    public int getStoreCount() { 
        return this.playerStore.getStoneCount(); 
    }

    public UserProfile getUserProfile() {
        return this.user;
    }

    public void setUserProfile(final UserProfile userProfile) {
        this.user = userProfile;
    }

    public void setStore(final Store store) {
        this.playerStore = store;
    }

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
