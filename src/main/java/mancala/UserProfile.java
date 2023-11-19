package mancala;

import java.io.Serializable;

public class UserProfile implements Serializable{
    private static final long serialVersionUID = -6855327323011336180L;
    
    private String name; 
    private int kalahPlayed;
    private int kalahWins;
    private int ayoPlayed;
    private int ayoWins;
    private int kalahLosses;
    private int ayoLosses;

    public UserProfile() {
        this.name = "Anonymous";
        this.kalahPlayed = 0; 
        this.kalahWins = 0; 
        this.ayoPlayed = 0; 
        this.ayoWins = 0; 
        this.kalahLosses = 0;
        this.ayoLosses = 0;
    }

    public String getName() {
        return this.name;
    }
    public int getKalahGames() {
        return this.kalahPlayed;
    }
    public int getKalahWins() {
        return this.kalahWins;
    }
    public int getAyoGames() {
        return this.ayoPlayed;
    }
    public int getAyoWins() {
        return this.ayoWins;
    }
    public int getAyoLosses() {
        return this.ayoLosses;
    }
    public int getKalahLosses() {
        return this.kalahLosses;
    }
    public void setName(final String newName) {
        this.name = newName;
    }
    public void setKalahGames() {
        this.kalahPlayed++;
    }
    public void setKalahWins() {
        this.kalahWins++;
    }
    public void setAyoGames() {
        this.ayoPlayed++;
    }
    public void setAyoWins() {
        this.ayoWins++;
    }
    public void setAyoLosses() {
        this.ayoLosses++;
    }
    public void setKalahLosses() {
        this.kalahLosses++;
    }
}
