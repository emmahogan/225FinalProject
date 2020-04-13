import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;

import examplegame.ExampleGame;
import racing.SpeedRacers;
import chess.Chess;

/**
 * Write a description of class MainScreen here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Arcade implements Runnable, ActionListener {
    private final int FRAME_WIDTH = 600;
    private final int FRAME_HEIGHT = 600;
    private JFrame frame = new JFrame("Arcade");
    private JPanel panel = new JPanel();;
    private ArrayList<JButton> games = new ArrayList<JButton>();
    
    public void run(){
        frame.setDefaultLookAndFeelDecorated(true);
        frame.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        games.add(new JButton("Example Game"));
        games.add(new JButton("Speed Racers"));
        games.add(new JButton("Chess"));
        
        for (JButton jb : games) {
            jb.addActionListener(this);
            panel.add(jb);
        }
        
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(games.get(0))) {
            System.out.println("Starting Example Game");
            javax.swing.SwingUtilities.invokeLater(new ExampleGame("Example Game"));
        } else if (e.getSource().equals(games.get(1))) {
            System.out.println("Starting Speed Racers");
            javax.swing.SwingUtilities.invokeLater(new SpeedRacers("Speed Racers"));
        } else if (e.getSource().equals(games.get(2))){
            System.out.println("Starting Chess");
            javax.swing.SwingUtilities.invokeLater(new Chess("Chess"));
        }
    }
    
    public static void main(String args[]){
        javax.swing.SwingUtilities.invokeLater(new Arcade());
    }
}
