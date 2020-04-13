package chess;
import gameutils.Game;
import gameutils.Controller;
import gameutils.Screen;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * Write a description of class Chess here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Chess extends Game
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class Chess
     */
    public Chess(String name)
    {
        super(name);
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public int sampleMethod(int y)
    {
        // put your code here
        return x + y;
    }
}
