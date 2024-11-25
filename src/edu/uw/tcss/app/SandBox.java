package edu.uw.tcss.app;

import java.util.logging.Level;
import java.util.logging.Logger;
import model.Board;


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
    /**
     * A string for a logger, Starting a new Game
     */
    private static final String START = "Start a new game";
    /**
     * A string for a logger, Move Tetris Piece Once
     */
    private static final String STEP = "Tetris Piece Move down one step";
    /**
     * A string for a logger, moving left
     */
    private static final String MOVE_LEFT = "Move Tetris piece Left";
    /**
     * A string for a logger, moving Right
     */
    private static final String MOVE_RIGHT = "Move Tetris piece Right";
    /**
     * A string for a logger, Tetris piece rotating clockwise
     */
    private static final String MOVE_CW = "Rotate clockwise";
    /**
     * A string for a logger, Tetris piece rotating Counter-clockwise
     */
    private static final String MOVE_CCW = "Rotate Counter clockwise";
    /**
     * A string for a logger, Tetris piece dropped all the way down
     */
    private static final String DROP = "Drop Tetris Piece to the bottom of the Board";

    private SandBox() {
        super();
    }

    static {
        // Level.ALL - Display ALL logging messages
        // Level.OFF - Display NO logging messages
        LOGGER.setLevel(Level.ALL);
    }

    /**
     * The main driver method for this class.
     * @param theArgs Argument from the command line.
     */
    public static void main(final String[] theArgs) {
        final Board b = new Board();
        b.newGame();
        LOGGER.info(START + NEWLINE + b);
        //System.out.println(b);
        b.step();
        LOGGER.info(STEP + NEWLINE + b);
        b.rotateCW();
        //System.out.println(b);
        LOGGER.info(MOVE_CW + NEWLINE + b);
        b.rotateCCW();
        //System.out.println(b);
        LOGGER.info(MOVE_CCW + NEWLINE + b);
        b.rotateCW();
        //System.out.println(b);
        LOGGER.info(MOVE_CW + NEWLINE + b);
        b.left();
        LOGGER.info(MOVE_LEFT + NEWLINE + b);
        b.right();
        LOGGER.info(MOVE_RIGHT + NEWLINE + b);
        b.right();
        LOGGER.info(MOVE_RIGHT + NEWLINE + b);
        b.drop();
        //System.out.println(b);
        LOGGER.info(DROP + NEWLINE + b);
        //System.out.println(b);
    }

}
