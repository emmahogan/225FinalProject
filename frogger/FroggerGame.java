package frogger;

import gameutils.Game;
/**
 * Write a description of class FroggerGame here.
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
