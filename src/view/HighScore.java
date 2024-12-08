package view;

import java.io.Serializable;
import java.util.Date;

/**
 * Represents a high score entry for a game, including the player's name, score,
 * and the date the score was achieved. Implements Serializable for saving high
 * score objects.
 *
 * @author Abdulrahman Hassan
 * @version Autumn 2024
 */
public class HighScore implements Serializable {

    /**
     * The name of the player who achieved the score.
     */
    private final String playerName;

    /**
     * The score achieved by the player.
     */
    private final int score;

    /**
     * The date when the score was achieved.
     */
    private final Date date;

    /**
     * Constructs a new HighScore object with the specified player's name and score.
     * The date is automatically set to the current date and time.
     *
     * @param playerName the name of the player
     * @param score the score achieved by the player
     */
    public HighScore(final String playerName, final int score) {
        super();
        this.playerName = playerName;
        this.score = score;
        this.date = new Date();
    }

    /**
     * Gets the name of the player who achieved the score.
     *
     * @return the player's name
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Gets the score achieved by the player.
     *
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * Gets the date when the score was achieved.
     *
     * @return the date of the score
     */
    public Date getDate() {
        return date;
    }

    /**
     * Returns a string representation of the high score,
     * including the player's name, score, and date.
     *
     * @return a string in the format "playerName - score - date"
     */
    @Override
    public String toString() {
        return playerName + " - " + score + " - " + date;
    }
}