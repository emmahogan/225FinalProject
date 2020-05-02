package marstrip;

import gameutils.Game;

public class MarsTrip extends Game {
    public static final int FRAME_HEIGHT = 821;
    public static final int FRAME_WIDTH = 480;
    private PlayScreen playScreen;

    /**
     * Creates a new instance of Mars Trip
     *
     * @param name the title of the Game
     */
    public MarsTrip(String name) {
        super(name, FRAME_WIDTH, FRAME_HEIGHT);
        playScreen = new PlayScreen();
        changeScreen(playScreen);
    }
}
