package frogger;

import gameutils.Texture;
import marstrip.MarsTrip;

import java.awt.*;

/**
 * The logs that the frog can jump on and ride.
 *
 * @author Nick Shelby
 * @version Spring 2020
 */
public class Log extends Hazard {

    private River river;
    private double x; // X value of the log on the river.

    /**
     * The constructor for the log on the river.
     *
     * @param inputSpeed The speed of the log.
     * @param x The position of the log on the river.
     */
    public Log(double inputSpeed, double x, River river) {
        texture = new Texture("assets/frogger/log.png");
        speed = inputSpeed;
        this.x = x;
        this.river = river;
        setBounds();
    }

    /**
     * Updates the log. Makes sure that the logs revolve around and come back on the river again so that it
     * doesn't need to continually make new logs.
     */
    @Override
    public void update() {
        //Checks if the log is at the end of the level and will reset it to the other side if it is.
        if (speed > 0) {
            if (x < FroggerGame.FRAME_WIDTH) {
                x += speed;
            } else {
                x = -60.0;
            }
        } else {
            if (x > -60.0) {
                x += speed;
            } else {
                x = FroggerGame.FRAME_WIDTH;
            }
        }
        setPosition((int) x, river.position.y);
        setBounds();
    }

}
