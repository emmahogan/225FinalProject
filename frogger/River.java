package frogger;

import gameutils.Texture;

import java.util.ArrayList;
import java.util.Random;

public class River extends Environment {

    private ArrayList<Hazard> logs; // ArrayList of the logs in the level.

    /**
     * Creates the river and the logs that float on it.
     */
    public River () {
        super();
        texture = new Texture("assets/frogger/water_line.png");

        logs = new ArrayList<>();
        Random rand = new Random();
        double speed = (rand.nextDouble() * 2) - 1.0; // Random speed for the logs, needs to be changed to support left and right.
        int numLogs = rand.nextInt(2) + 2; // Random number of logs on the river. Has a minimum of 4.

        for (int i = 0; i < numLogs; i++) {
            logs.add(new Log(speed, i * 150));
        }
    }

    /**
     * Updates all of the logs on the River. Nothing has to be updated about the River itself.
     */
    @Override
    public void update() {
        for (Hazard log: logs) {
            log.update();
        }
    }

    /**
     * Returns all of the logs on the River.
     *
     * @return All of the logs on the River.
     */
    public ArrayList<Hazard> getHazards() {
        return logs;
    }
}
