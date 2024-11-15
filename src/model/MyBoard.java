package model;

import java.util.List;

/**
 * Represents a Tetris board. Board objects communicate with clients via Observer pattern.
 * <p>Clients can expect Board objects to call norifyObservers with four different
 * data types:</p>
 * <dl>
 * <dt>{@code List<Block[]>}</dt>
 * <dd>Represents the non-moving pieces on the Board. i.e. Frozen Blocks</dd>
 * <dt>{@link MovableTetrisPiece MovableTerisPiece}</dt>
 * <dd>Represents current moving Piece.</dd>
 * <dt>{@link TetrisPiece TertisPiece}</dt>
 * <dd>Represents next Piece.</dd>
 * <dt>{@code Integer[]}</dt>
 * <dd>The size of the array represents the number of rows of Frozen Blocks removed.</dd>
 * <dt>{@code Boolean}</dt>
 * <dd>When true, the game is over. </dd>
 * </dl>
 *
 */
public interface MyBoard {
    /**
     * Get the width of the board.
     *
     * @return Width of the board.
     */
    int getWidth();
    /**
     * Get the height of the board.
     *
     * @return Height of the board.
     */
    int getHeight();
    /**
     * Resets the board for a new game.
     * This method must be called before the first game
     * and before each new game.
     */
    void newGame();
    /**
     * Sets a non random sequence of pieces to loop through.
     *
     * @param thePieces the List of non random TetrisPieces.
     */
    void setPieceSequence(List<TetrisPiece> thePieces);
    /**
     * Advances the board by one 'step'.
     * <p>
     * This could include
     * - moving the current piece down 1 line
     * - freezing the current piece if appropriate
     * - clearing full lines as needed
     */
    void step();
    /**
     * Try to move the movable piece down.
     * Freeze the Piece in position if down tries to move into an illegal state.
     * Clear full lines.
     */
    void down();
    /**
     * Try to move the movable piece left.
     */
    void left();
    /**
     * Try to move the movable piece right.
     */
    void right();
    /**
     * Try to rotate the movable piece in the clockwise direction.
     */
    void rotateCW();
    /**
     * Try to rotate the movable piece in the counter-clockwise direction.
     */
    void rotateCCW();
    /**
     * Drop the piece until piece is set.
     */
    void drop();
}
