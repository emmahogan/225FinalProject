package racing;

import gameutils.Controller;

/**
 * Write a description of class SRController here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SRController extends Controller {
    private Car p1;
    private Car p2;

    public SRController(Car p1, Car p2) {
        super();
        this.p1 = p1;
        this.p2 = p2;
    }
    
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
