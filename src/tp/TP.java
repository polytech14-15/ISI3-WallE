package tp;

import controller.Controller;
import java.util.List;
import java.util.Map;
import model.algo.AlgoDepthFirst;
import model.graph.Edge;
import model.graph.Graph;
import model.graph.Node;
import model.robot.FeetRobot;
import model.robot.Robot;

public class TP {

    public static void main(String[] args) {
        Graph graph = new Graph();
//        Node n1 = new Node(227.0, 105.0, "NORMAL");
//        Node n2 = new Node(189.0, 184.0, "NORMAL");
//        Node n3 = new Node(103.0, 278.0, "INCENDIE");
//        Node n4 = new Node(233.0, 148.0, "NORMAL");
        Node n1 = new Node(0.0, 0.0, "NORMAL");
        Node n2 = new Node(10.0, 10.0, "NORMAL");
        Node n3 = new Node(20.0, 20.0, "INCENDIE");
        Node n4 = new Node(0.0, 20.0, "NORMAL");
        graph.addNode(n1);
        graph.addNode(n2);
        graph.addNode(n3);
        graph.addNode(n4);
        graph.addEdge(new Edge(n1, n2, "ESCARPE"));
        graph.addEdge(new Edge(n2, n3, "PLAT"));
        graph.addEdge(new Edge(n1, n4, "ESCARPE"));
        graph.addEdge(new Edge(n4, n3, "ESCARPE"));
        
        AlgoDepthFirst a = new AlgoDepthFirst();
        Robot r = new FeetRobot();
        r.setCurrentNode(n1);
        Map<Integer, List<Node>> sol = a.shortestPath(n3, graph, r);
        System.out.println(sol.keySet().toArray()[0]);
        for (List<Node> l : sol.values()){
            for (Node n : l){
                System.out.println(n);
            }
        }
//        graph.printXML();
        
//        Controller controller = new Controller();
    }

}
