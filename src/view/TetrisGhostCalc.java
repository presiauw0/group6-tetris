package view;

import java.util.List;
import model.Block;
import model.MyMovableTetrisPiece;
import model.Point;

/**
 * Calculates where the ghost piece should render on screen.
 *
 * @author Preston Sia
 * @version F2024_001
 */
public class TetrisGhostCalc implements GhostCalc {
    /**
     * A list of currently frozen pieces.
     */
    private final List<Block[]> myFrozenBlocks;
    /**
     * The MovableTetrisPiece used for calculation.
     */
    private final MyMovableTetrisPiece myMovablePiece;
    /**
     * Width of the board
     */
    private final int myWidth;
    /**
     * Height of the board
     */
    private final int myHeight;

    /**
     * Constructor for the TetrisGhostCalc class which
     * stores the list of frozen pieces and the
     * movable piece.
     *
     * @param theFrozenBlocks List of frozen pieces with block arrays
     * @param theMovablePiece The MovableTetrisPiece object
     * @param theWidth Board witdh
     * @param theHeight Board height
     */
    public TetrisGhostCalc(final List<Block[]> theFrozenBlocks,
                           final MyMovableTetrisPiece theMovablePiece,
                           final int theWidth, final int theHeight) {
        super();

        myFrozenBlocks = theFrozenBlocks;
        myMovablePiece = theMovablePiece;
        myWidth = theWidth;
        myHeight = theHeight;
    }

    /**
     * Checks if a coordinate block in the list of frozen blocks is empty.
     *
     * @param theX integer x coordinate
     * @param theY integer y coordinate
     * @return Status indicating whether the coordinate is empty
     */
    private boolean isEmpty(final int theX, final int theY) {
        boolean status = true;
        if (myFrozenBlocks != null) {
            if (myFrozenBlocks.get(theY)[theX] != null) {
                status = false;
            }
        }

        return status;
    }

    /**
     * Checks if a MovableTetrisPiece is overlapping
     * a frozen block.
     *
     * @param thePiece MovableTetrisPiece to test against.
     * @return Boolean true or false
     */
    private boolean isOverlap(final MyMovableTetrisPiece thePiece) {
        boolean overlapState = false;

        final Point[] piecePoints = thePiece.getBoardPoints();
        for (final Point block : piecePoints) {
            if (!isEmpty(block.x(), block.y())) {
                overlapState = true;
                break;
            }
        }

        return overlapState;
    }

    /**
     * Tests to see if a piece is actually on the board
     * (within set width and height, withing range of frozen pieces,
     * and greater than or equal to 0).
     *
     * @param thePiece MovableTetrisPiece to test against
     * @return Boolean true or false
     */
    private boolean isOnBoard(final MyMovableTetrisPiece thePiece) {
        boolean status = true;

        final Point[] piecePoints = thePiece.getBoardPoints();
        for (final Point block : piecePoints) {
            if (block.x() < 0 || block.x() >= myWidth) {
                status = false;
            }
            if (block.y() < 0 || block.y() >= myHeight) {
                status = false;
            }

            if (myFrozenBlocks != null) {
                if (block.x() >= myFrozenBlocks.getFirst().length) {
                    status = false;
                }
                if (block.x() >= myFrozenBlocks.size()) {
                    status = false;
                }
            }

        }



        return status;
    }

    @Override
    public MyMovableTetrisPiece getGhost() {
        MyMovableTetrisPiece newPiece = myMovablePiece;
        MyMovableTetrisPiece downPiece = myMovablePiece;

        while (isOnBoard(downPiece) && !isOverlap(downPiece)) {
            newPiece = downPiece;
            downPiece = downPiece.down();
        }

        return newPiece;
    }
}
