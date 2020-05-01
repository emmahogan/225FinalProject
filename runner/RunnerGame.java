package runner;

import gameutils.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RunnerGame extends Game {

    private RunnerScreen runScreen;

    public RunnerGame (String name){
        super(name);
        runScreen = new RunnerScreen();
        changeScreen(runScreen);

    }


}
