package racing;

import java.awt.Point;
import java.awt.Rectangle;

import gameutils.GameObject;

/**
 * This class represents the a wall for the track in the racing game
 *
 * @author Justin, Andrew, Emma, Tim, Nick
 * @version Spring 2020
 */
public class Wall extends GameObject {
    //size of the wall
    public static final int SIZE = 50;

    /**
     * The constructor creates the wall at the input position calculated by the size times the x and y
     * parameters
     *
     * @param x horizontal position
     * @param y vertical position
     */
    public Wall(int x, int y) {
        x = (x * SIZE);
        y = SpeedRacers.FRAME_HEIGHT - (y * SIZE) - SIZE;
        position = new Point(x, y);
        setBounds(new Rectangle(position.x, position.y, 50, 50));
    }

    @Override
    public void update() {

    }
}

