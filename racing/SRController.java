package racing;

import gameutils.Controller;

/**
 * This class represents the controller for the racing game
 *
 * @author Justin, Andrew, Emma, Tim, Nick
 * @version Spring 2020
 */
public class SRController extends Controller {
    //The cars representing player 1 and player 2
    private Car p1;
    private Car p2;

    /**
     * The constructor initializes the player's cars
     *
     * @param p1 player 1's car
     * @param p2 player 2's car
     */
    public SRController(Car p1, Car p2) {
        super();
        this.p1 = p1;
        this.p2 = p2;
    }

    /**
     * This method implements its superclass' method for the racing game, to control the movement
     * of the cars through the keyboard
     */
    public void handleKeyInput() {
        // player 1
        if (isKeyPressed(W) && p1.velocity.y > (-4.18)) {
            p1.velocity.y -= .22f;
        }
        if (isKeyPressed(S) && p1.velocity.y < (4.18)) {
            p1.velocity.y += .22f;
        }
        if (isKeyPressed(A) && p1.velocity.x > (-4.18)) {
            p1.velocity.x -= .22f;
        }
        if (isKeyPressed(D) && p1.velocity.x < (4.18)) {
            p1.velocity.x += .22f;
        }

        // player 2
        if (isKeyPressed(UP) && p2.velocity.y > (-4.18)) {
            p2.velocity.y -= .2f;
        }
        if (isKeyPressed(DOWN) && p2.velocity.y < (4.18)) {
            p2.velocity.y += .2f;
        }
        if (isKeyPressed(LEFT) && p2.velocity.x > (-4.18)) {
            p2.velocity.x -= .2f;
        }
        if (isKeyPressed(RIGHT) && p2.velocity.x < (4.18)) {
            p2.velocity.x += .2f;
        }
    }
}
