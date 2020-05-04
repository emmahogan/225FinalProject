package gameutils;

import java.awt.Rectangle;
import java.awt.Image;
import java.awt.Point;

/**
 * An abstract GameObject class to
 * streamline making game objects.
 *
 * @author Justin, Andrew, Emma, Tim, Nic
 * @version Spring 2020
 */
public abstract class GameObject extends Thread {
    public Texture texture;
    public Point position;
    public Rectangle bounds;
//    public double rotation;
    
    /**
     * Generic constructor for a GameObject
     */
    public GameObject() {
        position = new Point(0, 0);
    }
    
    public abstract void update();
    
    /**
     * Gets the image of the GameObject's texture
     * @return the image of the texture
     */
    public Image getTexture() {
        return texture.getImage();
    }

    /**
     * Returns the position of the GameObject
     * @return the position of the GameObject
     */
    public Point getPosition() {
        return this.position;
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
     * to the height and width of the texture with the objects
     * position
     */
    public void setBounds() {
        bounds = new Rectangle(this.position.x, this.position.y, texture.getWidth(), texture.getHeight());
    }

    /**
     * Sets the bounding rectangle of the GameObject.
     * The parameter Rectangle must have an x, y position
     * @param rect the rectangle of the new bounds
     */
    public void setBounds(Rectangle rect) {
        bounds = new Rectangle(rect);
    }
    
    /**
     * Sets the position of the GameObject to a new Point
     * @param newPosition the point of the new position
     */
    public void setPosition(Point newPosition) {
        this.position.x = newPosition.x;
        this.position.y = newPosition.y;
    }

    /**
     * Sets the position of the GameObject to a new Point
     * @param x the x value of the new position
     * @param y the y value of the new position
     */
    public void setPosition(int x, int y) {
        this.position.x = x;
        this.position.y = y;
    }

//    public void rotate(double angle) {
//        texture.rotate(angle);
//        setBounds();
//    }
    
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