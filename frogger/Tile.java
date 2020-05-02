package frogger;

import gameutils.GameObject;
import gameutils.Texture;

import java.awt.Point;

/**
 * This will create the horizontal row of grass.
 *
 * @author Nick Shelby
 * @version Spring 2020
 */
public class Tile extends GameObject {

    public Tile() {
        super();
        texture = new Texture("assets/frogger/grass_line.png");
        //Add code to construct grass stuff
        //Shouldn't really need much in here since it is just the grass, nothing special.
    }

    @Override
    public void update() {
        //May not need code in here.
    }

}
