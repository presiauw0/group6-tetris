package edu.uw.tcss.app;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Board;
import model.TetrisPiece;


/**
 * The sandbox class for testing Git.
 *
 * @author Preston Sia (305 Group 6)
 * @version F2024_001
 */
public final class SandBox {

    /**
     * Logger object for logging info and issues with this code.
     */
    public static final Logger LOGGER = Logger.getLogger(SandBox.class.getName());
    /**
     * New line field for use in logging
     */
    private static final String NEWLINE = "\n";

    private SandBox() {
        super();
    }

    static {
        // Level.ALL - Display ALL logging messages
        // Level.OFF - Display NO logging messages
        LOGGER.setLevel(Level.WARNING);
    }

    /**
     * The main driver method for this class.
     * @param theArgs Argument from the command line.
     */
    public static void main(final String[] theArgs) {

        test2();

        final Board b = new Board();
        b.newGame();
        //System.out.println(b);
        LOGGER.info(NEWLINE + b);

        b.step();
        //System.out.println(b);
        LOGGER.info(NEWLINE + b);
        b.rotateCW();
        //System.out.println(b);
        LOGGER.info(NEWLINE + b);
        b.rotateCW();
        //System.out.println(b);
        LOGGER.info(NEWLINE + b);
        b.rotateCW();
        //System.out.println(b);
        LOGGER.info(NEWLINE + b);
        b.rotateCW();
        //System.out.println(b);
        LOGGER.info(NEWLINE + b);
        b.drop();
        //System.out.println(b);
        LOGGER.info(NEWLINE + b);

    }

    /**
     * another test.
     */
    public static void test1() {
        final Board testBoard = new Board();
        LOGGER.warning(testBoard.getWidth() + " by " + testBoard.getHeight());
        //testBoard.newGame(); // must be called before each new game

        final List<TetrisPiece> pieces = new ArrayList<>();
        pieces.add(TetrisPiece.I);
        pieces.add(TetrisPiece.J);
        pieces.add(TetrisPiece.Z);
        testBoard.setPieceSequence(pieces);
        testBoard.newGame();

        LOGGER.warning("TEST BOARD:" + NEWLINE + testBoard);
        testBoard.drop();
        LOGGER.warning("TEST BOARD:" + NEWLINE + testBoard);
        testBoard.drop();
        LOGGER.warning("TEST BOARD:" + NEWLINE + testBoard);
        testBoard.drop();
        LOGGER.warning("TEST BOARD:" + NEWLINE + testBoard);
    }

    /**
     * A second additional test.
     */
    public static void test2() {
        final Board testBoard = new Board();
        LOGGER.warning(testBoard.getWidth() + " by " + testBoard.getHeight());
        //testBoard.newGame(); // must be called before each new game

        final List<TetrisPiece> pieces = new ArrayList<>();
        pieces.add(TetrisPiece.I);
        pieces.add(TetrisPiece.I);
        pieces.add(TetrisPiece.O);
        testBoard.setPieceSequence(pieces);
        testBoard.newGame();

        LOGGER.warning("TEST BOARD" + NEWLINE + testBoard);
        testBoard.left();
        testBoard.left();
        testBoard.left();
        testBoard.drop();
        LOGGER.warning("TEST BOARD" + NEWLINE + testBoard);
        testBoard.right();
        testBoard.drop();
        LOGGER.warning("TEST BOARD" + NEWLINE + testBoard);
        testBoard.right();
        testBoard.right();
        testBoard.right();
        testBoard.right();
        testBoard.drop();
        LOGGER.warning("TEST BOARD" + NEWLINE + testBoard);
    }

}
