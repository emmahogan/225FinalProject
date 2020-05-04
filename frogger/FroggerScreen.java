package frogger;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import gameutils.Screen;
import gameutils.Texture;

/**
 * Outputs the screen and contains classes for functionality regarding output.
 *
 * @author Nick Shelby
 * @version Spring 2020
 */
public class FroggerScreen extends Screen
{
    private ArrayList<Environment> environments; // This holds all of the areas of land and rivers.
    private Frog froggah; // This is the player character, the frog object that you control.
    private int screenBottomIndex; // This is where in the ArrayList the bottom of the screen begins.
    private int frogLevelPos; // This is how far into the level the frog has progressed.

    private final int NUM_ROWS = 20; // This is how many rows are to be displayed on the screen at once.
    private final int FROG_TO_BOTTOM_DIST = 5; // This is how many rows above the bottom the frog is.

    /**
     * Creates the screen that the game is played on.
     */
    public FroggerScreen() {
        super();
        froggah = new Frog();
        controller = new FroggerController(froggah);
        frogLevelPos = 5;
        screenBottomIndex = frogLevelPos - FROG_TO_BOTTOM_DIST;
        
    }

    /**
     * Renders everything on the screen.
     *
     * @param g the Graphics from paintComponent
     */
    @Override
    public void render(Graphics g) {
         screenBottomIndex = frogLevelPos - FROG_TO_BOTTOM_DIST;
         int rowPos = 570;

         // Loops through the portion of the level that needs to be rendered.
         for (int i = screenBottomIndex; i < NUM_ROWS + screenBottomIndex; i++) {

             // Draws the water or ground.
             g.drawImage(environments.get(i).getTexture(), 0, rowPos, null);

             // Checks if the portion is a river so that it can then render the logs as well.
             if (environments.get(i) instanceof River) {
                 // Loops through all of the logs.
                 for(Hazard log: environments.get(i).getHazards()) {
                     g.drawImage(log.getTexture(), log.position.x, rowPos, null);
                     g.drawRect(log.bounds.x, log.bounds.y, log.bounds.width, log.bounds.height);
                 }
             }

             if (environments.get(i) instanceof Road) {
                 // Loops through all of the cars.
                 for(Hazard car: environments.get(i).getHazards()) {
                     g.drawImage(car.getTexture(), car.position.x, rowPos, null);
                 }
             }
             // Changes the value to draw at so it can draw the different parts of the level in order.
             rowPos = rowPos - 30;
         }

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
        frogLevelPos = froggah.getPosInLevel();
        screenBottomIndex = frogLevelPos - FROG_TO_BOTTOM_DIST;

        // Updates each of the level parts.
        for (int i = screenBottomIndex; i < NUM_ROWS + screenBottomIndex; i++) {
            environments.get(i).update();
        }

        // Checks if the layer that the frog is on is a River so that it will check for collision on a log.
        if (environments.get(frogLevelPos) instanceof River) {
            ArrayList<Hazard> logs = environments.get(frogLevelPos).getHazards();
            for(int j = 0; j < logs.size(); j++) {
                logs.get(j).position.y = (600 - (screenBottomIndex * 30));
                if (!froggah.isOnLog() && logs.get(j).checkCollision(froggah)) {
                    froggah.setOnLog(true, logs.get(j).getSpeed());
                }
            }

            // Checks if the frog is on a log so that it can change it's texture and cause a game over if necessary.
            if (!froggah.isOnLog()) {
                froggah.texture = new Texture("assets/frogger/water_line.png");
                //game over
            }
        }

        // Checks if the layer that the frog is on is a Road so that it will check for collision with a car.
        if (environments.get(frogLevelPos) instanceof Road) {
            ArrayList<Hazard> cars = environments.get(frogLevelPos).getHazards();
            for(int j = 0; j < cars.size(); j++) {
                cars.get(j).position.y = (600 - (screenBottomIndex * 30));
                   if (cars.get(j).checkCollision(froggah)) {
                       System.out.println("Frog dead");
                   }
            }

            // Checks if the frog is alive so that it can change it's texture and cause a game over if necessary.
            if (!froggah.isAlive()) {
                froggah.texture = new Texture("assets/frogger/grass_line.png");
                //game over
            }
        }
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
        if (frogLevelPos == environments.size() - 15) {
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
