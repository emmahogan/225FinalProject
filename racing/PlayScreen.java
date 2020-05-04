package racing;

import java.awt.Graphics;

import gameutils.Screen;

/**
 * Write a description of class PlayScreen here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PlayScreen extends Screen {
    private Wall[][] walls;
    private Car p1;
    private Car p2;

    public PlayScreen() {
        super();
        buildTrack();
        this.p1 = new Car("blue", 100, 60);
        this.p2 = new Car("red", 100, 80);
        this.controller = new SRController(p1, p2);
    }

    @Override
    public void render(Graphics g) {
        for (int i = 0; i < walls.length; i++) {
            for (int j = 0; j < walls.length; j++) {
                if (walls[i][j] != null)
                    g.fillRect(walls[i][j].position.x, walls[i][j].position.y, Wall.SIZE, Wall.SIZE);
            }
        }
        g.drawImage(p1.getTexture(), p1.getPosition().x, p1.getPosition().y, null);
        g.drawImage(p2.getTexture(), p2.getPosition().x, p2.getPosition().y, null);
    }

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

    private void buildTrack() {
        walls = new Wall[12][12];
        boolean[][] track = new boolean[12][12];

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

        for (int i = 0; i < walls.length; i++) {
            for (int j = 0; j < walls.length; j++) {
                if (track[i][j] == false)
                    walls[i][j] = new Wall(i, j);
            }
        }
    }
}
