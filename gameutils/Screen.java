package gameutils;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * Write a description of class TitleScreen here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class Screen extends JPanel {
    public Graphics g;
    
    /**
     * Constructor for objects of class TitleScreen
     */
    public Screen(){
        super();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(this.g);
        render(this.g);
    }
    
    public abstract void render(Graphics g);
    
    public abstract void update();
}
