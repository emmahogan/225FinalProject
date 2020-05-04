package runner;

import gameutils.GameObject;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Random;

/**
 * This class represents the pairs of gates that come down in the Runner game with an opening through which
 * the player must manipulate the ball to go through without hitting either of the gates
 *
 * @author Justin, Andrew, Emma, Tim, Nick
 * @version Spring 2020
 */

public class Gate extends GameObject implements Runnable{

    //Random object for generating where gate opening is
    private Random rand = new Random();

    //Upper Left corners of left and right gates
    private Point upperLeft1;
    private Point upperLeft2;

    //Widths of left and right gates
    private int width1;
    private int width2;

    //The current y coordinate of the gates
    private int yCoord;

    //speed at which gates are falling
    private double speed;

    //Constant height of gates
    public static final int GATE_HEIGHT = 20;

    //Constant width of opening between gates
    public static final int GATE_OPENING = 250;

    //Helps control the speed at which the gates are updated
    private int speedWaiter = 0;


    /**
     * Constructor for Gate object which creates a pair of gates and randomly generates where
     * their opening occurs
     */
    public Gate(){
        super();

        //gates start at top of screen
        yCoord = 0;

        //Upper left of left gate
        upperLeft1 = new Point(Walls.WIDTH,yCoord);

        //Width of left gate randomly generated within range so that the constant width of the opening
        //between two gates happens in bounds
        width1 = rand.nextInt(RunnerGame.FRAME_WIDTH - GATE_OPENING - 2*Walls.WIDTH) + Walls.WIDTH;

        //Upper left of right gate
        upperLeft2 = new Point(width1 + GATE_OPENING, yCoord);

        //width of right gate
        width2 = RunnerGame.FRAME_WIDTH - (2*Walls.WIDTH) - width1 - GATE_OPENING;

        //Speed falling
        speed = 3;
    }



//////////////////////////////////// UPDATE AND RENDER /////////////////////////////////////////

    /**
     * The gates' update method increases the y coordinate to move them down the screen
     * at their given speed, as well as updating their upper left positions
     */
    public void update(){
            yCoord += speed;
            upperLeft1.y = yCoord;
            upperLeft2.y = yCoord;
    }


    /**
     * This method draws the two black rectangles representing the left and right gates
     *
     * @param g Graphics object
     */
    public void renderGates(Graphics g){
            g.fillRect(upperLeft1.x, upperLeft1.y, width1, GATE_HEIGHT);
            g.fillRect(upperLeft2.x, upperLeft2.y, width2, GATE_HEIGHT);
    }



    /////////////////////////////////// ACCESSORS ////////////////////////////////////

    /**
     * Accessor for left gate in pair
     *
     * @return left rectangle
     */
    public Rectangle getLeftRect(){
        return new Rectangle(upperLeft1.x, upperLeft1.y, width1, GATE_HEIGHT);
    }

    /**
     * Accessor for right gate in pair
     *
     * @return right rectangle
     */
    public Rectangle getRightRect(){
        return new Rectangle(upperLeft2.x, upperLeft2.y, width2, GATE_HEIGHT);
    }


    /**
     * Accessor method for current y coordinate of the gates
     *
     * @return the current y coordinate of the gates
     */
    public int getYcoord(){
        return yCoord;
    }
}
