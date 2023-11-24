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

    /**
     * Gets the name of the user.
     *
     * @return The name of the user.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the number of Kalah games played by the user.
     *
     * @return The number of Kalah games played.
     */
    public int getKalahGames() {
        return this.kalahPlayed;
    }

    /**
     * Gets the number of Kalah games won by the user.
     *
     * @return The number of Kalah games won.
     */
    public int getKalahWins() {
        return this.kalahWins;
    }

    /**
     * Gets the number of Ayo games played by the user.
     *
     * @return The number of Ayo games played.
     */
    public int getAyoGames() {
        return this.ayoPlayed;
    }

    /**
     * Gets the number of Ayo games won by the user.
     *
     * @return The number of Ayo games won.
     */
    public int getAyoWins() {
        return this.ayoWins;
    }

    /**
     * Gets the number of Ayo games lost by the user.
     *
     * @return The number of Ayo games lost.
     */
    public int getAyoLosses() {
        return this.ayoLosses;
    }

    /**
     * Gets the number of Kalah games lost by the user.
     *
     * @return The number of Kalah games lost.
     */
    public int getKalahLosses() {
        return this.kalahLosses;
    }

    /**
     * Sets the name for the user.
     *
     * @param newName The new name to set for the user.
     */
    public void setName(final String newName) {
        this.name = newName;
    }

    /**
     * Increments the number of Kalah games played by the user.
     */
    public void setKalahGames() {
        this.kalahPlayed++;
    }

    /**
     * Increments the number of Kalah games won by the user.
     */
    public void setKalahWins() {
        this.kalahWins++;
    }

    /**
     * Increments the number of Ayo games played by the user.
     */
    public void setAyoGames() {
        this.ayoPlayed++;
    }

    /**
     * Increments the number of Ayo games won by the user.
     */
    public void setAyoWins() {
        this.ayoWins++;
    }

    /**
     * Increments the number of Ayo games lost by the user.
     */
    public void setAyoLosses() {
        this.ayoLosses++;
    }

    /**
     * Increments the number of Kalah games lost by the user.
     */
    public void setKalahLosses() {
        this.kalahLosses++;
    }
}
