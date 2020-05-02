package frogger;

import gameutils.GameObject;
import gameutils.Texture;

public class Log extends GameObject {

    private double speed;
    private double x;
    private double y;

    public Log(double inputSpeed) {
        texture = new Texture("assets/frogger/froggah_left.png");
        speed = inputSpeed;
    }

    @Override
    public void update() {
        // Add code to update log position
    }

}
