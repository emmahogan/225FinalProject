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
    private int screenBottomIndex;

    private final int NUM_ROWS = 20;
    private final int FROG_TO_BOTTOM_DIST = 5;

    public FroggerScreen() {
        super();
        froggah = new Frog();
        controller = new FroggerController(froggah);
        makeLevel();
        screenBottomIndex = froggah.getPosInLevel() - FROG_TO_BOTTOM_DIST;
        
    }

    public void makeLevel() {
        levelLayout = new ArrayList<>();
        Random rand = new Random();
        int levelLength = rand.nextInt(60) + 60; //Generates a random number to use for the level length (Number of horizontal lines 30px in height)
        int numRivers = levelLength / 3;  // Makes 1/3 of the level rivers.

        for (int i = 0; i < levelLength; i++) {
            levelLayout.add(new Tile());
        }

        // png seams don't seem to be right, sometimes they are aligned, sometimes they are not.
        // error when you reach the end of the level, null obviously
        // make some sort of rock wall for edges, start, and maybe a little frog nesting hole for the end
        // start and end never have water, but end needs to work better
        // probably needs to allow frogger to move upwards to the final tiles
        // could also happen at start, level only moves when it isn't at the end. (remove frog distance final)
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
    }

    // ******* Needs functionality and message for when you catch a dub
    public boolean checkWin() {
//        if (froggah.getPosInLevel() == levelLayout.size()) {
//            return true;
//        }
        return false;
    }
    
    @Override
    public void render(Graphics g) {
         screenBottomIndex = froggah.getPosInLevel() - FROG_TO_BOTTOM_DIST;
         int rowPos = 570;
         for (int i = screenBottomIndex; i < NUM_ROWS + screenBottomIndex; i++) {
             if (levelLayout.get(i) instanceof River) {
                 g.drawImage(levelLayout.get(i).getTexture(), 0, rowPos, null);
                 for(Hazard log: levelLayout.get(i).getHazards()) {
                     g.drawImage(log.getTexture(), (int) log.getX(), rowPos, null);
                 }
                 rowPos = rowPos - 30;
             } else {
                 g.drawImage(levelLayout.get(i).getTexture(), 0, rowPos, null);
                 rowPos = rowPos - 30;
             }
         }
         g.drawImage(froggah.getTexture(), froggah.getXVal(), froggah.Y_VAL, null);
    }
    
    @Override
    public void update() {
        screenBottomIndex = froggah.getPosInLevel() - FROG_TO_BOTTOM_DIST;
        for (int i = screenBottomIndex; i < NUM_ROWS; i++) {
            levelLayout.get(i).update();
        }
        froggah.update();
        if (levelLayout.get(froggah.getPosInLevel()) instanceof River) {
            for(Hazard log: ((River) levelLayout.get(froggah.getPosInLevel())).getHazards()) {
                if (!froggah.isOnLog() && log.checkCollision(froggah)) {
                    froggah.setOnLog(true, log.getSpeed());
                }
            }

            if (!froggah.isAlive()) {
                froggah.texture = new Texture("assets/frogger/water_line.png");
                //game over
            }
        }
    }

    public void gameOver() {
        System.out.println("Game Over");
    }

    @Override
    public void dispose() {

    }
}
