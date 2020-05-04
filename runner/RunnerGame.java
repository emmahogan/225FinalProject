package runner;

import gameutils.Game;

/**
 * This class represents an implementation of a game "Runner" that extends the Game class
 *
 * @author Justin, Andrew, Emma, Tim, Nick
 * @version Spring 2020
 */

public class RunnerGame extends Game {

    //Screen used for playing the game
    private RunnerScreen runScreen;

    /**
     * Constructor for RunnerGame that calls its super class' constructor, then creates a new
     * instance of RunnerScreen and changes the screen to this
     *
     * @param name The title of the game "Runner"
     */
    public RunnerGame (String name){
        super(name);
        runScreen = new RunnerScreen();
        changeScreen(runScreen);
    }

    /**
     * This class uses its superclass' implementation for the run method
     */
    @Override
    public void run() {
        super.run();
    }
}
