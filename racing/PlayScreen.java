package racing;

import java.awt.Graphics;
import java.util.ArrayList;

import gameutils.Screen;

/**
 * Write a description of class PlayScreen here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PlayScreen extends Screen {
    private ArrayList<Track> tracks;
    private Car p1;
    private Car p2;

    public PlayScreen() {
        super();
        tracks = new ArrayList<Track>();
        tracks.add(new Track(0, 0));
        this.p1 = new Car("blue", 200, 100);
        this.p2 = new Car("red", 200, 150);
        this.controller = new SRController(p1, p2);
    }

    @Override
    public void render(Graphics g) {
        g.fillRect(tracks.get(0).position.x, tracks.get(0).position.y, Track.SIZE, Track.SIZE);
        g.drawImage(p1.getTexture(), p1.getPosition().x, p1.getPosition().y, null);
        g.drawImage(p2.getTexture(), p2.getPosition().x, p2.getPosition().y, null);
        //g.fillRect(p1.top.x, p1.top.y, p1.top.width, p1.top.height);
    }

    @Override
    public void update() {
        p1.collision(tracks, p2);
        p2.collision(tracks, p1);
        p1.update();
        p2.update();
    }

    @Override
    public void dispose() {

    }
}
