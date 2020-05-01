import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
/**
 * Simulates rope physics in Java
 *
 * @author Tim Ryan, Emma Hogan, Justin Marotta, Andrew Towse, and Nick Shelby
 * @version 4/28/2020
 */
public class RopePhysics extends MouseAdapter implements Runnable
{
    private RopeSegment[] segments = new RopeSegment[4];
    private JPanel panel;
    private static final double DISTANCE = 50.0;
    private Point mousePos = new Point(250, 250);
    protected void redraw(Graphics g)
    {
        g.drawOval(mousePos.x - (int)DISTANCE/2, mousePos.y - (int)DISTANCE/2, (int)DISTANCE, (int)DISTANCE);
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

    /**
    The run method to set up the graphical user interface
     */
    @Override
    public void run() {

        // set up the GUI "look and feel" which should match
        // the OS on which we are running
        JFrame.setDefaultLookAndFeelDecorated(true);

        // create a JFrame in which we will build our very
        // tiny GUI, and give the window a name
        JFrame frame = new JFrame("RopePhysics");
        frame.setPreferredSize(new Dimension(500,500));

        // tell the JFrame that when someone closes the
        // window, the application should terminate
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // JPanel with a paintComponent method
        panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {

                // first, we should call the paintComponent method we are
                // overriding in JPanel
                super.paintComponent(g);

                // redraw our graphics items
                redraw(g);
            }
        };
        frame.add(panel);
        panel.addMouseListener(this);
        panel.addMouseMotionListener(this);

        // display the window we've created
        frame.pack();
        frame.setVisible(true);
        
        RopeSegment r0 = new RopeSegment(new Point(250, 250), DISTANCE, panel);
        segments[0] = r0;
        r0.start();
        
        RopeSegment r1 = new RopeSegment(new Point(250, 250), DISTANCE, panel);
        segments[1] = r1;
        r1.start();
        
        RopeSegment r2 = new RopeSegment(new Point(250, 250), DISTANCE, panel);
        segments[2] = r2;
        r2.start();
        
        RopeSegment r3 = new RopeSegment(new Point(250, 250), DISTANCE, panel);
        segments[3] = r3;
        r3.start();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mousePos = e.getPoint();
        segments[0].setPrevious(mousePos);
        segments[1].setPrevious(new Point((int)segments[0].getX(), (int)segments[0].getY()));
        segments[2].setPrevious(new Point((int)segments[1].getX(), (int)segments[1].getY()));
        segments[3].setPrevious(new Point((int)segments[2].getX(), (int)segments[2].getY()));
        segments[2].setNext(new Point((int)segments[3].getX(), (int)segments[3].getY()));
        segments[1].setNext(new Point((int)segments[2].getX(), (int)segments[2].getY()));
        segments[0].setNext(new Point((int)segments[1].getX(), (int)segments[1].getY()));
        panel.repaint();
    }
    
    public static void main(String args[]) {
        javax.swing.SwingUtilities.invokeLater(new RopePhysics());
    }
}
