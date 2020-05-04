package arcade;

import gameutils.GameObject;
import gameutils.Texture;

import java.awt.*;

public class GameEntrance extends GameObject {

    private boolean containsWalker;

    public GameEntrance(Point position){
        containsWalker = false;
        this.position = position;
        texture = new Texture("assets/runner/ball1.png");
        texture.scale(0.25, 0.25);
    }

    @Override
    public void update() {

    }
}
