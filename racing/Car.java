package racing;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.sound.sampled.Line;

import gameutils.GameObject;
import gameutils.Texture;

public class Car extends GameObject {
    public Rectangle top;
    private Rectangle bottom;
    private Rectangle leftSide;
    private Rectangle rightSide;

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
        setPosition(new Point(x, y));
        velocity = new FloatPoint(0, 0);

        setBoundsAndLines();
    }

    @Override
    public void update() {
        if (bounds.getLocation() != position.toPoint()) {
            setBoundsAndLines();
        }
        moveByVelocity();
        addFriction();
        checkRotation();
    }

    public Point getPosition() {
        return new Point((int) position.x, (int) position.y);
    }

    public void setPosition(Point newPosition) {
        this.position = new FloatPoint(newPosition.x, newPosition.y);
    }

    private void setBoundsAndLines() {
        bounds = new Rectangle((int) position.x, (int) position.y, texture.getWidth(), texture.getHeight());
        top = new Rectangle(bounds.x + 7, bounds.y, bounds.width - 7, 1);
        bottom = new Rectangle(bounds.x + 7, bounds.y + bounds.height - 1, bounds.width - 7, 1);
        leftSide = new Rectangle(bounds.x, bounds.y + 7, 1, bounds.height - 7);
        rightSide = new Rectangle(bounds.x + bounds.width - 1, bounds.y + 7, 1, bounds.height - 7);
    }

    public void collision(ArrayList<Track> tracks, Car other) {
        boolean done = false;
        for (int i = 0; i < tracks.size() && !done; i++) {
            if (top.intersects(tracks.get(i).bounds) ||
                    top.intersects(other.bounds)) {
                done = true;
                velocity.y = 0;
                velocity.y += 1;
            }
            if (bottom.intersects(tracks.get(i).bounds) ||
                    bottom.intersects(other.bounds)) {
                done = true;
                velocity.y = 0;
                velocity.y -= 2;
            }
            if (leftSide.intersects(tracks.get(i).bounds) ||
                    leftSide.intersects(other.bounds)) {
                done = true;
                velocity.x = 0;
                velocity.x += 2;
            }
            if (rightSide.intersects(tracks.get(i).bounds) ||
                    rightSide.intersects(other.bounds)) {
                done = true;
                velocity.x = 0;
                velocity.x -= 2;
            }
        }
    }

    private void moveByVelocity() {
        position.x += velocity.x;
        position.y += velocity.y;
    }

    private void checkRotation() {
        if (velocity.x > 2) {
            nextTexture = right;
            setBoundsAndLines();
        }
        if (velocity.x < -2) {
            nextTexture = left;
            setBoundsAndLines();
        }
        if (velocity.y < -2) {
            nextTexture = up;
            setBoundsAndLines();
        }
        if (velocity.y > 2) {
            nextTexture = down;
            setBoundsAndLines();
        }
        if (curTexture != nextTexture) {
            curTexture = nextTexture;
            texture = curTexture;
        }
    }

    private void addFriction() {
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

    private void initTextures(String color) {
        if (color.equals("blue")) {
            up = new Texture("assets/racing/player1_up.png");
            up.scale(.05, .05);
            down = new Texture("assets/racing/player1_down.png");
            down.scale(.05, .05);
            left = new Texture("assets/racing/player1_left.png");
            left.scale(.05, .05);
            right = new Texture("assets/racing/player1_right.png");
            right.scale(.05, .05);
        } else {
            up = new Texture("assets/racing/player2_up.png");
            up.scale(.05, .05);
            down = new Texture("assets/racing/player2_down.png");
            down.scale(.05, .05);
            left = new Texture("assets/racing/player2_left.png");
            left.scale(.05, .05);
            right = new Texture("assets/racing/player2_right.png");
            right.scale(.05, .05);
        }
        texture = right;
        curTexture = right;
        nextTexture = right;
    }
}
