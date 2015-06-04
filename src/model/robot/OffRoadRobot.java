package model.robot;

import model.graph.Edge;
import model.graph.Node;

public class OffRoadRobot extends Robot {

    public OffRoadRobot(){
        super();
    }
    
    public OffRoadRobot(Node n){
        super(n);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canMove(Edge e) {
        return true;
    }

}
