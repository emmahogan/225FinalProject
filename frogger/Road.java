package frogger;

import gameutils.Texture;

import java.util.ArrayList;
import java.util.Random;

/**
 * This will create the horizontal row of Road and cars.
 *
 * @author Nick Shelby
 * @version Spring 2020
 */
public class Road extends Environment {

    private ArrayList<Hazard> cars; // ArrayList of the cars in the level.

    /**
     * Creates the ground tile line.
     */
    public Road(int y) {
        super(y);
        texture = new Texture("assets/frogger/road_line.png");

        cars = new ArrayList<>();
        Random rand = new Random();
        double speed = (rand.nextDouble() * 2) - 1.0; // Random speed for the logs, needs to be changed to support left and right.
        int numCars = rand.nextInt(2) + 2; // Random number of logs on the river. Has a minimum of 4.

        for (int i = 0; i < numCars; i++) {
            cars.add(new CarHazard(speed, (i * 150), this));
        }
    }

    /**
     * Updates all of the cars on the road.
     */
    @Override
    public void update() {
        for (Hazard car: cars) {
            car.update();
        }
    }

    /**
     * Returns the cars on the road.
     *
     * @return The ArrayList of the cars on the road.
     */
    public ArrayList<Hazard> getHazards() {
        return cars;
    }

}
