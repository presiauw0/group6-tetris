package view;


import java.awt.*;
import javax.swing.*;


/**
 *  The graphical user interface for the Tetris game.
 *
 * @author khalid
 * @version 1.1
 */
public final class TetrisGUI extends JPanel {

/** The Tetris Board Panel. */
private final TetrisBoardPanel myBoardPanel;

/** The Next Piece Panel. */
private final NextPeice myNextPeicePanel;

/** The Scoreboard Panel. */
private final ScoreBoard myScoreBoardPanel;

/** The main game JFrame. */
private final JFrame myFrame;

/**
 * Constructs the Tetris GUI, integrating the panels and menu bar.
 *
 * @param theTitle the title of the Frame.
 */
public TetrisGUI(final String theTitle) {
    super();
    myFrame = new JFrame(theTitle);

    myBoardPanel = new TetrisBoardPanel();
    myNextPeicePanel = new NextPeice();
    myScoreBoardPanel = new ScoreBoard();

    buildMenu();
    layoutComponents();
}

/**
 * Builds the menu bar for the Tetris GUI.
 */
private void buildMenu() {
    final JMenuBar menuBar = new JMenuBar();

    final JMenu gameMenu = new JMenu("Game");
    final JMenuItem newGameItem = new JMenuItem("New Game");
    final JMenuItem pauseGameItem = new JMenuItem("Pause/Resume");
    final JMenuItem exitItem = new JMenuItem("Exit");

    newGameItem.addActionListener(e -> startNewGame());
    pauseGameItem.addActionListener(e -> togglePauseResume());
    exitItem.addActionListener(e -> System.exit(0));

    gameMenu.add(newGameItem);
    gameMenu.add(pauseGameItem);
    gameMenu.addSeparator();
    gameMenu.add(exitItem);

    final JMenu helpMenu = new JMenu("Help");
    final JMenuItem howToPlayItem = new JMenuItem("How to Play");
    final JMenuItem aboutItem = new JMenuItem("About");

    howToPlayItem.addActionListener(e -> showHowToPlayDialog());
    aboutItem.addActionListener(e -> JOptionPane.showMessageDialog(
            myFrame,
            "Tetris Game v1.0\nCreated with Swing.",
            "About",
            JOptionPane.INFORMATION_MESSAGE
    ));

    helpMenu.add(howToPlayItem);
    helpMenu.add(aboutItem);

    menuBar.add(gameMenu);
    menuBar.add(helpMenu);

    myFrame.setJMenuBar(menuBar);
}

/**
 * Lays out the components for the Tetris GUI.
 */
private void layoutComponents() {
    // Set the main layout for the entire GUI
        setLayout(new BorderLayout());

    // Add the Tetris Board Panel to the CENTER region
        add(myBoardPanel, BorderLayout.CENTER);

    // Create a right-side panel to hold Next Piece and Scoreboard
        final JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS)); // Vertical layout

    // Add the Next Piece and Scoreboard to the right panel
        rightPanel.add(Box.createVerticalStrut(10)); // Add vertical spacing
        rightPanel.add(myNextPeicePanel);
        rightPanel.add(Box.createVerticalStrut(10)); // Add vertical spacing
        rightPanel.add(Box.createHorizontalStrut(10)); // Add vertical spacing
        rightPanel.add(myScoreBoardPanel);

    // Add the right panel to the EAST region
        add(rightPanel, BorderLayout.EAST);

    // Set up the JFrame
        myFrame.setContentPane(this);
        myFrame.setPreferredSize(new Dimension(400, 400)); // Adjust overall size
        myFrame.pack();
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setVisible(true);
}

/**
 * Starts a new game and notifies the user.
 */
    private void startNewGame() {
        JOptionPane.showMessageDialog(myFrame, "Starting a new game!");
    }

/**
 * Toggles between pausing and resuming the game and notifies the user.
 */
    private void togglePauseResume() {
        JOptionPane.showMessageDialog(myFrame, "Toggling pause/resume!");
    }

/**
 * Displays a dialog with instructions on how to play the game.
 */
    private void showHowToPlayDialog() {
        JOptionPane.showMessageDialog(
            myFrame,
            """
                    How to Play:
                    1. Use arrow keys to move and rotate blocks.
                    2. Complete rows to clear them.
                    3. The game ends when blocks reach the top.
                    """,
            "How to Play",
            JOptionPane.INFORMATION_MESSAGE
        );
}

/**
 * Creates and displays the GUI.
 */
    private static void createAndShowGUI() {
        new TetrisGUI("Tetris Game");
}

/**
 * Main method to launch the application.
 *
 * @param args Command line arguments, ignored.
 */
    public static void main(final String[] args) {
        javax.swing.SwingUtilities.invokeLater(TetrisGUI::createAndShowGUI);
}
}