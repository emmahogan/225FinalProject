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
public abstract class GameObject extends Thread {
    private Texture texture;
    private Point position;
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
     * 
     */
    public Image getTexure() {
        return texture.getImage();
    }
    
    /**
     * 
     */
    public Rectangle getBounds() {
        return bounds;
    }
    
    /**
     * 
     */
    public Rectangle setBounds(float height, float width) {
        texture.scale(height, width);
        bounds = new Rectangle(texture.getWidth(), texture.getHeight());
        return bounds;
    }
    
    /**
     * 
     */
    public Point getPosition() {
        return position;
    }
    
    /**
     * 
     */
    public void setTexture(String filePath) {
        this.texture = new Texture(filePath);
    }
    
    /**
     * 
     */
    public void setPosition(Point newPosition) {
        this.position = newPosition;
        bounds.setLocation(newPosition);
    }
    
    /**
     * 
     */
    public boolean collidesWith(GameObject obj) {
        return bounds.intersects(obj.getBounds());
    }
}
