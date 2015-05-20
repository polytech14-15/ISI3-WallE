package model.robot;

import model.graph.Edge;

public class CaterpillarRobot extends Robot {

    @Override
    public boolean canMove(Edge e) {
        if (e.getType().equals("ESCARPE")) {
            return false;
        }
        return true;
    }

}
