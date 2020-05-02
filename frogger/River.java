package frogger;

import gameutils.GameObject;
import gameutils.Texture;

import java.util.ArrayList;

public class River extends GameObject {
    //Write code to have each horizontal line of the river have a spawn rate for the logs.
    //The logs all move at the same speed, for now.

    private ArrayList<Log> logs;

    public River () {
        texture = new Texture("assets/frogger/water_line.png");
    }

    @Override
    public void update() {
        //Add code to satisfy update if necessary
    }
}
