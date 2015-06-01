package model.robot;

import model.graph.Edge;
import model.graph.TypeEdge;

public class TrackedRobot extends Robot {

    public TrackedRobot(){
        super();
    }
    
    /**
     * {@inheritDoc} 
     */
    @Override
    public boolean canMove(Edge e) {
        if (e.getType().equals(TypeEdge.ESCARPE)) {
            return false;
        }
        return true;
    }
}