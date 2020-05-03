package runner;

import gameutils.Screen;
import gameutils.Texture;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class RunnerScreen extends Screen{
private Ball ball;
private Walls leftWall;
private Walls rightWall;
private Floor floor;
private ArrayList<Gate> gates;

private JLabel label;
private int gatesCleared;

public static final double GAME_CHANGE = 0.25;
public static final double GAME_ACC_CHANGE = 0.02;
private double gameSpeed;
private int speedCounter;
public static final int GAME_INCREASE_WAIT = 2000;
private boolean wantSpeedIncrease = false;

private int timeU;
private int timeO;


//FRAME STATS
    private int frameWidth = RunnerGame.FRAME_WIDTH;
    private int frameHeight = RunnerGame.FRAME_HEIGHT;

public RunnerScreen (){
    super();
    int time = 0;

    this.ball = new Ball(frameWidth, frameHeight);
    this.leftWall = new Walls(new Point(0, 0));
    this.rightWall = new Walls(new Point(RunnerGame.FRAME_WIDTH - Walls.WIDTH*2,0));
    this.gates = new ArrayList<Gate>();
    this.controller = new BallController(ball);
    gatesCleared = 0;
    label = new JLabel("SCORE: " + gatesCleared);
   // label.setPreferredSize(new Dimension(100,100));
    this.add(label);
        gameSpeed = 1;
        speedCounter = 0;


    repaint();
}
    public void gameSpeedIncrease(){
    System.out.println("Calling method to increase game Speed");
    gameSpeed += GAME_CHANGE;
        int i = 0;
        while(i < gates.size()){
            Gate temp = gates.get(i);
            gates.get(i).increaseSpeed();
            i++;

        }
        ball.increaseAcceleration();

    }


    public void createScreen(Graphics g){
        g.drawImage(rightWall.getTexture(), rightWall.getPosition().x, rightWall.getPosition().y, null);
        g.drawImage(leftWall.getTexture(), leftWall.getPosition().x, leftWall.getPosition().y, null);


        //////////////////////////////////////////////////////////////////////////////////////
        if(gates.size() == 0 || gates.get(gates.size()-1).getYcoord() >= 150){
            gates.add(new Gate(gameSpeed));

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


    }

    @Override
    public void update(){

        speedCounter += 16;
        if (speedCounter >= GAME_INCREASE_WAIT) {
            gameSpeedIncrease();
            speedCounter = 0;
        }


        contact();
        ball.update();
        int i = 0;
        while(i < gates.size()){
            Gate temp = gates.get(i);
            if(temp.getYcoord() > RunnerGame.FRAME_HEIGHT){
                gates.remove(i);
                gatesCleared++;
            }
            else {
                gates.get(i).update();
                i++;
            }
        }
        label.setText("SCORE: " + gatesCleared);

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
            if (ball.collidesWithRect(left)){
                gameOver();
            }
            if (ball.collidesWithRect(right)){
                gameOver();
            }
        }


    }

    public void gameOver(){
        gates.clear();
        gatesCleared = 0;
        ball.setAcceleration(-0.10);
        ball.neuterSpeed();
        gameSpeed = 1;
        ball.setPosition((frameWidth - ball.getTexture().getWidth(null)) / 2, frameHeight - (ball.getTexture().getHeight(null)) - 50);
        repaint();
    }


}
