package view;

import static view.score.PropertyChangeEnabledScoring.PROPERTY_SCORE_CHANGE;
import static view.score.PropertyChangeEnabledScoring.PROPERTY_LEVEL_CHANGE;
import static view.score.PropertyChangeEnabledScoring.PROPERTY_CLEARED_LINE_CHANGE;
import static view.score.PropertyChangeEnabledScoring.LineStats;

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
     *  Constant for the amount of rows needed in Control directions
     */
    private static final int INSTRUCTION_ROW = 7;
    /**
     *   Constant for the amount of columns needed in Control directions
     */
    private static final int INSTRUCTION_COLS = 1;
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
     * Store an instance of the ScoringSystem factory instance.
     */
    private final PropertyChangeEnabledScoring myScoringSystem;


    /**
     * Constructs the scoreboard panel.
     */
    public ScoreBoard() {
        super();

        myScoringSystem = ScoringSystem.getInstance();
        myScoringSystem.addPropertyChangeListener(this);

        myScoreLabel = new JLabel(SCORE_LABEL_TEXT + myScoringSystem.getScore(),
                SwingConstants.CENTER);
        myLinesLabel = new JLabel(LINES_LABEL_TEXT + myScoringSystem.getTotalLinesCleared(),
                SwingConstants.CENTER);
        myLevelLabel = new JLabel(LEVEL_LABEL_TEXT + myScoringSystem.getLevel(),
                SwingConstants.CENTER);
        myNextLevelLabel = new JLabel(buildNextLevelText(myScoringSystem.getNextLevelLines()),
                SwingConstants.CENTER);

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

        controlsPanel.setLayout(new GridLayout(INSTRUCTION_ROW, INSTRUCTION_COLS));

        controlsPanel.setOpaque(false);

        controlsPanel.add(new JLabel("Move Left: Left/D key"));
        controlsPanel.add(new JLabel("Move Right: Right/A key"));
        controlsPanel.add(new JLabel("Move Down: Down/S key"));
        controlsPanel.add(new JLabel("Rotate: Up/W key"));
        controlsPanel.add(new JLabel("Drop: SPACE"));
        controlsPanel.add(new JLabel("Pause: P"));
        controlsPanel.add(new JLabel("Mute: M"));

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
