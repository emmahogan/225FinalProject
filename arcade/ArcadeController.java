package arcade;

import gameutils.Controller;

import static java.awt.event.KeyEvent.VK_ENTER;

public class ArcadeController extends Controller {
    private Walker walker;


    public ArcadeController(Walker walker){
        super();
        this.walker = walker;
    }


    @Override
    public void handleKeyInput() {
        if (isKeyPressed(W))
            walker.position.y -= 4;
        if (isKeyPressed(A))
            walker.position.x -= 4;
        if (isKeyPressed(S))
            walker.position.y += 4;
        if (isKeyPressed(D))
            walker.position.x += 4;
        if (isKeyPressed(VK_ENTER))
            walker.startGame();
    }
}
