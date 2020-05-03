package frogger;

import gameutils.Texture;

import java.util.ArrayList;
import java.util.Random;

public class River extends Environment {

    private ArrayList<Hazard> logs;
    private double speed;

    public River () {
        super();
        texture = new Texture("assets/frogger/water_line.png");
        logs = new ArrayList<>();
        Random rand = new Random();
        speed = rand.nextDouble() + 0.2;
        int numLogs = rand.nextInt(5) + 4;

        for (int i = 0; i < numLogs; i++) {
            logs.add(new Log(speed, i * 100));
        }
    }

    @Override
    public void update() {
        for (Hazard log: logs) {
            log.update();
        }
    }

    public ArrayList<Hazard> getHazards() {
        return logs;
    }
}
