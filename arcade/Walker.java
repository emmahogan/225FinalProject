package arcade;

import java.awt.Point;

import chess.Chess;
import examplegame.ExampleGame;
import frogger.FroggerGame;
import gameutils.GameObject;
import gameutils.Texture;
import marstrip.MarsTrip;
import racing.SpeedRacers;
import runner.RunnerGame;


/**
 * This class is part of the experimental fancier version of the original arcade menu that we created, which we
 * did not end up fixing the main issue of not knowing how to have the window open in the back with the listener
 * still there to open another game if you close the window of the current game. Since we did not end up using this
 * in the final presentation, I am not including extensive javadoc comments for these classes (much of it is
 * similar to Arcade)
 * Please see "ArcadeMenu" class header for a more extensive description of what we were trying to do with
 * these classes.
 *
 * @author Justin, Andrew, Emma, Tim, Nick
 * @version Spring 2020
 */
public class Walker extends GameObject {

    private boolean isInGame;
    private GameEntrance game;

    /**
     * Constructs the "Walker" or the ball that is moving around the screen according to the
     * keyboard input, hovering over a game's icon
     *
     */
    public Walker (){
        //isInGame = false;
        game = null;
        position = new Point(ArcadeMenu.FRAME_WIDTH/2, ArcadeMenu.FRAME_HEIGHT/2);
        texture = new Texture("assets/runner/ball1.png");
        texture.scale(0.25, 0.25);
        setBounds();

    }

    public GameEntrance getGame(){
        return game;
    }

    public boolean isInGame(){
        return isInGame;
    }

    public void setInGame(boolean b, GameEntrance game){
        isInGame = b;
        this.game = game;
    }

    @Override
    public void update() {
        //Call a method that changes his texture texture for walk animation
    setBounds();

    }
    public void startGame(){
        System.out.println(ArcadeScreen.gameEntrances);
        System.out.println("We made it here");
        if (game.equals(ArcadeScreen.gameEntrances.get(0))) {
            System.out.println("Starting Example Game");
            ArcadeMenu.killGame();
            javax.swing.SwingUtilities.invokeLater(new ExampleGame("Example Game"));
        }
        else if (game.equals(ArcadeScreen.gameEntrances.get(1))) {
            System.out.println("Starting Speed Racers");
            ArcadeMenu.killGame();
            javax.swing.SwingUtilities.invokeLater(new SpeedRacers("Speed Racers"));
        }
        else if (game.equals(ArcadeScreen.gameEntrances.get(2))){
            System.out.println("Starting Chess");
            javax.swing.SwingUtilities.invokeLater(new Chess("Chess"));
        }
        else if (game.equals(ArcadeScreen.gameEntrances.get(3))){
            System.out.println("Starting Rise (not chess)");
            javax.swing.SwingUtilities.invokeLater(new RunnerGame("Rise"));
        }
        else if (game.equals(ArcadeScreen.gameEntrances.get(4))){
            System.out.println("Starting Frogger");
            javax.swing.SwingUtilities.invokeLater(new FroggerGame("Frogger"));
        }
        else if (game.equals(ArcadeScreen.gameEntrances.get(5))){
            System.out.println("Starting Mars Trip");
            javax.swing.SwingUtilities.invokeLater(new MarsTrip("Mars Trip"));
        }

    }

}
