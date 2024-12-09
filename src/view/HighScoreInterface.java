package view;

import java.io.Serializable;
import java.util.Date;

/**
 * Represents a high score entry for a game, including the player's name, score,
 * and the date the score was achieved. Implements Serializable for saving high
 * score objects.
 *
 * @author Abdulrahman Hassan, Preston Sia
 * @version Autumn 2024
 */
public interface HighScoreInterface extends Serializable {
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

}
