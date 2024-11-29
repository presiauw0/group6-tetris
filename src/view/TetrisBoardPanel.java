package view;

import static model.MyBoard.PROPERTY_GAME_OVER_STATE;
import static model.MyBoard.PROPERTY_CURRENT_PIECE_CHANGE;
import static model.MyBoard.PROPERTY_FROZEN_PIECES_CHANGE;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.Block;
import model.Board;
import model.MovableTetrisPiece;
import model.MyBoard;
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
public class TetrisBoardPanel extends JPanel implements PropertyChangeListener {
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
     * Indicates whether the game is over or not.
     */
    private boolean myGameOver;

    /**
     * Store the current piece for display.
     */
    private MyMovableTetrisPiece myCurrentPiece;

    /**
     * Store all the frozen blocks.
     */
    private List<Block[]> myFrozenBlocks;

    /**
     * Store a list of Tetris pieces to draw
     * for debugging purposes.
     */
    private final List<MyMovableTetrisPiece> myTetrisPiecesDbg;

    /**
     * Boolean value to indicate whether to show gridlines.
     */
    private boolean myShowGridLines;

    /**
     * Store a color scheme to use for the Tetrominos
     */
    private final TetrisColorScheme myColorScheme;


    //TODO remove constructors for setting width and height
    //  must retrieve from board
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
        myTetrisPiecesDbg = new ArrayList<>();

        myShowGridLines = false;

        myGameOver = true;

        layoutComponents();
        drawPiecesDbg();

        final MyBoard ourBoard = Board.getInstance();
        ourBoard.addPropertyChangeListener(this);
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
        // TODO Tetris pieces from sprint1 are now considered
        //  a debugging feature and displayed as a spash screen.
        //  this needs to be changed later.
        if (myGameOver) {
            paintHelperDrawPiecesDebug(g2d);
        } else {
            paintHelperDrawGamePiece(g2d);
            paintHelperDrawGameFrozen(g2d);
        }

        // *** CODE FOR GRIDLINES ***
        if (myShowGridLines) {
            paintHelperDrawGridlines(g2d);
        }
    }

    /**
     * Helper method for the paintComponent method for
     * drawing gridlines.
     *
     * @param theGraphics A Graphics2D object used as a drawing canvas.
     */
    private void paintHelperDrawGridlines(final Graphics2D theGraphics) {
        theGraphics.setStroke(new BasicStroke(1));
        theGraphics.setPaint(TetrisColorSchemeDefault.BORDER_COLOR);
        for (int i = 0; i < myBoardWidth; i++) {
            for (int j = 0; j < myBoardHeight; j++) {
                final Shape gridRect = new Rectangle2D.Double(
                        i * myBlockWidthPX, j * myBoardHeight,
                        myBlockWidthPX, myBlockWidthPX
                );
                theGraphics.draw(gridRect);
            }
        }
    }

    private void paintHelperDrawPiecesDebug(final Graphics2D theGraphics) {
        for (final MyMovableTetrisPiece piece : myTetrisPiecesDbg) {
            final Point[] piecePoints = piece.getBoardPoints();

            for (final Point block : piecePoints) {
                final int xCoord = block.x() * myBlockWidthPX;
                final int yCoord = block.y() * myBlockWidthPX;

                final Shape rect = new Rectangle2D.Double(
                        xCoord, yCoord, myBlockWidthPX, myBlockWidthPX
                );

                theGraphics.setStroke(new BasicStroke(DEFAULT_STROKE));
                theGraphics.setPaint(myColorScheme.getColor(piece.getTetrisPiece()));
                theGraphics.fill(rect);
                theGraphics.setPaint(TetrisColorSchemeDefault.BORDER_COLOR);
                theGraphics.draw(rect);
            }
        }
    }

    private void paintHelperDrawGamePiece(final Graphics2D theGraphics) {
        if (myCurrentPiece != null) {
            final Point[] piecePoints = myCurrentPiece.getBoardPoints();

            for (final Point block : piecePoints) {
                final int xCoord = block.x() * myBlockWidthPX;
                final int yCoord = (myBoardHeight - block.y() - 1) * myBlockWidthPX;

                final Shape rect = new Rectangle2D.Double(
                        xCoord, yCoord, myBlockWidthPX, myBlockWidthPX
                );

                theGraphics.setStroke(new BasicStroke(DEFAULT_STROKE));
                theGraphics.setPaint(myColorScheme.getColor(myCurrentPiece.getTetrisPiece()));
                theGraphics.fill(rect);
                theGraphics.setPaint(TetrisColorSchemeDefault.BORDER_COLOR);
                theGraphics.draw(rect);
            }
        }
    }

    private void paintHelperDrawGameFrozen(final Graphics2D theGraphics) {
        if (myFrozenBlocks != null && !myFrozenBlocks.isEmpty()) {
            for (int i = 0; i < myFrozenBlocks.size(); i++) {
                for (int j = 0; j < myFrozenBlocks.get(i).length; j++) {

                    if (myFrozenBlocks.get(i)[j] != null) {
                        final int xCoord = j * myBlockWidthPX;
                        final int yCoord = (myBoardHeight - i - 1) * myBlockWidthPX;

                        final Shape rect = new Rectangle2D.Double(
                                xCoord, yCoord, myBlockWidthPX, myBlockWidthPX
                        );

                        theGraphics.setStroke(new BasicStroke(DEFAULT_STROKE));
                        theGraphics.setPaint(myColorScheme.getColor(myFrozenBlocks.get(i)[j]));
                        theGraphics.fill(rect);
                        theGraphics.setPaint(TetrisColorSchemeDefault.BORDER_COLOR);
                        theGraphics.draw(rect);
                    }

                }
            }
        }
    }

    /**
     * Add Tetris pieces to drawing pipeling for display
     * when debugging.
     */
    private void drawPiecesDbg() {
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
            myTetrisPiecesDbg.add(new MovableTetrisPiece(
                    pieces[i],
                    new Point(i, myBoardHeight - pieceOffset - (i * pieceOffset))
            ));
        }
    }

    /**
     * Toggle gridlines on and off.
     */
    private void setGridlines(final boolean theValue) {
        myShowGridLines = theValue;
    }

    /**
     * Get the offset used to horizontally center a Tetris piece.
     * @return The x-coordinate in blocks
     */
    private int getHorizontalCenterOffset(final TetrisPiece thePiece) {
        return (myBoardWidth / 2) - (int) Math.ceil(thePiece.getWidth() / 2.0);
    }


    // *** PROPERTY CHANGE LISTENERS ***
    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if (PROPERTY_GAME_OVER_STATE.equals(theEvent.getPropertyName())) {
            propGameOverCheck((boolean) theEvent.getNewValue());
        }
        if (PROPERTY_CURRENT_PIECE_CHANGE.equals(theEvent.getPropertyName())) {
            propCurrentPieceChange((MyMovableTetrisPiece) theEvent.getOldValue(),
                                    (MyMovableTetrisPiece) theEvent.getNewValue());
        }

        if (PROPERTY_FROZEN_PIECES_CHANGE.equals(theEvent.getPropertyName())) {
            final Object newVal = theEvent.getNewValue();
            final List<?> newValCast;
            if (newVal instanceof List) {
                newValCast = (List<?>) newVal;
                propFrozenPieceChange((List<Block[]>) newValCast);
                // FIXME safe cast
            }

        }

    }

    private void propGameOverCheck(final boolean theGameOver) {
        if (!theGameOver) {
            myGameOver = false;
            myFrozenBlocks = null;
            repaint();
        } else {
            myGameOver = true;
            // TODO Display something when the game is over
            repaint();
        }

    }

    private void propCurrentPieceChange(final MyMovableTetrisPiece theOldPiece,
                                     final MyMovableTetrisPiece theNewPiece) {
        if (theNewPiece != null) {
            myCurrentPiece = theNewPiece;
        }
        repaint();
    }

    private void propFrozenPieceChange(final List<Block[]> theBlocks) {
        if (theBlocks != null) {
            myFrozenBlocks = theBlocks;
        }

        repaint();
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
