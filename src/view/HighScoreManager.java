package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

/**
 * Manages the high scores for a game, including adding new high scores,
 * saving them to a file, and loading them from a file.
 *
 * @author Abdulrahman Hassan
 * @version Autumn 2024
 */
public class HighScoreManager {

    /**
     * Logger for logging logs.
     */
    public static final Logger LOGGER = Logger.getLogger(HighScoreManager.class.getName());

    /**
     * The name of the file where high scores are saved.
     */
    private static final String HIGH_SCORE_FILE = "highscores.sav";

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
    public void clearHighScores() {
        myHighScores.clear();
        saveHighScores();
    }

    /**
     * Retrieves the list of high scores.
     *
     * @return a list of HighScore objects
     */
    public List<HighScore> getHighScores() {
        return myHighScores;
    }

    /**
     * Saves the current list of high scores to a file. If an I/O error occurs,
     * the exception stack trace is printed.
     */
    private void saveHighScores() {
        /*
        try  {
            final ObjectOutputStream oos =
                    new ObjectOutputStream(new FileOutputStream(HIGH_SCORE_FILE));
            oos.writeObject(myHighScores);
            oos.close();
        } catch (final IOException | SecurityException e) {
            // IOException, SecurityException, FileNotFoundException
            //e.printStackTrace();
            LOGGER.severe("An IO error occured: " + e);
        }*/
        try {
            final FileOutputStream fos = new FileOutputStream(HIGH_SCORE_FILE);

            try {
                final ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(myHighScores);
                oos.close();
            } catch (final IOException | SecurityException e) {
                LOGGER.severe("An IO error occured: " + e);
            }

            fos.close();
        } catch (final IOException fosException) {
            LOGGER.warning("Unable to write high scores: " + fosException);
        }
    }

    private void createEmptyFile() {
        final List<HighScore> emptyList = new ArrayList<>();

        try {
            final FileOutputStream fos = new FileOutputStream(HIGH_SCORE_FILE);

            try {
                final ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(emptyList);
                oos.close();
            } catch (final IOException | SecurityException e) {
                LOGGER.severe("Unable to write new high score file: " + e);
            }

            fos.close();
        } catch (final IOException fosException) {
            LOGGER.warning("Unable to create high scores file: "
                    + fosException);
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
        List<HighScore> highScoreList;
        /*
        try  {
            final ObjectInputStream ois =
                    new ObjectInputStream(new FileInputStream(HIGH_SCORE_FILE));
            highScoreList = (List<HighScore>) ois.readObject();
            ois.close();
        } catch (final IOException | ClassNotFoundException e) {
            LOGGER.warning("Unable to load high scores: " + e);
            highScoreList = new ArrayList<>();
        }
        return highScoreList;*/

        try {
            final FileInputStream fis = new FileInputStream(HIGH_SCORE_FILE);

            // Read from ObjectInputStream
            try {
                final ObjectInputStream ois = new ObjectInputStream(fis);
                highScoreList = (List<HighScore>) ois.readObject();
                ois.close();
            } catch (final IOException | ClassNotFoundException oisException) {
                LOGGER.warning("Unable to load high scores: " + oisException);
                highScoreList = new ArrayList<>();
            }

            fis.close();
        } catch (final IOException fisException) {
            // if it can't find the file, create a new file
            if (fisException instanceof FileNotFoundException) {
                createEmptyFile();
            } else {
                LOGGER.warning("Unable to read high scores: " + fisException);
            }
            highScoreList = new ArrayList<>();
        }

        // return list of high scores
        return highScoreList;
    }
}