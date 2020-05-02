package frogger;

import gameutils.GameObject;
import gameutils.Texture;

public class Frog extends GameObject {

    private Texture upFroggahTexture;
    private Texture downFroggahTexture;
    private Texture leftFroggahTexture;
    private Texture rightFroggahTexture;
    private int froggahPos;

    public final int Y_VAL = 400;

    @Override
    public void update() {
        //Add code to satisfy update if necessary
    }

    public Frog () {
        super();
        upFroggahTexture = new Texture("assets/frogger/froggah_up.png");
        downFroggahTexture = new Texture("assets/frogger/froggah_down.png");
        leftFroggahTexture = new Texture("assets/frogger/froggah_leftpng");
        rightFroggahTexture = new Texture("assets/frogger/froggah_right.png");
        texture = upFroggahTexture;
        froggahPos = 10;

    }

    public int getPos() {
        return froggahPos;
    }

    public int getXVal() {
        return froggahPos * 30;
    }
}
