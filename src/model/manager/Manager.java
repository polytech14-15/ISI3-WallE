package model.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.algo.AlgoDepthFirst;
import model.algo.IAlgo;
import model.graph.Edge;
import model.graph.Graph;
import model.graph.Node;
import model.graph.TypeEdge;
import model.graph.TypeNode;
import model.robot.FeetRobot;
import model.robot.Robot;
import model.robot.RobotState;

public class Manager extends Observable implements Runnable {
    
    public static final int TIME_STEP_SIMU = 1000; // 1sec
    private static int WAIT_FOR_ASK = Manager.TIME_STEP_SIMU * 3; 
    
    private List<Robot> robots;
    private Map<Node, Robot> mapFires;
    private Graph graph;
    private IAlgo algo;
    private boolean canAskRobots;
    
    public Manager() {
        this.robots = new ArrayList<>();
        this.mapFires = new HashMap<>();
        this.graph = new Graph();
        this.canAskRobots = true;
        this.test();
    }
    
    public Manager (Graph graph, List<Robot> robots, IAlgo algo){
        this.robots = robots;
        this.mapFires = new HashMap<>();
        this.graph = graph;
        this.algo = algo;
        this.canAskRobots = true;
    }
    
    public List<Robot> getRobots() {
        return this.robots;
    }

    public void setRobots(List<Robot> robots) {
        this.robots = robots;
    }

    public Map<Node, Robot> getMapFires() {
        return mapFires;
    }

    public void setMapFires(Map<Node, Robot> mapFires) {
        this.mapFires = mapFires;
    }

    public Graph getGraph() {
        return this.graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    public IAlgo getAlgo() {
        return this.algo;
    }

    public void setAlgo(IAlgo algo) {
        this.algo = algo;
    }
    
    /**
     * Permet d'ajouter un robot
     * @param r - Robot a ajouter
     */
    public void addRobot(Robot r){
        this.robots.add(r);
    }
    
    /**
     * Initialise la mapFires
     */
    private void initMapFires(){
        for (Node n : this.graph.getNodes()){
            if (n.getType().equals(TypeNode.INCENDIE)){
                this.mapFires.put(n, null);
            }
        }
    }
    
    /**
     * Met a jour la mapFires
     */
    private void updateMapFires(){
        // Pour chaque entree de la mapFires
        for (Map.Entry<Node, Robot> entry : this.mapFires.entrySet()) {
            // Si le robot qui s'occupait du feu est dispo, cela siginifie que le feu est soit eteint 
            // ou soit que le robot ne peut plus atteindre sa destination
           
            if (entry.getValue() != null && entry.getValue().getState().equals(RobotState.AVAILABLE)){
                this.mapFires.put(entry.getKey(), null);
            }
            // Si le noeud n'est plus un feu
            if (entry.getKey().getType().equals(TypeNode.NORMAL)){
                this.mapFires.remove(entry.getKey());
                
                
                //TODO
                // faire inondation aleatoire
            }
	}
        
        // Pour chaque noeud
        for (Node n : this.graph.getNodes()){
            // Si le noeud n'est pas contenu dans la mapFires
            if (!this.mapFires.containsKey(n)){
                this.mapFires.put(n, null);
            }
        }
    }
    
    /**
     * Chaque robot occupe eteint son feu
     */
    private void extinguishFires(){
        for (Robot r : this.getRobotsBusy()){
            r.extinguishFire();
        }
    }
    
    /**
     * Recupere le meilleur robot
     * @param n - Noeud a atteindre
     * @return - Le meilleur robot et la liste de noeuds qu'il doit parcourir
     */
    public Map<Robot, List<Node>> getBestRobot(Node n){
        Map<Robot, List<Node>> bestRobot = new HashMap<>();
        Map<Integer, List<Node>> map;
        Integer minValue = Integer.MAX_VALUE;
        Integer tempValue;
        
        // Pour chaque robot disponible
        for(Robot r : this.getRobotsAvailable()){
            map = this.algo.shortestPath(n, this.graph, r);
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
        return bestRobot;
    }

    /**
     * Recupere la liste de robots disponibles
     * @return Liste de robots disponibles
     */
    private List<Robot> getRobotsAvailable(){
        return this.getRobotsAccordingToState(RobotState.AVAILABLE);
    }
    
    /**
     * Recupere la liste de robots occupes
     * @return Liste de robots disponibles
     */
    private List<Robot> getRobotsBusy(){
        return this.getRobotsAccordingToState(RobotState.BUSY);
    }
    
    /**
     * Recupere une liste de robots
     * @param state - Etat du robot
     * @return Liste de robots
     */
    private List<Robot> getRobotsAccordingToState(RobotState state){
        List<Robot> lRobots = new ArrayList<>();
        for (Robot r : this.robots){
            if (r.getState().equals(state)){
                lRobots.add(r);
            }
        }
        return lRobots;
    }
    
    /**
     * Creer un sous-graphe a partir d'une liste de noeuds
     * @param lNodes - Liste de noeuds
     * @return Un graphe (sous-graphe du principal)
     */
    private Graph prepareRouteForRobot (List<Node> lNodes){
        Graph route = new Graph();
        
        // Ajoute les noeuds au graphe
        for(Node n : lNodes){
            route.addNode(n);
        }
        
        // Ajoute les arcs au graphe
        Edge e;
        for (int i=0; i<lNodes.size()-1; i++){
            e  = graph.getEdge(lNodes.get(i), lNodes.get(i+1));
            if (e != null){
                route.addEdge(e);
            }
        }
        return route;
    }
    
    @Override
    public void run() {
        Map<Robot, List<Node>> bestRobot;
        Map.Entry<Robot, List<Node>> entry;
        
        // Initialise la mapFires
        this.initMapFires();
        
        while(true){
            
            // Si le manager peut requeter les robots
            if (this.canAskRobots){
                this.changeCanAskRobots();
                
                // Pour chaque feu
                for (Node n : this.mapFires.keySet()){
                    // Si aucun robot ne s'occupe de ce feu
                    if (this.mapFires.get(n) == null){
                         // Recupere le meilleur robot pour eteindre ce feu
                        bestRobot = this.getBestRobot(n);
                        // Si un robot est capable d'accepter sa mission
                        if (!bestRobot.isEmpty()){
                            // Recupere le 1er element de la map
                            entry = bestRobot.entrySet().iterator().next();
                            // Appelle fonction move du robot avec le graph en parametre
                            entry.getKey().move(this.prepareRouteForRobot(entry.getValue()));
                            // Indique quel robot s'occupe du feu en question
                            this.mapFires.put(n, entry.getKey());
                        }
                    }
                }
                this.waitAskRobots();
            }
            
            this.extinguishFires();
            System.out.println("updatempfire--");
            this.updateMapFires();
            
            setChanged();
            notifyObservers();
            
            try {
                Thread.sleep(Manager.TIME_STEP_SIMU);
            } catch (InterruptedException ex) {
                Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * Change la valeur du boolean
     */
    private void changeCanAskRobots(){
        this.canAskRobots = !this.canAskRobots;
    }
    
    /**
     * Thread interne qui permet d'attendre avant de refaire une demande aux robots
     */
    private void waitAskRobots() {
        // Etablis le Thread 
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(Manager.WAIT_FOR_ASK);
                } catch (InterruptedException e) {
                }
                changeCanAskRobots();
            }
        }).start();
    }
    
    public void test(){
       Node n1 = new Node(227.0, 105.0, TypeNode.NORMAL);
        Node n2 = new Node(189.0, 184.0, TypeNode.NORMAL);
        Node n3 = new Node(103.0, 278.0, TypeNode.INCENDIE);
        Node n4 = new Node(233.0, 148.0, TypeNode.INCENDIE);
        graph.addNode(n1);
        graph.addNode(n2);
        graph.addNode(n3);
        graph.addNode(n4);
        graph.addEdge(new Edge(n1, n2, TypeEdge.ESCARPE));
        graph.addEdge(new Edge(n2, n3, TypeEdge.PLAT));
        graph.addEdge(new Edge(n1, n4, TypeEdge.ESCARPE));
        graph.addEdge(new Edge(n4, n3, TypeEdge.ESCARPE));
        
        Robot r = new FeetRobot();
        r.setCurrentNode(n1);
        this.robots.add(r);
        
        this.algo = new AlgoDepthFirst();
        
        new Thread(this).start();
    }
}
