package model.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import model.graph.Graph;
import model.graph.Node;
import model.robot.Robot;
import model.robot.RobotState;

public class Manager extends Observable implements Runnable {
    List<Robot> robots;
    List<Node> fires;
    Graph graph;
    IAlgo algo;

    public Manager(Graph graph, IAlgo algo) {
        this.robots = new ArrayList<>();
        this.fires = new ArrayList<>();
        this.graph = graph;
        this.algo = algo;
    }
    
    public List<Robot> getRobots() {
        return this.robots;
    }

    public void setRobots(List<Robot> robots) {
        this.robots = robots;
    }

    public List<Node> getFires() {
        return this.fires;
    }

    public void setFires(List<Node> fires) {
        this.fires = fires;
    }

    public Graph getGraph() {
        return this.g;
    }

    public void setGraph(Graph graph) {
        this.g = g;
    }

    public IAlgo getAlgo() {
        return this.algo;
    }

    public void setAlgo(IAlgo algo) {
        this.algo = algo;
    }
    
    public Map<Robot, List<Node>> getBestRobot(Node n){
        Map<Robot, List<Node>> bestRobot = new HashMap<>();
        Map<Integer, List<Node>> map;;
        Integer minValue = Integer.MAX_VALUE;
        Integer tempValue;
        
        for(Robot r : this.robots){
            // Si le robot est disponible
            if (r.getState.equals(RobotState.AVAILABLE)){
                 map = this.algo.shortestPath(r.getCurrentNode, n, this.graph, r);
                // Si le robot est capable de se rendre sur l'objectif
                if (map != null){
                    // Recupere la valeur du trajet
                    tempValue = (int) map.keySet().toArray()[0];
                    // Si la nouvelle valeur est plus petite que la
                    if (tempValue < minValue){
                        minValue = tempValue;
                        bestRobot.put(r, map.get(minValue));
                    }
                }
            }
        }
        return bestRobot;
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
