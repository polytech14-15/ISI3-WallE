package model.robot;

import model.graph.Edge;

public class FeetRobot extends Robot {

    @Override
    public boolean canMove(Edge e) {
        if (e.isFlooded()) {
            return false;
        }
        return true;
    }

}
