package view.colors;

import java.awt.Color;
import model.Block;
import model.TetrisPiece;


/**
 * Color scheme classes used to set colors
 * for Tetris game pieces. TetrisColorSchemes
 * will have a getter method to evaluate the
 * color that belongs to a particular piece.
 *
 * @author Preston Sia
 * @version F2024_001
 */

public interface TetrisColorScheme {

    /**
     * Returns the corresponding color of a Tetris piece
     * for the current color scheme. If a color is not
     * found, it will return the FALLBACK_COLOR.
     *
     * @param thePiece Tetris piece to evaluate against
     * @return The color representing the specified piece for this color scheme
     */
    Color getColor(TetrisPiece thePiece);

    /**
     * Returns the corresponding color of a Tetris block
     * for the current color scheme. If a color is not
     * found, it will return the FALLBACK_COLOR.
     *
     * @param theBlock Tetris Block enum to evaluate against
     * @return The color representing the specified block for this color scheme
     */
    Color getColor(Block theBlock);

}
