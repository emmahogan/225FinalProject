package frogger;

import gameutils.Controller;
import gameutils.Texture;

import java.awt.event.KeyEvent;

/**
 * Write a description of class FroggerController here.
 *
 * @author Nick Shelby
 * @version Spring 2020
 */
public class FroggerController extends Controller
{
    Frog froggah;
    private boolean keyNeedsToBeReleased;

    public FroggerController (Frog frogToControl) {
        super();
        froggah = frogToControl;
    }

    public void handleKeyInput() {
        if (isKeyJustPressed(W)) {
            froggah.jumpForward();
        } else if (isKeyJustPressed(S)) {
            froggah.jumpBack();
            keyNeedsToBeReleased = true;
        } else if (isKeyJustPressed(A)) {
            froggah.jumpLeft();
        } else if (isKeyJustPressed(D)) {
            froggah.jumpRight();
        }
    }

}
