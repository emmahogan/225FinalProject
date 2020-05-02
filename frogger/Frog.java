package frogger;

import gameutils.GameObject;
import gameutils.Texture;

public class Frog extends GameObject {

    private Texture upFroggahTexture;
    private Texture downFroggahTexture;
    private Texture leftFroggahTexture;
    private Texture rightFroggahTexture;
    private int posOnLine;
    private int posInLevel;


    public final int JUMP_LENGTH = 50;
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
        posOnLine = 10;
        posInLevel = 4;

    }

    public int getPosOnLine() {
        return posOnLine;
    }

    public int getPosInLevel() {
        return posInLevel;
    }

    public int getXVal() {
        return posOnLine * 30;
    }

    public void jumpForward() {
        posInLevel++;
        update();
    }

    public void jumpBack() {
        posInLevel--;
        update();
    }

    public void jumpLeft() {
        posOnLine = posOnLine - 10;
        update();
    }

    public void jumpRight() {
        posOnLine = posOnLine + 10;
        update();
    }
}
