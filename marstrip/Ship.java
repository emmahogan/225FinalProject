package marstrip;

import java.awt.Point;

import gameutils.FloatPoint;
import gameutils.GameObject;
import gameutils.Texture;

public class Ship extends GameObject {
    FloatPoint velocity;
    FloatPoint position;

    public Ship(int x, int y) {
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
        drag();
    }

    private void moveByVelocity() {
        if (position.x >= 0 && position.x <= MarsTrip.FRAME_WIDTH - texture.getWidth()) {
            position.x += velocity.x;
        }
        if (position.y >= 0 && position.y <= MarsTrip.FRAME_HEIGHT + texture.getHeight() + 40) {
            position.y += velocity.y;
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
