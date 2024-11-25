package view;


import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Represents the scoreboard panel for the Tetris game.
 * This panel displays game controls and score details.
 * The panel has a green background and uses a BorderLayout.
 *
 * @author Balkirat Singh
 * @version 1.1
 */
public class ScoreBoard extends JPanel {
    /**
     *  Constant for the amount of rows needed in Control directions
     */
    private static final int ROW = 7;

    /**
     *   Constant for the amount of columns needed in Control directions
     */
    private static final int COLS = 1;

    /**
     *   Constant for the amount of rows needed in Control directions
     */
    private static final int ROW_SCORE = 5;


    /**
     * Constructs the scoreboard panel.
     */
    public ScoreBoard() {
        super();
        initialize();
    }

    /**
     * Initializes the scoreboard panel.
     */
    private void initialize() {
        setBackground(Color.GREEN);

        setLayout(new GridLayout(2, 1));

        final JPanel controlsPanel = createControlsPanel();
        final JPanel scorePanel = createScorePanel();

        add(controlsPanel);
        add(scorePanel);
    }

    /**
     * Creates the game controls panel.
     *
     * @return the game controls panel.
     */
    private JPanel createControlsPanel() {
        final JPanel controlsPanel = new JPanel();

        controlsPanel.setLayout(new GridLayout(ROW, COLS));

        controlsPanel.setOpaque(false);

        controlsPanel.add(new JLabel("Move Left: left or D/d key"));
        controlsPanel.add(new JLabel("Move Right: right or A/a key"));
        controlsPanel.add(new JLabel("Move Down: down or S/s key"));
        controlsPanel.add(new JLabel("Rotate cw: up or W/w key"));
        controlsPanel.add(new JLabel("Drop: space"));
        controlsPanel.add(new JLabel("Pause: P/p"));
        controlsPanel.add(new JLabel("Mute: M/m"));

        controlsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        return controlsPanel;
    }

    /**
     * Creates the score details panel.
     *
     * @return the score details panel.
     */
    private JPanel createScorePanel() {
        final JPanel scorePanel = new JPanel();
        scorePanel.setLayout(new GridLayout(ROW_SCORE, COLS));

        scorePanel.setOpaque(false);

        scorePanel.add(new JLabel("Score: 0", SwingConstants.CENTER));
        scorePanel.add(new JLabel("Lines: 0", SwingConstants.CENTER));
        scorePanel.add(new JLabel("Level: 1", SwingConstants.CENTER));
        scorePanel.add(new JLabel("Next level in 5 lines", SwingConstants.CENTER));

        scorePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        return scorePanel;
    }


}
