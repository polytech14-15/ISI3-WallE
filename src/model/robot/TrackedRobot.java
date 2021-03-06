package model.robot;

import model.graph.Edge;
import model.graph.Node;
import model.graph.TypeEdge;

/**
 * Classe représantant le model d'un TrackedRobot.
 * @author Duret Sébastien
 */
public class TrackedRobot extends Robot {

    public TrackedRobot(){
        super();
    }
    
    public TrackedRobot(Node n){
        super(n);
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
