package frogger;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;

/**
 * This class is used to manage the environments, their hazards, and how they are rendered.
 *
 * @author Nick Shelby, Justin Marotta
 * @version Spring 2020
 */
public class EnvironmentManager {
    private final int LEVEL_LENGTH = 60;
    private final int START_GRASS = 7;
    private final int END_GRASS = 19;
    private final int USED_GRASS = 25;

    private final int NUM_ROWS = 20;
    private final int FROG_TO_BOTTOM_DIST = 3;

    private int screenBottomIndex; // This is where in the ArrayList the bottom of the screen begins.
    public int posInLevel; // This is how far into the level the frog has progressed.

    public ArrayList<Environment> environments;

    public EnvironmentManager() {
        posInLevel = 4;
        screenBottomIndex = posInLevel - FROG_TO_BOTTOM_DIST;
        makeLevel();
    }

    /**
     * This method updates the environment related objects.
     */
    public void update() {
        screenBottomIndex = posInLevel - FROG_TO_BOTTOM_DIST;

        // updates each environment on the screen
        for (int i = screenBottomIndex; i < NUM_ROWS + screenBottomIndex; i++) {
            environments.get(i).update();
        }

        if (posInLevel >= LEVEL_LENGTH - END_GRASS) { //Gets the line right before the last grass line.
            remakeLevel();
        }
    }

    /**
     * This method moves the environment up. (Thus moving Frogger further in the level.)
     */
    public void environmentUp() {
        if (posInLevel > 4) {
            posInLevel--;
            for (Environment e : environments) {
                e.position.y -= FroggerGame.UNIT;
                if (!(e instanceof Grass)) {
                    for (Hazard hazard : e.getHazards()) {
                        hazard.position.y -= FroggerGame.UNIT;
                    }
                }
            }
        }
    }

    /**
     * This method moves the environment down. (Thus moving Frogger back in the level.)
     */
    public void environmentDown() {
        posInLevel++;
        for (Environment e : environments) {
            e.position.y += FroggerGame.UNIT;
            if (!(e instanceof Grass)) {
                for (Hazard hazard : e.getHazards()) {
                    hazard.position.y += FroggerGame.UNIT;
                }
            }
        }
    }

    /**
     * This method dynamically renders the screen and what is to be rendered.
     *
     * @param g The Graphics pane to render to.
     */
    public void drawLevel(Graphics g) {
        // Loops through the portion of the level that needs to be rendered.
        for (int i = screenBottomIndex; i < NUM_ROWS + screenBottomIndex; i++) {

            // draws environments.
            g.drawImage(environments.get(i).getTexture(), 0, environments.get(i).position.y, null);

            // draws hazards
            if (!(environments.get(i) instanceof Grass)) {
                for (Hazard hazard : environments.get(i).getHazards()) {
                    g.drawImage(hazard.getTexture(), hazard.position.x, hazard.position.y, null);
                    g.drawRect(hazard.bounds.x, hazard.bounds.y, hazard.bounds.width, hazard.bounds.height);
                }
            }
        }
    }

    /**
     * Creates the randomly generated level.
     */
    public void makeLevel() {
        environments = new ArrayList<>();
        Random rand = new Random();

        // split level into thirds
        int numRivers = LEVEL_LENGTH / 3;
        int numRoads = LEVEL_LENGTH / 3;

        // make everything in the level grass.
        for (int i = 0; i < LEVEL_LENGTH; i++) {
            environments.add(new Grass(FroggerGame.FRAME_HEIGHT - (i * FroggerGame.SCALE)));
        }

        // This will go through and make all of the rivers needed for the level.
        // It will always have at least 4 in a row.
        int i = 0;
        while (i < numRivers) {
            int row = rand.nextInt(environments.size() - USED_GRASS) + START_GRASS;

            if (row < environments.size() - END_GRASS && row > START_GRASS) {
                environments.set(row, new River(FroggerGame.FRAME_HEIGHT - row * FroggerGame.SCALE));
                environments.set(row + 1, new River(FroggerGame.FRAME_HEIGHT - (row + 1) * FroggerGame.SCALE));
                environments.set(row + 2, new River(FroggerGame.FRAME_HEIGHT - (row + 2) * FroggerGame.SCALE));
                environments.set(row + 3, new River(FroggerGame.FRAME_HEIGHT - (row + 3) * FroggerGame.SCALE));
            }
            i = i + 4;
        }

        // This will go through and make all of the roads needed for the level.
        // It will always have at least 4 in a row.
        i = 0;
        while (i < numRoads) {

            int row = rand.nextInt(environments.size() - USED_GRASS) + START_GRASS;

            if (row < environments.size() - END_GRASS && row > START_GRASS &&
                    !(environments.get(row) instanceof River) && !(environments.get(row + 4) instanceof River)) {
                environments.set(row, new Road(FroggerGame.FRAME_HEIGHT - row * FroggerGame.SCALE));
                environments.set(row + 1, new Road(FroggerGame.FRAME_HEIGHT - (row + 1) * FroggerGame.SCALE));
                environments.set(row + 2, new Road(FroggerGame.FRAME_HEIGHT - (row + 2) * FroggerGame.SCALE));
                environments.set(row + 3, new Road(FroggerGame.FRAME_HEIGHT - (row + 3) * FroggerGame.SCALE));
            }
            i = i + 4;
        }
    }

    /**
     * This method remakes the level when you die or reach the end.
     */
    public void remakeLevel() {
        posInLevel = 4;
        screenBottomIndex = posInLevel - FROG_TO_BOTTOM_DIST;
        makeLevel();
    }
}
