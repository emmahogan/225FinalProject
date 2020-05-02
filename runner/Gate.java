package runner;

import gameutils.GameObject;
import java.util.Random;
import java.awt.Point;
import java.awt.Graphics;

public class Gate extends GameObject implements Runnable{
    private Random rand = new Random();
    private Point upperLeft1;
    private Point upperLeft2;
    private int width1;
    private int width2;
    private int yCoord;
    public static final int GATE_HEIGHT = 20;
    public static final int GATE_OPENING = 250;

    public Gate(){
        super();
        //this constructor randomly generate a gate
        yCoord = 0;
        upperLeft1 = new Point(Walls.WIDTH,yCoord);
        width1 = rand.nextInt(RunnerGame.FRAME_WIDTH - GATE_OPENING - 2*Walls.WIDTH) + Walls.WIDTH;
        upperLeft2 = new Point(width1 + GATE_OPENING, yCoord);
        width2 = RunnerGame.FRAME_WIDTH - (2*Walls.WIDTH) - width1 - GATE_OPENING;

    }

    public int getYcoord(){
        return yCoord;
    }

    public void update(){
        try{
            sleep(16);
        }
        catch(InterruptedException e){
            System.out.println("Error");
        }
        yCoord += 2;
        upperLeft1.y = yCoord;
        upperLeft2.y = yCoord;
    }

    public void renderGates(Graphics g){
            g.fillRect(upperLeft1.x, upperLeft1.y, width1, GATE_HEIGHT);
            g.fillRect(upperLeft2.x, upperLeft2.y, width2, GATE_HEIGHT);
    }

    /**
    public void run(){
        while(yCoord < RunnerGame.FRAME_HEIGHT){
            try{
                sleep(16);
            }
            catch(InterruptedException e){
                System.out.println("Error loser");
            }
            update();
        }

    }
    */


    public boolean contains(Ball ball){

        return false;
    }

    ///////////////////////////////////CHANGE TO WORK WITH RECTANGLES =) ////////////////
    @Override
    public boolean collidesWith(GameObject obj) {

        return super.collidesWith(obj);
    }
}
