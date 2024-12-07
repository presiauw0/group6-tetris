package view.score;

/**
 * Scoring classes are responsible for tracking and reporting
 * the score of a Tetris game. It must provide methods for getting
 * the latest scoring information, including the current score,
 * number of lines clear, current level, and the number of lines
 * remaining to reach the next level.
 *
 * @author Preston Sia
 * @version F2024_001
 */
public interface Scoring {
    /**
     * Gets the current score.
     *
     * @return return the current score
     */
    int getScore();

    /**
     * Gets the total number of lines cleared so far in the game.
     *
     * @return the total number of lines cleared
     */
    int getTotalLinesCleared();

    /**
     * Gets the current level of the game.
     * The level increases as more lines are cleared.
     *
     * @return the current level
     */
    int getLevel();

    /**
     * Calculates the number of lines required to reach the next level.
     * This method calculates how many more lines need to be cleared to reach the next level.
     *
     * @return the number of lines required to reach the next level
     */
    int getNextLevelLines();

    /**
     * Reset the scoreboard to its original state.
     */
    void resetScore();

}
