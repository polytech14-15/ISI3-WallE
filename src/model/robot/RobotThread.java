package model.robot;

import java.util.logging.Level;
import java.util.logging.Logger;
import model.graph.Graph;
import model.graph.Node;
import model.manager.Manager;

/**
 * Classe représantant le thread d'un Robot.
 * @author Duret Sébastien
 */
public class RobotThread implements Runnable {
    
    private Robot robot;
    private Graph graph;
    
    public RobotThread(Robot robot, Graph graph){
        this.robot = robot;
        this.graph = graph;
    }

    /**
     * Thread qui permet le deplacement du robot
     */
    @Override
    public void run() {
        boolean reachDestination = this.checkReachDestination();
        boolean canStillMove = true;
        int i, travelTime;
        Node nNext;
        
        // Tant que le robot n'est pas arrive a destination et qu'il peut encore se deplacer
        while (!reachDestination && canStillMove){
            // Pour tous les nodes du graphe
            for (i=1; i<this.graph.getNodes().size() && canStillMove; i++){
                nNext = this.graph.getNodes().get(i);
                travelTime = this.getTravelTime(nNext);
                // Tant que son temps de voyage n'est pas nul et qu'il peut encore se deplacer
                while (travelTime != 0 && canStillMove){
                    try {
                        Thread.sleep(Manager.TIME_STEP_SIMU);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(RobotThread.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    // Decremente de 1 le temp de voyage
                    travelTime--;
                    // Si le robot ne peut plus se deplacer sur le edge entre son noeud courant et sa destination
                    if (!this.robot.canMove(this.graph.getEdge(this.robot.getCurrentNode(), nNext))){
                        canStillMove = false;
                        // On libere le robot
                        this.robot.setState(RobotState.AVAILABLE);
                    }
                }
                // Au contraire, s'il peut toujours se depacer
                if (canStillMove){
                    // Changement de son noeud courant
                    this.robot.setCurrentNode(nNext);
                }
            }
            // Verifie s'il a atteint sa destination finale
            reachDestination = this.checkReachDestination();
        }
        // Si le robot est arrive a sa destination finale
        if (reachDestination){
            // Le robot est maintenant occupe a eteindre le feu
            this.robot.setState(RobotState.BUSY);
        }
    }
    
    /*
     * Determine le nombre de step necessaire afin d'atteindre sa destination
     */
    private int getTravelTime(Node n){
        return (this.graph.getDistance(this.robot.getCurrentNode(), n) / Robot.SPEED) + 1;
    }
    
    /**
     * Verifie s'il le robot est arrive a sa destination finale
     * @return true s'il est, false sinon
     */
    private boolean checkReachDestination(){
        return this.robot.getCurrentNode().equals(this.graph.getNodes().get(this.graph.getNodes().size()-1));
    }
}
