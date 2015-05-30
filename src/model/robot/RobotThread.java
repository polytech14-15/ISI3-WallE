package model.robot;

import model.graph.Graph;

public class RobotThread implements Runnable {
    
    private Robot robot;
    private Graph graph;
    
    public RobotThread(Robot robot, Graph graph){
        this.robot = robot;
        this.graph = graph;
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
