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

    public FroggerController (Frog frogToControl) {
        super();
        froggah = frogToControl;
    }

    public void handleKeyInput() {
        // Need to use keyPressed, not isKeyPressed

//        if (isKeyPressed(W)) {
//            froggah.jumpForward();
//        } else if (isKeyPressed(S)) {
//            froggah.jumpBack();
//        } else if (isKeyPressed(A)) {
//            froggah.jumpLeft();
//        } else if (isKeyPressed(D)) {
//            froggah.jumpRight();
//        }
    }
}
