package examplegame;

import gameutils.Controller;

public class ExampleController extends Controller {
    private Smiley smiley;

    public ExampleController(Smiley smiley) {
        super();
        this.smiley = smiley;
    }

    @Override
    public void handleKeyInput() {
        if (isKeyPressed(W))
            smiley.position.y -= 2;
        if (isKeyPressed(A))
            smiley.position.x -= 2;
        if (isKeyPressed(S))
            smiley.position.y += 2;
        if (isKeyPressed(D))
            smiley.position.x += 2;
    }
}
