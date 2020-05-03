package frogger;

import gameutils.Texture;

import java.awt.*;

public class Log extends Hazard {

    private double speed;
    private double x;

    public Log(double inputSpeed, double xPos) {
        texture = new Texture("assets/frogger/froggah_left.png");
        speed = inputSpeed;
        x = xPos;
        setBounds();
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds() {
        bounds = new Rectangle(this.position.x, this.position.y, texture.getWidth(), texture.getHeight());
    }

    public double getX() {
        return x;
    }

    @Override
    public double getSpeed() {
        return speed;
    }

    @Override
    public void update() {
        if (x > 0.0 - 30.0) {
            x = x - speed;
        } else {
            x = 630.0;
        }
        setBounds();
    }

    public boolean checkCollision(Frog froggah) {
        if (!this.collidesWith(froggah)) {
            return true;
        }
        return false;
    }

}
