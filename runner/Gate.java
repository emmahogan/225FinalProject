package runner;

import gameutils.GameObject;

public class Gate extends GameObject{

    public Gate(){
        super();
        //this constructor randomly generate a gate

    }

    public void update(){


    }

    public boolean contains(Ball ball){

        return false;
    }

}
