package view;

import static model.MyBoard.PROPERTY_GAME_OVER_STATE;
import static model.MyBoard.PROPERTY_NEXT_PIECE_CHANGE;
import static view.score.ScoringSystem.PROPERTY_LEVEL_CHANGE;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import model.Board;
import model.MyBoard;
import view.score.PropertyChangeEnabledScoring;
import view.score.ScoringSystem;


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

    /** Label for the about dialog box */
    private static final String ABOUT_HARDMODE = "About Hard Mode";

    /** Default Time Delay for Tetris Game in milliseconds. */
    private static final int DEFAULT_TIME_DELAY = 500;

    /** Default Time interval step for increasing/decreasing speed in milliseconds. */
    private static final int DEFAULT_TIME_STEP = 20;

    /** Fixed file path for my Tetris background music */
    private static final String FILE_PATH = "src/view/sound/m1.wav";

    /** Music player for background music. */
    private final MusicPlayer myMusicPlayer;

    /** The Tetris Board Panel. */
    private final TetrisBoardPanel myBoardPanel;

    /** The Next Piece Panel. */
    private final NextPeice myNextPeicePanel;

    /** The Scoreboard Panel. */
    private final ScoreBoard myScoreBoardPanel;

    /** The Scoring System Class. */
    private PropertyChangeEnabledScoring myScoreSystem;

    /** The Pause and Game Over Panel. */
    private final PauseEndPanel myPauseEndPanel;

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

    /** Boolean to enable Hard Mode. */
    private boolean myHardMode;

    /** an Integer to store the Rotation Counter. */
    private int myRotateCounter;


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
        myPauseEndPanel = new PauseEndPanel();
        myMusicPlayer = new MusicPlayer();

        // Timer ticks on a certain interval and calls step() on the Board
        myTimer = new Timer(DEFAULT_TIME_DELAY, e -> myBoard.step());

        callConstructorHelperMethods();
    }


    /**
     * Helper method to call necessary helper methods
     * when constructing a new GUI.
     */
    private void callConstructorHelperMethods() {
        myScoreSystem = ScoringSystem.getInstance();
        buildMenu();
        layoutComponents();
        addListeners();
        addPropertyChangeListeners();

        // True if the game does not start on launch
        myGameOver = true;
        myIsMuted = false; // Start with music playing
    }

    /**
     * Builds the menu bar for the Tetris GUI.
     * Adds the Game and Help menus to the menu bar.
     */
    private void buildMenu() {
        final JMenuBar menuBar = new JMenuBar();
        menuBar.add(buildGameMenu());
        menuBar.add(buildOptionsMenu());
        menuBar.add(buildHelpMenu());
        menuBar.add(buildHighScoreMenu());
        myFrame.setJMenuBar(menuBar);
    }

    private JMenu buildGameMenu() {
        final JMenu gameMenu = new JMenu("Game");

        gameMenu.setMnemonic(KeyEvent.VK_G);

        final JMenuItem newGameItem = new JMenuItem("New Game");
        newGameItem.setMnemonic(KeyEvent.VK_N);

        final JMenuItem endGameItem = new JMenuItem("End Game");
        endGameItem.setMnemonic(KeyEvent.VK_E);

        final JMenuItem pauseGameItem = new JMenuItem("Pause/Resume");
        pauseGameItem.setMnemonic(KeyEvent.VK_P);

        final JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setMnemonic(KeyEvent.VK_X);

        newGameItem.addActionListener(e -> startNewGame());
        endGameItem.addActionListener(e -> endGame());
        pauseGameItem.addActionListener(theEvent -> togglePauseResume());
        exitItem.addActionListener(theEvent ->
                myFrame.dispatchEvent(new WindowEvent(myFrame, WindowEvent.WINDOW_CLOSING)));

        gameMenu.add(newGameItem);
        gameMenu.add(endGameItem);
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

        final JMenuItem musicToggleItem = new JMenuItem("Music On/Off");
        musicToggleItem.setMnemonic(KeyEvent.VK_M);

        howToPlayItem.addActionListener(e -> showHowToPlayDialog());
        aboutItem.addActionListener(e -> showAboutDialog());
        musicToggleItem.addActionListener(e -> toggleMusic());

        helpMenu.add(howToPlayItem);
        helpMenu.add(aboutItem);
        helpMenu.add(musicToggleItem); // Add the music toggle menu item

        return helpMenu;
    }

    private JMenu buildOptionsMenu() {
        final JMenu optionsMenu = new JMenu("Options");
        optionsMenu.setMnemonic(KeyEvent.VK_O);

        final JMenuItem toggleGridLines = new JMenuItem("Toggle Gridlines");
        toggleGridLines.setMnemonic(KeyEvent.VK_T);

        final JMenuItem toggleGhostPiece = new JMenuItem("Toggle Ghost Piece");
        toggleGhostPiece.setMnemonic(KeyEvent.VK_O);

        final JMenuItem setHardMode = new JMenuItem("Set Hard Mode");
        setHardMode.setMnemonic(KeyEvent.VK_I);


        toggleGridLines.addActionListener(theEvent -> myBoardPanel.setGridlines(
                !myBoardPanel.getGridlines()
        ));

        toggleGhostPiece.addActionListener(theEvent -> myBoardPanel.setGhostPieceState(
                !myBoardPanel.getGhostPieceState()
        ));

        setHardMode.addActionListener(e -> showAboutHardModeDialog());
        setHardMode.addActionListener(e -> {
            startHardGame();
            toggleGridLines.setEnabled(false);
            toggleGhostPiece.setEnabled(false);
        });

        optionsMenu.add(toggleGridLines);
        optionsMenu.add(toggleGhostPiece);
        optionsMenu.add(setHardMode);

        return optionsMenu;
    }

    private JMenu buildHighScoreMenu() {
        final JMenu highScoreMenu = new JMenu("High Scores");

        final JMenuItem viewHighScores = new JMenuItem("View High Scores");
        viewHighScores.addActionListener(e -> {
            final HighScoreManager manager = new HighScoreManager();
            final StringBuilder highScoresText = new StringBuilder("High Scores:\n");
            for (final HighScore hs : manager.getHighScores()) {
                highScoresText.append(hs).append("\n");
            }
            JOptionPane.showMessageDialog(myFrame, highScoresText.toString(), "High Scores", JOptionPane.INFORMATION_MESSAGE);
        });

        highScoreMenu.add(viewHighScores);
        return highScoreMenu;
    }

    private void updateOptionsMenu() {
        if (myHardMode) {
            myBoardPanel.setGridlines(false);
            myBoardPanel.setGhostPieceState(false);
        }
    }

    /**
     * Lays out the components for the Tetris GUI.
     * Sets the layout for the board, next piece, and scoreboard panels.
     * Uses a JLayoredPanel to handel displaying the puse and game over message.
     */
    private void layoutComponents() {

        final Dimension boardSize = myBoardPanel.getPreferredSize();

        final JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(boardSize);

        myBoardPanel.setBounds(0, 0, boardSize.width, boardSize.height);
        layeredPane.add(myBoardPanel, JLayeredPane.DEFAULT_LAYER);
        myPauseEndPanel.setBounds(0, 0, boardSize.width, boardSize.height);
        layeredPane.add(myPauseEndPanel, JLayeredPane.PALETTE_LAYER);

        setLayout(new BorderLayout());
        add(layeredPane, BorderLayout.CENTER);

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
        myBoard.addPropertyChangeListener(PROPERTY_GAME_OVER_STATE, this::gameOverHelper);
        myScoreSystem.addPropertyChangeListener(PROPERTY_LEVEL_CHANGE,
                this::increaseSpeedHalper);
        myBoard.addPropertyChangeListener(PROPERTY_NEXT_PIECE_CHANGE,
                this::resetRotateCounter);
    }

    /**
     * Helper method to hold logic for the lambda addPropertyChangeListner.
     */
    private void gameOverHelper(final PropertyChangeEvent theEvent) {
        final boolean isGameOver = (boolean) theEvent.getNewValue();
        myTimer.stop();
        myMusicPlayer.stopMusic();
        myGameOver = true;
        myPauseEndPanel.setGameOver(isGameOver);
        promptForHighScore();
    }

    private void increaseSpeedHalper(final PropertyChangeEvent theEvent) {
        final int oldVal = (int) theEvent.getOldValue();
        final int newVal = (int) theEvent.getNewValue();
        if (newVal > oldVal && myTimer.getDelay() >= 0) {
            myTimer.setDelay(myTimer.getDelay() - DEFAULT_TIME_STEP);
        }
    }

    /**
     * Updates the music state based on the game's current status.
     * Music should play only if the game is not over, not muted, and not paused.
     */
    private void updateMusicState() {
        if (!myGameOver && !myIsMuted && myTimer.isRunning()) {
            myMusicPlayer.startMusic(FILE_PATH);
        } else {
            myMusicPlayer.stopMusic();
        }
    }

    /**
     * Helper Method to reset the rotation counter.
     */
    private void resetRotateCounter(final PropertyChangeEvent theEvent) {
        myRotateCounter = 0;
    }

    /**
     * Starts a new game and notifies the user.
     * This method is called when a new game is started from the menu.
     */
    private void startNewGame() {
        if (myGameOver) { // Only allow starting a new game if the previous one is over
            myBoard.newGame();  // Reset the game board
            myTimer.start();    // Start the game timer
            myGameOver = false; // Mark the game as active
            myHardMode = false;
            myIsMuted = false;  // Unmute music for the new game
            myPauseEndPanel.setPaused(false);
            myTimer.setDelay(DEFAULT_TIME_DELAY);
            updateMusicState(); // Handle music playback
            buildMenu();
        }
    }

    /**
     * Starts a new game With Hard Mode Selected and notifies the user.
     * This method is called when a new game is started from the menu.
     */
    private void startHardGame() {
        myGameOver = true;
        myBoard.newGame();
        myTimer.start();
        myMusicPlayer.startMusic(FILE_PATH);
        myGameOver = false;
        myHardMode = true;
        myPauseEndPanel.setPaused(false);
        updateOptionsMenu();

    }

    /**
     * Starts a new game and notifies the user.
     * This method is called when a new game is started from the menu.
     */
    private void endGame() {
        if (!myGameOver) {
            myTimer.stop();
            myGameOver = true;
            myHardMode = false;
            myPauseEndPanel.setGameOver(true);
            updateMusicState();
            promptForHighScore();
        }
    }

    /**
     * Toggles between pausing and resuming the game and notifies the user.
     * This method is triggered from the menu.
     */
    private void togglePauseResume() {
        if (!myGameOver) {
            if (myTimer.isRunning()) {
                myTimer.stop();
                myPauseEndPanel.setPaused(true);
            } else {
                myTimer.start();
                myPauseEndPanel.setPaused(false);
            }
            updateMusicState(); // Adjust music based on game state
        }
    }

    /**
     * Toggles the background music playback state (mute/unmute).
     */
    private void toggleMusic() {
        myIsMuted = !myIsMuted; // Toggle the muted state
        updateMusicState(); // Adjust music based on game state
    }

    /**
     * Prompts the user to enter their name for a new high score if the
     * current score is greater than 0. If a valid name is entered, the
     * score is saved using the HighScoreManager.
     */
    private void promptForHighScore() {
        if (myScoreSystem.getScore() > 0) { // Optional: Check if score qualifies
            final String playerName = JOptionPane.showInputDialog(
                    myFrame,
                    "Enter your name:",
                    "New High Score!",
                    JOptionPane.PLAIN_MESSAGE
            );

            if (playerName != null && !playerName.isEmpty()) {
                final HighScoreManager manager = new HighScoreManager();
                manager.addHighScore(new HighScore(playerName, myScoreSystem.getScore()));
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
                        
                        Scoring:
                        * 4 points per frozen piece.
                        * 1 line = 40 points * level.
                        * 2 lines = 100 points * level.
                        * 3 lines = 300 points * level.
                        * 4 lines = 1200 points * level.
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
     * Displays a dialog with information about The Hard Mode.
     * This method is triggered when "About Hard Mode" is selected from the help menu.
     */
    private void showAboutHardModeDialog() {
        JOptionPane.showMessageDialog(
                myFrame,
                """
                        Tetris Game Hard Mode:
                        
                        Hard Mode disables
                        GridLines and
                        Ghost Piece.
                        Rotation of Tetramino
                        is limited to 4 Times
                        Per Piece.
                        
                        Selecting Hard Mode
                        Will start A new Game.
                    
                        Group 6""",
                ABOUT_HARDMODE,
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
                        case KeyEvent.VK_UP, KeyEvent.VK_W -> rotateCW();
                        default -> {
                        } // No action for other keys
                    }
                }
                otherEvents(theEvent);
            }
        }

        private void otherEvents(final KeyEvent theEvent) {
            if (!myGameOver) {
                switch (theEvent.getKeyCode()) {
                    case KeyEvent.VK_P -> togglePauseResume();
                    case KeyEvent.VK_E -> endGame();
                    case KeyEvent.VK_M -> toggleMusic();
                    default -> {
                    }
                }
            }
        }

        private void rotateCW() {
            if (myHardMode) {
                final int four = 4;
                if (myRotateCounter < four) {
                    myBoard.rotateCW();
                    myRotateCounter++;
                }
            } else {
                myBoard.rotateCW();
            }
        }
    }
}
