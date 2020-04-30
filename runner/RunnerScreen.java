package runner;

import java.awt.Graphics;
import gameutils.Screen;
import java.util.ArrayList;

public class RunnerScreen extends Screen{
private Ball ball;
private Walls walls;
private Floor floor;
private ArrayList<Gate> gates;
private BallController controller;

private boolean touched;

//FRAME STATS
    private int frameWidth = RunnerGame.FRAME_WIDTH;
    private int frameHeight = RunnerGame.FRAME_HEIGHT;

public RunnerScreen (){
    super();
    this.ball = new Ball(frameWidth, frameHeight);
    this.walls = new Walls(frameWidth, frameHeight);
    this.gates = new ArrayList<Gate>();
    this.controller = new BallController(ball);
    this.touched = false;

    //render(super.getGraphics());
}
    @Override
    public void render(Graphics g){
    ball.render(g);


    }

    @Override
    public void update(){


    }

    @Override
    public void dispose(){


    }

    /**
     * Method checks if ball contact was made with wall or gate
     *
     */

    public void contact(){
    if(walls.contains(ball) || gates.get(0).contains(ball)){
        touched = true;
    }


    }

}
