package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.Point;
import model.TetrisPiece;



/**
 *  Creates a Panel to display the Next Tetris piece.
 *
 * @author Khalid Rashid
 * @version 1.1
 */
public class NextPeice extends JPanel {

    /** Size of a single block */
    private static final int BLOCK_SIZE = 20;

    /** The width of the panel. */
    private static final int WIDTH = 200;

    /** The height of the panel. */
    private static final int HEIGHT = 200;

    /** instantiates the next tetris piece. */
    private final TetrisPiece myNextPiece;

    /**
     * This lay's out the components.
     * Gets the next Tetris piece to be displayed.
     */
    public NextPeice() {
        super();
        layoutComponents();
        // pass in the next test piece instead of T.
        myNextPiece = TetrisPiece.T;
    }

    /**
     * Creates a border Layout and sets the Background color.
     */
    private void layoutComponents() {
        setLayout(new BorderLayout());
        setBackground(Color.RED);

//        setPreferredSize(new Dimension(WIDTH, HEIGHT));
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
        final Color blockColor = getBlockColor();
        g2d.setPaint(blockColor);

        final int blockSize = BLOCK_SIZE;


        final int pieceWidth = myNextPiece.getWidth() * blockSize;
        final int pieceHeight = myNextPiece.getHeight() * blockSize;
        //final int pieceHeight = (myNextPiece.getHeight() + 2)  * blockSize;

        final int sideWidth = (getWidth() - pieceWidth) / 2;
        final int sideHeight = (getHeight() + pieceHeight) / 2;

        for (final Point point : points) {
            final int xCoordinates = sideWidth + point.x() * blockSize;
            int yCoordinates = sideHeight - point.y() * blockSize;
            if (myNextPiece == TetrisPiece.I) {
                yCoordinates += blockSize;
            }
            g2d.fillRect(xCoordinates, yCoordinates, blockSize, blockSize);
        }
    }
    /**
     * Assign a color for each of the Tetris piece.
     *  returns the color of the tetris peice
     * @return the color of the Tetris piece
     */
    private Color getBlockColor() {

        Color result = null;
        if (myNextPiece == TetrisPiece.J) {
            result = Color.BLACK;
        }
        return result;
    }

    /** Creates and displays the GUI. */
    private static void createAndShowGUI() {
        final JFrame window = new JFrame("Next Tetris Piece");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final NextPeice nextPeicePanel = new NextPeice();
        window.setContentPane(nextPeicePanel);

        window.pack();
        window.setVisible(true);
    }

    /**
     * Create a JFrame to display to the window.
     *
     * @param theArgs Command line arguments, ignored.
     */
    public static void main(final String[] theArgs) {
        javax.swing.SwingUtilities.invokeLater(NextPeice::createAndShowGUI);
    }

}