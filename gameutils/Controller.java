package gameutils;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.ArrayList;

/**
 * An abstract Controller class to
 * streamline making new player controllers.
 *
 * @author Justin, Andrew, Emma, Tim, Nick
 * @version Spring 2020
 */
public abstract class Controller extends MouseAdapter implements KeyListener {
    protected static final int W = KeyEvent.VK_W;
    protected static final int A = KeyEvent.VK_A;
    protected static final int S = KeyEvent.VK_S;
    protected static final int D = KeyEvent.VK_D;
    protected static final int UP = KeyEvent.VK_UP;
    protected static final int DOWN = KeyEvent.VK_DOWN;
    protected static final int LEFT = KeyEvent.VK_LEFT;
    protected static final int RIGHT = KeyEvent.VK_RIGHT;

    private boolean[] keys;

    /**
     * Generic constructor of a Controller
     */
    public Controller() {
        super();
        keys = new boolean[256];
    }

    /**
     * KeyListener's keyPressed. Adds an event to
     * the ArrayList of events.
     * @param e the KeyEvent
     */
    @Override
    public void keyPressed(KeyEvent e) {
        keys[(e.getKeyCode())] = true;
    }

    /**
             * KeyListener's keyReleased. Removes an event to
             * the ArrayList of events.
             * @param e the KeyEvent
             */
    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

    /**
             * KeyListener's keyTyped
             * @param e the KeyEvent
             */
    @Override
    public void keyTyped(KeyEvent e) {}

    /**
     * MouseAdapter's mousePressed to be implemented
     * @param e the MouseEvent
     */
    @Override
    public void mousePressed(MouseEvent e) {}

    /**
     * MouseAdapter's mouseReleased to be implemented
     * @param e the MouseEvent
     */
    @Override
    public void mouseReleased(MouseEvent e) {}

    /**
     * MouseAdapter's mouseDragged to be implemented
     * @param e the MouseEvent
     */
    @Override
    public void mouseDragged(MouseEvent e) {}

    /**
     * MouseAdapter's mouseMoved to be implemented
     * @param e the MouseEvent
     */
    @Override
    public void mouseMoved(MouseEvent e) {}

    /**
     * Handles all key input.
     * Uses the isKeyPressed method
     */
    public void handleKeyInput() {}

    /**
     * Checks whether a key is in the pressed ArrayList
     * @param key the key to check if pressed
     * @return true if the key is currently pressed
     */
    public boolean isKeyPressed(int key) {
        return keys[key];
    }
}

