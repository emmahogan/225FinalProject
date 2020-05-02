package racing;

import java.awt.Point;
import java.awt.Rectangle;

import gameutils.GameObject;
import gameutils.Texture;
import gameutils.FloatPoint;

public class Car extends GameObject {
    public Rectangle top;
    public Rectangle bottom;
    public Rectangle leftSide;
    public Rectangle rightSide;

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

    private void setBoundsAndLines() {
        bounds = new Rectangle((int) position.x, (int) position.y, texture.getWidth(), texture.getHeight());
        top = new Rectangle(bounds.x, bounds.y, bounds.width, 1);
        bottom = new Rectangle(bounds.x, bounds.y + bounds.height - 1, bounds.width, 1);
        leftSide = new Rectangle(bounds.x, bounds.y, 1, bounds.height);
        rightSide = new Rectangle(bounds.x + bounds.width - 1, bounds.y, 1, bounds.height);
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
