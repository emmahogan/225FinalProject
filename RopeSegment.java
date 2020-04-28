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
    private static final int DELAY_TIME = 33;
    private static final int SIZE = 20;
    private Point previous;
    private Object lock = new Object();

    public RopeSegment(Point start, double dis, Point previous, JComponent container)
    {
        this.pos = start;
        this.distance = dis;
        this.previous = previous;
        this.container = container;
    }

    public void paint(Graphics g)
    {
        g.setColor(new Color(0, 200, 100));
        g.fillOval(pos.x, pos.y, SIZE, SIZE);
        g.setColor(new Color(0, 0, 0));
        g.drawOval(pos.x, pos.y, SIZE, SIZE);
    }

    @Override
    public void run() {
        while (true) {
            try {
                sleep(DELAY_TIME);
            }
            catch (InterruptedException e) {
            }
            if(findDistance(previous) > distance)
            {
                synchronized(lock)
                {
                    setDistance(previous);
                    System.out.println("done");
                    container.repaint();
                }
            }
        }
    }

    public Boolean isDone()
    {
        return false;
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
        if(findDistance(p) > distance)
        {
            double changeY;
            if(p.getX() - this.getX() == 0)
            {
                changeY = 1.0;
            }
            else
            {
                changeY = Math.abs((p.getY() - this.getY())/(p.getX() - this.getX()));
            }
            if(p.getY() - this.getY() < 0)
            {
                changeY = changeY * -1;
            }
            int changeX = 1;
            if(p.getX() - this.getX() < 0)
            {
                changeX = changeX * -1;
            }
            while(findDistance(p) > distance)
            {
                this.pos.setLocation(this.getY() + changeY, this.getX() + changeX);
                System.out.println(pos);
            }
        }
        System.out.println("called");
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
