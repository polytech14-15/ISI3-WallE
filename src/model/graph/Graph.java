package graph;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Graph {

    protected ArrayList<Node> nodes = new ArrayList<>();
    protected ArrayList<Edge> edges = new ArrayList<>();

    public Graph() {
    }

    public void addNode(Node a) {
        nodes.add(a);
    }

    public void addEdge(Edge e) {
        edges.add(e);
    }

    public void unvisitAllNodes() {
        for (Node node : nodes) {
            node.unvisit();
        }
    }

    public ArrayList<Node> getNeighbors(Node a) {
        ArrayList<Node> neighbors = new ArrayList<>();
        for (Edge edge : edges) {
            if (edge.getA() == a && !neighbors.contains(edge.getB())) {
                neighbors.add(edge.getB());
            }

            if (edge.getB() == a && !neighbors.contains(edge.getA())) {
                neighbors.add(edge.getA());
            }
        }
        return neighbors;
    }

    public void printXML() {
        File f = new File("mapgraph.xml");
        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(f)));
            pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            pw.println("<osm>");
            for (Node node : nodes) {
                pw.println(" " + node.toString());
            }
            for (Edge edge : edges) {
                pw.println(" " + edge.toString());
            }
            pw.println("</osm>");
            pw.close();
        } catch (IOException exception) {
            System.out.println("Erreur lors de l'écriture : " + exception.getMessage());
        }
    }

}
