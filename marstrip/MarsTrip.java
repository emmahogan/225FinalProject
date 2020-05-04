package marstrip;

import gameutils.Game;
import gameutils.SoundManager;

public class MarsTrip extends Game {
    public static final int FRAME_HEIGHT = 800;
    public static final int FRAME_WIDTH = 480;
    private PlayScreen playScreen;
    public static SoundManager soundManager;

    /**
     * Creates a new instance of Mars Trip
     *
     * @param name the title of the Game
     */
    public MarsTrip(String name) {
        super(name, FRAME_WIDTH, FRAME_HEIGHT);

        soundManager = new SoundManager();
        changeSoundManager(soundManager);
        soundManager.addSound("assets/marstrip/music.wav");

        playScreen = new PlayScreen();
        changeScreen(playScreen);
    }
}
