package runner;

import gameutils.GameObject;
import gameutils.Texture;

import java.awt.Point;

/**
 * This class represents the walls on either side of the frame for the Runner game
 *
 * They are created as game objects instead of simply drawn on the screen because they are
 * part of the game - if the ball hits them, game over.
 *
 * @author Justin, Andrew, Emma, Tim, Nick
 * @version Spring 2020
 */

public class Walls extends GameObject{

    //The constant width of the walls on either side
    public static final int WIDTH = 10;

    /**
     * Constructor for Walls creates a wall with the given point as the upper left, scales its
     * texture and sets the position and bounds which are important in order to use its super class'
     * collidesWith method
     *
     * @param point the upper left point for the wall
     */
    public Walls(Point point){
        super();

        //Sets upper left point
        this.position = point;

        //Initializes wall texture and calculates the appropriate scaling
        //percentages in order to fit the given width and the height of the screen
        this.texture = new Texture("assets/runner/blackTexture.JFIF");
        double widthPerc = ((double) WIDTH)/texture.getWidth();
        double heightPerc =((double) RunnerGame.FRAME_HEIGHT)/texture.getHeight();;
        texture.scale(widthPerc, heightPerc);

        //Sets the bounds of the object
        setBounds();
    }

    //Required by super class but not used by Walls
    public void update(){}
}
