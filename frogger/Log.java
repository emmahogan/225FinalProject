package frogger;

import gameutils.Texture;

import java.awt.*;

/**
 * The logs that the frog can jump on and ride.
 */
public class Log extends Hazard {

    private double x; // X value of the log on the river.
    private int y;

    /**
     * The constructor for the log on the river.
     *
     * @param inputSpeed The speed of the log.
     * @param xPos The position of the log on the river.
     */
    public Log(double inputSpeed, double xPos) {
        texture = new Texture("assets/frogger/log.png");
        speed = inputSpeed;
        x = xPos;
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
            if (x < 630.0) {
                x = x + speed;
            } else {
                x = -30.0;
            }
        } else {
            if (x > -30.0) {
                x = x - speed;
            } else {
                x = 630.0;
            }
        }
        setPosition((int) x, y);
        setBounds();
    }

    /**
     * Checks if the frog is on the log.
     *
     * @param froggah The input frog to check collision with.
     * @return Whether or not the frog is on the log.
     */
    public boolean checkCollision(Frog froggah) {
        if (!this.collidesWith(froggah)) {
            return true;
        }
        return false;
    }

}
