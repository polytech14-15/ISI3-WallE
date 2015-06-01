package model.algo;

import model.graph.*;
import model.robot.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * Parcous en Profondeur
 *
 * @author Quentin Degrange
 */
public class AlgoDepthFirst extends IAlgo {

    private Stack<ResearchNode> frontier;
    private List<ResearchNode> explored;

    /**
     * initialise la frontière (représentée par une Pile) et la liste des noeuds
     * explorés
     *
     * @param start Le noeud de départ
     */
    @Override
    public void initFrontier(Node start) {
        // initialisation of the frontier and the explored lists
        ResearchNode rnStart = new ResearchNode(start, null, 0);
        this.explored = new ArrayList<>();

        this.frontier = new Stack<>();
        frontier.push(rnStart);
    }

    /**
     *
     * @return true si la frontiere est vide, false sinon
     */
    @Override
    public boolean frontierIsEmpty() {
        return frontier.isEmpty();
    }

    /**
     * Recupère et supprime le prochain noeud de la frontière. On ajoute aussi
     * le noeud à la liste des noeuds explorés.
     *
     * @return un noeud de recherche correspondant au prochain noeud à parcourir
     */
    @Override
    public ResearchNode exploreNext() {
        ResearchNode next = frontier.pop();
        explored.add(next);
        return next;
    }

    /**
     * Créé la solution à partir du noeud objectif. On trouve le noeud de
     * recherche correspondant dans les noeuds explorés puis on remonte jusqu'au
     * noeud de départ. On obtient ainsi le chemin en sens inverse.
     *
     * @param obj Le noeud objectif
     * @return la solution : une Map avec en clé, la taille du chemin, et la
     * liste des noeuds du chemin le plus
     */
    @Override
    public Map<Integer, List<Node>> getSolution(Node obj) {
        ResearchNode n = null;
        for (ResearchNode rn : explored) {
            if (rn.getAssociated().equals(obj)) {
                // this is the ResearchNode corresponding to the objective Node
                n = rn;
            }
        }

        if (n != null) {
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

    /**
     * Ajoute le noeud n aux noeuds explorés
     *
     * @param rn Un noeud de recherche exploré
     */
    @Override
    public void addToExplored(ResearchNode rn) {
        explored.add(rn);
    }

    /**
     * Cette fonction met à jour la frontière et créé les noeuds de recherche
     * correspondants.
     *
     * @param parent Le noeud de recherche parent
     * @param g Le Graph
     * @param r Le Robot
     */
    @Override
    public void expand(ResearchNode parent, Graph g, Robot r) {
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
