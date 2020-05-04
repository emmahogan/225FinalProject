package frogger;

import gameutils.Texture;

import java.awt.*;

public class CarHazard extends Hazard {

    private double speed; // Speed of the car.
    private double x; // X value of the car on the road.

    /**
     * The constructor for the car on the road.
     *
     * @param inputSpeed The speed of the car.
     * @param xPos The position of the car on the road.
     */
    public CarHazard(double inputSpeed, double xPos) {

        speed = inputSpeed;
        x = xPos;

        if (speed > 0) {
            texture = new Texture("assets/frogger/frogger_car_left.png");
        }
        texture = new Texture("assets/frogger/frogger_car_right.png");

        setBounds();
    }

    /**
     * Returns the bounds of the car's collision box.
     *
     * @return The bounds of the car's collision box.
     */
    public Rectangle getBounds() {
        return bounds;
    }

    /**
     * Sets the bounds of the car's collision box.
     */
    public void setBounds() {
        bounds = new Rectangle(this.position.x, this.position.y, texture.getWidth(), texture.getHeight());
    }

    /**
     * Returns the position of the car on the road.
     *
     * @return The x value of the car.
     */
    public double getX() {
        return x;
    }

    /**
     * Returns the speed of the car.
     *
     * @return The speed of the car.
     */
    @Override
    public double getSpeed() {
        return speed;
    }

    /**
     * Updates the car. Makes sure that the car revolve around and come back on the road again so that it
     * doesn't need to continually make new cars.
     */
    @Override
    public void update() {
        //Checks if the car is at the end of the level and will reset it to the other side if it is.
        if (speed > 0) {
            if (x < 630.0) {
                x = x + speed;
            } else {
                x = -30.0;
            }
        } else {
            if (x > -30.0) {
                x = x - speed;
            } else {
                x = 630.0;
            }
        }
        setBounds();
    }

    /**
     * Checks if the frog is hit by the car.
     *
     * @param froggah The input frog to check collision with.
     * @return Whether or not the frog was hit by the car.
     */
    public boolean checkCollision(Frog froggah) {
        if (!this.collidesWith(froggah)) {
            return true;
        }
        return false;
    }
}
