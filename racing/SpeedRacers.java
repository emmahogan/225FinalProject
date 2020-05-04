package racing;

import gameutils.Game;

/**
 * Write a description of class SpeedRacers here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SpeedRacers extends Game {
    private PlayScreen playScreen;

    public SpeedRacers(String name) {
        super(name);
        playScreen = new PlayScreen();
        changeScreen(playScreen);
    }
}
