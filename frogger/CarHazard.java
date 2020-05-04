package frogger;

import gameutils.Texture;

import java.awt.*;

public class CarHazard extends Hazard {

    private double x; // X value of the car on the road.
    private Road road;

    /**
     * The constructor for the car on the road.
     *
     * @param inputSpeed The speed of the car.
     * @param x The position of the car on the road.
     */
    public CarHazard(double inputSpeed, double x, Road road) {
        speed = inputSpeed;

        if (speed < 0) {
            texture = new Texture("assets/frogger/frogger_car_left.png");
        } else {
            texture = new Texture("assets/frogger/frogger_car_right.png");
        }

        this.x = x;
        this.road = road;
        setBounds();
    }

    /**
     * Updates the car. Makes sure that the car revolve around and come back on the road again so that it
     * doesn't need to continually make new cars.
     */
    @Override
    public void update() {
        //Checks if the car is at the end of the level and will reset it to the other side if it is.
        if (speed > 0) {
            if (x < 600.0) {
                x += speed;
            } else {
                x = -60.0;
            }
        } else {
            if (x > -30.0) {
                x += speed;
            } else {
                x = 600.0;
            }
        }
        setPosition((int) x, road.position.y);
        setBounds();
    }
}
