package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.MovableTetrisPiece;
import model.MyMovableTetrisPiece;
import model.Point;
import model.TetrisPiece;
import view.colors.TetrisColorScheme;
import view.colors.TetrisColorSchemeDefault;


/**
 * Panel representing the Tetris Board in the Tetris game.
 *
 * @author Preston Sia
 * @version F2024_001
 */
public class TetrisBoardPanel extends JPanel {
    /**
     * Default multiple for the width of the panel.
     * Multiply the block side length by this amount.
     */
    public static final int DEFAULT_BOARD_WIDTH = 10;

    /**
     * Default multiple for the height of the panel.
     * Multiply the block side length by this amount.
     */
    public static final int DEFAULT_BOARD_HEIGHT = 20;

    /**
     * Default block side length in pixels.
     */
    public static final int DEFAULT_BLOCK_WIDTH = 20;

    /**
     * Default stroke/border width.
     */
    public static final int DEFAULT_STROKE = 2;

    /**
     * Default background color.
     */
    public static final Color DEFAULT_BG_COLOR = Color.RED;


    /**
     * Side length of the individual blocks in pixels.
     */
    private final int myBlockWidthPX;

    /**
     * Width of the board in Tetris blocks.
     */
    private final int myBoardWidth;

    /**
     * Height of the board in Tetris blocks.
     */
    private final int myBoardHeight;

    /**
     * Store a list of Tetris pieces to draw.
     */
    private final List<MyMovableTetrisPiece> myTetrisPieces;

    /**
     * Store a color scheme to use for the Tetrominos
     */
    private final TetrisColorScheme myColorScheme;


    /**
     * Constructor to configure the Tetris board
     * using the defaults specified in the static fields.
     */
    public TetrisBoardPanel() {
        this(DEFAULT_BLOCK_WIDTH, DEFAULT_BOARD_WIDTH, DEFAULT_BOARD_HEIGHT);
    }

    /**
     * Constructor to configure the Tetris board
     * with just the block length. Defaults specified
     * above will be used for block length and width.
     *
     * @param theBlockLengthPX Length of a block in pixels
     */
    public TetrisBoardPanel(final int theBlockLengthPX) {
        this(theBlockLengthPX, DEFAULT_BOARD_WIDTH, DEFAULT_BOARD_HEIGHT);
    }

    /**
     * Constructor to configure dimensions and other attributes
     * related to the Tetris board. Takes the length and width
     * of the board, and the side length of a block in pixels.
     *
     * @param theBlockLengthPX Length of a block in pixels
     * @param theBoardWidth Width of the board in blocks
     * @param theBoardHeight Height of the board in blocks
     */
    public TetrisBoardPanel(final int theBlockLengthPX,
                            final int theBoardWidth, final int theBoardHeight) {
        super();

        myBlockWidthPX = theBlockLengthPX;
        myBoardWidth = theBoardWidth;
        myBoardHeight = theBoardHeight;

        myColorScheme = new TetrisColorSchemeDefault();
        myTetrisPieces = new ArrayList<>();

        layoutComponents();
        drawPieces();
    }

    /**
     * Configure the layout the components of the Tetris board
     */
    private void layoutComponents() {
        setBackground(DEFAULT_BG_COLOR);
        setPreferredSize(new Dimension(myBlockWidthPX * myBoardWidth,
                myBlockWidthPX * myBoardHeight));
    }

    @Override
    public void paintComponent(final Graphics theGraphics) {
        // *** BOILERPLATE ***
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;

        // for better graphics display
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);

        // *** CODE FOR TETROMINOS ***
        for (final MyMovableTetrisPiece piece : myTetrisPieces) {
            final Point[] piecePoints = piece.getBoardPoints();

            for (final Point block : piecePoints) {
                final int xCoord = block.x() * myBlockWidthPX;
                final int yCoord = block.y() * myBlockWidthPX;

                final Shape rect = new Rectangle2D.Double(
                        xCoord, yCoord, myBlockWidthPX, myBlockWidthPX
                );

                g2d.setStroke(new BasicStroke(DEFAULT_STROKE));
                g2d.setPaint(myColorScheme.getColor(piece.getTetrisPiece()));
                g2d.fill(rect);
                g2d.setPaint(TetrisColorSchemeDefault.BORDER_COLOR);
                g2d.draw(rect);
            }
        }
    }

    private void drawPieces() {
        // store all pieces here so we can iterate over them
        final TetrisPiece[] pieces = {
            TetrisPiece.I,
            TetrisPiece.J,
            TetrisPiece.L,
            TetrisPiece.O,
            TetrisPiece.S,
            TetrisPiece.T,
            TetrisPiece.Z
        };

        // make a static constant?
        final int pieceOffset = 3;

        for (int i = 0; i < pieces.length; i++) {
            myTetrisPieces.add(new MovableTetrisPiece(
                    pieces[i],
                    new Point(i, myBoardHeight - pieceOffset - (i * pieceOffset))
            ));
        }
    }

    /**
     * Get the offset used to horizontally center a Tetris piece.
     * @return The x-coordinate in blocks
     */
    private int getHorizontalCenterOffset(final TetrisPiece thePiece) {
        return (myBoardWidth / 2) - (int) Math.ceil(thePiece.getWidth() / 2.0);
    }


    // *** TEMPORARY FOR TESTING -- REMOVE WHEN COMPLETE ***

    /**
     * Generate GUI for testing purposes.
     */
    public static void generateGui() {
        final TetrisBoardPanel mainPanel = new TetrisBoardPanel();

        final JFrame window = new JFrame("Test Tetris Board Panel");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setContentPane(mainPanel);
        window.pack();
        window.setVisible(true);
    }

    /**
     * Creates a window to test the Tetris panel.
     */
    public static void main(final String[] theArgs) {
        javax.swing.SwingUtilities.invokeLater(TetrisBoardPanel::generateGui);
    }
}
