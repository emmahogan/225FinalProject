package chess;

import gameutils.Game;

/**
 * Runs a simulation of a chess game by calling the creation of a new ChessBoard
 *
 * @author Justin, Andrew, Emma, Tim, Nick
 * @version Spring 2020
 */
public class Chess extends Game
{
    //The chess board
    private ChessBoard board;

    /**
     * Constructor calls for the creation of a new ChessBoard, and switches to that screen
     */
    public Chess(String name)
    {
        super(name);
        this.board = new ChessBoard();
        changeScreen(board);
    }

    /**
     * Called from game over screen if button is selected to play again, starts
     * a new game by creating a new board
     */
    public void playAgain(){
        this.board = new ChessBoard();
        changeScreen(board);
    }

    /**
     * Switches to game over screen
     *
     * @param winner The winning team
     */
    public void gameOver(Side winner){
        changeScreen(new GameOverScreen(winner));
    }


    /**
     * Calls the run method of it's super class, Game
     */
    @Override
    public void run() {
        super.run();
    }
}
