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
    private JPanel panel;
    private static final double DISTANCE = 50.0;
    private Point mousePos = new Point(250, 250);
    
    private SegmentArray s;
    
    protected void redraw(Graphics g)
    {
        g.drawOval(mousePos.x - (int)DISTANCE, mousePos.y - (int)DISTANCE, (int)DISTANCE * 2, (int)DISTANCE * 2);
        s.paint(g);
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
        s = new SegmentArray(DISTANCE, panel);
        s.start();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mousePos = e.getPoint();
        s.setMousePos(mousePos);
        panel.repaint();
    }
    
    public static void main(String args[]) {
        javax.swing.SwingUtilities.invokeLater(new RopePhysics());
    }
}
