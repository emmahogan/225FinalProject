package frogger;

import java.awt.Graphics;
import java.util.ArrayList;

import gameutils.Screen;

import static frogger.FroggerGame.SCALE;

/**
 * Outputs the screen and contains classes for functionality regarding output.
 *
 * @author Nick Shelby
 * @version Spring 2020
 */
public class FroggerScreen extends Screen {
    public EnvironmentManager environmentManager;
    private ArrayList<Environment> environments; // This holds all of the areas of land and rivers.
    public Frog froggah; // This is the player character, the frog object that you control.

    /**
     * Creates the screen that the game is played on.
     */
    public FroggerScreen() {
        super();
        environmentManager = new EnvironmentManager();
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

}
