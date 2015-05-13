package tp;

import model.graph.*;

public class TP {

    public static void main(String[] args) {
        Graph graph = new Graph();
        Node n1 = new Node(227.0, 105.0, "NORMAL");
        Node n2 = new Node(189.0, 184.0, "NORMAL");
        Node n3 = new Node(103.0, 278.0, "INCENDIE");
        Node n4 = new Node(233.0, 148.0, "NORMAL");
        graph.addNode(n1);
        graph.addNode(n2);
        graph.addNode(n3);
        graph.addNode(n4);
        graph.addEdge(new Edge(n1, n2, "ESCARPE"));
        graph.addEdge(new Edge(n2, n3, "PLAT"));
        graph.addEdge(new Edge(n1, n4, "ESCARPE"));
        graph.printXML();
    }

}