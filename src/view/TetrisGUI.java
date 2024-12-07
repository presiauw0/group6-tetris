package view;

import static model.MyBoard.PROPERTY_GAME_OVER_STATE;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import model.Board;
import model.MyBoard;


/**
 * The graphical user interface for the Tetris game.
 * Combines the menu bar, game board, next piece preview, and scoreboard.
 * Updated for Sprint 2 to include integration with Board, Timer, and listeners.
 *
 * @author Abdulrahman Hassan, Balkirat Singh, Preston Sia
 * @version Autumn 2024
 */
public final class TetrisGUI extends JPanel {
    /** Lable for the how to play dialog box */
    private static final String MENULABEL_HOWTOPLAY = "How to Play";

    /** Label for the about dialog box */
    private static final String MENULABEL_ABOUT = "About";

    /** Default Time interval for game timer in milliseconds */
    private static final int DEFAULT_TIME_DELAY = 500;

    /** Fixed file path for my tetris background music  */
    private static final String FILE_PATH =
            "src/view/sound/m1.wav";

    /** Music player for background music. */
    private final MusicPlayer myMusicPlayer;

    /** The Tetris Board Panel. */
    private final TetrisBoardPanel myBoardPanel;

    /** The Next Piece Panel. */
    private final NextPeice myNextPeicePanel;

    /** The Scoreboard Panel. */
    private final ScoreBoard myScoreBoardPanel;

    /** The main game JFrame. */
    private final JFrame myFrame;

    /** The primary model object using the Board interface. */
    private final MyBoard myBoard;

    /** The game timer controlling the game loop. */
    private final Timer myTimer;

    /** Boolean value indicates if the game is over or not. */
    private boolean myGameOver;

    /** Boolean value indicates if the music is muted or not. */
    private boolean myMuted;



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
        myMusicPlayer = new MusicPlayer();

        // Timer ticks on a certain interval and calls step() on the Board
        myTimer = new Timer(DEFAULT_TIME_DELAY, e -> myBoard.step());

        myGameOver = true; // True if the game does not start on launch

        callConstructorHelperMethods();
    }

    /**
     * Helper method to call necessary helper methods
     * when constructing a new GUI.
     */
    private void callConstructorHelperMethods() {
        buildMenu();
        layoutComponents();
        addListeners();
        addPropertyChangeListeners();
    }

    /**
     * Builds the menu bar for the Tetris GUI.
     * Adds the Game and Help menus to the menu bar.
     */
    private void buildMenu() {
        final JMenuBar menuBar = new JMenuBar();
        menuBar.add(buildGameMenu());
        menuBar.add(buildHelpMenu());
        myFrame.setJMenuBar(menuBar);
    }

    private JMenu buildGameMenu() {
        final JMenu gameMenu = new JMenu("Game");

        gameMenu.setMnemonic(KeyEvent.VK_G);

        final JMenuItem newGameItem = new JMenuItem("New Game");
        newGameItem.setMnemonic(KeyEvent.VK_N);

        final JMenuItem pauseGameItem = new JMenuItem("Pause/Resume");
        pauseGameItem.setMnemonic(KeyEvent.VK_P);

        final JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setMnemonic(KeyEvent.VK_X);

        newGameItem.addActionListener(e -> startNewGame());
        pauseGameItem.addActionListener(theEvent -> togglePauseResume());
        exitItem.addActionListener(theEvent ->
                myFrame.dispatchEvent(new WindowEvent(myFrame, WindowEvent.WINDOW_CLOSING)));

        gameMenu.add(newGameItem);
        gameMenu.add(pauseGameItem);
        gameMenu.addSeparator();
        gameMenu.add(exitItem);

        return gameMenu;
    }

    private JMenu buildHelpMenu() {
        final JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);

        final JMenuItem howToPlayItem = new JMenuItem(MENULABEL_HOWTOPLAY);
        howToPlayItem.setMnemonic(KeyEvent.VK_T);

        final JMenuItem aboutItem = new JMenuItem(MENULABEL_ABOUT);
        aboutItem.setMnemonic(KeyEvent.VK_A);

        howToPlayItem.addActionListener(e -> showHowToPlayDialog());
        aboutItem.addActionListener(e -> showAboutDialog());

        helpMenu.add(howToPlayItem);
        helpMenu.add(aboutItem);
        return helpMenu;
    }

    /**
     * Lays out the components for the Tetris GUI.
     * Sets the layout for the board, next piece, and scoreboard panels.
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
        myFrame.setResizable(false);
    }

    /**
     * Adds necessary listeners to the GUI.
     * Includes the key listener for user input.
     */
    private void addListeners() {
        // KeyListener for user input
        addKeyListener(new MyKeyAdapter());
        setFocusable(true);
        requestFocusInWindow();
    }

    /**
     * Adds property change listeners to the Board.
     * Stops the game timer when the game is over.
     */
    private void addPropertyChangeListeners() {
        myBoard.addPropertyChangeListener(PROPERTY_GAME_OVER_STATE, evt -> {
            myTimer.stop();
            myMusicPlayer.stopMusic();
            myGameOver = true;
        });
    }

    /**
     * Starts a new game and notifies the user.
     * This method is called when a new game is started from the menu.
     */
    private void startNewGame() {
        myBoard.newGame();
        myTimer.start();
        myMusicPlayer.startMusic(FILE_PATH);
        myGameOver = false;
    }

    /**
     * Toggles between pausing and resuming the game and notifies the user.
     * This method is triggered from the menu.
     */
    private void togglePauseResume() {
        if (!myGameOver) {
            if (myTimer.isRunning()) {
                myTimer.stop();
                myMusicPlayer.stopMusic();
            } else {
                myTimer.start();
                myMusicPlayer.startMusic(FILE_PATH);
            }
        }


    }

    /**
     * Displays a dialog with instructions on how to play the game.
     * This method is triggered when "How to Play" is selected from the help menu.
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
                MENULABEL_HOWTOPLAY,
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    /**
     * Displays a dialog with information about the game.
     * This method is triggered when "About" is selected from the help menu.
     */
    private void showAboutDialog() {
        JOptionPane.showMessageDialog(
                myFrame,
                """
                        Tetris Game v1.0
                        Created with Swing.
                        
                        Developed by:
                        Abdulrahman Hassan
                        Preston Sia
                        Khalid Rashid
                        Balkirat Singh
                        
                        Group 6""",
                MENULABEL_ABOUT,
                JOptionPane.INFORMATION_MESSAGE
        );
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
            if (!myGameOver) {
                if (myTimer.isRunning()) {
                    switch (theEvent.getKeyCode()) {
                        case KeyEvent.VK_LEFT, KeyEvent.VK_A -> myBoard.left();
                        case KeyEvent.VK_RIGHT, KeyEvent.VK_D -> myBoard.right();
                        case KeyEvent.VK_DOWN, KeyEvent.VK_S -> myBoard.down();
                        case KeyEvent.VK_SPACE -> myBoard.drop();
                        case KeyEvent.VK_UP, KeyEvent.VK_W -> myBoard.rotateCW();
                        default -> {
                            // No action for other keys
                        }
                    }
                }

                if (theEvent.getKeyCode() == KeyEvent.VK_P) {
                    togglePauseResume();
                }
            }

            // Handle the 'm' key for muting/unmuting
            if (theEvent.getKeyCode() == KeyEvent.VK_M) {
                toggleMute();
            }
        }

        private void toggleMute() {
            if (myMuted) {
                myMuted = false;
                myMusicPlayer.startMusic(FILE_PATH);
            } else {
                myMuted = true;
                myMusicPlayer.stopMusic();
            }
        }

    }
}
