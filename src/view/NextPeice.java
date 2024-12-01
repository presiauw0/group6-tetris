package view;
import static model.MyBoard.PROPERTY_NEXT_PIECE_CHANGE;


import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JPanel;
import model.Board;
import model.MyBoard;
import model.Point;
import model.TetrisPiece;
import view.colors.TetrisColorScheme;
import view.colors.TetrisColorSchemeDefault;


/**
 *  Creates a Panel to display the Next Tetris piece.
 *
 * @author Khalid Rashid
 * @version 1.1
 */
public class NextPeice extends JPanel implements PropertyChangeListener {

    /** Size of a single block in pixels. */
    private static final int BLOCK_SIZE = 20;

    /** Default stroke/border width. */
    private static final int DEFAULT_STROKE = 2;

    /** The width of the panel. */
    private static final int WIDTH = 150;

    /** The height of the panel. */
    private static final int HEIGHT = 150;

    /** instantiates the next tetris piece. */
    private TetrisPiece myNextPiece;
    /**
     * Store a color scheme to use for the Tetrominos
     */
    private final TetrisColorScheme myColorScheme;

    /**
     * This lay's out the components.
     * Gets the next Tetris piece to be displayed.
     */
    public NextPeice() {
        super();
        layoutComponents();
        myColorScheme = new TetrisColorSchemeDefault();
        // pass in the next test piece instead of T.
        myNextPiece = TetrisPiece.T;
        final MyBoard ourBoard = Board.getInstance();
        ourBoard.addPropertyChangeListener(this);
    }

    /**
     * Creates a border Layout and sets the Background color.
     */
    private void layoutComponents() {
        setLayout(new BorderLayout());
        setBackground(Color.BLUE);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    /**
     * Creates the graphics for a tetris piece,
     * draws the Tetris piece and centers it within the panel.
     * The following code is temporary:
     * Includes a modified Y cordinate to handle the "I" tetris piece
     * @param theGraphics the <code>Graphics</code> object to protect
     */
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        final Point[] points = myNextPiece.getPoints();
        final Color blockColor = myColorScheme.getColor(myNextPiece);

        final int pieceWidth = myNextPiece.getWidth() * BLOCK_SIZE;
        final int pieceHeight = myNextPiece.getHeight() * BLOCK_SIZE;
        final int sideWidth = (getWidth() - pieceWidth) / 2;
        final int sideHeight = (getHeight() + pieceHeight) / 2;

        for (final Point point : points) {
            final int xCoordinates = sideWidth + point.x() * BLOCK_SIZE;
            int yCoordinates = sideHeight - point.y() * BLOCK_SIZE;
            if (myNextPiece == TetrisPiece.I) {
                yCoordinates += BLOCK_SIZE;
            }

            final Shape rect = new Rectangle2D.Double(
                    xCoordinates, yCoordinates, BLOCK_SIZE, BLOCK_SIZE);
            g2d.setStroke(new BasicStroke(DEFAULT_STROKE));
            g2d.setPaint(blockColor);
            g2d.fillRect(xCoordinates, yCoordinates, BLOCK_SIZE, BLOCK_SIZE);
            g2d.setPaint(TetrisColorSchemeDefault.BORDER_COLOR);
            g2d.draw(rect);
        }
    }

    /**
     * A property change Event that handles replacing the NextTetris Piece.
     *
     * @param theEvent A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if (PROPERTY_NEXT_PIECE_CHANGE.equals(theEvent.getPropertyName())) {
            myNextPiece = (TetrisPiece) theEvent.getNewValue();
            repaint();
        }
    }

}