package view;


/**
 * An interface for the public methods in the Pause End Panel.
 *
 * @author Khalid
 * @version Autumn 2024
 */
public interface IPauseEndPanel {

    /**
     * Set the Game over to true allowing the Game over screen to be Displayed.
     * @param theGameOver a boolean that returns whether the game is over or not.
     */
    void setGameOver(boolean theGameOver);

    /**
     * Set the Paused to true allowing the Game Paused screen to be Displayed.
     * @param thePaused A boolean that determins if the game is paused
     */
    void setPaused(boolean thePaused);

}
