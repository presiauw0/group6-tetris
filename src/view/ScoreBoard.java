package view;

import static view.score.PropertyChangeEnabledScoring.PROPERTY_SCORE_CHANGE;
import static view.score.PropertyChangeEnabledScoring.PROPERTY_LEVEL_CHANGE;
import static view.score.PropertyChangeEnabledScoring.PROPERTY_CLEARED_LINE_CHANGE;
import static view.score.PropertyChangeEnabledScoring.LineStats;
import static view.colors.TetrisColorSchemeDefault.SCORE_BG_COLOR;
import static view.colors.TetrisColorSchemeDefault.SCORE_FG_COLOR;
import static view.colors.TetrisColorSchemeDefault.INSTRUCTIONS_FG_COLOR;

import java.awt.Color;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import view.score.PropertyChangeEnabledScoring;
import view.score.ScoringSystem;

/**
 * Represents the scoreboard panel for the Tetris game.
 * This panel displays game controls and score details.
 * The panel has a green background and uses a BorderLayout.
 *
 * @author Balkirat Singh, Abdulrahman Hassan, Preston Sia
 * @version 1.2
 */
public class ScoreBoard extends JPanel implements PropertyChangeListener {
    /**
     * Constant for the rows needed for the live scoreboard
     */
    private static final int SCORE_ROW = 4;
    /**
     * Constant for the columns needed for the live socreboard
     */
    private static final int SCORE_COLS = 1;

    /**
     * Default text prepended to the score label.
     */
    private static final String SCORE_LABEL_TEXT = "Score: ";
    /**
     * Default text prepended to the lines label.
     */
    private static final String LINES_LABEL_TEXT = "Lines: ";
    /**
     * Default text prepended to the level label.
     */
    private static final String LEVEL_LABEL_TEXT = "Level: ";

    /**
     * Stores the label for the current score.
     */
    private final JLabel myScoreLabel;
    /**
     * Stores the label for the current number of cleared lines.
     */
    private final JLabel myLinesLabel;
    /**
     * Stores the label for the current level.
     */
    private final JLabel myLevelLabel;
    /**
     * Stores the label that displays how many lines are needed
     * before reaching the next level.
     */
    private final JLabel myNextLevelLabel;


    /**
     * Constructs the scoreboard panel.
     */
    public ScoreBoard() {
        super();

        final PropertyChangeEnabledScoring scoringSystem = ScoringSystem.getInstance();
        scoringSystem.addPropertyChangeListener(this);

        myScoreLabel = new JLabel(SCORE_LABEL_TEXT + scoringSystem.getScore(),
                SwingConstants.CENTER);
        myLinesLabel = new JLabel(LINES_LABEL_TEXT + scoringSystem.getTotalLinesCleared(),
                SwingConstants.CENTER);
        myLevelLabel = new JLabel(LEVEL_LABEL_TEXT + scoringSystem.getLevel(),
                SwingConstants.CENTER);
        myNextLevelLabel = new JLabel(buildNextLevelText(scoringSystem.getNextLevelLines()),
                SwingConstants.CENTER);

        initialize();
    }

    /**
     * Initializes the scoreboard panel.
     */
    private void initialize() {

        setBackground(SCORE_BG_COLOR);

        myScoreLabel.setForeground(SCORE_FG_COLOR);
        myLinesLabel.setForeground(SCORE_FG_COLOR);
        myLevelLabel.setForeground(SCORE_FG_COLOR);
        myNextLevelLabel.setForeground(SCORE_FG_COLOR);

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

        controlsPanel.setOpaque(false);

        final JLabel txtInstructions = new JLabel(
                """
                <html>
                Move Left: Left/D key<br>
                Move Right: Right/A key<br>
                Move Down: Down/S key<br>
                Rotate: Up/W key<br>
                Drop: SPACE<br>
                Pause: P<br>
                Mute: M<br>
                </html>
                """);
        txtInstructions.setForeground(INSTRUCTIONS_FG_COLOR);

        controlsPanel.add(txtInstructions);

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
        scorePanel.setLayout(new GridLayout(SCORE_ROW, SCORE_COLS));

        scorePanel.setOpaque(false);

        scorePanel.add(myScoreLabel);
        scorePanel.add(myLinesLabel);
        scorePanel.add(myLevelLabel);
        scorePanel.add(myNextLevelLabel);

        scorePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        return scorePanel;
    }

    private String buildNextLevelText(final int theLines) {
        String s = "Next level in " + theLines;
        if (theLines > 1) {
            s += " lines";
        } else {
            s += " line";
        }
        return s;
    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if (PROPERTY_SCORE_CHANGE.equals(theEvent.getPropertyName())) {
            myScoreLabel.setText(SCORE_LABEL_TEXT + theEvent.getNewValue());
        }
        if (PROPERTY_LEVEL_CHANGE.equals(theEvent.getPropertyName())) {
            myLevelLabel.setText(LEVEL_LABEL_TEXT + theEvent.getNewValue());
        }
        if (PROPERTY_CLEARED_LINE_CHANGE.equals(theEvent.getPropertyName())) {
            final LineStats stats = (LineStats) theEvent.getNewValue();
            myLinesLabel.setText(LINES_LABEL_TEXT + stats.totalLines());
            myNextLevelLabel.setText(buildNextLevelText(stats.nextLevelLines()));
        }
    }

}
