package racing;

import java.awt.Point;
import java.awt.Rectangle;

import gameutils.GameObject;
import racing.SpeedRacers;
import racing.Car;

public class Wall extends GameObject {
    public static final int SIZE = 50;

    public Wall(int x, int y) {
        x = (x * SIZE);
        y = SpeedRacers.FRAME_HEIGHT - (y * SIZE) - SIZE;
        position = new Point(x, y);
        setBounds(new Rectangle(position.x, position.y, 50, 50));
    }

    @Override
    public void update() {
        if (bounds.getLocation() != position) {
            bounds.setLocation(position);
        }
    }

    public void collision(Car p1, Car p2) {
        Car currentCar = null;
        if (this.collidesWith(p1)) {
            currentCar = p1;
        } else if (this.collidesWith(p2)) {
            currentCar = p2;
        }

        if (currentCar != null) {
            if (bounds.intersects(currentCar.rightSide)) {
                currentCar.velocity.x = 0;
                currentCar.velocity.x -= 2;
            }
            if (bounds.intersects(currentCar.bottom)) {
                currentCar.velocity.y = 0;
                currentCar.velocity.y -= 2;
            }
            if (bounds.intersects(currentCar.top)) {
                currentCar.velocity.y = 0;
                currentCar.velocity.y += 2;
            }
            if (bounds.intersects(currentCar.leftSide)) {
                currentCar.velocity.x = 0;
                currentCar.velocity.x += 2;
            }
        }
    }
}

