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
    private int screenBottomIndex;

    private final int NUM_ROWS = 20;
    private final int FROG_TO_BOTTOM_DIST = 4;

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
        int lvlLength = rand.nextInt(60) + 60; //Generates a random number to use for the level length (Number of horizontal lines 30px in height)
        int numRivers = lvlLength / 3;  // Makes 1/3 of the level rivers.

        for (int i = 0; i < lvlLength; i++) {
            levelLayout.add(new Tile());
        }

        //*************************
        //Need to add something to spice up the end and start so that it isn't just grass

        //*************************

        // Null pointer error because the whole level isn't made or at least isn't shown *************
        // **************************************************************************************
        //******************************************************************************************
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
         screenBottomIndex = froggah.getPosInLevel() - FROG_TO_BOTTOM_DIST;
         int rowPos = 570;
         for (int i = screenBottomIndex; i < NUM_ROWS + screenBottomIndex; i++) {
             g.drawImage(levelLayout.get(i).getTexture(), 0, rowPos, null);
             rowPos = rowPos - 30;
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
    }

    @Override
    public void dispose() {

    }
}
