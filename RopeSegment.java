import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
/**
 * Class that represents each segment of rope
 *
 * @author Tim Ryan, Emma Hogan, Justin Marotta, Andrew Towse, and Nick Shelby
 * @version 4/28/2020
 */
public class RopeSegment extends Thread
{
    protected Point pos;
    private double distance;
    private JComponent container;
    private static final int DELAY_TIME = 30;
    private static final int SIZE = 20;
    private Point previous;
    
    //velocity stuff
    private double xVelocity;
    private double xPrevious;
    private static final double GRAVITY = 5;
    private double yVelocity;
    private double yPrevious;
    private int numOfSegment;

    public RopeSegment(Point start, double dis, int num, JComponent container)
    {
        this.pos = start;
        this.distance = dis;
        this.container = container;
        this.xVelocity = 0;
        yVelocity = GRAVITY;
        xPrevious = start.getX();
        yPrevious = start.getY();
        numOfSegment = num;
    }

    public void paint(Graphics g)
    {
        g.setColor(new Color(0, 200, 100));
        g.fillOval(pos.x - SIZE/2, pos.y - SIZE/2, SIZE, SIZE);
        g.setColor(new Color(0, 0, 0));
        g.drawOval(pos.x - SIZE/2, pos.y - SIZE/2, SIZE, SIZE);
    }

    @Override
    public void run() {
        while (true) {
            this.pos.setLocation(this.getX() + xVelocity, this.getY() + yVelocity);
            xPrevious = this.getX();
            yPrevious = this.getY();
            if(previous != null)
            {
                setDistance(previous);
            }
            try {
                sleep(DELAY_TIME);
            }
            catch (InterruptedException e) {
            }
            
            xVelocity = xVelocity + (this.getX() - xPrevious)/numOfSegment;
            yVelocity = yVelocity + (this.getY() - yPrevious)/numOfSegment + GRAVITY;
            if(yPrevious == this.getY())
            {
                yVelocity = GRAVITY;
            }
            container.repaint();
        }
    }

    /**
     * calculates distance between two segments
     */
    public double findDistance(Point p)
    {
        return Math.sqrt((p.getX() - this.getX())*(p.getX() - this.getX()) + (p.getY() - this.getY())*(p.getY() - this.getY()));
    }

    /**
     * sets distance between two segments
     */
    public void setDistance(Point p)
    {
        if(findDistance(p) != distance)
        {
            double slope = 0;
            double angle;
            double x;
            double y;
            if(p.getX() - this.getX() == 0)
            {
                x = 0;
                if(this.getY() > previous.getY())
                {
                    y = distance;
                }
                else
                {
                    y = -distance;
                }
            }
            else
            {
                slope = (p.getY() - this.getY())/(p.getX() - this.getX());
                angle = Math.atan(slope);

                x = distance * Math.cos(angle);
                y = distance * Math.sin(angle);
                if(p.getX() - this.getX() > 0)
                {
                    x = x * (-1);
                    y = y * (-1);
                }
            }
            this.pos.setLocation(p.getX() + x, p.getY() + y);
        }
    }

    /**
     * returns the x position of the rope segment
     */
    public double getX()
    {
        return pos.getX();
    }

    /**
     * returns the y position of the rope segment
     */
    public double getY()
    {
        return pos.getY();
    }

    /**
     * allows previous to be set
     */
    public void setPrevious(Point p)
    {
        previous = p;
    }
}
