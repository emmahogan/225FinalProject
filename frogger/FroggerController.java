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
        keyNeedsToBeReleased = false;
    }

    public void handleKeyInput() {
        // Need to use keyPressed, not isKeyPressed

        if (isKeyPressed(W) && !keyNeedsToBeReleased) {
            froggah.jumpForward();
            keyNeedsToBeReleased = true;
        } else if (isKeyPressed(S) && !keyNeedsToBeReleased) {
            froggah.jumpBack();
            keyNeedsToBeReleased = true;
        } else if (isKeyPressed(A) && !keyNeedsToBeReleased) {
            froggah.jumpLeft();
            keyNeedsToBeReleased = true;
        } else if (isKeyPressed(D) && !keyNeedsToBeReleased) {
            froggah.jumpRight();
            keyNeedsToBeReleased = true;
        }

        if (!isKeyPressed(W) && !isKeyPressed(A) && !isKeyPressed(S) && !isKeyPressed(D)) {
            keyNeedsToBeReleased = false;
        }
    }
}
