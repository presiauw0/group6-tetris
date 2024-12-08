package view;

import java.util.List;

/**
 * Interface for managing high scores, including operations to add, clear,
 * retrieve, and save high scores.
 * @author Balkirat Singh
 * @version Autumn 2024
 */
public interface HighScoreManagerInterface {

    /**
     * Adds a new high score to the list. The list is sorted in descending order
     * by score, and if the list exceeds the maximum number of high scores,
     * the lowest score is removed.
     *
     * @param theScore the HighScore object to add
     */
    void addHighScore(HighScore theScore);

    /**
     * Clears all high scores from the list.
     */
    void clearHighScores();

    /**
     * Retrieves the list of high scores.
     *
     * @return a list of HighScore objects
     */
    List<HighScore> getHighScores();
}
