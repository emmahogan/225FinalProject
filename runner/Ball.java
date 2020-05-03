package runner;

import gameutils.GameObject;
import gameutils.Texture;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
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

    //all corners of texture
    private Point upperLeft;
    private Point bottomLeft;
    private Point upperRight;
    private Point bottomRight;

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

    private int animationClock;

    private ArrayList<Texture> textures = new ArrayList<Texture>();

    public Ball(int frameWidth, int frameHeight) {
        super();
        this.texture = new Texture("assets/runner/ball1.png");
        texture.scale(0.25, 0.25);
        animationClock = 0;

        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;

        position = new Point((frameWidth - (texture.getWidth())) / 2, frameHeight - (texture.getHeight()) - 50);
        calculatePoints();

        setBounds();
        acceleration = -0.10;
        xSpeed = 0;
        scaleTextures();
        //initialize radius and center for collides with purposes
        radius = texture.getWidth()/2;
    }

    public void calculatePoints(){
        upperLeft = new Point(position.x, position.y);
        bottomLeft = new Point(position.x, position.y + texture.getHeight());
        upperRight = new Point(position.x + texture.getWidth(), position.y);
        bottomRight = new Point(position.x + texture.getWidth(), position.y + texture.getHeight());
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

    public void increaseAcceleration(){
        System.out.println("Increasing acceleration");
        if(acceleration < 0){
            acceleration = acceleration - RunnerScreen.GAME_ACC_CHANGE;
        }
        else{
            acceleration = acceleration + RunnerScreen.GAME_ACC_CHANGE;
        }
        System.out.println(acceleration);
    }


    public void update() {
        changeTexture();
        speedWait += 16;
        xSpeed = xSpeed + acceleration;
        speedWait = 0;
        position.x += xSpeed;
        setBounds();
        calculatePoints();

    }

    public void calcPixMove() {
        currentMove = (int )xSpeed / (RunnerGame.waitTIme / 16);
    }

    public void neuterSpeed() {
        this.xSpeed = 0.0;
    }


    public boolean collidesWithRect(Rectangle rect){
        Point rectUL = new Point(rect.x, rect.y);
        Point rectUR = new Point((int)(rect.x + rect.getWidth()), rect.y);
        Point rectBL = new Point(rect.x, (int)(rect.y + rect.getHeight()));
        Point rectBR = new Point((int)(rect.x + rect.getWidth()), (int)(rect.y + rect.getHeight()));

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

    private boolean collisionHelper1(Point corner, Rectangle rect){
        if(corner.x >= rect.x && corner.x <= rect.x + rect.getWidth() && corner.y >= rect.y && corner.y <= rect.y + rect.getHeight() ){
            return true;

        }
        return false;
    }

    private boolean collisionHelper2(Point bCorner, Point rCorner){
        int dx = Math.abs(bCorner.x - rCorner.x);
        int dy = Math.abs(bCorner.y - rCorner.y);
        int sum = dx + dy;
        if(sum >= texture.getWidth()){
            return true;
        }
        return false;
    }

    private void scaleTextures(){
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
        for(Texture t: textures) {
            t.scale(0.25, 0.25);
        }
    }

    public void changeTexture(){
        animationClock += 16;
        if(animationClock >= 64) {
            Random rand = new Random();
            int x = rand.nextInt(13);
            texture = textures.get(x);
            setBounds();
            calculatePoints();
            animationClock = 0;
        }
    }

}


