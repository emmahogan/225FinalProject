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

public RunnerScreen (){
    super();
    this.ball = new Ball();
    this.walls = new Walls();
    this.floor = new Floor();
    this.gates = new ArrayList<Gate>();
    this.controller = new BallController(ball);
    this.touched = false;


}

    public void render(Graphics g){


    }

    public void update(){


    }

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
