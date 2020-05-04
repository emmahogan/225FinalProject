package frogger;

import gameutils.Game;
/**
 * This class creates the game and gives it a screen to output to.
 *
 * @author Nick Shelby
 * @version Spring 2020
 */
public class FroggerGame extends Game {
    public static final int SCALE = 30;
    public static final int UNIT = 30;
    private FroggerScreen froggerScreen;
    
    public FroggerGame(String name) {
        super(name);
        froggerScreen = new FroggerScreen();
        changeScreen(froggerScreen);
    }
}
