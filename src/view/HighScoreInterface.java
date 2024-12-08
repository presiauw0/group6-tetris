package view;

import java.util.Date;

/**
 * Represents the contract for a high score entry, including the player's name, score,
 * and the date the score was achieved.
 * @author Balkirat Singh
 * @version Autumn 2024
 */
public interface HighScoreInterface {

    /**
     * Gets the name of the player who achieved the score.
     *
     * @return the player's name
     */
    String getPlayerName();

    /**
     * Gets the score achieved by the player.
     *
     * @return the score
     */
    int getScore();

    /**
     * Gets the date when the score was achieved.
     *
     * @return the date of the score
     */
    Date getDate();

    /**
     * Returns a string representation of the high score,
     * including the player's name, score, and date.
     *
     * @return a string in the format "playerName - score - date"
     */
    String toString();
}
