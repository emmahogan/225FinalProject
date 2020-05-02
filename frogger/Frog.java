package frogger;

import gameutils.GameObject;
import gameutils.Texture;

public class Frog extends GameObject {

    private Texture froggahTexture;
    private int froggahPos;

    @Override
    public void update() {
        //Add code to satisfy update if necessary
    }

    public Frog () {
        super();
        froggahTexture = new Texture("assets/frogger/froggah_up.png");
        froggahPos = 10;

    }

    public Texture getFroggahTexture() {
        return froggahTexture;
    }

    public int getFroggahPos() {
        return froggahPos;
    }

    public int getFroggahXVal() {
        return froggahPos * 30;
    }
}
