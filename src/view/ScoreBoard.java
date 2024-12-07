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
 * @author Balkirat Singh, Abdulrahman Hassan
 * @version 1.1
 */
public class ScoreBoard extends JPanel {
    private final JLabel scoreLabel;
    private final JLabel linesLabel;
    private final JLabel levelLabel;
    private final JLabel nextLevelLabel;

    private final Scoring scoringSystem;

    /**
     * Constructs the scoreboard panel.
     */
    public ScoreBoard() {
        super();
        scoringSystem = ScoringSystem.getInstance();
        scoreLabel = new JLabel("Score: 0", SwingConstants.CENTER);
        linesLabel = new JLabel("Lines: 0", SwingConstants.CENTER);
        levelLabel = new JLabel("Level: 1", SwingConstants.CENTER);
        nextLevelLabel = new JLabel("Next level in 5 lines", SwingConstants.CENTER);
        initialize();
    }

    /**
     * Initializes the scoreboard panel.
     */
    private void initialize() {
        setBackground(Color.GREEN);
        setLayout(new GridLayout(4, 1));

        add(scoreLabel);
        add(linesLabel);
        add(levelLabel);
        add(nextLevelLabel);

        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    /**
     * Updates the displayed score, total lines cleared, current level, and lines remaining
     * until the next level.
     *
     */
    public void updateScore() {
        scoreLabel.setText("Score: " + scoringSystem.getScore());
        linesLabel.setText("Lines: " + scoringSystem.getTotalLinesCleared());
        levelLabel.setText("Level: " + scoringSystem.getLevel());
        nextLevelLabel.setText("Next level in " + scoringSystem.getNextLevelLines() + " lines");
    }

    /**
     * Updates the score when a piece is frozen in place, adding 4 points to the score.
     */
//    public void pieceFrozen() {
//        scoringSystem.pieceFrozen();
//        scoreLabel.setText("Score: " + scoringSystem.getScore());
//    }
}