package marstrip;

import gameutils.Controller;

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
