package view;

import java.util.List;

/**
 * Manages the high scores for a game, including adding new high scores,
 * saving them to a file, and loading them from a file.
 *
 * @author Abdulrahman Hassan, Preston Sia
 * @version Autumn 2024
 */
public interface HighScoreManagerInterface {
    /**
     * Adds a new high score to the list. The list is sorted in descending order
     * by score, and if the list exceeds the maximum number of high scores,
     * the lowest score is removed. The updated list is then saved to the file.
     *
     * @param theScore the HighScore object to add
     */
    void addHighScore(HighScoreInterface theScore);

    /**
     * Clears all high scores from the list and updates the high score file.
     */
    void clearHighScores();

    /**
     * Retrieves the list of high scores.
     *
     * @return a list of HighScore objects
     */
    List<HighScoreInterface> getHighScores();
}
