package runner;

import gameutils.Screen;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class RunnerScreen extends Screen{
private Ball ball;
private Walls leftWall;
private Walls rightWall;
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
    this.leftWall = new Walls(new Point(0, 0));
    this.rightWall = new Walls(new Point(RunnerGame.FRAME_WIDTH - Walls.WIDTH*2,0));
    this.gates = new ArrayList<Gate>();
    this.controller = new BallController(ball);
    this.touched = false;
    repaint();
}

    public void createScreen(Graphics g){
        g.drawImage(rightWall.getTexture(), rightWall.getPosition().x, rightWall.getPosition().y, null);
        g.drawImage(leftWall.getTexture(), leftWall.getPosition().x, leftWall.getPosition().y, null);

        if(gates.size() == 0 || gates.get(gates.size()-1).getPosition().y >= 150){
            gates.add(new Gate());


        }
    }

    @Override
    public void render(Graphics g){
        createScreen(g);
        ball.update();
        g.drawImage(ball.getTexture(), ball.getPosition().x, ball.getPosition().y, null);
        for(int i = 0; i < gates.size(); i++){
            gates.get(i).update();
            gates.get(i).renderGates(g);
        }

    }

    @Override
    public void update(){
        repaint();


    }

    @Override
    public void dispose(){


    }

    /**
     * Method checks if ball contact was made with wall or gate
     *
     */

    public void contact(){



    }

}
