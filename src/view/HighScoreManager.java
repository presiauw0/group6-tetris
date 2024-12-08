package view;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Manages the high scores for a game, including adding new high scores,
 * saving them to a file, and loading them from a file.
 *
 * @author Abdulrahman Hassan
 * @version Autumn 2024
 */
public class HighScoreManager {

    /**
     * The name of the file where high scores are saved.
     */
    private static final String HIGH_SCORE_FILE = "highscores.txt";

    /**
     * The maximum number of high scores to store.
     */
    private static final int MAX_HIGH_SCORES = 10;

    /**
     * The list of high scores.
     */
    private final List<HighScore> highScores;

    /**
     * Constructs a new HighScoreManager and initializes the high scores
     * by loading them from the file.
     */
    public HighScoreManager() {
        super();
        highScores = loadHighScores();
    }

    /**
     * Adds a new high score to the list. The list is sorted in descending order
     * by score, and if the list exceeds the maximum number of high scores,
     * the lowest score is removed. The updated list is then saved to the file.
     *
     * @param score the HighScore object to add
     */
    public void addHighScore(final HighScore score) {
        highScores.add(score);
        highScores.sort(Comparator.comparingInt(HighScore::getScore).reversed());
        if (highScores.size() > MAX_HIGH_SCORES) {
            highScores.removeLast();
        }
        saveHighScores();
    }

    /**
     * Clears all high scores from the list and updates the high score file.
     */
    public void clearHighScores() {
        highScores.clear();
        saveHighScores();
    }

    /**
     * Retrieves the list of high scores.
     *
     * @return a list of HighScore objects
     */
    public List<HighScore> getHighScores() {
        return highScores;
    }

    /**
     * Saves the current list of high scores to a file. If an I/O error occurs,
     * the exception stack trace is printed.
     */
    private void saveHighScores() {
        try (final ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(HIGH_SCORE_FILE))) {
            oos.writeObject(highScores);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the list of high scores from a file. If the file does not exist
     * or an error occurs during loading, an empty list is returned.
     *
     * @return a list of HighScore objects
     */
    @SuppressWarnings("unchecked")
    private List<HighScore> loadHighScores() {
        try (final ObjectInputStream ois = new ObjectInputStream(new FileInputStream(HIGH_SCORE_FILE))) {
            return (List<HighScore>) ois.readObject();
        } catch (final IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }
}