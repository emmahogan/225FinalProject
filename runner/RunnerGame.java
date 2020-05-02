package runner;

import gameutils.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RunnerGame extends Game {
    public static int waitTIme =
    private RunnerScreen runScreen;
    //private JFrame fram;

    public RunnerGame (String name){
        super(name);
        runScreen = new RunnerScreen();
        changeScreen(runScreen);

    }

    @Override
    public void run() {
        super.run();
    }
}
