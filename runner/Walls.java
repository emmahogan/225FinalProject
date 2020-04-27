package runner;

import gameutils.GameObject;

public class Walls extends GameObject{
    private int frameWidth;
    private int frameHeight;

    public Walls(int frameWidth, int frameHeight){
        super();
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;

        //Two rectangles that are the walls
        //maybe very thick depending on width of the screen
        //might make a class that this will extends to make the wall constantly updating to make
        //the ball seem to be rising
    }

    public void update(){


    }

    public boolean contains(Ball ball){

       return false;
    }
}
