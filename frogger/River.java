package frogger;

import gameutils.GameObject;
import gameutils.Texture;

import java.util.ArrayList;
import java.util.Random;

public class River extends GameObject {

    private ArrayList<Log> logs;
    private double speed;

    public River () {
        super();
        texture = new Texture("assets/frogger/water_line.png");
        logs = new ArrayList<>();
        Random rand = new Random();
        speed = rand.nextDouble() + 0.2;
        int numLogs = rand.nextInt(2) + 3;

        for (int i = 0; i < numLogs; i++) {
            logs.add(new Log(speed));
        }
    }

    public ArrayList<Log> getLogs() {
        return logs;
    }

    @Override
    public void update() {
        for (Log log: logs) {
            log.update();
        }
    }
}
