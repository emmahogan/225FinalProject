package runner;

import gameutils.GameObject;
import java.awt.Point;
import java.awt.Graphics;

public class Ball extends GameObject{
private Point center;
private int radius;
private int frameWidth;
private int frameHeight;

    public Ball(int frameWidth, int frameHeight){
        super();
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        radius = 10;
        position = new Point((frameWidth - (radius * 2))/2, frameHeight - (radius * 2) - 50);
        center = new Point(position.x + radius, position.y + radius);

    }

    public int getRadius(){
        return radius;
    }


    public void update(){

    }

    public void render(Graphics g){
        g.fillOval(position.x, position.y, radius*2, radius*2);
        //render(g);
    }
}
