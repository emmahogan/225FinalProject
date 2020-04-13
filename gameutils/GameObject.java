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
    public Texture texture;
    public Point position;
    private Rectangle bounds;
    
    /**
     * Constructor for objects of class GameObject
     */
    public GameObject() {
        position = new Point(0, 0);
    }
    
    public abstract void update();
    
    /**
     * Gets the image of the GameObject's texture
     * @return the image of the texture
     */
    public Image getTexure() {
        return texture.getImage();
    }
    
    /**
     * Gets the bounding rectangle of the GameObject
     * @return the rectangle of the the bounds
     */
    public Rectangle getBounds() {
        return bounds;
    }
    
    /**
     * Sets the bounding rectangle of the GameObject
     * to the height and width of the texture
     */
    public void setBounds() {
        bounds = new Rectangle(texture.getWidth(), texture.getHeight());
    }

    /**
     * Sets the bounding rectangle of the GameObject
     * @param rect the rectangle of the new bounds
     */
    public void setBounds(Rectangle rect) {
        bounds = rect;
    }
    
    /**
     * Returns the position of the GameObject
     * @return the position of the GameObject
     */
    public Point getPosition() {
        return position;
    }
    
    /**
     * Sets the texture of the GameObject to a new texture
     * @param filePath the path of the image file
     */
    public void setTexture(String filePath) {
        this.texture = new Texture(filePath);
    }
    
    /**
     * Sets the position of the GameObject to a new Point
     * @param newPosition the point of the new position
     */
    public void setPosition(Point newPosition) {
        this.position = newPosition;
        bounds.setLocation(newPosition);
    }

    /**
     * Sets the position of the GameObject to a new Point
     * @param x the x value of the new position
     * @param y the y value of the new position
     */
    public void setPosition(int x, int y) {
        Point newPosition = new Point(x, y);
        this.position = newPosition;
        bounds.setLocation(newPosition);
    }
    
    /**
     * Returns whether the GameObject is colliding
     * with another GameObject.
     * @param obj the object to detect collision
     * @return true if the objects are colliding
     */
    public boolean collidesWith(GameObject obj) {
        return bounds.intersects(obj.getBounds());
    }
}
