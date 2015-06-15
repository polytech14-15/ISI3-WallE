package model.robot;

import model.graph.Edge;
import model.graph.Node;

/**
 * Classe représantant le model d'un OffRoadRobot.
 * @author Duret Sébastien
 */
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
