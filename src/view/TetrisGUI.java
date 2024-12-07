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
    /** Label for the how to play dialog box */
    private static final String MENULABEL_HOWTOPLAY = "How to Play";

    /** Label for the about dialog box */
    private static final String MENULABEL_ABOUT = "About";

    /** Default Time interval for game timer in milliseconds */
    private static final int DEFAULT_TIME_DELAY = 500;

    /** Fixed file path for my Tetris background music */
    private static final String FILE_PATH = "src/view/sound/m1.wav";

    /** Music player for background music. */
    private final MusicPlayer myMusicPlayer = new MusicPlayer();

    /** The Tetris Board Panel. */
    private final TetrisBoardPanel myBoardPanel = new TetrisBoardPanel();

    /** The Next Piece Panel. */
    private final NextPeice myNextPeicePanel = new NextPeice();

    /** The Scoreboard Panel. */
    private final ScoreBoard myScoreBoardPanel = new ScoreBoard();

    /** The main game JFrame. */
    private final JFrame myFrame;

    /** The primary model object using the Board interface. */
    private final MyBoard myBoard;

    /** The game timer controlling the game loop. */
    private final Timer myTimer;

    /** Boolean value indicates if the game is over or not. */
    private boolean myGameOver;

    /** Boolean value to track if the music is muted or not. */
    private boolean myIsMuted;

    /**
     * Constructs the Tetris GUI, integrating the panels and menu bar.
     *
     * @param theTitle the title of the Frame.
     */
    public TetrisGUI(final String theTitle) {
        super();
        myFrame = new JFrame(theTitle);
        myBoard = Board.getInstance(); // Factory method
        myTimer = new Timer(DEFAULT_TIME_DELAY, e -> myBoard.step());
        myGameOver = true; // True if the game does not start on launch
        myIsMuted = false; // Start with music playing

        callConstructorHelperMethods();
    }


    /**
     * Helper method to call necessary helper methods when constructing a new GUI.
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
        menuBar.setFocusable(false); // Prevent menu bar from stealing focus
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

        final JMenuItem musicToggleItem = new JMenuItem("Music On/Off");
        musicToggleItem.setMnemonic(KeyEvent.VK_M);

        final JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setMnemonic(KeyEvent.VK_X);

        newGameItem.addActionListener(e -> startNewGame());
        pauseGameItem.addActionListener(theEvent -> togglePauseResume());
        musicToggleItem.addActionListener(e -> toggleMusic());
        exitItem.addActionListener(theEvent ->
                myFrame.dispatchEvent(new WindowEvent(myFrame, WindowEvent.WINDOW_CLOSING)));

        gameMenu.add(newGameItem);
        gameMenu.add(pauseGameItem);
        gameMenu.add(musicToggleItem); // Add the music toggle menu item
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
        myFrame.addKeyListener(new MyKeyAdapter());
        myFrame.setFocusable(true);
        myFrame.requestFocus(); // Ensure focus on the frame
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
        myFrame.requestFocus(); // Ensure focus after starting a new game
    }

    /**
     * Toggles between pausing and resuming the game and notifies the user.
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
        myFrame.requestFocus(); // Ensure focus after toggling
    }

    /**
     * Toggles the background music playback state.
     * Mutes or unmutes the music based on the current state.
     */
    private void toggleMusic() {
        if (myIsMuted) {
            myIsMuted = false;
            myMusicPlayer.startMusic(FILE_PATH);
        } else {
            myIsMuted = true;
            myMusicPlayer.stopMusic();
        }
        myFrame.requestFocus(); // Restore focus to the frame after toggling
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
                MENULABEL_HOWTOPLAY,
                JOptionPane.INFORMATION_MESSAGE
        );
        myFrame.requestFocus(); // Restore focus after dialog
    }

    /**
     * Displays a dialog with information about the game.
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
        myFrame.requestFocus(); // Restore focus after dialog
    }

    /**
     * Main method to launch the application.
     */
    public static void main(final String[] theArgs) {
        javax.swing.SwingUtilities.invokeLater(() -> new TetrisGUI("Tetris Game"));
    }

    /**
     * Key adapter for handling user inputs.
     */
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

            // Toggle music playback when 'm' is pressed
            if (theEvent.getKeyCode() == KeyEvent.VK_M) {
                toggleMusic();
            }
        }
    }
}
