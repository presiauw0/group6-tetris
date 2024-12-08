package view;


/**
 * Represents the operations for a music player.
 * Provides methods to start, stop, check, and manage background music playback.
 * @author Balkriat Singh
 * @version Autumn 2024
 */

public interface MusicPlayerInterface {

    /**
     * Starts playing background music from the specified file path.
     * This method loads the audio file, initializes the audio clip, and starts
     * playing it in a continuous loop. If any issues occur during the process,
     * such as unsupported file format, I/O errors, or unavailable audio lines,
     * the errors are logged.
     *
     * @param theFilePath the file path of the audio file to be played.
     *                    The file must be a valid audio format supported by the
     *                    Java Sound API (e.g., WAV).
     * @throws NullPointerException if theFilePath is null.
     */
    void startMusic(String theFilePath);

    /**
     * Stops the background music if it is playing.
     */
    void stopMusic();

    /**
     * Closes the audio resources.
     */
    void close();

    /**
     * Checks if the music is currently playing.
     *
     * @return true if music is playing, false otherwise.
     */
    boolean isPlaying();
}