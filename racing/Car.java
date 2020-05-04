package racing;

import java.awt.Point;
import java.awt.Rectangle;

import gameutils.GameObject;
import gameutils.Texture;
import gameutils.FloatPoint;

public class Car extends GameObject {
    public Rectangle topBound;
    public Rectangle bottomBound;
    public Rectangle leftBound;
    public Rectangle rightBound;

    private Texture up;
    private Texture down;
    private Texture left;
    private Texture right;
    private Texture curTexture;
    private Texture nextTexture;

    FloatPoint velocity;
    FloatPoint position;

    public Car(String color, int x, int y) {
        super();
        initTextures(color);
        position = new FloatPoint(x, y);
        velocity = new FloatPoint(0, 0);

        setBoundsAndLines();
    }

    @Override
    public void update() {
        if (bounds.getLocation() != position.toPoint()) {
            setBoundsAndLines();
        }
        moveByVelocity();
        drag();
        checkRotation();
    }

    private void moveByVelocity() {
        position.x += velocity.x;
        position.y += velocity.y;
    }

    private void checkRotation() {
        if (velocity.x > 1.5) {
            nextTexture = right;
            setBoundsAndLines();
        }
        if (velocity.x < -1.5) {
            nextTexture = left;
            setBoundsAndLines();
        }
        if (velocity.y < -1.5) {
            nextTexture = up;
            setBoundsAndLines();
        }
        if (velocity.y > 1.5) {
            nextTexture = down;
            setBoundsAndLines();
        }
        if (curTexture != nextTexture) {
            curTexture = nextTexture;
            texture = curTexture;
        }
    }

    private void drag() {
        if (velocity.x >= .12) {
            velocity.x -= .12;
        } else if (velocity.x > 0) {
            velocity.x = 0;
        }

        if (velocity.x <= -.12) {
            velocity.x += .12;
        } else if (velocity.x < 0) {
            velocity.x = 0;
        }

        if (velocity.y >= .12) {
            velocity.y -= .12;
        } else if (velocity.y > 0) {
            velocity.y = 0;
        }

        if (velocity.y <= -.12) {
            velocity.y += .12;
        } else if (velocity.y < 0) {
            velocity.y = 0;
        }
    }

    public void checkCollision(Wall[][] walls, Car other) {
        if (topBound.intersects(other.bounds)) {
            velocity.y = 0;
            velocity.y += 1;
        } else if (bottomBound.intersects(other.bounds)) {
            velocity.y = 0;
            velocity.y -= 1;
        } else if (leftBound.intersects(other.bounds)) {
            velocity.x = 0;
            velocity.x += 1;
        } else if (rightBound.intersects(other.bounds)) {
            velocity.x = 0;
            velocity.x -= 1;
        }
        for (Wall[] wallRow : walls) {
            for (Wall wall : wallRow) {
                if (wall != null) {

                    // check top
                    if (topBound.intersects(wall.bounds)) {
                        velocity.y = 0;
                        velocity.y += 1;

                        // check bottom
                    } else if (bottomBound.intersects(wall.bounds)) {
                        velocity.y = 0;
                        velocity.y -= 1;

                        // check left
                    } else if (leftBound.intersects(wall.bounds)) {
                        velocity.x = 0;
                        velocity.x += 1;

                        // check right
                    } else if (rightBound.intersects(wall.bounds)) {
                        velocity.x = 0;
                        velocity.x -= 1;
                    }
                }
            }
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

    private void setBoundsAndLines() {
        bounds = new Rectangle((int) position.x, (int) position.y, texture.getWidth(), texture.getHeight());
        topBound = new Rectangle(bounds.x + 2, bounds.y, bounds.width - 4, 3);
        bottomBound = new Rectangle(bounds.x + 2, bounds.y + bounds.height - 1, bounds.width - 4, 3);
        leftBound = new Rectangle(bounds.x, bounds.y + 2, 3, bounds.height - 4);
        rightBound = new Rectangle(bounds.x + bounds.width - 1, bounds.y + 2, 3, bounds.height - 4);
    }

    private void initTextures(String color) {
        if (color.equals("blue")) {
            up = new Texture("assets/racing/player1_up.png");
            up.scale(.03, .03);
            down = new Texture("assets/racing/player1_down.png");
            down.scale(.03, .03);
            left = new Texture("assets/racing/player1_left.png");
            left.scale(.03, .03);
            right = new Texture("assets/racing/player1_right.png");
            right.scale(.03, .03);
        } else {
            up = new Texture("assets/racing/player2_up.png");
            up.scale(.03, .03);
            down = new Texture("assets/racing/player2_down.png");
            down.scale(.03, .03);
            left = new Texture("assets/racing/player2_left.png");
            left.scale(.03, .03);
            right = new Texture("assets/racing/player2_right.png");
            right.scale(.03, .03);
        }
        texture = right;
        curTexture = right;
        nextTexture = right;
    }
}
