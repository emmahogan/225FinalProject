package frogger;

import java.awt.Graphics;
import java.util.ArrayList;

import gameutils.Screen;
/**
 * Outputs the screen and contains classes for functionality regarding output.
 *
 * @author Nick Shelby
 * @version Spring 2020
 */
public class FroggerScreen extends Screen
{
    private ArrayList<Tile> tiles; // This holds all of the grass texture tiles that get displayed
    private Frog froggah; // This is the player character, the frog object that you control
    private ArrayList<River> rivers; // Holds all of the levels river objects

    public FroggerScreen() {
        super();
        int lvlLength = Math.random(60) + 20; //Generates a random number to use for the level length (Number of horizontal lines 32px in height)
        int numRivers = lvlLength / 5;  // Makes 1/5
        int numTiles = (lvlLength - numRivers) * 12; // Gets the number of tiles needed to make the level, according to random length

        for (int i = 0; i < numRivers; i++) {
            rivers.add(new River());
        }

        
    }
    
     @Override
    public void render(Graphics g) {
        
    }
    
    @Override
    public void update() {
    
    }
    
    @Override
    public void dispose() {

    }
}
