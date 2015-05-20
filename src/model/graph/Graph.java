package model.graph;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Graph {

    private ArrayList<Node> nodes = new ArrayList<>();
    private ArrayList<Edge> edges = new ArrayList<>();

    public Graph() {
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public ArrayList<Edge> getEdges() {
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

    public void unvisitAllNodes() {
        for (Node node : getNodes()) {
            node.unvisit();
        }
    }

    /**
     * Retourne la liste des noeuds voisins du noeud a
     * @param a
     * @return 
     */
    public ArrayList<Node> getNeighbors(Node a) {
        ArrayList<Node> neighbors = new ArrayList<>();
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

}
