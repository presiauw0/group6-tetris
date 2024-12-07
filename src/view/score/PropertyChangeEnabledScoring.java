package view.score;

import java.beans.PropertyChangeListener;

/**
 * Scoring classes are responsible for tracking and reporting
 * the score of a Tetris game. It must provide methods for getting
 * the latest scoring information, including the current score,
 * number of lines clear, current level, and the number of lines
 * remaining to reach the next level.
 * Additionally, this interface stipulates that any
 * implementation thereof must include the Observer Design
 * Pattern to fire events related to scoring and level changes.
 *
 * @author Preston Sia
 * @version F2024_001
 */
public interface PropertyChangeEnabledScoring extends Scoring {
    /**
     * Property fired when the score changes.
     * Old value - previous score
     * New value - new score
     */
    String PROPERTY_SCORE_CHANGE = "SCORE CHANGE";

    /**
     * Property fired when the level changes.
     * Old value - previous level
     * New value - current level
     */
    String PROPERTY_LEVEL_CHANGE = "SCORING LEVEL CHANGE";

    /**
     * Property fired when the total number of lines changes.
     * Old value - previous total number of lines
     * New value - current total number of lines
     */
    String PROPERTY_CLEARED_LINE_CHANGE = "CLEARED LINE CHANGE";


    /**
     * Add a PropertyChangeListener to the listener list. The listener is registered for
     * all properties. The same listener object may be added more than once, and will be
     * called as many times as it is added. If listener is null, no exception is thrown and
     * no action is taken.
     *
     * @param theListener The PropertyChangeListener to be added
     */
    void addPropertyChangeListener(PropertyChangeListener theListener);

    /**
     * Add a PropertyChangeListener for a specific property. The listener will be invoked only
     * when a call on firePropertyChange names that specific property. The same listener object
     * may be added more than once. For each property, the listener will be invoked the number
     * of times it was added for that property. If propertyName or listener is null, no
     * exception is thrown and no action is taken.
     *
     * @param thePropertyName The name of the property to listen on.
     * @param theListener The PropertyChangeListener to be added
     */
    void addPropertyChangeListener(String thePropertyName, PropertyChangeListener theListener);

    /**
     * Remove a PropertyChangeListener from the listener list. This removes a
     * PropertyChangeListener that was registered for all properties. If listener was added
     * more than once to the same event source, it will be notified one less time after being
     * removed. If listener is null, or was never added, no exception is thrown and no action
     * is taken.
     *
     * @param theListener The PropertyChangeListener to be removed
     */
    void removePropertyChangeListener(PropertyChangeListener theListener);

    /**
     * Remove a PropertyChangeListener for a specific property. If listener was added more than
     * once to the same event source for the specified property, it will be notified one less
     * time after being removed. If propertyName is null, no exception is thrown and no action
     * is taken. If listener is null, or was never added for the specified property, no
     * exception is thrown and no action is taken.
     *
     * @param thePropertyName The name of the property that was listened on.
     * @param theListener The PropertyChangeListener to be removed
     */
    void removePropertyChangeListener(String thePropertyName,
                                      PropertyChangeListener theListener);


    /**
     * A record that describes the total number of cleared lines
     * and the number of lines needed to get to the next level.
     *
     * @param totalLines The total number of cleared lines
     * @param nextLevelLines The number of lines needed to reach the next level
     */
    record LineStats(int totalLines, int nextLevelLines) { }
}
