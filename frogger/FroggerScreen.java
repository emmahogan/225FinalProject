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
    private ArrayList<Environment> levelLayout; // This holds all of the areas of land and rivers.
    private Frog froggah; // This is the player character, the frog object that you control.
    private int screenBottomIndex; // This is where in the ArrayList the bottom of the screen begins.
    private double frogX; // This is the x value of the frog.
    private final int frogY = 420; // This is the y value of the frog. (Always stays the same.)
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
        makeLevel();
        frogLevelPos = 5;
        screenBottomIndex = frogLevelPos - FROG_TO_BOTTOM_DIST;
        
    }

    /**
     * Creates the randomly generated level.
     */
    public void makeLevel() {
        levelLayout = new ArrayList<>();
        Random rand = new Random();
        int levelLength = rand.nextInt(60) + 60; //Generates a random number to use for the level length (Number of horizontal lines 30px in height)
        int numRivers = levelLength / 3;  // Makes 1/3 of the level rivers.

        int numRoads = levelLength / 3;  // Makes 1/3 of the level roads.

        // This loops through and makes everything in the level just ground tiles.
        for (int i = 0; i < levelLength; i++) {
            levelLayout.add(new Tile());
        }

        // This will go through and make all of the rivers needed for the level.
        // It will always have at least 4 in a row.
        int riversAdded = 0;
        while (riversAdded < numRivers) {

            int indexToAdd = rand.nextInt(levelLayout.size() - 12) + 12;

            if (indexToAdd < levelLayout.size() - 6 && indexToAdd > 6) {
                levelLayout.set(indexToAdd, new River());
                levelLayout.set(indexToAdd + 1, new River());
                levelLayout.set(indexToAdd + 2, new River());
                levelLayout.set(indexToAdd + 3, new River());
            }
            riversAdded = riversAdded + 4;
        }

        // This will go through and make all of the roads needed for the level.
        // It will always have at least 4 in a row.
        int roadsAdded = 0;
        while (roadsAdded < numRoads) {

            int indexToAdd = rand.nextInt(levelLayout.size() - 12) + 12;

            if (indexToAdd < levelLayout.size() - 6 && indexToAdd > 6 &&
                    !(levelLayout.get(indexToAdd) instanceof River) && !(levelLayout.get(indexToAdd + 4) instanceof River)) {
                levelLayout.set(indexToAdd, new Road());
                levelLayout.set(indexToAdd + 1, new Road());
                levelLayout.set(indexToAdd + 2, new Road());
                levelLayout.set(indexToAdd + 3, new Road());
            }
            roadsAdded = roadsAdded + 4;
        }
    }

    /**
     * This will check to see if the frog has made it to the end of the level.
     *
     * @return Whether or not the frog has made it to the end.
     */
    public boolean checkWin() {
        if (frogLevelPos == levelLayout.size() - 15) {
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
             g.drawImage(levelLayout.get(i).getTexture(), 0, rowPos, null);

             // Checks if the portion is a river so that it can then render the logs as well.
             if (levelLayout.get(i) instanceof River) {
                 // Loops through all of the logs.
                 for(Hazard log: levelLayout.get(i).getHazards()) {
                     g.drawImage(log.getTexture(), (int) log.getX(), rowPos, null);
                 }
             }

             if (levelLayout.get(i) instanceof Road) {
                 // Loops through all of the cars.
                 for(Hazard car: levelLayout.get(i).getHazards()) {
                     g.drawImage(car.getTexture(), (int) car.getX(), rowPos, null);
                 }
             }
             // Changes the value to draw at so it can draw the different parts of the level in order.
             rowPos = rowPos - 30;
         }

         g.drawImage(froggah.getTexture(), (int) frogX, frogY, null);
    }

    /**
     * This will update all of the things in the game.
     */
    @Override
    public void update() {
        // Updates values regarding the frog's positioning.
        froggah.update();
        frogX = froggah.getXVal();
        frogLevelPos = froggah.getPosInLevel();
        screenBottomIndex = frogLevelPos - FROG_TO_BOTTOM_DIST;

        // Updates each of the level parts.
        for (int i = screenBottomIndex; i < NUM_ROWS + screenBottomIndex; i++) {
            levelLayout.get(i).update();
        }

        // Checks if the layer that the frog is on is a River so that it will check for collision on a log.
        if (levelLayout.get(frogLevelPos) instanceof River) {
            for(Hazard log: (levelLayout.get(frogLevelPos).getHazards())) {
                if (!froggah.isOnLog() && log.checkCollision(froggah)) {
                    froggah.setOnLog(true, log.getSpeed());
                }
            }
            // Checks if the frog is alive so that it can change it's texture and cause a game over if necessary.
            if (!froggah.isAlive()) {
                froggah.texture = new Texture("assets/frogger/water_line.png");
                //game over
            }
        }

        // Checks if the layer that the frog is on is a Road so that it will check for collision with a car.
        if (levelLayout.get(frogLevelPos) instanceof Road) {
            for(Hazard car: (levelLayout.get(frogLevelPos).getHazards())) {
                if (!froggah.isOnLog() && car.checkCollision(froggah)) {
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
}
