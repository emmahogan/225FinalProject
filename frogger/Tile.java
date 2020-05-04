package frogger;

import gameutils.Texture;

import java.util.ArrayList;

/**
 * This will create the horizontal row of grass.
 *
 * @author Nick Shelby
 * @version Spring 2020
 */
public class Tile extends Environment {

    /**
     * Creates the ground tile line.
     */
    public Tile() {
        super();
        texture = new Texture("assets/frogger/grass_line.png");

    }

    @Override
    public void update() {

    }

    public ArrayList<Hazard> getHazards() {
        return null;
    }

}
