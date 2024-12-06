package view;

import model.MyMovableTetrisPiece;

/**
 * Calculates where the ghost piece should render on screen.
 *
 * @author Preston Sia
 * @version F2024_001
 */
@FunctionalInterface
public interface GhostCalc {
    /**
     * Returns a MovableTetrisPiece that represents the ghost piece.
     * This can be drawn directly on screen in the correct position.
     * @return A MovableTetrisPiece
     */
    MyMovableTetrisPiece getGhost();
}
