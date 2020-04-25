package racing;

import java.awt.Point;

import gameutils.GameObject;
import gameutils.Texture;

    public class Car extends GameObject {
    private Texture up;
    private Texture down;
    private Texture left;
    private Texture right;
    private Texture curTexture;
    private Texture nextTexture;

    FloatPoint velocity;
    FloatPoint position;

    public Car(String color) {
        super();
        initTextures(color);
        setBounds();

        Point center = new Point((SpeedRacers.FRAME_WIDTH / 2) - (texture.getWidth() / 2),
                (SpeedRacers.FRAME_HEIGHT / 2) - (texture.getHeight() / 2));

        setPosition(center);
        velocity = new FloatPoint(0, 0);
    }

    @Override
    public void update() {
        if (bounds.getLocation() != position.toPoint()) {
            bounds.setLocation(position.toPoint());
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

    private void moveByVelocity() {
        position.x += velocity.x;
        position.y += velocity.y;
    }

    private void checkRotation() {
        if (velocity.x > 2) {
            nextTexture = right;
            setBounds();
        }
        if (velocity.x < -2) {
            nextTexture = left;
            setBounds();
        }
        if (velocity.y < -2) {
            nextTexture = up;
            setBounds();
        }
        if (velocity.y > 2) {
            nextTexture = down;
            setBounds();
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
        }
        else {
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
