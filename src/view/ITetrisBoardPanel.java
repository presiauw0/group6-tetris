package view;

/**
 * Interface for public methods in the Tetris Board Panel.
 *
 * @author Preston Sia
 * @version F2024_001
 */
public interface ITetrisBoardPanel {
    /**
     * Toggle gridlines on and off. True for on, false for off.
     * @param theValue A boolean value indicating whether to show gridlines.
     */
    void setGridlines(boolean theValue);
}
