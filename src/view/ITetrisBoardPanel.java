package view;

/**
 * Interface for public methods in the Tetris Board Panel.
 *
 * @author Preston Sia
 * @version F2024_001
 */
public interface ITetrisBoardPanel {
    /**
     * Get state of whether gridlines are enabled or not.
     *
     * @return Boolean value stating whether gridlines are enabled or not.
     */
    boolean getGridlines();

    /**
     * Toggle gridlines on and off. True for on, false for off.
     * @param theValue A boolean value indicating whether to show gridlines.
     */
    void setGridlines(boolean theValue);

    /**
     * Get the state of whether the ghost piece is enabled or not.
     * @return A boolean value indicating whether to show the ghost piece.
     */
    boolean getGhostPieceState();

    /**
     * Toggle the ghost piece on and off. True for on, false for off.
     * @param theValue A boolean value indicating whether to show the ghost piece.
     */
    void setGhostPieceState(boolean theValue);
}
