package frogger;

import gameutils.GameObject;
import gameutils.Texture;

import java.awt.*;

public class Frog extends GameObject {

    private Texture upFroggahTexture;
    private Texture downFroggahTexture;
    private Texture leftFroggahTexture;
    private Texture rightFroggahTexture;

    private int posOnLine;
    private int posInLevel;

    private boolean alive;
    private boolean onLog;
    private boolean onLand;
    private double logSpeed;


    public final int JUMP_LENGTH = 30;
    public final int Y_VAL = 420;

    public Frog () {
        super();
        upFroggahTexture = new Texture("assets/frogger/froggah_up.png");
        downFroggahTexture = new Texture("assets/frogger/froggah_down.png");
        leftFroggahTexture = new Texture("assets/frogger/froggah_left.png");
        rightFroggahTexture = new Texture("assets/frogger/froggah_right.png");
        texture = upFroggahTexture;
        posOnLine = 10;
        posInLevel = 5;
        onLand = true;
        onLog = false;
        alive = true;
        setBounds();
    }

    /**
     * Updates the frog and checks for things like if it's alive.
     */
    @Override
    public void update() {
        posOnLine = posOnLine + (int) logSpeed;

        // Checks if the frog fell in the river.
        if (!onLog && !onLand) {
            alive = false;
            // *****change texture to "drowned frog"and game over
        }
        // ******add getting mashed by a car
        setBounds();
    }

    /**
     * Returns where the frog is in the level. (Progress made.)
     *
     * @return The index of it's positioning in the level.
     */
    public int getPosInLevel() {
        return posInLevel;
    }

    /**
     * Returns the x value of the frog so it can be drawn.
     *
     * @return The x value of the frog.
     */
    public int getXVal() {
        return posOnLine * 30;
    }

    /**
     * Checks if the frog is alive. (Couldn't be isAlive() since Threads use those.)
     *
     * @return Whether or not the frog is alive.
     */
    public boolean isDead() {
        return alive;
    }

    /**
     * Returns whether or not the frog is alive.
     *
     * @return Whether or not the frog is alive.
     */
    public boolean isOnLog() {
        return onLog;
    }

    /**
     * Used to put the frog on a log and then give him horizontal velocity so that he moves with the log. Also used
     * to remove the frog from a log and bring his velocity back to 0.
     *
     * @param logStatus Whether or not the frog is on a log.
     * @param speedOfLog The speed of the log.
     */
    public void setOnLog(boolean logStatus, double speedOfLog) {
        onLog = logStatus;
        logSpeed = speedOfLog;
    }

    /**
     * Returns whether or not the frog is on land.
     *
     * @return Whether or not the frog is on land.
     */
    public boolean isOnLand() {
        return onLand;
    }

    /**
     * Sets the frog to being on land.
     *
     * @param landStatus Whether or not the frog is to be set to on or off land.
     */
    public void setOnLand(boolean landStatus) {
        onLand = landStatus;
    }

    /**
     * Returns the bounds of the frog's collision box.
     *
     * @return The bounds of the frog's collision box.
     */
    public Rectangle getBounds() {
        return bounds;
    }

    /**
     * Sets the bounds of the frog's collision box.
     */
    public void setBounds() {
        bounds = new Rectangle(this.position.x, this.position.y, texture.getWidth(), texture.getHeight());
    }

    /**
     * Moves the frog forward and changes its texture accordingly.
     */
    public void jumpForward() {
        posInLevel++;
        texture = upFroggahTexture;
        update();
    }

    /**
     * Moves the frog backward and changes its texture accordingly.
     */
    public void jumpBack() {
        if (posInLevel > 5) {
            posInLevel--;
        }
        texture = downFroggahTexture;
        update();
    }

    /**
     * Moves the frog left and changes its texture accordingly.
     */
    public void jumpLeft() {
        if (posOnLine != 0) {
            posOnLine--;
        }
        texture = leftFroggahTexture;
        update();
    }

    /**
     * Moves the frog right and changes its texture accordingly.
     */
    public void jumpRight() {
        if (posOnLine != 19) {
            posOnLine++;
        }
        texture = rightFroggahTexture;
        update();
    }
}
