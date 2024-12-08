package view.colors;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import model.Block;
import model.TetrisPiece;


/**
 * Default color scheme for Tetris game pieces.
 *
 * @author Preston Sia
 * @version F2024_001
 */
public final class TetrisColorSchemeDefault implements TetrisColorScheme {
    /**
     * Default border color for blocks.
     */
    public static final Color BORDER_COLOR = Color.gray;

    /**
     * Default border color for ghost pieces.
     */
    public static final Color GHOST_BORDER_COLOR = new Color(255, 215, 0);

    /**
     * Score panel background color.
     */
    public static final Color SCORE_BG_COLOR = new Color(48, 48, 48);

    /**
     * Score panel foreground (text) color.
     */
    public static final Color SCORE_FG_COLOR = new Color(255, 215, 0);

    /**
     * Score panel instruction text color.
     */
    public static final Color INSTRUCTIONS_FG_COLOR = new Color(176, 176, 176);

    /**
     * Color to be used if no specific color is found
     * for a particular piece.
     */
    public static final Color FALLBACK_COLOR = Color.black;

    /**
     * Color to be used on the background of the board.
     */
    public static final Color BACKGROUND_COLOR = new Color(204, 0, 0);

    /**
     * Default color for I pieces.
     */
    public static final Color COLOR_I = Color.cyan;

    /**
     * Default color for J pieces.
     */
    public static final Color COLOR_J = Color.blue;

    /**
     * Default color for L pieces.
     */
    public static final Color COLOR_L = Color.orange;

    /**
     * Default color for O pieces.
     */
    public static final Color COLOR_O = Color.yellow;

    /**
     * Default color for S pieces.
     */
    public static final Color COLOR_S = Color.green;

    /**
     * Default color for T pieces.
     */
    public static final Color COLOR_T = new Color(50, 0, 110);

    /**
     * Default color for Z pieces.
     */
    public static final Color COLOR_Z = Color.pink;


    /**
     * Map of colors used with getter methods.
     */
    private final Map<TetrisPiece, Color> myColorMap;

    /**
     * Map of colors for Tetris Block enum types.
     */
    private final Map<Block, Color> myColorMapForBlock;


    /**
     * Constructor for creating the map of colors.
     */
    public TetrisColorSchemeDefault() {
        super();
        myColorMap = new HashMap<>();
        myColorMapForBlock = new HashMap<>();

        setColors();
        setColorsForBlock();
    }

    private void setColors() {
        myColorMap.put(TetrisPiece.I, COLOR_I);
        myColorMap.put(TetrisPiece.J, COLOR_J);
        myColorMap.put(TetrisPiece.L, COLOR_L);
        myColorMap.put(TetrisPiece.O, COLOR_O);
        myColorMap.put(TetrisPiece.S, COLOR_S);
        myColorMap.put(TetrisPiece.T, COLOR_T);
        myColorMap.put(TetrisPiece.Z, COLOR_Z);
    }

    private void setColorsForBlock() {
        myColorMapForBlock.put(Block.I, COLOR_I);
        myColorMapForBlock.put(Block.J, COLOR_J);
        myColorMapForBlock.put(Block.L, COLOR_L);
        myColorMapForBlock.put(Block.O, COLOR_O);
        myColorMapForBlock.put(Block.S, COLOR_S);
        myColorMapForBlock.put(Block.T, COLOR_T);
        myColorMapForBlock.put(Block.Z, COLOR_Z);
        myColorMapForBlock.put(Block.EMPTY, null);
    }

    @Override
    public Color getColor(final TetrisPiece thePiece) {
        Color someColor = myColorMap.get(thePiece);

        if (someColor == null) {
            someColor = FALLBACK_COLOR;
        }

        return someColor;
    }

    @Override
    public Color getColor(final Block theBlock) {
        Color someColor = myColorMapForBlock.get(theBlock);

        if (theBlock != Block.EMPTY && someColor == null) {
            someColor = FALLBACK_COLOR;
        }

        return someColor;
    }
}
