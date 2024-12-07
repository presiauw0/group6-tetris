package view.score;

import static model.MyBoard.PROPERTY_FROZEN_PIECES_CHANGE;
import static model.MyBoard.PROPERTY_CLEAR_ROW;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import model.Board;
import model.MyBoard;

/**
 * The ScoringSystem class is responsible for tracking the score, total lines cleared,
 * and the current level in a Tetris-like game. It provides methods to update the score
 * based on game actions and calculate the current level based on the number of lines cleared.
 *
 * @author Balkirat Singh, Abdulrahman Hassan, Preston Sia
 * @version 1.2
 */
public class ScoringSystem implements PropertyChangeListener, PropertyChangeEnabledScoring {
    // ***Score Increments***
    /**
     * Number of lines that must be cleared before moving to the next level.
     */
    public static final int LINES_PER_LEVEL = 5;
    /**
     * Number of points added when a piece freezes on the board.
     */
    public static final int SCORE_PIECE_FREEZE = 4;
    /**
     * Multiplier for the number of points removed per number of lines
     * cleared at a time. These numbers are multiplied by the level number.
     */
    public static final int[] SCORE_LINE_MULTIPLIER = {40, 100, 300, 1200};


    // ***SINGLETON***
    /**
     * A singleton using the static factory method
     */
    private static final PropertyChangeEnabledScoring INSTANCE = new ScoringSystem();


    // ***INSTANCE***
    /**
     * Store the current score.
     */
    private int myScore;
    /**
     * Store the total number of cleared lines.
     */
    private int myTotalLinesCleared;
    /**
     * Store the current level.
     */
    private int myLevel;

    // ***Line statistics for calculating***
    /**
     * Counter for storing the number of lines cleared
     * so far, to be used to calculate the lines cleared.
     */
    private int myLineCounter;

    // ***OBSERVER DESIGN PATTERN***
    /**
     * A manager for Property Change Listeners
     * interested in events fired by this class,
     * including changes to the score and level.
     */
    private final PropertyChangeSupport myPcs;



    /**
     * Constructs a new ScoringSystem with initial values.
     * The score is set to 0, the total lines cleared is set to 0,
     * and the level is set to 1.
     */
    public ScoringSystem() {
        super();
        myScore = 0;
        myTotalLinesCleared = 0;
        myLevel = 1;
        myLineCounter = 0;

        // ***OBSERVER DESIGN PATTERN***
        myPcs = new PropertyChangeSupport(this);

        // ***Singleton Design Pattern***
        // Add instance to property change list for the Board class
        final MyBoard ourBoard = Board.getInstance();
        ourBoard.addPropertyChangeListener(this);
    }

    /**
     * Adds points to the score.
     */
    private void addToScore(final int theValue) {
        final int oldVal = myScore;
        myScore += theValue; // 4 points per frozen piece
        // Fire event
        myPcs.firePropertyChange(PropertyChangeEnabledScoring.PROPERTY_SCORE_CHANGE,
                oldVal, myScore);
    }

    /**
     * Updates the score and total lines cleared when a specified number of lines are cleared.
     * The score is increased based on the number of lines cleared and the current level.
     * The level is updated after each line clearance.
     *
     * @param theLines the number of lines cleared
     */
    private void linesCleared(final int theLines) {
        if (theLines > 0 && theLines <= SCORE_LINE_MULTIPLIER.length) {
            addToScore(SCORE_LINE_MULTIPLIER[theLines - 1] * myLevel);
            myTotalLinesCleared += theLines;

            final int oldLevel = myLevel;
            myLevel = (myTotalLinesCleared / LINES_PER_LEVEL) + 1;
            myPcs.firePropertyChange(PROPERTY_LEVEL_CHANGE, oldLevel, myLevel);
        }
    }

    /**
     * Increment the number of lines while the Board
     * class is still counting to calculate the
     * total number of lines cleared at a time.
     */
    private void incrementLineCounter() {
        myLineCounter++;
    }

    @Override
    public void resetScore() {
        myScore = 0;
        myTotalLinesCleared = 0;
        myLevel = 1;
        myLineCounter = 0;
    }

    @Override
    public int getScore() {
        return myScore;
    }

    @Override
    public int getTotalLinesCleared() {
        return myTotalLinesCleared;
    }

    @Override
    public int getLevel() {
        return myLevel;
    }

    @Override
    public int getNextLevelLines() {
        return LINES_PER_LEVEL - (myTotalLinesCleared % LINES_PER_LEVEL);
    }


    // ***OBSERVER DESIGN PATTERN***
    @Override
    public void addPropertyChangeListener(final PropertyChangeListener theListener) {
        myPcs.addPropertyChangeListener(theListener);
    }

    @Override
    public void addPropertyChangeListener(final String thePropertyName,
                                          final PropertyChangeListener theListener) {
        myPcs.addPropertyChangeListener(thePropertyName, theListener);
    }

    @Override
    public void removePropertyChangeListener(final PropertyChangeListener theListener) {
        myPcs.removePropertyChangeListener(theListener);
    }

    @Override
    public void removePropertyChangeListener(final String thePropertyName,
                                             final PropertyChangeListener theListener) {
        myPcs.removePropertyChangeListener(thePropertyName, theListener);
    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if (PROPERTY_CLEAR_ROW.equals(theEvent.getPropertyName())) {
            incrementLineCounter();
        }
        if (PROPERTY_FROZEN_PIECES_CHANGE.equals(theEvent.getPropertyName())) {
            addToScore(SCORE_PIECE_FREEZE);
            linesCleared(myLineCounter);
            myLineCounter = 0;
        }
    }


    /**
     * Returns the specific instance of the ScoringSystem class.
     * @return Returns the specific instance of the ScoringSystem class
     */
    public static PropertyChangeEnabledScoring getInstance() {
        return INSTANCE;
    }
}
