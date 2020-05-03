package runner;


import gameutils.Controller;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.security.Key;

public class BallController extends Controller {
    private Ball ball;
    public static final int SPACE = KeyEvent.VK_SPACE;
    private int x = 1;
    public BallController(Ball ball){
        super();
        this.ball = ball;
    }


    @Override
    public void handleKeyInput() {
        }

    @Override
    public void keyTyped(KeyEvent e){
        System.out.println("KeyINPuttededdddd");
        if(isKeyPressed(SPACE)){
            System.out.println("Changing Acceleration");
            ball.setAcceleration(ball.getAcceleration() * -1);
        }
    }

    @Override
    public void mousePressed(MouseEvent e){
        //CHANGE VELOCITY FUNCTION
        //System.out.println("Hello World!!");
    }

}
