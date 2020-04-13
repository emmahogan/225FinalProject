package racing;

import gameutils.Game;

/**
 * Write a description of class SpeedRacers here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SpeedRacers extends Game {
    
    public SpeedRacers(String name) {
        super(name);
        //setController(new SRController());
        changeScreen(new PlayScreen());
    }
}
