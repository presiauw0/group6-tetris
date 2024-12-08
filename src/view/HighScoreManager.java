package view;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Manages the high scores for a game, including adding new high scores,
 * saving them to a file, and loading them from a file.
 *
 * @author Abdulrahman Hassan
 * @version Autumn 2024
 */
public class HighScoreManager implements HighScoreManagerInterface {

    /** Logger for logging errors and events. */
    private static final Logger LOGGER =
            Logger.getLogger(HighScoreManager.class.getName());

    /**
     * The name of the into to my exception stating messages.
     */
    private static final String EXCEPTION = "Exception details: ";

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
    private final List<HighScore> myHighScores;

    /**
     * Constructs a new HighScoreManager and initializes the high scores
     * by loading them from the file.
     */
    public HighScoreManager() {
        super();
        myHighScores = loadHighScores();
    }

    /**
     * Adds a new high score to the list. The list is sorted in descending order
     * by score, and if the list exceeds the maximum number of high scores,
     * the lowest score is removed. The updated list is then saved to the file.
     *
     * @param theScore the HighScore object to add
     */
    @Override
    public void addHighScore(final HighScore theScore) {
        myHighScores.add(theScore);
        myHighScores.sort(Comparator.comparingInt(HighScore::getScore).reversed());
        if (myHighScores.size() > MAX_HIGH_SCORES) {
            myHighScores.removeLast();
        }
        saveHighScores();
    }

    /**
     * Clears all high scores from the list and updates the high score file.
     */
    @Override
    public void clearHighScores() {
        myHighScores.clear();
        saveHighScores();
    }

    /**
     * Retrieves the list of high scores.
     *
     * @return a list of HighScore objects
     */
    @Override
    public List<HighScore> getHighScores() {
        return myHighScores;
    }

    /**
     * Saves the current list of high scores to a file. If an I/O error occurs,
     * the exception stack trace is printed.
     */
    private void saveHighScores() {
        try (final ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(HIGH_SCORE_FILE))) {
            oos.writeObject(myHighScores);
        } catch (final IOException e) {
            LOGGER.log(Level.SEVERE,
                    "Failed to save high scores to file: {0}", HIGH_SCORE_FILE);
            LOGGER.log(Level.SEVERE, EXCEPTION, e);
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
        List<HighScore> highScores = new ArrayList<>();
        try (final ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(HIGH_SCORE_FILE))) {
            highScores = (List<HighScore>) ois.readObject();
        } catch (final IOException | ClassNotFoundException e) {
            LOGGER.log(Level.WARNING, "Highscore failed to load");
            LOGGER.log(Level.WARNING, EXCEPTION, e);
        }
        return highScores;
    }
}