package model.algo;

import java.util.List;
import java.util.Map;
import model.graph.*;
import model.robot.*;

/**
 * Interface implémentant la recherche du plus court chemin de façon générique
 *
 * @author Quentin Degrange
 */
public abstract class IAlgo {
    
    /**
     * Cette fonction permet de calculer de façon générique le chemin le plus
     * court à partir de la position actuelle du Robot jusqu'au noeud objective
     *
     * @param objective Le noeud objectif
     * @param g Le Graph à parcourir
     * @param r Le Robot
     * @return une Map avec en clé, la taille du chemin, et la liste des noeuds
     * du chemin le plus
     */
    public Map<Integer, List<Node>> shortestPath(Node objective, Graph g, Robot r) {
        initFrontier(r.getCurrentNode());
        do {
            // we go through each node untill the frontier is empty (it means we visited every node)
            ResearchNode leaf = exploreNext();
            addToExplored(leaf);
            expand(leaf, g, r);
        } while (!frontierIsEmpty());
        return getSolution(objective);
    }

    /**
     * initialise la frontière et la liste des noeuds explorés
     *
     * @param start Le noeud de départ
     */
    public abstract void initFrontier(Node start);

    /**
     *
     * @return true si la frontiere est vide, false sinon
     */
    public abstract boolean frontierIsEmpty();

    /**
     * Recupère et supprime le prochain noeud de la frontière
     *
     * @return un noeud de recherche correspondant au prochain noeud à parcourir
     */
    public abstract ResearchNode exploreNext();

    /**
     * Créé la solution à partir du noeud objectif. On trouve le noeud de
     * recherche correspondant dans les noeuds explorés puis on remonte jusqu'au
     * noeud de départ. On obtient ainsi le chemin en sens inverse.
     *
     * @param n Le noeud objectif
     * @return la solution : une Map avec en clé, la taille du chemin, et la
     * liste des noeuds du chemin le plus
     */
    public abstract Map<Integer, List<Node>> getSolution(Node n);

    /**
     * Ajoute le noeud n aux noeuds explorés
     *
     * @param n Un noeud de recherche exploré
     */
    public abstract void addToExplored(ResearchNode n);

    /**
     * Cette fonction met à jour la frontière et créé les noeuds de recherche
     * correspondants
     *
     * @param n Le noeud de recherche parent
     * @param g Le Graph
     * @param r Le Robot
     */
    public abstract void expand(ResearchNode n, Graph g, Robot r);

}
