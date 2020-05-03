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

    @Override
    public void update() {
        posOnLine = posOnLine + (int) logSpeed;

        if (!onLog && !onLand) {
            alive = false;
            //change texture to "drowned frog"and game over
        }
        //add getting mashed by a car
        setBounds();
    }

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

    public int getPosOnLine() {
        return posOnLine;
    }

    public int getPosInLevel() {
        return posInLevel;
    }

    public int getXVal() {
        return posOnLine * 30;
    }

    public boolean isDead() {
        return alive;
    }

    public boolean isOnLand() {
        return onLand;
    }

    public boolean isOnLog() {
        return onLog;
    }

    public void setOnLog(boolean logStatus, double speedOfLog) {
        onLog = logStatus;
        logSpeed = speedOfLog;
    }

    public void setOnLand(boolean landStatus) {
        onLand = landStatus;
    }

    public void setBounds() {
        bounds = new Rectangle(this.position.x, this.position.y, texture.getWidth(), texture.getHeight());
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void jumpForward() {
        posInLevel++;
        texture = upFroggahTexture;
        update();
    }

    public void jumpBack() {
        if (posInLevel > 5) {
            posInLevel--;
        }
        texture = downFroggahTexture;
        update();

    }

    public void jumpLeft() {
        if (posOnLine != 0) {
            posOnLine--;
        }
        texture = leftFroggahTexture;
        update();

    }

    public void jumpRight() {
        if (posOnLine != 19) {
            posOnLine++;
        }
        texture = rightFroggahTexture;
        update();

    }
}
