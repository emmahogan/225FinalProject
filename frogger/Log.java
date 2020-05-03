package frogger;

import gameutils.Texture;

public class Log extends Hazard {

    private double speed;
    private double x;

    public Log(double inputSpeed) {
        texture = new Texture("assets/frogger/froggah_left.png");
        speed = inputSpeed;
    }

    public double getX() {
        return x;
    }

    @Override
    public void update() {
        if (x > 0.0 - 30.0) {
            x = x - speed;
        } else {
            x = 630.0;
        }
    }

    public boolean checkCollision(Frog froggah) {
        return false;
    }

}
