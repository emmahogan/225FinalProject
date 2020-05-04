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
 * Write a description of class MainScreen here.
 *
 * @author Justin, Andrew, Emma, Tim, Nick
 * @version Spring 2020
 */
public class Arcade implements Runnable, ActionListener {
    private final int FRAME_WIDTH = 600;
    private final int FRAME_HEIGHT = 600;
    private JFrame frame = new JFrame("Arcade");
    private JPanel panel = new JPanel();
    private ArrayList<JButton> games = new ArrayList<>();
    private JLabel label;
    
    public void run(){
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        ImageIcon smileyIcon = new ImageIcon("assets/arcade/smileyIcon.png");
        ImageIcon froggerIcon = new ImageIcon("assets/arcade/froggerIcon.png");
        ImageIcon marsIcon = new ImageIcon("assets/arcade/marsIcon.png");
        ImageIcon racingIcon = new ImageIcon("assets/arcade/racingIcon.png");
        ImageIcon riseIcon = new ImageIcon("assets/arcade/riseIcon.png");
        ImageIcon chessIcon = new ImageIcon("assets/arcade/chessIcon.png");


        games.add(new JButton(smileyIcon));
        games.add(new JButton(racingIcon));
        games.add(new JButton(chessIcon));
        games.add(new JButton(riseIcon));
        games.add(new JButton(froggerIcon));
        games.add(new JButton(marsIcon));


        JPanel mainPanel = new JPanel(new GridLayout(3, 1));
        JPanel row1 = new JPanel(new GridLayout(1, 3));
        JPanel row2 = new JPanel(new GridLayout(3, 1));
        JPanel row3 = new JPanel(new GridLayout(1, 3));

        mainPanel.add(row1);
        mainPanel.add(row2);
        mainPanel.add(row3);

        for (int i = 0; i < 3; i++) {
            games.get(i).addActionListener(this);
            row1.add(games.get(i));
        }
        for (int i = 3; i < 6; i++) {
            games.get(i).addActionListener(this);
            row3.add(games.get(i));
        }


        label = new JLabel("         ARCADE");
        Font msgFont = new Font("Helvetica", Font.BOLD, 65);
        label.setFont(msgFont);
        label.setForeground(new Color(255, 255, 255));

        row2.add(new JLabel(""));
        row2.add(label);

        Color background = new Color(150, 150, 150);

        mainPanel.setBackground(background);
        row1.setBackground(background);
        row2.setBackground(background);
        row3.setBackground(background);

        frame.add(mainPanel);
        frame.pack();
        frame.setVisible(true);
    }
    
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
    
    public static void main(String args[]){
        javax.swing.SwingUtilities.invokeLater(new Arcade());
    }
}
