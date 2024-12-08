package view;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;


/**
 * A Class dedicated to creating a menu for the Main  Tetris Gui.
 * @author Khalid
 * @version Autumn 2024
 */
public class TetrisMenuBuild {

    /** Label for the how to play dialog box */
    private static final String HIGH_SCORE = "High Scores";

    /** a field to store the Tetris Gui CLass. */
    private final TetrisGUI myTetrisGUI;

    /** A field to store the HighScore Manager. */
    private final HighScoreManager myHighScoreManager;

    /** A field to store the Toggle for gridlines. */
    private JMenuItem myToggleGridLines;

    /** A field to store the Togglr for Ghost Piece. */
    private JMenuItem myToggleGhostPiece;



    /**
     * A constructor for a menu for the Tetris Gui.
     * @param theTetrisGUI retrieves the state of the tetrisGui.
     */
    public TetrisMenuBuild(final TetrisGUI theTetrisGUI) {
        super();
        this.myTetrisGUI = theTetrisGUI;
        myHighScoreManager = new HighScoreManager();
    }

    /** a method to create the menu bar. */
    public JMenuBar createMenuBar() {
        final JMenuBar menuBar = new JMenuBar();
        menuBar.add(buildGameMenu());
        menuBar.add(buildOptionsMenu());
        menuBar.add(buildHighScoreMenu());
        menuBar.add(buildHelpMenu());
        return menuBar;
    }


    private JMenu buildGameMenu() {
        final JMenu gameMenu = new JMenu("Game");
        gameMenu.setMnemonic(KeyEvent.VK_G);

        final JMenuItem newGameItem = createMenuItem("New Game",
                KeyEvent.VK_N, e -> startNewGame());
        final JMenuItem endGameItem = createMenuItem("End Game",
                KeyEvent.VK_E, e -> myTetrisGUI.endGame());
        final JMenuItem pauseGameItem = createMenuItem("Pause/Resume",
                KeyEvent.VK_P, e -> myTetrisGUI.togglePauseResume());
        final JMenuItem exitItem = createMenuItem("Exit", KeyEvent.VK_X,
                e -> myTetrisGUI.exitGame());

        gameMenu.add(newGameItem);
        gameMenu.add(endGameItem);
        gameMenu.add(pauseGameItem);
        gameMenu.addSeparator();
        gameMenu.add(exitItem);

        return gameMenu;
    }

    private JMenu buildOptionsMenu() {
        final JMenu optionsMenu = new JMenu("Options");
        optionsMenu.setMnemonic(KeyEvent.VK_O);

        myToggleGridLines = createMenuItem("Toggle Gridlines", KeyEvent.VK_T, e ->
                myTetrisGUI.toggleGridlines());
        myToggleGhostPiece = createMenuItem("Toggle Ghost Piece", KeyEvent.VK_O, e ->
                myTetrisGUI.toggleGhostPiece());
        final JMenuItem setHardMode = createMenuItem("Set Hard Mode", KeyEvent.VK_I, e ->
                myTetrisGUI.setHardMode(myToggleGridLines, myToggleGhostPiece));
        final JMenuItem musicToggleItem = createMenuItem("Music On/Off", KeyEvent.VK_M, e ->
                myTetrisGUI.toggleMusic());


        optionsMenu.add(myToggleGridLines);
        optionsMenu.add(myToggleGhostPiece);
        optionsMenu.add(setHardMode);
        optionsMenu.add(musicToggleItem);

        return optionsMenu;
    }

    private JMenu buildHighScoreMenu() {
        final JMenu highScoreMenu = new JMenu(HIGH_SCORE);

        final JMenuItem viewHighScores = createMenuItem("View High Scores",
                0, e -> viewHighScores());
        final JMenuItem clearHighScores = createMenuItem("Clear " + HIGH_SCORE,
                0, e -> clearHighScores());

        highScoreMenu.add(viewHighScores);
        highScoreMenu.add(clearHighScores);
        return highScoreMenu;
    }

    private JMenu buildHelpMenu() {
        final JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);

        final JMenuItem howToPlayItem = createMenuItem("How to Play",
                KeyEvent.VK_T, e -> myTetrisGUI.showHowToPlayDialog());
        final JMenuItem aboutItem = createMenuItem("About",
                KeyEvent.VK_A, e -> myTetrisGUI.showAboutDialog());

        helpMenu.add(howToPlayItem);
        helpMenu.add(aboutItem);

        return helpMenu;
    }

    private JMenuItem createMenuItem(final String theText, final int theMnemonic,
                                     final ActionListener theAction) {
        final JMenuItem menuItem = new JMenuItem(theText);
        menuItem.setMnemonic(theMnemonic);
        menuItem.addActionListener(theAction);
        return menuItem;
    }

    private void viewHighScores() {
        final List<HighScore> highScores = myHighScoreManager.getHighScores();
        if (highScores.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "No high scores available.",
                    HIGH_SCORE, JOptionPane.INFORMATION_MESSAGE);
        } else {
            final StringBuilder highScoresText = new StringBuilder(HIGH_SCORE + ":\n");
            for (final HighScore score : highScores) {
                highScoresText.append(score.toString()).append("\n");
            }
            JOptionPane.showMessageDialog(null,
                    highScoresText.toString(), HIGH_SCORE,
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void clearHighScores() {
        final int confirmation  = JOptionPane.showConfirmDialog(null,
                "Are you sure you want to clear all high scores?",
                "Clear High Scores", JOptionPane.YES_NO_OPTION);
        if (confirmation  == JOptionPane.YES_OPTION) {
            myHighScoreManager.clearHighScores();
            JOptionPane.showMessageDialog(null,
                    "High scores cleared!", HIGH_SCORE,
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void startNewGame() {
        myTetrisGUI.startNewGame();
        myToggleGridLines.setEnabled(true);
        myToggleGhostPiece.setEnabled(true);
    }
}