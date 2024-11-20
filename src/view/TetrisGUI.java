package view;


import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 *  The graphical user interface for the Tetris game.
 *
 * @author khalid
 * @version 1.1
 */
public final class TetrisGUI extends JPanel {

    /**
     *
     * @param theTitle the title of the Frame.
     */
    public TetrisGUI(final String theTitle) {
        super();
        buildComponents();
        layoutComponents();

    }

    /**
     * get the other classes into the regions they belong (THIS IS INCOMPLETE)
     */
    private void buildComponents() {
    }

    /**
     * Lay out the components and makes this frame visible.
     */
    private void layoutComponents() {
        setLayout(new BorderLayout());
    }

    /**
     * Create the GUI and show it.
     */
    private static void createAndShowGUI() {

        final JFrame window = new JFrame("Tetris");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // this portion is temporary and should be
        // removed in favor of pack() once content is added
        final Dimension frameSize = new Dimension(400, 300);
        window.setSize(frameSize);

        //this should be used instead of frame size
        //window.pack();

        window.setVisible(true);
    }

    /**
     * Creates a JFrame to display the window.
     *
     * @param theArgs Command line arguments, ignored.
     */
    public static void main(final String[] theArgs) {
        javax.swing.SwingUtilities.invokeLater(TetrisGUI::createAndShowGUI);
    }


}
