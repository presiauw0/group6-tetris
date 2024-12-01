package model;

import java.beans.PropertyChangeListener;
import java.util.List;

/**
 * Represents the operations supported by a Tetris game board.
 * This interface defines the public methods that the Board class must implement.
 * The Board interface allows clients to retrieve board dimensions,
 * reset the board for a new game,
 * set a non-random sequence of Tetris pieces, and control piece movement.
 *
 * @author Balkirat Singh
 * @author Khalid Rashid
 * @author Preston Sia
 * @version Autumn 2024
 */

public interface MyBoard {
    /**
     * A property fired when a piece is moved or rotated.
     * This event delivers information about the previous
     * MovableTetrisPiece and the new MovableTetrisPiece
     * with state changes (MovableTetrisPiece is immutable,
     * so a new MovableTetrisPiece will contain new changes
     * to position and rotation).
     */
    String PROPERTY_CURRENT_PIECE_CHANGE = "CURRENT PIECE CHANGE";

    /**
     * A property for when the collection of frozen pieces change.
     * This includes when a piece is added or when rows are cleared.
     * It returns a collection (List&lt;Block[]&gt;) that contains the blocks that make
     * up all the currently frozen pieces or fragments thereof.
     */
    String PROPERTY_FROZEN_PIECES_CHANGE = "FROZEN PIECES CHANGE";

    /**
     * A property that lets interested parties know
     * that a row was cleared. It returns a list
     * containing the index of the completed rows
     * found thus far. This event is fired for every row
     * that is found to be complete.
     */
    String PROPERTY_CLEAR_ROW = "ROW CLEARED";

    /**
     * A property that indicates what the previous "next piece"
     * was and what the new "next piece" is queued as. Paremeters
     * indicate the TetrisPiece (enum) of the old and new next piece.
     */
    String PROPERTY_NEXT_PIECE_CHANGE = "THE NEXT GAME PIECE CHANGES";

    /**
     * A property that indicates whether the
     * game over or not. When the game is over
     * an event is fired indicating that this
     * property is true. When a new game is created,
     * an event is fired indicating that this
     * property is false.
     */
    String PROPERTY_GAME_OVER_STATE = "GAME STATE";

    /**
     * Add a PropertyChangeListener to the listener list. The listener is registered for
     * all properties. The same listener object may be added more than once, and will be
     * called as many times as it is added. If listener is null, no exception is thrown and
     * no action is taken.
     *
     * @param theListener The PropertyChangeListener to be added
     */
    void addPropertyChangeListener(PropertyChangeListener theListener);


    /**
     * Add a PropertyChangeListener for a specific property. The listener will be invoked only
     * when a call on firePropertyChange names that specific property. The same listener object
     * may be added more than once. For each property, the listener will be invoked the number
     * of times it was added for that property. If propertyName or listener is null, no
     * exception is thrown and no action is taken.
     *
     * @param thePropertyName The name of the property to listen on.
     * @param theListener The PropertyChangeListener to be added
     */
    void addPropertyChangeListener(String thePropertyName, PropertyChangeListener theListener);

    /**
     * Remove a PropertyChangeListener from the listener list. This removes a
     * PropertyChangeListener that was registered for all properties. If listener was added
     * more than once to the same event source, it will be notified one less time after being
     * removed. If listener is null, or was never added, no exception is thrown and no action
     * is taken.
     *
     * @param theListener The PropertyChangeListener to be removed
     */
    void removePropertyChangeListener(PropertyChangeListener theListener);

    /**
     * Remove a PropertyChangeListener for a specific property. If listener was added more than
     * once to the same event source for the specified property, it will be notified one less
     * time after being removed. If propertyName is null, no exception is thrown and no action
     * is taken. If listener is null, or was never added for the specified property, no
     * exception is thrown and no action is taken.
     *
     * @param thePropertyName The name of the property that was listened on.
     * @param theListener The PropertyChangeListener to be removed
     */
    void removePropertyChangeListener(String thePropertyName,
                                      PropertyChangeListener theListener);

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
