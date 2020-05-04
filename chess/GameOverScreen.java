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

    //if text is flashing
    boolean flashing = true;

    //Time waiting between flashes of text
    private int flashWait = 1000;
    private int curTime = 0;

    //Text font
    Font msgFont = new Font("Helvetica", Font.BOLD, 50);
    FontMetrics fontMetrics = getFontMetrics(msgFont);

    /**
     * Constructs a new GameOverScreen with the appropriate winner,
     * and sets the Colors appropriately
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

        //Play again button
        this.setLayout(new BorderLayout());
        JPanel bottomPanel = new JPanel();
        this.add(bottomPanel, BorderLayout.SOUTH);
        bottomPanel.add(newGame);

        this.controller = new ChessController();
    }

    /**
     * Method to repaint, calculating whether flashing then after done flashing just
     * draws string
     *
     * @param g the Graphics from paintComponent
     */
    @Override
    public void render(Graphics g) {
        //Draw text, calculating whether to draw currently if still flashing
        if(flashing){
            //calculates whether to flash on or off
            curTime += 16;
            if(curTime > flashWait){
                flashWait += 1000;
                //only flashes a few times, then stays solid
                if(flashWait > 6000){
                    flashing = false;
                }
            }
            if(flashWait % 1000 != 1){
                drawText(g);
            }
        //if not flashing, draw text
        } else {
            drawText(g);
        }

    }

    /**
     * Draws string with game over text saying the winning team
     *
     * @param g Graphics object
     */
    public void drawText(Graphics g){
        g.setColor(textColor);
        g.setFont(msgFont);
        g.drawString(winner, (ChessBoard.FRAME_WIDTH - fontMetrics.stringWidth(winner)) / 2, ChessBoard.FRAME_WIDTH /3);
    }

    @Override
    public void update() {}

    @Override
    public void dispose() {}

    @Override
    public void actionPerformed(ActionEvent e) {
        Chess.playAgain();
    }
}
