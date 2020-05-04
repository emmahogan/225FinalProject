package frogger;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import gameutils.Screen;
import gameutils.Texture;

import static frogger.FroggerGame.SCALE;

/**
 * Outputs the screen and contains classes for functionality regarding output.
 *
 * @author Nick Shelby
 * @version Spring 2020
 */
public class FroggerScreen extends Screen {
    private EnvironmentManager environmentManager;
    private ArrayList<Environment> environments; // This holds all of the areas of land and rivers.
    private Frog froggah; // This is the player character, the frog object that you control.

    /**
     * Creates the screen that the game is played on.
     */
    public FroggerScreen() {
        super();
        environmentManager = new EnvironmentManager();
        environmentManager.makeLevel();
        environments = environmentManager.environments;

        froggah = new Frog(environmentManager);

        controller = new FroggerController(froggah);
    }

    /**
     * Renders everything on the screen.
     *
     * @param g the Graphics from paintComponent
     */
    @Override
    public void render(Graphics g) {

        environmentManager.drawLevel(g);

        g.drawImage(froggah.getTexture(), froggah.position.x, froggah.position.y, null);
        g.drawRect(froggah.bounds.x, froggah.bounds.y, froggah.bounds.width, froggah.bounds.height);
    }

    /**
     * This will update all of the things in the game.
     */
    @Override
    public void update() {
        // Updates values regarding the frog's positioning.
        froggah.update();
        environmentManager.update();
    }

    @Override
    public void dispose() {

    }

    /**
     * This will check to see if the frog has made it to the end of the level.
     *
     * @return Whether or not the frog has made it to the end.
     */
    public boolean checkWin() {
        if (environmentManager.posInLevel == environments.size() - 15) {
            return true;
        }
        return false;
    }

    /**
     * This output a game over screen if the frog dies.
     */
    public void gameOver() {
        System.out.println("Game Over");
    }
}
