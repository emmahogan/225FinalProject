package frogger;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import gameutils.GameObject;
import gameutils.Screen;
/**
 * Outputs the screen and contains classes for functionality regarding output.
 *
 * @author Nick Shelby
 * @version Spring 2020
 */
public class FroggerScreen extends Screen
{
    private ArrayList<GameObject> levelLayout; // This holds all of the areas of land and rivers.
    private Frog froggah; // This is the player character, the frog object that you control.
    private int screenBottomIndex = 0;

    private final int NUM_ROWS = 20;
    private final int JUMP_LENGTH = 50;

    public FroggerScreen() {
        super();
        froggah = new Frog();
        makeLevel();
        
    }

    public void makeLevel() {
        levelLayout = new ArrayList<>();
        Random rand = new Random();
        int lvlLength = rand.nextInt(40) + 10; //Generates a random number to use for the level length (Number of horizontal lines 32px in height)
        int numRivers = lvlLength / 3;  // Makes 1/3 of the level river tiles.

        for (int i = 0; i < lvlLength; i++) {
            levelLayout.add(new Tile());
        }

        int riversAdded = 0;
        while (riversAdded < numRivers) {
            int indexToAdd = rand.nextInt(levelLayout.size());
            if (indexToAdd < levelLayout.size() - 4) {
                levelLayout.set(indexToAdd, new River());
                levelLayout.set(indexToAdd + 1, new River());
                levelLayout.set(indexToAdd + 2, new River());
                levelLayout.set(indexToAdd + 3, new River());
            }
            riversAdded = riversAdded + 4;
        }
    }
    
     @Override
    public void render(Graphics g) {
        int rowPos = 570;
         for (int i = screenBottomIndex; i < NUM_ROWS; i++) {
             g.drawImage(levelLayout.get(i).getTexture(), 0, rowPos, null);
             rowPos = rowPos - 30;
         }
         g.drawImage(froggah.getTexture(), froggah.getXVal(), froggah.Y_VAL, null);
    }
    
    @Override
    public void update() {
        for (int i = screenBottomIndex; i < NUM_ROWS; i++) {
            levelLayout.get(i).update();
        }
        froggah.update();
    }
    
    @Override
    public void dispose() {

    }
}
