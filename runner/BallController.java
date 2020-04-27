package runner;


import gameutils.Controller;
import java.awt.event.MouseEvent;

public class BallController extends Controller {
    private Ball ball;

    public BallController(Ball ball){
        super();
        this.ball = ball;
    }

    @Override
    public void mousePressed(MouseEvent e){
        //CHANGE VELOCITY FUNCTION
    }

}
