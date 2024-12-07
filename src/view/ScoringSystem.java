package view;

import static model.MyBoard.PROPERTY_FROZEN_PIECES_CHANGE;
import static model.MyBoard.PROPERTY_CLEAR_ROW;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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
public class ScoringSystem implements PropertyChangeListener, Scoring {
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
    private static final Scoring INSTANCE = new ScoringSystem();


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

        // ***Singleton Design Pattern***
        final MyBoard ourBoard = Board.getInstance();
        ourBoard.addPropertyChangeListener(this);
    }

    /**
     * Adds points to the score when a piece is frozen in place.
     * This method adds 4 points to the score whenever a piece is frozen.
     */
    private void pieceFrozen() {
        myScore += SCORE_PIECE_FREEZE; // 4 points per frozen piece
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
            myScore += SCORE_LINE_MULTIPLIER[theLines - 1] * myLevel;
            myTotalLinesCleared += theLines;
            myLevel = (myTotalLinesCleared / LINES_PER_LEVEL) + 1;
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

    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if (PROPERTY_CLEAR_ROW.equals(theEvent.getPropertyName())) {
            incrementLineCounter();
        }
        if (PROPERTY_FROZEN_PIECES_CHANGE.equals(theEvent.getPropertyName())) {
            pieceFrozen();
            linesCleared(myLineCounter);
            myLineCounter = 0;
        }
    }


    /**
     * Returns the specific instance of the ScoringSystem class.
     * @return Returns the specific instance of the ScoringSystem class
     */
    public static Scoring getInstance() {
        return INSTANCE;
    }
}
