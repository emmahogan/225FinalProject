package marstrip;

import java.awt.Point;
import java.util.ArrayList;

import gameutils.FloatPoint;
import gameutils.GameObject;
import gameutils.Texture;

/**
 * Justin added this game Mars Trip he had created previously for fun as an experimental addition, playing around
 * with the game engine we made from scratch. Since it was just for fun I am not adding javadoc comments.
 *
 * @author Justin Marotta
 * @version Spring 2020
 */
public class Ship extends GameObject {
    public ArrayList<Bullet> bullets;
    FloatPoint velocity;
    FloatPoint position;

    public Ship(int x, int y) {
        bullets = new ArrayList<>();
        texture = new Texture("assets/marstrip/ship.png");
        texture.scale(2, 2);
        position = new FloatPoint(x, y);
        velocity = new FloatPoint(0, 0);
        setBounds();
    }

    @Override
    public void update() {
        if (bounds.getLocation() != position.toPoint()) {
            setBounds();
        }
        moveByVelocity();
        flyingBoundry();
        drag();
    }

    public void shoot() {
        bullets.add(new Bullet(this));
    }

    private void moveByVelocity() {
        position.x += velocity.x;
        position.y += velocity.y;
    }

    private void flyingBoundry() {
        if (position.x < 0) {
            position.x = 0;
        }
        if (position.x > MarsTrip.FRAME_WIDTH - texture.getWidth()) {
            position.x = MarsTrip.FRAME_WIDTH - texture.getWidth();
        }
        if (position.y < 0) {
            position.y = 0;
        }
        if (position.y > MarsTrip.FRAME_HEIGHT- texture.getHeight() - 40) {
            position.y =  MarsTrip.FRAME_HEIGHT- texture.getHeight() - 40;
        }
    }

    private void drag() {
        if (velocity.x >= .09) {
            velocity.x -= .1;
        }
        if (velocity.x <= -.09) {
            velocity.x += .1;
        }
        if (velocity.y <= -.09) {
            velocity.y += .1;
        }
        if (velocity.y >= .09) {
            velocity.y -= .1;
        }
    }

    @Override
    public Point getPosition() {
        return new Point((int) position.x, (int) position.y);
    }

    @Override
    public void setPosition(Point newPosition) {
        this.position = new FloatPoint(newPosition.x, newPosition.y);
    }
}
