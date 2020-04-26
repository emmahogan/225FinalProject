package runner;

import gameutils.GameObject;
import java.awt.Point;

public class Ball extends GameObject{
private Point center;
private int radius;

    public Ball(){
        super();
        position = new Point(100, 100);
        radius = 50;
        center = new Point(position.x + radius, position.y - radius);
    }

    public void update(){

    }
}
