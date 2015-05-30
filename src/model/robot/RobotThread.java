package model.robot;

import java.util.logging.Level;
import java.util.logging.Logger;
import model.graph.Graph;
import model.graph.Node;
import model.manager.Manager;

public class RobotThread implements Runnable {
    
    private Robot robot;
    private Graph graph;
    
    public RobotThread(Robot robot, Graph graph){
        this.robot = robot;
        this.graph = graph;
    }

    @Override
    public void run() {
        
        boolean reachDestination = this.checkReachDestination();
        boolean canStillMove = true;
        int i, travelTime;
        Node nNext;
        while (!reachDestination && canStillMove){
            for (i=1; i<this.graph.getNodes().size() && canStillMove; i++){
                nNext = this.graph.getNodes().get(i);
                travelTime = this.getTravelTime(nNext);
                while (travelTime != 0 && canStillMove){
                    try {
                        Thread.sleep(Manager.TIME_STEP_SIMU);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(RobotThread.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    travelTime--;
                    if (!this.robot.canMove(this.graph.getEdge(this.robot.getCurrentNode(), nNext))){
                        canStillMove = false;
                        this.robot.setState(RobotState.AVAILABLE.toString());
                    }
                }
                if (canStillMove){
                    this.robot.setCurrentNode(nNext);
                }
            }
            reachDestination = this.checkReachDestination();
        }
        if (reachDestination){
            this.robot.setState(RobotState.BUSY.toString());
        }
    }
    
    private int getTravelTime(Node n){
        return (this.graph.getDistance(this.robot.getCurrentNode(), n) / Robot.SPEED) + 1;
    }
    
    private boolean checkReachDestination(){
        return this.robot.getCurrentNode().equals(this.graph.getNodes().get(this.graph.getNodes().size()-1));
    }
    
}
