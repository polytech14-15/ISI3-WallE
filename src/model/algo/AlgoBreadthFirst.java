package model.algo;

import model.graph.*;
import model.robot.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlgoBreadthFirst extends IAlgo {

    private List<ResearchNode> frontier;
    private List<ResearchNode> explored;

    @Override
    public void initFrontier(Node start) {
        // initialisation of the frontier and the explored lists
        ResearchNode rnStart = new ResearchNode(start, null, 0);
        this.explored = new ArrayList<>();

        this.frontier = new ArrayList<>();
        frontier.add(rnStart);
    }

    @Override
    public boolean frontierIsEmpty() {
        return frontier.isEmpty();
    }

    @Override
    public ResearchNode exploreNext() {
        // get and remove the next Node from the frontier
        ResearchNode next = frontier.get(0);
        explored.add(next);
        frontier.remove(0);
        return next;
    }

    @Override
    public Map<Integer, List<Node>> getSolution(Node obj) {
        // Build the solution by taking the objective node and going backwards to get to the starting node 
        ResearchNode n = null;
        for (ResearchNode rn : explored) {
            if (rn.getAssociated().equals(obj)) {
                // this is the ResearchNode corresponding to the objective Node
                n = rn;
            }
        }
        
        if (n != null){
            HashMap<Integer, List<Node>> res = new HashMap<>();
            int val = n.getValue();
            ArrayList<Node> sol = new ArrayList<>();
            sol.add(n.getAssociated());
            while ((n = n.getParent()) != null) {
                // we take the parent of the objective node and its parent before etc... untill we get to the start
                sol.add(n.getAssociated());
            }

            // Inverse la liste sol
            Collections.reverse(sol);
            
            res.put(val, sol);
            return res;
        } else {
            return null;
        }
    }

    @Override
    public void addToExplored(ResearchNode rn) {
        explored.add(rn);
    }

    @Override
    public void expand(ResearchNode parent, Graph g, Robot r) {
        // this function updates the frontier  and creates the ResearchNodes
        List<Node> neighbours = g.getNeighbors(parent.getAssociated());
        for (Node n : neighbours) {
            // for each neighbour we try to get the corresponding researchNode
            ResearchNode[] t = new ResearchNode[frontier.size()];
            frontier.toArray(t);
            ResearchNode rn = ResearchNode.search(n, t);
            if (rn == null) {
                // If it's not in the frontier, we look in the explored list
                t = new ResearchNode[explored.size()];
                explored.toArray(t);
                rn = ResearchNode.search(n, t);
                if (rn == null) {
                    // if we're here, it means the corresponding ResearchNode doesn't exist yet.
                    // So we create it
                    rn = new ResearchNode(n, parent, parent.getValue() + g.getDistance(n, parent.getAssociated()));
                } else {
                    // else, it exists, we update its value to get the minimum
                    int previousVal = rn.getValue();
                    int currentVal = parent.getValue() + g.getDistance(n, parent.getAssociated());
                    if (previousVal > currentVal) {
                        rn.setParent(parent);
                        rn.setValue(currentVal);
                    }
                }
            } else {
                // the corresponding ResearchNode exists, we update its value to get the minimum
                int previousVal = rn.getValue();
                int currentVal = parent.getValue() + g.getDistance(n, parent.getAssociated());
                if (previousVal > currentVal) {
                    rn.setParent(parent);
                    rn.setValue(currentVal);
                }
            }
            
            // And we update the frontier with this node
            if (!frontier.contains(rn) && !explored.contains(rn) && r.canMove(g.getEdge(parent.getAssociated(), n))) {
                frontier.add(rn);
            }
        }
    }

}