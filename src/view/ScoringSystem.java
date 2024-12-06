package view;

/**
 * The ScoringSystem class is responsible for tracking the score, total lines cleared,
 * and the current level in a Tetris-like game. It provides methods to update the score
 * based on game actions and calculate the current level based on the number of lines cleared.
 *
 * @author Balkirat Singh, Abdulrahman Hassan
 * @version 1.1
 */
public class ScoringSystem {
    private int score;
    private int totalLinesCleared;
    private int level;

    // Initial level lines needed to progress
    private static final int LINES_PER_LEVEL = 5;

    /**
     * Constructs a new ScoringSystem with initial values.
     * The score is set to 0, the total lines cleared is set to 0,
     * and the level is set to 1.
     */
    public ScoringSystem() {
        super();
        score = 0;
        totalLinesCleared = 0;
        level = 1;
    }

    /**
     * Adds points to the score when a piece is frozen in place.
     * This method adds 4 points to the score whenever a piece is frozen.
     */
    public void pieceFrozen() {
        score += 4; // 4 points per frozen piece
    }

    /**
     * Updates the score and total lines cleared when a specified number of lines are cleared.
     * The score is increased based on the number of lines cleared and the current level.
     * The level is updated after each line clearance.
     *
     * @param lines the number of lines cleared
     */
    public void linesCleared(final int lines) {
        if (lines >= 1 && lines <= 4) {
            final int[] lineScores = {40, 100, 300, 1200};
            score += lineScores[lines - 1] * level;
            totalLinesCleared += lines;
        }
        updateLevel();
    }

    /**
     * Updates the current level based on the number of lines cleared.
     * The level increases after every LINES_PER_LEVEL lines cleared.
     */
    private void updateLevel() {
        level = (totalLinesCleared / LINES_PER_LEVEL) + 1;
    }

    /**
     * Gets the current score.
     *
     * @return the current score
     */
    public int getScore() {
        return score;
    }

    /**
     * Gets the total number of lines cleared so far in the game.
     *
     * @return the total number of lines cleared
     */
    public int getTotalLinesCleared() {
        return totalLinesCleared;
    }

    /**
     * Gets the current level of the game.
     * The level increases as more lines are cleared.
     *
     * @return the current level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Calculates the number of lines required to reach the next level.
     * This method calculates how many more lines need to be cleared to reach the next level.
     *
     * @return the number of lines required to reach the next level
     */
    public int getNextLevelLines() {
        return LINES_PER_LEVEL - (totalLinesCleared % LINES_PER_LEVEL);
    }
}
