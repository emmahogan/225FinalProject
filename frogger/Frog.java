package frogger;

import gameutils.GameObject;
import gameutils.Texture;

import static frogger.FroggerGame.SCALE;
import static gameutils.Game.changeScreen;

/**
 * This class creates the player frog and controls the frogs movements, death, and textures.
 *
 * @author Nick Shelby
 * @Version Spring 2020
 */
public class Frog extends GameObject {
    private final int FRAME_BUFFER = 2;
    private int collisionFrameCount;

    private Texture upFroggahTexture;
    private Texture downFroggahTexture;
    private Texture leftFroggahTexture;
    private Texture rightFroggahTexture;

    private EnvironmentManager environmentManager;
    private double x;

    /**
     * This creates the frog and places it in the environment that is created.
     *
     * @param environmentManager The object that creates the environment.
     */
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

    /**
     * This is called when the frog is killed. The Frame Buffer is used to ensure that the frog isn't killed
     * during the frame it jumps onto a log.
     */
    private void die() {
        if (collisionFrameCount < FRAME_BUFFER) {
            collisionFrameCount++;
        } else {
            environmentManager.remakeLevel();
            collisionFrameCount = 0;
        }
    }

    /**
     * This checks the collision of the frog and will react accordingly to the collision made.
     * It kills the frog if it is hit by a car and will ride a log if it is on a log.
     */
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

    /**
     * Initializes the textures that are used for the frog.
     */
    private void initTextures() {
        upFroggahTexture = new Texture("assets/frogger/froggah_up.png");
        downFroggahTexture = new Texture("assets/frogger/froggah_down.png");
        leftFroggahTexture = new Texture("assets/frogger/froggah_left.png");
        rightFroggahTexture = new Texture("assets/frogger/froggah_right.png");
        texture = upFroggahTexture;
    }
}
