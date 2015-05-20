package model.algo;

import model.graph.*;
import model.robot.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class AlgoDepthFirst extends IAlgo {

    private Stack<ResearchNode> frontier;
    private ArrayList<ResearchNode> explored;

    @Override
    public void initFrontier(Node start) {
        // initialisation of the frontier and the explored lists
        ResearchNode rnStart = new ResearchNode(start, null, 0);
        this.explored = new ArrayList<>();

        this.frontier = new Stack<>();
        frontier.push(rnStart);
    }

    @Override
    public boolean frontierIsEmpty() {
        return frontier.isEmpty();
    }

    @Override
    public ResearchNode exploreNext() {
        // get and remove the next Node from the frontier
        ResearchNode next = frontier.pop();
        explored.add(next);
        return next;
    }

    @Override
    public Map<Double, List<Node>> getSolution(Node obj) {
        // Build the solution by taking the objective node and going backwards to get to the starting node 
        ResearchNode n = null;
        for (ResearchNode rn : explored) {
            if (rn.getAssociated().equals(obj)) {
                // this is the ResearchNode corresponding to the objective Node
                n = rn;
            }
        }

        HashMap<Double, List<Node>> res = new HashMap<>();
        double val = n.getValue();
        ArrayList<Node> sol = new ArrayList<>();
        sol.add(n.getAssociated());
        while ((n = n.getParent()) != null) {
            // we take the parent of the objective node and its parent before etc... untill we get to the start
            sol.add(n.getAssociated());
        }
        res.put(val, sol);
        return res;
    }

    @Override
    public void addToExplored(ResearchNode rn) {
        explored.add(rn);
    }

    @Override
    public void expand(ResearchNode parent, Graph g, Robot r) {
        // this function updates the frontier  and creates the ResearchNodes
        ArrayList<Node> neighbours = g.getNeighbors(parent.getAssociated());
        for (Node n : neighbours) {
            // for each neighbour we try to get the corresponding researchNode
//            System.out.println("neighbour node : " + n.getId());
            ResearchNode[] t = new ResearchNode[frontier.size()];
            frontier.toArray(t);
            ResearchNode rn = ResearchNode.search(n, t);
            if (rn == null) {
                // If it's not in the frontier, we look in the explored list
//                System.out.println("not found node : " + n.getId());
                t = new ResearchNode[explored.size()];
                explored.toArray(t);
                rn = ResearchNode.search(n, t);
                if (rn == null) {
                    // if we're here, it means the corresponding ResearchNode doesn't exist yet.
                    // So we create it
                    rn = new ResearchNode(n, parent, parent.getValue() + g.distance(n, parent.getAssociated()));
                } else {
                    // else, it exists, we update its value to get the minimum
                    double previousVal = rn.getValue();
                    double currentVal = parent.getValue() + g.distance(n, parent.getAssociated());
                    if (previousVal > currentVal) {
                        rn.setParent(parent);
                        rn.setValue(currentVal);
                    }
                }
            } else {
                // the corresponding ResearchNode exists, we update its value to get the minimum
                double previousVal = rn.getValue();
                double currentVal = parent.getValue() + g.distance(n, parent.getAssociated());
                if (previousVal > currentVal) {
                    rn.setParent(parent);
                    rn.setValue(currentVal);
                }
            }
            
            // And we update the frontier with this node
            if (!frontier.contains(rn) && !explored.contains(rn) /*&& r.canMove(g.getEdge(parent.getAssociated(), n))*/) {
//                System.out.println("add node : " + n.getId());
                frontier.add(rn);
            }
        }
    }

}
