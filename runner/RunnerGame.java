package runner;

import gameutils.Game;

public class RunnerGame extends Game {

    private RunnerScreen runScreen;

    public RunnerGame (String name){
        super(name);
        runScreen = new RunnerScreen();
        changeScreen(runScreen);

    }
}
