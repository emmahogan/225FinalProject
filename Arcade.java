import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.*;

import examplegame.ExampleGame;
import marstrip.MarsTrip;
import racing.SpeedRacers;
import chess.Chess;
import runner.RunnerGame;
import frogger.FroggerGame;

/**
 * This class represents the simpler version of a main screen that is run, with a button for each
 * game that starts it in another window
 *
 * @author Justin, Andrew, Emma, Tim, Nick
 * @version Spring 2020
 */
public class Arcade implements Runnable, ActionListener {

    //dimensions of frame
    private final int FRAME_WIDTH = 600;
    private final int FRAME_HEIGHT = 600;

    //Frame with title
    private JFrame frame = new JFrame("Arcade");

    //Main panel and three row panels added to it
    private JPanel mainPanel;
    private JPanel row1;
    private JPanel row2;
    private JPanel row3;

    //Array List of JButtons, one for each game
    private ArrayList<JButton> games = new ArrayList<>();

    //Arcade label
    private JLabel label;

    //All of the image icons for the JButtons, with an image of the game that it goes to
    private static ImageIcon smileyIcon = new ImageIcon("assets/arcade/smileyIcon.png");
    private static ImageIcon froggerIcon = new ImageIcon("assets/arcade/froggerIcon.png");
    private static ImageIcon marsIcon = new ImageIcon("assets/arcade/marsIcon.png");
    private static ImageIcon racingIcon = new ImageIcon("assets/arcade/racingIcon.png");
    private static ImageIcon riseIcon = new ImageIcon("assets/arcade/riseIcon.png");
    private static ImageIcon chessIcon = new ImageIcon("assets/arcade/chessIcon.png");

    //Gray background color
    Color background = new Color(150, 150, 150);



    /**
     * The run method brings up the screen with all of its components and listeners
     */
    public void run(){
        //Frame utilities
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add all JButtons with image icons to array list
        games.add(new JButton(smileyIcon));
        games.add(new JButton(racingIcon));
        games.add(new JButton(chessIcon));
        games.add(new JButton(riseIcon));
        games.add(new JButton(froggerIcon));
        games.add(new JButton(marsIcon));

        //initialize panels
        mainPanel = new JPanel(new GridLayout(3, 1));
        row1 = new JPanel(new GridLayout(1, 3));
        row2 = new JPanel(new GridLayout(3, 1));
        row3 = new JPanel(new GridLayout(1, 3));

        //Add the 3 row panels to main
        mainPanel.add(row1);
        mainPanel.add(row2);
        mainPanel.add(row3);

        //Add the first 3 buttons with action listeners in the top row
        for (int i = 0; i < 3; i++) {
            games.get(i).addActionListener(this);
            row1.add(games.get(i));
        }

        //Add the second 3 buttons and their action listeners in the bottom row
        for (int i = 3; i < 6; i++) {
            games.get(i).addActionListener(this);
            row3.add(games.get(i));
        }

        //Create new font for label and initialize label
        Font labelFont = new Font("Helvetica", Font.BOLD, 65);
        label = new JLabel("         ARCADE");
        label.setFont(labelFont);
        label.setForeground(new Color(255, 255, 255));
        //add to second row
        row2.add(new JLabel(""));
        row2.add(label);

        //Set background color of all panels
        mainPanel.setBackground(background);
        row1.setBackground(background);
        row2.setBackground(background);
        row3.setBackground(background);

        //Add main panel to frame and set visible
        frame.add(mainPanel);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * The action performed method for this class sees which button in the array list was pushed,
     * prints a message to the terminal saying which game it is starting, and opens that game
     * in a new window
     *
     * @param e The action event
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(games.get(0))) {
            System.out.println("Starting Example Game");
            javax.swing.SwingUtilities.invokeLater(new ExampleGame("Example Game"));
        }
        else if (e.getSource().equals(games.get(1))) {
            System.out.println("Starting Speed Racers");
            javax.swing.SwingUtilities.invokeLater(new SpeedRacers("Speed Racers"));
        }
        else if (e.getSource().equals(games.get(2))){
            System.out.println("Starting Chess");
            javax.swing.SwingUtilities.invokeLater(new Chess("Chess"));
        }
        else if (e.getSource().equals(games.get(3))){
            System.out.println("Starting Rise (not chess)");
            javax.swing.SwingUtilities.invokeLater(new RunnerGame("Rise"));
        }
        else if (e.getSource().equals(games.get(4))){
            System.out.println("Starting Frogger");
            javax.swing.SwingUtilities.invokeLater(new FroggerGame("Frogger"));
        }
        else if (e.getSource().equals(games.get(5))){
            System.out.println("Starting Mars Trip");
            javax.swing.SwingUtilities.invokeLater(new MarsTrip("Mars Trip"));
        }
    }

    /**
     * Main method that opens this window
     *
     * @param args none
     */
    public static void main(String args[]){
        javax.swing.SwingUtilities.invokeLater(new Arcade());
    }
}
