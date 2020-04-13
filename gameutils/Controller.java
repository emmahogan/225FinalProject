package gameutils;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.ArrayList;

/**
 * Write a description of class Controller here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class Controller extends MouseAdapter implements KeyListener {
    public static final int W = KeyEvent.VK_W;
    public static final int A = KeyEvent.VK_A;
    public static final int S = KeyEvent.VK_S;
    public static final int D = KeyEvent.VK_D;
    public static final int UP = KeyEvent.VK_UP;
    public static final int DOWN = KeyEvent.VK_DOWN;
    public static final int LEFT = KeyEvent.VK_LEFT;
    public static final int RIGHT = KeyEvent.VK_RIGHT;

    private ArrayList<Integer> keys;

    /**
     * Constructor for objects of class Controller
     */
    public Controller() {
        super();
        keys = new ArrayList<Integer>();
    }

    public abstract void handleKeyInput();

    @Override
    public void keyPressed(KeyEvent e) {
        keys.add(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys.remove(e.getKeyCode());
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {}

    public boolean isKeyPressed(int key) {
        return keys.contains(key);
    }
}

