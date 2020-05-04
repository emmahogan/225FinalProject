package frogger;

import gameutils.GameObject;
import java.util.ArrayList;

/**
 * This abstract class is for the different environments that are on the level.
 */
public abstract class Environment extends GameObject {

    public Environment(int y) {
        setPosition(0, y);
    }

    /**
     * Returns the hazards on that environment. Returns null if it does not have hazards.
     *
     * @return The hazards on that environment. Null if it does not have hazards.
     */
    public abstract ArrayList<Hazard> getHazards();
}
