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
        if (isKeyPressed(A)) {
            System.out.println("A");
            smiley.position.x -= 2;
        }

        if (isKeyPressed(D)) {
            System.out.println("D");
            smiley.position.x += 2;
        }
    }
}
