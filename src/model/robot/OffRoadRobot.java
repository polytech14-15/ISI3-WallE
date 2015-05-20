package model.robot;

import model.graph.Edge;

public class OffRoadRobot extends Robot {

    @Override
    public boolean canMove(Edge e) {
        return true;
    }

}
