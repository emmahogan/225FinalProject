package frogger;

import gameutils.GameObject;
import java.util.ArrayList;


public abstract class Environment extends GameObject {

    public abstract ArrayList<Hazard> getHazards();

}
