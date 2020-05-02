package runner;

import gameutils.Screen;

import java.awt.*;
import java.util.ArrayList;

public class RunnerScreen extends Screen{
private Ball ball;
private Walls leftWall;
private Walls rightWall;
private Floor floor;
private ArrayList<Gate> gates;


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
    repaint();
}

    public void createScreen(Graphics g){
        g.drawImage(rightWall.getTexture(), rightWall.getPosition().x, rightWall.getPosition().y, null);
        g.drawImage(leftWall.getTexture(), leftWall.getPosition().x, leftWall.getPosition().y, null);


        //////////////////////////////////////////////////////////////////////////////////////
        if(gates.size() == 0 || gates.get(gates.size()-1).getYcoord() >= 150){
            gates.add(new Gate());

        }
        ////////////////////////////////////////////////////////////////////////////////////////
    }



    @Override
    public void render(Graphics g){
        createScreen(g);
        g.drawImage(ball.getTexture(), ball.getPosition().x, ball.getPosition().y, null);
        int i = 0;
        while(i < gates.size()){
            Gate temp = gates.get(i);
                gates.get(i).renderGates(g);
                i++;

        }

        update();

    }

    @Override
    public void update(){

        ball.update();

        int i = 0;
        while(i < gates.size()){
            Gate temp = gates.get(i);
            if(temp.getYcoord() > RunnerGame.FRAME_HEIGHT){
                gates.remove(i);
            }
            else {
                gates.get(i).update();
                i++;
            }
        }
        contact();
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
        if(ball.collidesWith(leftWall) || ball.collidesWith(rightWall)){
            gameOver();
        }
        if(gates.size() > 0) {
            Rectangle left = gates.get(0).getLeftRect();
            Rectangle right = gates.get(0).getRightRect();
            if (ball.collidesWithRect((int) left.getWidth(), (int) left.getHeight(), left.getLocation())) {
                gameOver();
            }
            if (ball.collidesWithRect((int) right.getWidth(), (int) right.getHeight(), right.getLocation())) {
                gameOver();
            }
        }


    }

    public void gameOver(){
        gates.clear();
        ball.setAcceleration(0);
        ball.neuterSpeed();

        ball.setPosition((frameWidth - ball.getTexture().getWidth(null)) / 2, frameHeight - (ball.getTexture().getHeight(null)) - 50);
        repaint();
    }

}
