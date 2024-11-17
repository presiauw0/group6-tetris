package model;

import java.util.List;

/**
 * Represents the operations supported by a Tetris game board.
 * This interface defines the public methods that the Board class must implement.
 * The Board interface allows clients to retrieve board dimensions,
 * reset the board for a new game,
 * set a non-random sequence of Tetris pieces, and control piece movement.
 *
 * @author Balkirat Singh
 * @version Autumn 2024
 */

public interface Board {
    /**
     * Returns the width of the board.
     *
     * @return the width of the board
     */
    int getWidth();

    /**
     * Returns the height of the board.
     *
     * @return the height of the board
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
