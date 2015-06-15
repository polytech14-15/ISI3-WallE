package model.robot;

import model.graph.Edge;
import model.graph.Node;

/**
 * Classe représantant le model d'un FeetRobot.
 * @author Duret Sébastien
 */
public class FeetRobot extends Robot {

    public FeetRobot(){
        super();
    }
    
    public FeetRobot(Node n){
        super(n);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canMove(Edge e) {
        return !e.isFlooded();
    }
}
