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
     * Default width of the panel.
     */
    public static final int DEFAULT_WIDTH = 200;

    /**
     * Default height of the panel.
     */
    public static final int DEFAULT_HEIGHT = 400;

    /**
     * Default block side length.
     */
    public static final int BLOCK_WIDTH = 20;

    /**
     * Default stroke/border width.
     */
    public static final int DEFAULT_STROKE = 2;

    /**
     * Default background color.
     */
    public static final Color DEFAULT_BG_COLOR = Color.RED;


    /**
     * Store a list of Tetris pieces to draw
     */
    private final List<MyMovableTetrisPiece> myTetrisPieces;

    /**
     * Store a color scheme to use for the Tetrominos
     */
    private final TetrisColorScheme myColorScheme;

    /**
     * Configures dimensions, colors, and other attributes
     * related to the Tetris board.
     */
    public TetrisBoardPanel() {
        super();

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
        setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
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
                final int xCoord = block.x() * BLOCK_WIDTH;
                final int yCoord = block.y() * BLOCK_WIDTH;

                final Shape rect = new Rectangle2D.Double(
                        xCoord, yCoord, BLOCK_WIDTH, BLOCK_WIDTH
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

        final int boardWidth = DEFAULT_WIDTH / BLOCK_WIDTH;
        final int boardHeight = DEFAULT_HEIGHT / BLOCK_WIDTH;
        // make a static constant?
        final int pieceOffset = 3;

        for (int i = 0; i < pieces.length; i++) {
            final int centerPosition =
                    (boardWidth / 2) - (int) Math.ceil(pieces[i].getWidth() / 2.0);

            myTetrisPieces.add(new MovableTetrisPiece(
                    pieces[i],
                    new Point(centerPosition, boardHeight - pieceOffset - (i * pieceOffset))
            ));
        }
    }


    // *** TEMPORARY FOR TESTING -- REMOVE WHEN COMPLETE ***

    /**
     * Generate GUI for testing purposes.
     */
    public static void generateGui() {
        final TetrisBoardPanel mainPanel = new TetrisBoardPanel();

        final JFrame window = new JFrame("Test Tetrie Board Panel");
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
