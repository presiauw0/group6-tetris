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

        LOGGER.info("Tetris Piece moved one block down" + NEWLINE + b);
        b.left();
        //System.out.println(b);
        LOGGER.info(NEWLINE + b);
        b.drop();
        //System.out.println(b);
        LOGGER.info(NEWLINE + b);

    }

}
