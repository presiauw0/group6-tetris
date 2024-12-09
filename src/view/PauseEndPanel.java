package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;


/**
 * A panel that displays a "Game Over" or "Paused" message when neccecary.
 *
 * @author Khalid Rashid
 * @version autumn 2024
 */
public final class PauseEndPanel extends JPanel implements IPauseEndPanel {

    /**
     * A string that holds the message displayed on a Game Over Panel.
     */
    private static final String GAME_OVER = "Game Over";

    /**
     * A string that holds the message displayed on a Game Over Panel.
     */
    private static final String GAME_PAUSED = "Game Paused";

    /**
     * Indicates whether the game is over or not.
     */
    private boolean myGameOver;

    /**
     * Indicates whether the game is paused
     */
    private boolean myPaused;


    /**
     * A panel that displays a "Game Over" or "Paused" message when neccecary.
     */
    public PauseEndPanel() {
        super();
        setLayout(new BorderLayout());
        setOpaque(false);
        myGameOver = false;
        myPaused = false;
    }

    /**
     * Displays the Game over screen.
     * @param theGameOver a boolean that returns whether the game is over or not.
     */
    @Override
    public void setGameOver(final boolean theGameOver) {
        myGameOver = theGameOver;
        setVisible(myGameOver);
        repaint();
    }

    /**
     * Displays the Game Paused screen.
     * @param thePaused A boolean that determins if the game is paused
     */
    @Override
    public void setPaused(final boolean thePaused) {
        myPaused = thePaused;
        setVisible(myPaused);
        repaint();
    }


    /**
     * A helper method that determines what string to pass when the panel is displayed.
     * @return A string holding the message to display based on Game state.
     */
    private String getGameState() {
        final String result;
        if (myGameOver) {
            result = GAME_OVER;
        } else {
            result = GAME_PAUSED;
        }
        return result;
    }

    @Override
    protected void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final int opacity = 150;
        final int fontSize = 35;
        if (myGameOver || myPaused) {
            final Graphics2D g2d = (Graphics2D) theGraphics;
            g2d.setColor(new Color(0, 0, 0, opacity));
            g2d.fillRect(0, 0, getWidth(), getHeight());

            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Arial", Font.BOLD, fontSize));
            final String message = getGameState();
            final FontMetrics metrics = g2d.getFontMetrics();
            final int x = (getWidth() - metrics.stringWidth(message)) / 2;
            final int y = getHeight() / 2;
            g2d.drawString(message, x, y);
        }
    }

}
