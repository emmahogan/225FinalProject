package runner;

import gameutils.Screen;

import javax.swing.JLabel;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 * This class represents the screen used to play the game Runner, and extends the
 * abstract class Screen
 *
 * @author Justin, Andrew, Emma, Tim, Nick
 * @version Spring 2020
 */

public class RunnerScreen extends Screen{

    //The ball object for game
    private Ball ball;

    //Left and right wall objects
    private Walls leftWall;
    private Walls rightWall;

    //Array list that holds all Gates currently on the screen
    private ArrayList<Gate> gates;

    //Labels for the high score and current score
    private JLabel highScore;
    private JLabel scorelabel;

    //Variables keeping track of the high score and current score, determined
    //by the number of gates successfully cleared
    private int gatesCleared;
    private int highestScore;

    //Frame dimensions brought in for easier access
    private int frameWidth = RunnerGame.FRAME_WIDTH;
    private int frameHeight = RunnerGame.FRAME_HEIGHT;

    /**
     * The constructor for the runner screen class creates all of the necessary game objects needed -
     * the ball, walls, and arraylist to hold gates as well as an instance of the controller, initializes
     * the score variables and places the JLabels displaying them at the top of the panel.
     */
    public RunnerScreen (){
        super();

        //Ball object to be used
        this.ball = new Ball(frameWidth, frameHeight);

        //Creates left and right walls
        this.leftWall = new Walls(new Point(0, 0));
        this.rightWall = new Walls(new Point(frameWidth - Walls.WIDTH*2,0));

        //Array list to hold all gates
        this.gates = new ArrayList<Gate>();

        //Instance of controller for game
        this.controller = new BallController(ball);

        //Variables keeping track of and labels displaying high and current scores
        gatesCleared = 0;
        highestScore = 0;
        highScore = new JLabel("HIGH SCORE: " +  highestScore + "  |");
        scorelabel = new JLabel("SCORE: " + gatesCleared);

        //Adds labels for scores to top of panel
        this.add(highScore);
        this.add(scorelabel);

        repaint();
    }


    /**
     * This method is called by the render method in repainting the screen to draw the
     * walls, and also determines whether a new pair of gates should be added to the screen
     *
     * @param g Graphics object
     */
    public void createScreen(Graphics g){

        //Draws the left and right walls
        g.drawImage(rightWall.getTexture(), rightWall.getPosition().x, rightWall.getPosition().y, null);
        g.drawImage(leftWall.getTexture(), leftWall.getPosition().x, leftWall.getPosition().y, null);

        //If there are no gates in the arraylist currently, or if the most recent
        //gate that was added has traveled at least 150 pixels down the screen,
        //a new pair of gates should be added
        if(gates.size() == 0 || gates.get(gates.size()-1).getYcoord() >= 150){
            gates.add(new Gate());

        }
    }


    /**
     * This class' render method:
     *     - Calls the createScreen method to draw walls
     *     - Draws the ball at its current position
     *     - Draws all of the gates currently on screen in the gates arraylist
     *
     * @param g the Graphics from paintComponent
     */
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


    /**
     * The update method for this class is responsible for:
     *    - Calling a method for checking for contact between objects
     *    - Calling the balls update method
     *    - Checking if a gate has reached the bottom of the screen, and if so
     *    removes it from the array list of gates as well as incrementing the
     *    current score
     *    - updates the label with the current score
     */
    @Override
    public void update(){
        //calls method to check for contact
        contact();

        //calls ball's update method
        ball.update();

        //Checks if a gate has reached the bottom of the screen
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
        scorelabel.setText("SCORE: " + gatesCleared);
    }



    /**
     * This method checks if ball contact was made with wall or gate
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
        if(gatesCleared > highestScore){
            highestScore = gatesCleared;
        }
        highScore.setText("HIGH SCORE: " + highestScore + "  |");

        gatesCleared = 0;
        ball.setAcceleration(-0.23);
        ball.neuterSpeed();
        ball.setPosition((frameWidth - ball.getTexture().getWidth(null)) / 2, frameHeight - (ball.getTexture().getHeight(null)) - 50);
        repaint();
    }


    @Override
    public void dispose(){}

}
