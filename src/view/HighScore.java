package view;

import java.util.Date;

/**
 * Represents a high score entry for a game, including the player's name, score,
 * and the date the score was achieved. Implements Serializable for saving high
 * score objects.
 *
 * @author Abdulrahman Hassan
 * @version Autumn 2024
 */
public class HighScore implements HighScoreInterface {

    /**
     * The name of the player who achieved the score.
     */
    private final String myPlayerName;

    /**
     * The score achieved by the player.
     */
    private final int myScore;

    /**
     * The date when the score was achieved.
     */
    private final Date myDate;

    /**
     * Constructs a new HighScore object with the specified player's name and score.
     * The date is automatically set to the current date and time.
     *
     * @param thePlayerName the name of the player
     * @param theScore the score achieved by the player
     */
    public HighScore(final String thePlayerName, final int theScore) {
        super();
        myPlayerName = thePlayerName;
        myScore = theScore;
        myDate = new Date();
    }

    @Override
    public String getPlayerName() {
        return myPlayerName;
    }

    @Override
    public int getScore() {
        return myScore;
    }

    @Override
    public Date getDate() {
        return myDate;
    }

    /**
     * Returns a string representation of the high score,
     * including the player's name, score, and date.
     *
     * @return a string in the format "playerName - score - date"
     */
    @Override
    public String toString() {
        final String dashSeparator = " - ";
        return myPlayerName + dashSeparator + myScore + dashSeparator + myDate;
    }
}