package runner;

import gameutils.GameObject;
import gameutils.Texture;

import java.awt.Point;
import java.awt.Graphics;

public class Ball extends GameObject{
private Point center;
private int frameWidth;
private int frameHeight;
//FOR POSITIONING
private int acceleration;
private int xSpeed;

    public Ball(int frameWidth, int frameHeight){
        super();
        this.texture = new Texture("assets/runner/ballStart.png");
        texture.scale(0.35, 0.35);
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        position = new Point((frameWidth - (texture.getWidth()))/2, frameHeight - (texture.getHeight()) - 50);
        setBounds();
        acceleration = 0;
        xSpeed = 0;
    }

    public int getRadius(){
        return 0;
    }

    public int getAcceleration(){return acceleration;}

    public void setAcceleration(int acceleration){this.acceleration = acceleration;}

    public void update(){
         xSpeed = xSpeed + acceleration;
         position.x += xSpeed;
    }


}
