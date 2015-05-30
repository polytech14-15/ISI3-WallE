package model.robot;

import model.graph.Edge;

public class FeetRobot extends Robot {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canMove(Edge e) {
        return !e.isFlooded();
    }
}
