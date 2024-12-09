package view;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Handles background music playback for the Tetris game.
 * @author balkiratsingh
 * @version 1.1
 */
public class MusicPlayer implements MusicPlayerInterface {

    /** Logger for logging errors. */
    private static final Logger LOGGER = Logger.getLogger(MusicPlayer.class.getName());

    /** Clip for audio playback. */
    private Clip myMusicClip;


    /**
     * Starts playing background music from the specified file path.
     * <p>
     * This method loads the audio file, initializes the audio clip, and starts
     * playing it in a continuous loop. If any issues occur during the process,
     * such as unsupported file format, I/O errors, or unavailable audio lines,
     * the errors are logged using a logger.
     * </p>
     *
     * @param theFilePath the file path of the audio file to be played.
     *                    The file must be a valid audio format supported by the
     *                    Java Sound API (e.g., WAV).
     * @throws NullPointerException if theFilePath is null.
     *         This must be handled before calling the method.
     */
    @Override
    public void startMusic(final String theFilePath) {
        try {
            // Load the music file
            final File musicFile = new File(theFilePath);
            final AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFile);

            // Initialize the Clip and open the audio stream
            myMusicClip = AudioSystem.getClip();
            myMusicClip.open(audioStream);

            // Loop the music continuously
            myMusicClip.loop(Clip.LOOP_CONTINUOUSLY);
            myMusicClip.start();
        } catch (final UnsupportedAudioFileException e) {
            LOGGER.log(Level.SEVERE, "The provided audio file is not supported: {0}",
                    theFilePath);
        } catch (final IOException e) {
            LOGGER.log(Level.SEVERE, "Error reading the audio file: {0}", theFilePath);
        } catch (final LineUnavailableException e) {
            LOGGER.log(Level.SEVERE, "Audio line is unavailable: {0}", e.getMessage());
        }
    }

    /**
     * Stops the background music if it is playing.
     */
    @Override
    public void stopMusic() {
        if (myMusicClip != null && myMusicClip.isRunning()) {
            myMusicClip.stop();
        }
    }

    /**
     * Closes the audio resources.
     */
    @Override
    public void close() {
        if (myMusicClip != null) {
            myMusicClip.close();
        }
    }

    /**
     * Checks if the music is currently playing.
     *
     * @return true if music is playing, false otherwise.
     */
    @Override
    public boolean isPlaying() {
        return myMusicClip != null && myMusicClip.isRunning();
    }
}
