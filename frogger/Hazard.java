package frogger;

import gameutils.GameObject;

/**
 * This abstract class is for the hazards on the level. Logs and Cars. (Logs are not hazards, but they are included
 * since the functionality is the same.)
 */
public abstract class Hazard extends GameObject {
    public double speed;

    /**
     * Checks whether or not the input frog collides with the hazard and returns true if it does, false if not.
     *
     * @param froggah The input frog to check collision with.
     * @return Whether or not the frog is colliding with the hazard.
     */
    public abstract boolean checkCollision(Frog froggah);

    /**
     * Returns the speed of the hazard.
     *
     * @return The speed of the hazard.
     */
    public double getSpeed() {
        return speed;
    }
}
