package frogger;

import java.util.ArrayList;
import java.util.Random;

public class EnvironmentManager {
    public ArrayList<Environment> environments;

    public EnvironmentManager(Frog frog) {
    }

    public void update() {

    }

    public void evnvironmentUp() {

    }

    public void environmentDown() {

    }

    /**
     * Creates the randomly generated level.
     */
    public void makeLevel() {
        environments = new ArrayList<>();
        Random rand = new Random();
        int levelLength = rand.nextInt(60) + 60; //Generates a random number to use for the level length (Number of horizontal lines 30px in height)
        int numRivers = levelLength / 3;  // Makes 1/3 of the level rivers.

        int numRoads = levelLength / 3;  // Makes 1/3 of the level roads.

        // This loops through and makes everything in the level just ground tiles.
        for (int i = 0; i < levelLength; i++) {
            environments.add(new Grass());
        }

        // This will go through and make all of the rivers needed for the level.
        // It will always have at least 4 in a row.
        int riversAdded = 0;
        while (riversAdded < numRivers) {

            int indexToAdd = rand.nextInt(environments.size() - 12) + 12;

            if (indexToAdd < environments.size() - 6 && indexToAdd > 6) {
                environments.set(indexToAdd, new River());
                environments.set(indexToAdd + 1, new River());
                environments.set(indexToAdd + 2, new River());
                environments.set(indexToAdd + 3, new River());
            }
            riversAdded = riversAdded + 4;
        }

        // This will go through and make all of the roads needed for the level.
        // It will always have at least 4 in a row.
        int roadsAdded = 0;
        while (roadsAdded < numRoads) {

            int indexToAdd = rand.nextInt(environments.size() - 12) + 12;

            if (indexToAdd < environments.size() - 6 && indexToAdd > 6 &&
                    !(environments.get(indexToAdd) instanceof River) && !(environments.get(indexToAdd + 4) instanceof River)) {
                environments.set(indexToAdd, new Road());
                environments.set(indexToAdd + 1, new Road());
                environments.set(indexToAdd + 2, new Road());
                environments.set(indexToAdd + 3, new Road());
            }
            roadsAdded = roadsAdded + 4;
        }
    }
}
