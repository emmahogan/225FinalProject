package runner;


import gameutils.Controller;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * This class represents the Runner game's version of controller, used to manipulate the ball
 * when the space bar is pressed
 *
 * @author Justin, Andrew, Emma, Tim, Nick
 * @version Spring 2020
 */

public class BallController extends Controller {

    //The ball object in the current game passed through the constructor
    private Ball ball;

    //Code for the space key
    public static final int SPACE = KeyEvent.VK_SPACE;


    private int x = 1;


    /**
     * Constructor for a Ball Controller that calls cuper class constructor and
     * initializes the ball variable
     *
     * @param ball The ball in the current game
     */
    public BallController(Ball ball){
        super();
        this.ball = ball;
    }

    /**
     * This method of the parent class is not used for the Runner game
     */
    @Override
    public void handleKeyInput() {}


    /**
     * Method used to control runner game by multiplying the current acceleration of the
     * ball by -1 every time the player hits the space bar
     *
     * @param e the KeyEvent
     */
    @Override
    public void keyTyped(KeyEvent e){
        //If the space bar is pressed
        if(isKeyPressed(SPACE)){
            //switch to opposite acceleration
            ball.setAcceleration(ball.getAcceleration() * -1);
        }
    }


}
