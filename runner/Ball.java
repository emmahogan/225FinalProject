package runner;

import gameutils.GameObject;
import gameutils.Texture;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 * This class represents an instance of the ball used in the runner game, which extends GameObject
 *
 * @author Justin, Andrew, Emma, Tim, Nick
 * @version Spring 2020
 */
public class Ball extends GameObject {

    //frame measurements
    private int frameWidth;
    private int frameHeight;

    //FOR POSITIONING
    private double acceleration;
    private double xSpeed;
    private int speedWait = 0;
    private int currentMove = 0;

    //all corners of texture
    private Point upperLeft;
    private Point bottomLeft;
    private Point upperRight;
    private Point bottomRight;

    //array list holding all textures used for animation
    private ArrayList<Texture> textures = new ArrayList<Texture>();

    //textures for animation
    private Texture ball1;
    private Texture ball2;
    private Texture ball3;
    private Texture ball4;
    private Texture ball5;
    private Texture ball6;
    private Texture ball7;
    private Texture ball8;
    private Texture ball9;
    private Texture ball10;
    private Texture ball11;
    private Texture ball12;
    private Texture ball13;

    //timer for animation
    private int animationClock;

    //which texture
    private int pic;

    //the radius of the ball
    public static int radius;


    /**
     * Constructor for Ball object sets its initial texture, and initializes all variables
     * needed for animation and calculations for positioning
     *
     * @param frameWidth Width of frame
     * @param frameHeight Height of frame
     */
    public Ball(int frameWidth, int frameHeight) {
        super();

        //Set to initial texture
        this.texture = new Texture("assets/runner/ball1.png");
        texture.scale(0.25, 0.25);

        //Initialize variables used for animation
        animationClock = 0;
        pic = 0;

        //Call method to scale all textures used for animation
        scaleTextures();

        //set input frame dimensions
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;

        //Calculate starting position horizontally centered towards bottom of screen
        position = new Point((frameWidth - (texture.getWidth())) / 2, frameHeight - (texture.getHeight()) - 50);

        //Calculate corner points and set bounds
        calculatePoints();
        setBounds();

        //initialize variables used for speed and acceleration with the ball
        //starting by accelerating to the left
        acceleration = -0.23;
        xSpeed = 0;

        //initialize radius for collides with purposes
        radius = texture.getWidth()/2;
    }

    /**
     * Method to calculate the corners of the bounds based on the current position
     */
    public void calculatePoints(){
        upperLeft = new Point(position.x, position.y);
        bottomLeft = new Point(position.x, position.y + texture.getHeight());
        upperRight = new Point(position.x + texture.getWidth(), position.y);
        bottomRight = new Point(position.x + texture.getWidth(), position.y + texture.getHeight());
    }

    /**
     * The Ball's update method does the following:
     *    - Calls method to change texture for animation effect
     *    - Updates the speed and acceleration variables
     *    - Then updates the position accordingly
     *    - Calls method of superclass to update bounds
     */
    public void update() {
        //for animation
        changeTexture();

        //updates variables for speed and acceleration
        speedWait += 16;
        xSpeed = xSpeed + acceleration;
        speedWait = 0;

        //updates positioning and bounds
        position.x += xSpeed;
        calculatePoints();
        setBounds();
    }


    ////////////////////////////// MODIFIERS AND ACCESSORS //////////////////////////////////////

    /**
     * Accessor method for current acceleration
     *
     * @return current acceleration
     */
    public double getAcceleration() {
        return acceleration;
    }

    /**
     * Modifier method to set acceleration
     *
     * @param acceleration The acceleration to change to
     */
    public void setAcceleration(double acceleration) {
        this.acceleration = acceleration;
    }

    /**
     * This method neutralizes the ball's speed (sets to 0)
     */
    public void neuterSpeed() {
        this.xSpeed = 0.0;
    }




    ////////////////////////////////// COLLISION WITH GATES /////////////////////////////////////////


    /**
     * The first of 3 methods used to accurately detect the collision of the circular ball with
     * the rectangular gates based on circumference of the ball, not the bounds
     * of the rectangle encapsulating it
     *
     * @param rect The rectangle representing a gate
     *
     * @return true if the objects are colliding, false if not
     */
    public boolean collidesWithRect(Rectangle rect){

        //Bottom left corner of rectangle
        Point rectBL = new Point(rect.x, (int)(rect.y + rect.getHeight()));
        //Bottom right corner of rectangle
        Point rectBR = new Point((int)(rect.x + rect.getWidth()), (int)(rect.y + rect.getHeight()));

        //Call pair of helper methods to help with accurate detection of collision
        if(collisionHelper1(upperLeft, rect)){
            return collisionHelper2(upperLeft, rectBR);
        }
        if(collisionHelper1(upperRight, rect)){
            return collisionHelper2(upperRight, rectBL);
        }
        if(collisionHelper1(bottomLeft, rect)){
            return collisionHelper2(bottomLeft, upperRight);
        }
        if(collisionHelper1(bottomRight, rect)){
            return collisionHelper2(bottomRight, upperLeft);
        }
        return false;
    }

    /**
     * First helper method for detecting collision between a ball and a gate
     *
     * @param corner Corner of ball's bounds
     * @param rect The rectangle to check for collision with
     *
     * @return Whether they are colliding
     */
    private boolean collisionHelper1(Point corner, Rectangle rect){
        if(corner.x >= rect.x && corner.x <= rect.x + rect.getWidth() && corner.y >= rect.y && corner.y <= rect.y + rect.getHeight() ){
            return true;
        }
        return false;
    }

    /**
     * Second helper method for detecting collision between a ball and a gate that
     * now knows the specific corners of the ball and rectangle to check
     *
     * @param bCorner Corner of ball's bounds
     * @param rCorner The rectangle's corner to check for collision with
     *
     * @return Whether they are colliding
     */
    private boolean collisionHelper2(Point bCorner, Point rCorner){
        int dx = Math.abs(bCorner.x - rCorner.x);
        int dy = Math.abs(bCorner.y - rCorner.y);
        int sum = dx + dy;
        if(sum >= texture.getWidth()){
            return true;
        }
        return false;
    }


    /////////////////////////////////////// ANIMATION ///////////////////////////////////////////////

    /**
     * This method for the animation is used to:
     *    - Initialize all textures used for animation
     *    - Add all of these textures to the arraylist
     *    - Scale all of these textures to the correct size
     */
    private void scaleTextures(){

        //Initialize all textures
        ball1 = new Texture("assets/runner/ball1.png");
        ball2 = new Texture("assets/runner/ball2.png");
        ball3 = new Texture("assets/runner/ball3.png");
        ball4 = new Texture("assets/runner/ball4.png");
        ball5 = new Texture("assets/runner/ball5.png");
        ball6 = new Texture("assets/runner/ball6.png");
        ball7 = new Texture("assets/runner/ball7.png");
        ball8 = new Texture("assets/runner/ball8.png");
        ball9 = new Texture("assets/runner/ball9.png");
        ball10 = new Texture("assets/runner/ball10.png");
        ball11 = new Texture("assets/runner/ball11.png");
        ball12 = new Texture("assets/runner/ball12.png");
        ball13 = new Texture("assets/runner/ball13.png");

        //Add textures to array list
        textures.add(ball1);
        textures.add(ball2);
        textures.add(ball3);
        textures.add(ball4);
        textures.add(ball5);
        textures.add(ball6);
        textures.add(ball7);
        textures.add(ball8);
        textures.add(ball9);
        textures.add(ball10);
        textures.add(ball11);
        textures.add(ball12);
        textures.add(ball13);

        //Scale all textures
        for(Texture t: textures) {
            t.scale(0.25, 0.25);
        }
    }

    /**
     * This method is called to switch the texture to create the animation effect
     */
    public void changeTexture(){
        //Set variable accordingly that helps with timing of image switches
        animationClock += 16;
        if(animationClock >= 48) {
            //keeping track of what picture number it is on, iterates through the array list of textures
            if(pic >= textures.size()){
                pic = 0;
            }

            //Switches texture
            texture = textures.get(pic);
            pic++;

            //resets bounds
            setBounds();
            calculatePoints();

            //Resets clock
            animationClock = 0;
        }
    }

}


