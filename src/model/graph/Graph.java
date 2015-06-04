package model.graph;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Graph {

    private List<Node> nodes;
    private List<Edge> edges;

    public Graph() {
        this.nodes = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public List<Edge> getEdges() {
        return edges;
    }
    
    /**
     * @param nodes the nodes to set
     */
    public void setNodes(ArrayList<Node> nodes) {
        this.nodes = nodes;
    }

    /**
     * @param edges the edges to set
     */
    public void setEdges(ArrayList<Edge> edges) {
        this.edges = edges;
    }

    public void addNode(Node a) {
        getNodes().add(a);
    }

    public void addEdge(Edge e) {
        getEdges().add(e);
    }

    /**
     * Recupere la liste des noeuds voisins du noeud a
     * @param a - Noeud
     * @return Liste des noeuds voisins du noeud
     */
    public List<Node> getNeighbors(Node a) {
        List<Node> neighbors = new ArrayList<>();
        for (Edge edge : getEdges()) {
            if (edge.getA() == a && !neighbors.contains(edge.getB())) {
                neighbors.add(edge.getB());
            }

            if (edge.getB() == a && !neighbors.contains(edge.getA())) {
                neighbors.add(edge.getA());
            }
        }
        return neighbors;
    }

    /**
     * Crée le fichier XML du graph
     */
    public void printXML() {
        File f = new File("mapgraph.xml");
        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(f)));
            pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            pw.println("<osm>");
            for (Node node : getNodes()) {
                pw.println(" " + node.toString());
            }
            for (Edge edge : getEdges()) {
                pw.println(" " + edge.toString());
            }
            pw.println("</osm>");
            pw.close();
        } catch (IOException exception) {
            System.out.println("Erreur lors de l'écriture : " + exception.getMessage());
        }
    }
    
    /**
     * Recupere la distance entre un noeud et un point
     * @param n - Noeud
     * @param x_point - Point x
     * @param y_point - Point y
     * @return La distance entre deux points
     */
    public int getDistance(Node n, int x_point, int y_point) {
        return (int) Math.sqrt(Math.pow(n.getX() - x_point, 2) + Math.pow(n.getY()- y_point, 2));
    }
    
    /**
     * Recupere la distance entre deux noeuds
     * @param n1 - 1er noeud
     * @param n2 - 2eme noeud
     * @return La distance entre les deux noeuds
     */
    public int getDistance(Node n1, Node n2){
        // return the weight of the edge between these 2 nodes
        Edge e = getEdge(n1, n2);
        if (e != null){
            return e.getValue();
        } else {
            return 0;
        }
    }

    /**
     * Recupere le edge entre deux nodes
     * @param n1 - Premier node
     * @param n2 - Deuxieme node
     * @return Le edge s'il existe, null sinon
     */
    public Edge getEdge(Node n1, Node n2) {
        // returns the edge between the nodes n1 and n2
        for (Edge e : edges){
            if ((e.getA().equals(n1) && e.getB().equals(n2)) || (e.getA().equals(n2) && e.getB().equals(n1))) return e;
        }
        return null;
    }
    
    /**
     * Recupere une liste de edge qui part d'un noeud
     * @param n - Noeud
     * @return Liste de edge qui part du noeud
     */
    public List<Edge> getEdgesFromNode(Node n){
        List<Edge> edgesFromNode = new ArrayList<>();
        Edge e;
        // Pour tous les noeuds voisins
        for (Node nNeighbors : this.getNeighbors(n)){
            e = this.getEdge(n, nNeighbors);
            // S'il existe un edge entre le noeud de base et son noeud voisin
            if (e != null){
                edgesFromNode.add(e);
            }
        }
        return edgesFromNode;
    }
    
    public void reset(){
        this.nodes = new ArrayList<>();
        this.edges = new ArrayList<>();
    }
}
