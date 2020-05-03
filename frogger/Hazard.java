package frogger;

import gameutils.GameObject;

public abstract class Hazard extends GameObject {

    public abstract boolean checkCollision(Frog froggah);

    public abstract double getX();

    public abstract double getSpeed();
}
