package runner;

import gameutils.GameObject;
import gameutils.Texture;

import java.awt.Point;

public class Walls extends GameObject{
    public static final int WIDTH = 10;


    public Walls(Point point){
        super();
        this.position = point;
        this.texture = new Texture("assets/runner/blackTexture.JFIF");
        double widthPerc = ((double) WIDTH)/texture.getWidth();
        double heightPerc =((double) RunnerGame.FRAME_HEIGHT)/texture.getHeight();;
        texture.scale(widthPerc, heightPerc);
        setBounds();
    }

    public void update(){


    }

    public boolean contains(Ball ball){

       return false;
    }
}
