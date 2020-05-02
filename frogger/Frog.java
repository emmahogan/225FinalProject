package frogger;

import gameutils.GameObject;
import gameutils.Texture;

public class Frog extends GameObject {

    Texture froggahTexture;

    @Override
    public void update() {
        //Add code to satisfy update if necessary
    }

    public Frog () {
        super();
        froggahTexture = new Texture("assets/frogger/froggah_up.png");
        //setPosition --- center of screen, on grass


    }
}
