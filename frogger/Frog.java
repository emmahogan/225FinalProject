package frogger;

import gameutils.GameObject;
import gameutils.Texture;

import java.awt.*;
import java.util.ArrayList;

import static frogger.FroggerGame.SCALE;

public class Frog extends GameObject {
    private final int FRAME_BUFFER = 2;
    private int collisionFrameCount;

    private Texture upFroggahTexture;
    private Texture downFroggahTexture;
    private Texture leftFroggahTexture;
    private Texture rightFroggahTexture;

    private EnvironmentManager environmentManager;
    private double x;

    public Frog(EnvironmentManager environmentManager) {
        super();
        initTextures();

        this.environmentManager = environmentManager;

        position.y = FroggerGame.FRAME_HEIGHT - (4 * SCALE);
        this.x = 10 * SCALE;
        position.x = (int) x;

        setBounds();
    }

    /**
     * Updates the frog and checks for things like if it's alive.
     */
    @Override
    public void update() {
        checkCollision();
        position.x = (int) x;
        setBounds();
    }


    /**
     * Moves the frog forward and changes its texture accordingly.
     */
    public void jumpForward() {
        environmentManager.environmentDown();
        texture = upFroggahTexture;
    }

    /**
     * Moves the frog backward and changes its texture accordingly.
     */
    public void jumpBack() {
        environmentManager.environmentUp();
        texture = downFroggahTexture;
    }

    /**
     * Moves the frog left and changes its texture accordingly.
     */
    public void jumpLeft() {
        if (x != 0) {
            x -= SCALE;
        }
        texture = leftFroggahTexture;
        update();
    }

    /**
     * Moves the frog right and changes its texture accordingly.
     */
    public void jumpRight() {
        if (x != FroggerGame.FRAME_WIDTH - SCALE) {
            x += SCALE;
        }
        texture = rightFroggahTexture;
    }

    private void die() {
        if (collisionFrameCount < FRAME_BUFFER) {
            collisionFrameCount++;
        } else {
            System.out.println("dead");
        }
    }

    private void checkCollision() {
        int posInLevel = environmentManager.posInLevel;
        Environment e = environmentManager.environments.get(posInLevel);
        Hazard currentHazard = null;
        boolean done = false;

        if (e instanceof River) {
            for (int i = 0; i < e.getHazards().size() && !done; i++) {
                Hazard log = e.getHazards().get(i);
                if (this.collidesWith(log) && this.collidesWith(e)) {
                    done = true;
                    currentHazard = log;
                }
            }
            if (done && currentHazard != null) {
                x += currentHazard.speed;
                collisionFrameCount = 0;
            } else {
                die();
            }
        } else if (e instanceof Road) {
            for (Hazard car : e.getHazards()) {
                if (this.collidesWith(car)) {
                    die();
                }
            }
        }
    }

    private void initTextures() {
        upFroggahTexture = new Texture("assets/frogger/froggah_up.png");
        downFroggahTexture = new Texture("assets/frogger/froggah_down.png");
        leftFroggahTexture = new Texture("assets/frogger/froggah_left.png");
        rightFroggahTexture = new Texture("assets/frogger/froggah_right.png");
        texture = upFroggahTexture;
    }
}
