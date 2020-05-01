package frogger;

import gameutils.Game;
/**
 * This class creates the game and gives it a screen to output to.
 *
 * @author Nick Shelby
 * @version Spring 2020
 */
public class FroggerGame extends Game 
{
    private FroggerScreen froggerScreen;
    
    public FroggerGame(String name) {
        super(name);
        froggerScreen = new FroggerScreen();
        changeScreen(froggerScreen);
    }
}
