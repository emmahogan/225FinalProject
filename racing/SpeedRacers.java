package racing;

import gameutils.Game;

/**
 * This class represents the racing game
 *
 * @author Justin, Andrew, Emma, Tim, Nick
 * @version Spring 2020
 */
public class SpeedRacers extends Game {
    private PlayScreen playScreen;

    /**
     * The constructor creates a new instance of the playscreen for the racing game and changes the screen to this
     *
     * @param name the title of the window
     */
    public SpeedRacers(String name) {
        super(name);
        playScreen = new PlayScreen();
        changeScreen(playScreen);
    }
}
