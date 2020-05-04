package gameutils;

import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * An abstract Screen class to
 * streamline making game screens.
 *
 * @author Justin, Andrew, Emma, Tim, Nick
 * @version Spring 2020
 */
public abstract class Screen extends JPanel {
    protected Controller controller;
    public SoundManager soundManager;

    /**
     * A generic constructor for a Screen
     */
    public Screen() {
        super();
        controller = null;
    }

    /**
     * A constructor for a Screen with a SoundManager
     */
    public Screen(SoundManager soundManager) {
        super();
        this.soundManager = soundManager;
        controller = null;
    }

    /**
     * JPanel's paintComponent, outsourcing
     * work to the render method
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }

    /**
     * Renders what is on the game screen.
     * To be called in the thread of the game class
     * @param g the Graphics from paintComponent
     */
    public abstract void render(Graphics g);

    /**
     * Code that changes what is on the screen goes here.
     * To be called in the thread of the game class
     */
    public abstract void update();

    /**
     * All objects that need to be disposed go here.
     *
     * Example:
     * Let obj be a pointer to a GameObject
     * obj = null;
     *
     * Typically, objects no longer needed should
     * be placed in an ArrayList and be disposed as follows:
     * for (GameObjects obj: removedObjs) {
     *     obj = null;
     * }
     */
    public abstract void dispose();
}
