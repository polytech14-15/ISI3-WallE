package model.robot;

import model.graph.Edge;

public class OffRoadRobot extends Robot {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canMove(Edge e) {
        return true;
    }

}
