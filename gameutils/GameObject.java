package gameutils;

import java.awt.Rectangle;
import java.awt.Image;
import java.awt.Point;

/**
 * Write a description of class GameObject here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class GameObject extends Thread{
    private Texture texture;
    public Point position;
    private Rectangle bounds;
    
    /**
     * Constructor for objects of class GameObject
     */
    public GameObject(String filePath) {
        this.texture = new Texture(filePath);
        position = new Point(0, 0);
        bounds = new Rectangle(texture.getWidth(), texture.getHeight());
    }
    
    public abstract void update();
    
    /**
     * An example of a method - replace this comment with your own
     *
     * @return    the sum of x and y
     */
    public Image getTexure() {
        return texture.getImage();
    }
    
    /**
     * An example of a method - replace this comment with your own
     *
     * @return    the sum of x and y
     */
    public Point getPosition() {
        return position;
    }
}
