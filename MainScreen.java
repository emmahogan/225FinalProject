import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * Write a description of class MainScreen here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MainScreen extends MouseAdapter implements Runnable
{
    private JFrame frame = new JFrame();
    private final int FRAME_WIDTH = 400;
    private final int FRAME_HEIGHT = 400;
    public void run(){
        frame.setDefaultLookAndFeelDecorated(true);
        frame.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
    }
    
}
