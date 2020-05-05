package racing;

import java.awt.Point;
import java.awt.Rectangle;

import gameutils.GameObject;
import gameutils.Texture;
import gameutils.FloatPoint;

/**
 * This class represents one of the cars in the racing game
 *
 * @author Justin, Andrew, Emma, Tim, Nick
 * @version Spring 2020
 */
public class Car extends GameObject {
    //Rectangles representing the bounds of the car
    public Rectangle topBound;
    public Rectangle bottomBound;
    public Rectangle leftBound;
    public Rectangle rightBound;

    //All of the possible textures depending on which way the car is facing, as well as the current
    //texture and next texture
    private Texture up;
    private Texture down;
    private Texture left;
    private Texture right;
    private Texture curTexture;
    private Texture nextTexture;

    //FloatPoint objects used to animate the realistic animation of the cars' movement
    FloatPoint velocity;
    FloatPoint position;


    /**
     * The constructor for this class calls a method to initialize the textures for a new car
     * of the given color as well as its bounds, position, and velocity
     *
     * @param color Color of the car
     * @param x Initial x positon
     * @param y Initial y position
     */
    public Car(String color, int x, int y) {
        super();
        initTextures(color);
        position = new FloatPoint(x, y);
        velocity = new FloatPoint(0, 0);

        setBoundsAndLines();
    }

    /**
     * The update method for the cars updates the bounds of the car, then calls methods
     * to move using its velocity as well as checking the rotation
     */
    @Override
    public void update() {
        if (bounds.getLocation() != position.toPoint()) {
            setBoundsAndLines();
        }
        moveByVelocity();
        drag();
        checkRotation();
    }

    /**
     * This method moves the car according to its current velocity
     */
    private void moveByVelocity() {
        position.x += velocity.x;
        position.y += velocity.y;
    }

    /**
     * This method checks based on the cars velocity whether the texture should be changed
     * to face the car in the accurate direction, then changes the texture if necessary
     */
    private void checkRotation() {

        //If statements check based on velocity if car's texture should be changed

        if (velocity.x > 1.5) {
            nextTexture = right;
            setBoundsAndLines();
        }
        if (velocity.x < -1.5) {
            nextTexture = left;
            setBoundsAndLines();
        }
        if (velocity.y < -1.5) {
            nextTexture = up;
            setBoundsAndLines();
        }
        if (velocity.y > 1.5) {
            nextTexture = down;
            setBoundsAndLines();
        }

        //Changes texture if needed

        if (curTexture != nextTexture) {
            curTexture = nextTexture;
            texture = curTexture;
        }
    }

    /**
     * This method is used to create an accurate simulation by adjusting the velocity
     * to make the movement of the car look realistic
     *
     */
    private void drag() {
        if (velocity.x >= .12) {
            velocity.x -= .12;
        } else if (velocity.x > 0) {
            velocity.x = 0;
        }

        if (velocity.x <= -.12) {
            velocity.x += .12;
        } else if (velocity.x < 0) {
            velocity.x = 0;
        }

        if (velocity.y >= .12) {
            velocity.y -= .12;
        } else if (velocity.y > 0) {
            velocity.y = 0;
        }

        if (velocity.y <= -.12) {
            velocity.y += .12;
        } else if (velocity.y < 0) {
            velocity.y = 0;
        }
    }

    /**
     * Accessor for the car's position due to using float points instead throughout the above methods
     * which need to be converted back to ints
     *
     * @return the Point representing the cars current position
     */
    @Override
    public Point getPosition() {
        return new Point((int) position.x, (int) position.y);
    }

    /**
     * Overrides the set position method to convert the int representation of the Point to a new FloatPoint
     *
     * @param newPosition the point of the new position
     */
    @Override
    public void setPosition(Point newPosition) {
        this.position = new FloatPoint(newPosition.x, newPosition.y);
    }

    /**
     * This method is called to set the bounds around the car
     */
    private void setBoundsAndLines() {
        bounds = new Rectangle((int) position.x, (int) position.y, texture.getWidth(), texture.getHeight());
        topBound = new Rectangle(bounds.x + 2, bounds.y, bounds.width - 4, 3);
        bottomBound = new Rectangle(bounds.x + 2, bounds.y + bounds.height - 1, bounds.width - 4, 3);
        leftBound = new Rectangle(bounds.x, bounds.y + 2, 3, bounds.height - 4);
        rightBound = new Rectangle(bounds.x + bounds.width - 1, bounds.y + 2, 3, bounds.height - 4);
    }

    /**
     * This method checks for collision between the car and any of the walls in the array, and the other player's
     * car
     *
     * @param walls The array of walls around the track
     * @param other The other player's car
     */
    public void checkCollision(Wall[][] walls, Car other) {

        //Checks if the car is colliding with the other players car, and if so adjusts
        //its velocity accordingly

        if (topBound.intersects(other.bounds)) {
            velocity.y = 0;
            velocity.y += 1;
        }
        if (bottomBound.intersects(other.bounds)) {
            velocity.y = 0;
            velocity.y -= 1;
        }
        if (leftBound.intersects(other.bounds)) {
            velocity.x = 0;
            velocity.x += 1;
        }
        if (rightBound.intersects(other.bounds)) {
            velocity.x = 0;
            velocity.x -= 1;
        }

        //For all of the walls in the array, checks if the car is colliding with the wall, and if so adjusts
        //its velocity accordingly

        for (Wall[] wallRow : walls) {
            for (Wall wall : wallRow) {
                if (wall != null) {
                    if (topBound.intersects(wall.bounds)) {
                        velocity.y = 0;
                        velocity.y += 1;
                    }
                    if (bottomBound.intersects(wall.bounds)) {
                        velocity.y = 0;
                        velocity.y -= 1;
                    }
                    if (leftBound.intersects(wall.bounds)) {
                        velocity.x = 0;
                        velocity.x += 1;
                    }
                    if (rightBound.intersects(wall.bounds)) {
                        velocity.x = 0;
                        velocity.x -= 1;
                    }
                }
            }
        }
    }


    /**
     * Initializes all of the textures representing the different directions the car is facing and scales them,
     * using the images for the car of the given input color
     *
     * @param color The color of the car
     */
    private void initTextures(String color) {

        //Initializes and scales the textures for the blue car if input was blue

        if (color.equals("blue")) {
            up = new Texture("assets/racing/player1_up.png");
            up.scale(.03, .03);
            down = new Texture("assets/racing/player1_down.png");
            down.scale(.03, .03);
            left = new Texture("assets/racing/player1_left.png");
            left.scale(.03, .03);
            right = new Texture("assets/racing/player1_right.png");
            right.scale(.03, .03);

        //Initializes and scales the textures for the blue car if input was blue
        } else {
            up = new Texture("assets/racing/player2_up.png");
            up.scale(.03, .03);
            down = new Texture("assets/racing/player2_down.png");
            down.scale(.03, .03);
            left = new Texture("assets/racing/player2_left.png");
            left.scale(.03, .03);
            right = new Texture("assets/racing/player2_right.png");
            right.scale(.03, .03);
        }

        //Sets current and next textures to be facing right
        texture = right;
        curTexture = right;
        nextTexture = right;
    }
}
