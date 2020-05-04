package chess;

import gameutils.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverScreen extends Screen implements ActionListener {

    //String of side that won
    private String winner;

    //Button to start new game
    private JButton newGame = new JButton("Play Again");

    //Background color set to winning team's color
    private Color background;

    //Text color set to opposite of winning team's color
    private Color textColor;

    //Time to wait between flashes of text in ms
    private int flashWait;

    /**
     * Constructs a new GameOverScreen with the appropriate winner
     *
     * @param winner The team that won
     */
    public GameOverScreen(Side winner){
        //Set appropriate colors for winner and set winner String to the winning side
        if (winner.equals(Side.WHITE)){
            background = Color.WHITE;
            textColor = Color.BLACK;
            this.winner = "White";
        } else {
            background = Color.BLACK;
            textColor = Color.WHITE;
            this.winner = "Black";
        }

        this.winner += "Wins!!!";
    }

    /**
     *
     * @param g the Graphics from paintComponent
     */
    @Override
    public void render(Graphics g) {
        //
    }

    @Override
    public void update() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
