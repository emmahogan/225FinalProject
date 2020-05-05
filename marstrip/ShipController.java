package marstrip;

import gameutils.Controller;

/**
 * Justin added this game Mars Trip he had created previously for fun as an experimental addition, playing around
 * with the game engine we made from scratch. Since it was just for fun I am not adding javadoc comments.
 *
 * @author Justin Marotta
 * @version Spring 2020
 */
public class ShipController extends Controller {
    Ship ship;

    public ShipController (Ship ship) {
        super();
        this.ship = ship;
    }

    @Override
    public void handleKeyInput() {
        if (isKeyPressed(W) && ship.velocity.y > (-4)) {
            ship.velocity.y -= .4f;
        }
        if (isKeyPressed(S) && ship.velocity.y < (4)) {
            ship.velocity.y += .4f;
        }
        if (isKeyPressed(A) && ship.velocity.x > (-4)) {
            ship.velocity.x -= .4f;
        }
        if (isKeyPressed(D) && ship.velocity.x < (4)) {
            ship.velocity.x += .4f;
        }
        if (isKeyJustPressed(SPACE)) {
            ship.shoot();
        }
    }
}
