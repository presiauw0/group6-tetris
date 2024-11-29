package view;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.Timer;
import model.Board;
import model.MyBoard;

/**
 * The graphical user interface for the Tetris game.
 * Combines the menu bar, game board, next piece preview, and scoreboard.
 * Updated for Sprint 2 to include integration with Board, Timer, and listeners.
 *
 * @author Abdulrahman Hassan, Balkirat Singh
 * @version Autumn 2024
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

    /** The primary model object using the Board interface. */
    private final Board myBoard;

    /** The game timer controlling the game loop. */
    private final Timer myTimer;

    /**
     * Constructs the Tetris GUI, integrating the panels and menu bar.
     *
     * @param theTitle the title of the Frame.
     */
    public TetrisGUI(final String theTitle) {
        super();
        myFrame = new JFrame(theTitle);
        myBoard = Board.getInstance(); // Factory method

        myBoardPanel = new TetrisBoardPanel();
        myNextPeicePanel = new NextPeice();
        myScoreBoardPanel = new ScoreBoard();

        myBoard.newGame(); // Ensure the game starts with an active piece

        // Timer ticks every 500 ms and calls step() on the Board
        myTimer = new Timer(500, e -> myBoard.step());

        buildMenu();
        layoutComponents();
        addListeners();
        addPropertyChangeListeners();
    }

    /**
     * Builds the menu bar for the Tetris GUI.
     */
    private void buildMenu() {
        final JMenuBar menuBar = new JMenuBar();
        menuBar.add(buildGameMenu());
        menuBar.add(buildHelpMenu());
        myFrame.setJMenuBar(menuBar);
    }

    private JMenu buildGameMenu() {
        final JMenu gameMenu = new JMenu("Game");

        final JMenuItem newGameItem = new JMenuItem("New Game");
        newGameItem.addActionListener(e -> {
            //myBoard.reset();
            myTimer.start();
        });

        gameMenu.add(newGameItem);
        return gameMenu;
    }

    private JMenu buildHelpMenu() {
        final JMenu helpMenu = new JMenu("Help");

        final JMenuItem howToPlayItem = new JMenuItem("How to Play");
        howToPlayItem.addActionListener(e -> showHowToPlayDialog());

        helpMenu.add(howToPlayItem);
        return helpMenu;
    }

    /**
     * Lays out the components for the Tetris GUI.
     */
    private void layoutComponents() {
        setLayout(new BorderLayout());
        add(myBoardPanel, BorderLayout.CENTER);

        final JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(myNextPeicePanel, BorderLayout.NORTH);
        rightPanel.add(myScoreBoardPanel, BorderLayout.CENTER);

        add(rightPanel, BorderLayout.EAST);

        myFrame.setContentPane(this);
        myFrame.pack();
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setVisible(true);
    }

    /**
     * Adds necessary listeners to the GUI.
     */
    private void addListeners() {
        // KeyListener for user input
        addKeyListener(new MyKeyAdapter());
        setFocusable(true);
        requestFocusInWindow();
    }

    /**
     * Adds property change listeners to the Board.
     */
    private void addPropertyChangeListeners() {
        myBoard.addPropertyChangeListener("gameOver", evt -> myTimer.stop());
    }

    /**
     * Displays a dialog with instructions on how to play the game.
     */
    private void showHowToPlayDialog() {
        // Implementation for showing the "How to Play" dialog
    }

    /**
     * Main method to launch the application.
     *
     * @param theArgs Command line arguments, ignored.
     */
    public static void main(final String[] theArgs) {
        javax.swing.SwingUtilities.invokeLater(() -> new TetrisGUI("Tetris Game"));
    }

    private final class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(final KeyEvent theEvent) {
            switch (theEvent.getKeyCode()) {
                case KeyEvent.VK_LEFT -> myBoard.left();
                case KeyEvent.VK_RIGHT -> myBoard.right();
                case KeyEvent.VK_DOWN -> myBoard.down();
                case KeyEvent.VK_SPACE -> myBoard.drop();
                case KeyEvent.VK_UP -> myBoard.rotateCW();
                case KeyEvent.VK_P -> {
                    if (myTimer.isRunning()) {
                        myTimer.stop();
                    } else {
                        myTimer.start();
                    }
                }
                default -> { } // No action for other keys
            }
        }
    }
}
