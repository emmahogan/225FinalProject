package runner;


import gameutils.Controller;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class BallController extends Controller {
    private Ball ball;
    public static final int SPACE = KeyEvent.VK_SPACE;

    public BallController(Ball ball){
        super();
        this.ball = ball;
    }


    @Override
    public void handleKeyInput() {
        //super.handleKeyInput();
        if (isKeyPressed(SPACE)) {
            if(ball.getAcceleration() == 0) {
                ball.setAcceleration(-1);

            }
            else{
                ball.setAcceleration(ball.getAcceleration() * -1);
            }
            }
        }


    @Override
    public void mousePressed(MouseEvent e){
        //CHANGE VELOCITY FUNCTION
    }

}
