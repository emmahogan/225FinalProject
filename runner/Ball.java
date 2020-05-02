package runner;

import gameutils.GameObject;
import gameutils.Texture;

import java.awt.Point;
import java.awt.Graphics;

public class Ball extends GameObject {
    private Point center;
    private int frameWidth;
    private int frameHeight;
    //FOR POSITIONING
    private int acceleration;
    private int xSpeed;
    private int speedWait = 0;
    private int currentMove = 0;

    public Ball(int frameWidth, int frameHeight) {
        super();
        this.texture = new Texture("assets/runner/ballStart.png");
        texture.scale(0.35, 0.35);
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        position = new Point((frameWidth - (texture.getWidth())) / 2, frameHeight - (texture.getHeight()) - 50);
        setBounds();
        acceleration = 0;
        xSpeed = 0;
    }

    public int getRadius() {
        return 0;
    }

    public int getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(int acceleration) {
        this.acceleration = acceleration;
    }

    public void update() {
        speedWait += 16;

        calcPixMove();
        if (speedWait >= RunnerGame.waitTIme) {
            xSpeed = xSpeed + acceleration;
            speedWait = 0;
        }

        position.x += currentMove;


    }

    public void calcPixMove(){
        currentMove = xSpeed/(RunnerGame.waitTIme/16);
    }

    public void neuterSpeed() {
        this.xSpeed = 0;
    }
}


