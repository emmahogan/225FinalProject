import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
/**
 * Special array for the RopeSegments that updates the 'previous' variable
 * for each RopeSegment in a thread
 *
 * @author Tim Ryan, Emma Hogan, Justin Marotta, Andrew Towse, and Nick Shelby
 * @version 5/3/2020
 */
public class SegmentArray extends Thread
{
    protected RopeSegment[] segments = new RopeSegment[4];
    private double distance;
    private JComponent container;
    private static final int DELAY_TIME = 5;
    protected Point mousePos;
    
    public SegmentArray(double distance, JComponent container)
    {
        this.distance = distance;
        this.container = container;
        mousePos = new Point(250, 250);
        
        RopeSegment r0 = new RopeSegment(new Point(250, 250), distance, container);
        segments[0] = r0;
        r0.start();
        
        RopeSegment r1 = new RopeSegment(new Point(250, 250), distance, container);
        segments[1] = r1;
        r1.start();
        
        RopeSegment r2 = new RopeSegment(new Point(250, 250), distance, container);
        segments[2] = r2;
        r2.start();
        
        RopeSegment r3 = new RopeSegment(new Point(250, 250), distance, container);
        segments[3] = r3;
        r3.start();
    }
    
    public void paint(Graphics g)
    {
        RopeSegment r0 = segments[0];
        r0.paint(g);
        g.drawLine(mousePos.x, mousePos.y, (int)r0.getX(), (int) r0.getY());
        for(int i = 1; i < segments.length; i++)
        {
            RopeSegment s = segments[i];
            s.paint(g);
            g.drawLine((int)segments[i-1].getX(), (int)segments[i-1].getY(), (int)s.getX(), (int) s.getY());
        }
    }
    
    @Override
    public void run() {
        while (true) {
            segments[0].setPrevious(mousePos);
            segments[1].setPrevious(new Point((int)segments[0].getX(), (int)segments[0].getY()));
            segments[2].setPrevious(new Point((int)segments[1].getX(), (int)segments[1].getY()));
            segments[3].setPrevious(new Point((int)segments[2].getX(), (int)segments[2].getY()));
            //FABRIK
            //segments[2].setNext(new Point((int)segments[3].getX(), (int)segments[3].getY()));
            //segments[1].setNext(new Point((int)segments[2].getX(), (int)segments[2].getY()));
            //segments[0].setNext(new Point((int)segments[1].getX(), (int)segments[1].getY()));
            container.repaint();
            try {
                sleep(DELAY_TIME);
            }
            catch (InterruptedException e) {
            }
        }
    }
    
    public RopeSegment get(int num)
    {
        return segments[num];
    }
    
    public int length()
    {
        return segments.length;
    }
    
    public void setMousePos(Point p)
    {
        mousePos = p;
    }
}
