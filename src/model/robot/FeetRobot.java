package model.robot;

import model.graph.Edge;

public class FeetRobot extends Robot {

    public FeetRobot(){
        super();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canMove(Edge e) {
        return !e.isFlooded();
    }
}
