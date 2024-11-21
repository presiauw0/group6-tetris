package view.colors;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
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
     * Color to be used if no specific color is found
     * for a particular piece.
     */
    public static final Color FALLBACK_COLOR = Color.black;

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
     * Constructor for creating the map of colors.
     */
    public TetrisColorSchemeDefault() {
        super();
        myColorMap = new HashMap<>();

        setColors();
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

    @Override
    public Color getColor(final TetrisPiece thePiece) {
        Color someColor = myColorMap.get(thePiece);

        if (someColor == null) {
            someColor = FALLBACK_COLOR;
        }

        return someColor;
    }
}
