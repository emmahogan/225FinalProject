package racing;

import java.awt.Graphics;

import gameutils.Screen;

 /**
 * This class represents the screen used for playing the racing game
 *
 * @author Justin, Andrew, Emma, Tim, Nick
 * @version Spring 2020
 */
public class PlayScreen extends Screen {

    //The array of walls and car game objects needed for the racing game
    private Wall[][] walls;
    private Car p1;
    private Car p2;

     /**
      * The constructor for the play screen calls a method to build the track, and initializes the
      * cars for each of the two players as well as the controller
      */
    public PlayScreen() {
        super();
        buildTrack();
        this.p1 = new Car("blue", 100, 60);
        this.p2 = new Car("red", 100, 80);
        this.controller = new SRController(p1, p2);
    }

     /**
      * The render method for this class draws all of the walls for the track, as well as each of the
      * cars with the correct texture at its current position
      *
      * @param g the Graphics from paintComponent
      */
    @Override
    public void render(Graphics g) {

        //draws all of the walls in the array as filled rectangles
        for (int i = 0; i < walls.length; i++) {
            for (int j = 0; j < walls.length; j++) {
                if (walls[i][j] != null)
                    g.fillRect(walls[i][j].position.x, walls[i][j].position.y, Wall.SIZE, Wall.SIZE);
            }
        }

        //draws the cars for each player
        g.drawImage(p1.getTexture(), p1.getPosition().x, p1.getPosition().y, null);
        g.drawImage(p2.getTexture(), p2.getPosition().x, p2.getPosition().y, null);
    }

     /**
      * The update method for this class calls each of the cars' update methods and also checks for
      * collision between cars and walls
      */
    @Override
    public void update() {
        p1.update();
        p2.update();
        p1.checkCollision(walls, p2);
        p2.checkCollision(walls, p1);
    }

    @Override
    public void dispose() {

    }

     /**
      * This method creates the track for the game, represented by a 12 x 12 array of walls and an
      * accompanying array with their boolean values
      */
    private void buildTrack() {
        //arrays of walls and booleans
        walls = new Wall[12][12];
        boolean[][] track = new boolean[12][12];

        //creates track by setting the given indexes to true, representing these being
        //spaces that do not get a wall and are part of the track that the cars race around
        track[1][1] = true;
        track[2][1] = true;
        track[3][1] = true;
        track[4][1] = true;
        track[6][1] = true;
        track[7][1] = true;
        track[8][1] = true;
        track[9][1] = true;
        track[10][1] = true;

        track[1][2] = true;
        track[3][2] = true;
        track[4][2] = true;
        track[6][2] = true;
        track[7][2] = true;
        track[9][2] = true;
        track[10][2] = true;

        track[1][3] = true;
        track[2][3] = true;
        track[4][3] = true;
        track[7][3] = true;
        track[10][3] = true;

        track[1][4] = true;
        track[2][4] = true;
        track[4][4] = true;
        track[5][4] = true;
        track[6][4] = true;
        track[7][4] = true;
        track[10][4] = true;

        track[2][5] = true;
        track[3][5] = true;
        track[6][5] = true;
        track[7][5] = true;
        track[9][5] = true;
        track[10][5] = true;

        track[2][6] = true;
        track[3][6] = true;
        track[8][6] = true;
        track[9][6] = true;

        track[1][7] = true;
        track[2][7] = true;
        track[3][7] = true;
        track[5][7] = true;
        track[6][7] = true;
        track[9][7] = true;
        track[10][7] = true;

        track[1][8] = true;
        track[2][8] = true;
        track[4][8] = true;
        track[5][8] = true;
        track[6][8] = true;
        track[7][8] = true;
        track[10][8] = true;

        track[1][9] = true;
        track[3][9] = true;
        track[4][9] = true;
        track[7][9] = true;
        track[8][9] = true;
        track[9][9] = true;
        track[10][9] = true;

        track[1][10] = true;
        track[2][10] = true;
        track[3][10] = true;
        track[4][10] = true;
        track[8][10] = true;
        track[9][10] = true;
        track[10][10] = true;

        //Creates the walls at all of the indexes that were not set to be part of the track
        for (int i = 0; i < walls.length; i++) {
            for (int j = 0; j < walls.length; j++) {
                if (track[i][j] == false)
                    walls[i][j] = new Wall(i, j);
            }
        }
    }
}
