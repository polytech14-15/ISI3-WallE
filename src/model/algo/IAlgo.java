package model.algo;

import java.util.List;
import java.util.Map;
import model.graph.*;
import model.robot.*;

public abstract class IAlgo {
    
public Map<Double, List<Node>> shortestPath(Node objective, Graph g, Robot r){
    initFrontier(r.getCurrentNode());
    do {
        // we go through each node untill the frontier is empty (it means we visited every node
//        if (frontierIsEmpty()) return null;
        ResearchNode leaf = exploreNext();
//        if (leaf.getAssociated().getId() == objective.getId()) return getSolution(leaf);
        addToExplored(leaf);
        expand(leaf, g, r);
    } while(!frontierIsEmpty());
    return getSolution(objective);
}

public abstract void initFrontier(Node start);
public abstract boolean frontierIsEmpty();
public abstract ResearchNode exploreNext();
public abstract Map<Double, List<Node>> getSolution(Node n);
public abstract void addToExplored(ResearchNode n);
public abstract void expand(ResearchNode n, Graph g, Robot r);

}
