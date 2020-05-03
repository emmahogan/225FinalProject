package runner;

import gameutils.GameObject;
import gameutils.Texture;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.Random;

public class Ball extends GameObject {
    public static int radius;

    private int frameWidth;
    private int frameHeight;
    //FOR POSITIONING
    private double acceleration;
    private double xSpeed;
    private int speedWait = 0;
    private int currentMove = 0;

    public Ball(int frameWidth, int frameHeight) {
        super();
        this.texture = new Texture("assets/runner/ball0.png");
        texture.scale(0.25, 0.25);
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        position = new Point((frameWidth - (texture.getWidth())) / 2, frameHeight - (texture.getHeight()) - 50);
        setBounds();
        acceleration = -0.10;
        xSpeed = 0;

        //initialize radius and center for collides with purposes
        radius = texture.getWidth()/2;
    }

    public int getRadius() {
        return 0;
    }

    public double getAcceleration() {
        return acceleration;
    }



    public void setAcceleration(double acceleration) {
        this.acceleration = acceleration;
    }


    public void update() {
        changeTexture();
        speedWait += 16;

        //calcPixMove();
        //if (speedWait >= RunnerGame.waitTIme) {
            xSpeed = xSpeed + acceleration;
            speedWait = 0;
        //}
        position.x += xSpeed;
        setBounds();

    }

    public void calcPixMove() {
        //System.out.println("calcing");
        currentMove = (int )xSpeed / (RunnerGame.waitTIme / 16);
    }

    public void changeTexture(){
        Random rand = new Random();
        int x = rand.nextInt(6);
        texture = new Texture("assets/runner/ball" + x + ".png");
        texture.scale(0.25, 0.25);
    }

    public void neuterSpeed() {
        this.xSpeed = 0.0;
    }

    public boolean collidesWithRect(int width, int height, Point rectPos){
        Point center = new Point(position.x + texture.getWidth()/2, position.y + texture.getWidth()/2);
        Point bottomLeft = new Point(rectPos.x, rectPos.y+height);
        Point bottomRight = new Point(rectPos.x + width, rectPos.y+height);
        Point middleLeft = new Point(rectPos.x, rectPos.y + height/2);
        Point middleRight = new Point(rectPos.x + width, rectPos.y + height/2);
        Point upperLeft = rectPos;
        Point upperRight = new Point(rectPos.x + width, rectPos.y);
        //if the bottom of the rectangle is lower than the top of the circle
        if((rectPos.y + height) >= position.y){
            //if the bottom right corner and the bottom left corner of the rectangle are
            //more than the length of the radius distance from the center of the circle
            int x = position.x + radius;
            if((x > (rectPos.x + width) && x < frameWidth) || (x < (rectPos.x) && x > 0)) {
                if ((bottomLeft.distance(center) > radius) && (bottomRight.distance(center) > radius)) {
                    return false;
                }
            }
            return true;
        }
        if(middleLeft.distance(center) > radius && middleRight.distance(center) > radius && upperLeft.distance(center) > radius && upperRight.distance(center) > radius){
            return false;
        }
        return true;
    }
}


