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

public class Walker extends GameObject {

    private boolean isInGame;
    private GameEntrance game;

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
            javax.swing.SwingUtilities.invokeLater(new ExampleGame("Example Game"));
        }
        else if (game.equals(ArcadeScreen.gameEntrances.get(1))) {
            System.out.println("Starting Speed Racers");
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
